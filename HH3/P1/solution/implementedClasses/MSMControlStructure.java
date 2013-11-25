/**
 * 
 * Nelian E. Colon Collazo
 * 802-09-1384
 * ICOM 4035 Sec. 100
 * 13 de marzo de 2011
 * 
 */

package implementedClasses;

import java.security.InvalidParameterException;
import memoryClasses.MemBlock;
import interfaces.MemControlStructure;
import interfaces.Node;
import exceptionClasses.BlockBoundaryViolationException;
import exceptionClasses.InvalidAddressException;
import exceptionClasses.SpaceNotReservedException;

/**
 * 
 * This class contains a list to be managed.
 * The list contains the blocks of memory values that are available.
 * 
 * @author Nelian E. Colon Collazo
 *
 */
public class MSMControlStructure implements MemControlStructure
{

	private DLDHDTList<MemBlock> plist;
	private int memSize;

	/**
	 * Constructor - Initializes the list and adds the three basic nodes to it.
	 *
	 * @param ms the size of the memory to be managed
	 * @throws InvalidParameterException if memory size is <= 0
	 */
	public MSMControlStructure(int ms) throws InvalidParameterException
	{
		if (ms <= 0) throw new InvalidParameterException("Mem size must be > 0");
		memSize = ms;

		plist = new DLDHDTList<MemBlock>();

		Node<MemBlock> freeBlockNode = plist.createNewNode();
		freeBlockNode.setElement(new MemBlock(0, memSize-1));
		plist.addFirstNode(freeBlockNode);

		// add dummy blocks - this simplifies algorithms to free and
		// reserved as are suggested below...

		Node<MemBlock> firstDumyFreeNode = plist.createNewNode();
		firstDumyFreeNode.setElement(new MemBlock(-1,-2));
		plist.addFirstNode(firstDumyFreeNode);

		Node<MemBlock> lastDumyFreeNode = plist.createNewNode();
		lastDumyFreeNode.setElement(new MemBlock(memSize+1,memSize));
		plist.addNodeAfter(freeBlockNode, lastDumyFreeNode);


	}

	/**
	 * Searches for an available block of contiguous
	 * bytes of the given size (number of bytes) according
	 * to the current content of the structure.
	 * If successful, the structure is modified accordingly
	 * to record the space reservation.
	 * @param size number of bytes to reserve.
	 * @throws InvalidParameterException whenever the
	 * specified size is either negative or exceeds
	 * the size of the memory being manager.
	 * @return -1 if not successful (no block of
	 * available contiguous bytes of the requested
	 * size could be found; otherwise, it returns
	 * the address of the first byte in the block
	 * that was reserved.
	 **/
	public int reserveMemSpace(int size) throws InvalidParameterException {

		if (size <=0 || size > memSize)
			throw new InvalidParameterException("Invalid size.");
		// size is valid

		DNode<MemBlock> iNode = (DNode<MemBlock>) plist.getFirstNode();

		int count = 0;
		int address = -1;

		do{

			iNode = iNode.getNext();
			count++;

		}while((iNode.getElement().getLastAddress() - iNode.getElement().getAddress() + 1) < size && count < plist.length()-1);

		if((iNode.getElement().getLastAddress() - iNode.getElement().getAddress() + 1) > size){

			address = iNode.getElement().getAddress();
			iNode.getElement().setAddress(iNode.getElement().getAddress()+size);

		}
		else if((iNode.getElement().getLastAddress() - iNode.getElement().getAddress() + 1) == size){

			address = iNode.getElement().getAddress();
			plist.removeNode(iNode);

		}

		return address;

	} // end of reserveMemSpace

	/**
	 * Records the availability of a block of bytes
	 * for future use. It basically marks as "available"
	 * the specified number of bytes beginning from
	 * the byte at the given address.
	 * @param address the address of the first byte to free
	 * @param blockSize the number of bytes to free.
	 * @throws InvalidAddressException whenever the address
	 * given is not valid.
	 * @throws BlockBoundaryViolationException whenever
	 * the suggested block is out of the boundaries of
	 * the memory being managed
	 * @throws SpaceNotReservedException whenever some of
	 * the bytes in the specified block are already free.
	 **/
	public void freeSpace(int address, int blockSize) throws
	InvalidAddressException,
	BlockBoundaryViolationException,
	SpaceNotReservedException
	{
		if (address < 0 || address >= memSize)
			throw new InvalidAddressException("Invalid address.");

		if (blockSize <= 0 || address + blockSize > memSize)
			throw new BlockBoundaryViolationException(
			"Invalid block boundaries.");

		DNode<MemBlock> myNode = (DNode<MemBlock>) plist.getFirstNode();
		DNode<MemBlock> myNode2 = myNode.getNext();
		int count = 0;

		while(((address+blockSize) > myNode2.getElement().getAddress() || address <= myNode.getElement().getLastAddress()) && count < plist.length() - 2){
			myNode = myNode2;
			myNode2 = myNode.getNext();

			count++;
		}

		if(count == plist.length()-2 && ((address+blockSize) > myNode2.getElement().getAddress() || address <= myNode.getElement().getLastAddress()))
			throw new SpaceNotReservedException("Specified block to free has bytes that are free.");

		if((address+blockSize) == myNode2.getElement().getAddress() && address == myNode.getElement().getLastAddress()+1){

			myNode.getElement().setLastAddress(myNode2.getElement().getLastAddress());
			plist.removeNode(myNode2);

		}

		else if((address+blockSize) == myNode2.getElement().getAddress()){
			if(myNode2.getElement().getAddress()==myNode2.getElement().getLastAddress()){

				DNode<MemBlock> newNode = (DNode<MemBlock>) plist.createNewNode();
				newNode.setElement(new MemBlock(address,address+blockSize-1));
				plist.addNodeBefore(myNode2, newNode);

			}
			else
				myNode2.getElement().setAddress(address);

		}

		else if(address == myNode.getElement().getLastAddress()+1){
			if(myNode.getElement().getAddress()==myNode.getElement().getLastAddress()){

				DNode<MemBlock> newNode = (DNode<MemBlock>) plist.createNewNode();
				newNode.setElement(new MemBlock(address,address+blockSize-1));
				plist.addNodeAfter(myNode,newNode);

			}
			else
				myNode.getElement().setLastAddress(address+blockSize-1);
		}

		else{
			DNode<MemBlock> newNode = (DNode<MemBlock>) plist.createNewNode();
			newNode.setElement(new MemBlock(address,address+blockSize-1));
			plist.addNodeAfter(myNode, newNode);
		}
	}

	/**
	 * Verifies if memory is full.  Returns true if it is full.
	 * 
	 * @return true if full, false if not
	 */
	public boolean memIsFull(){
		return plist.length() == 2;
	}

	/**
	 * Shows the blocks of memory that are currently free in the node format designated.
	 *
	 */
	public void showFreeBlocks() {
		System.out.println("List of free nodes is: ");
		if (plist.length()-2 != 0) {
			Node<MemBlock> currentNode = plist.getFirstNode();
			// first discard the (-1,-2) node....
			currentNode = plist.getNodeAfter(currentNode);
			while (currentNode != plist.getLastNode()) {
				System.out.print(currentNode.getElement() +
				" ");
				currentNode = plist.getNodeAfter(currentNode);
			}
			// the last data node is not printed since it is
			// a dummy data node...
			System.out.println();
		}
		else
			System.out.println("List of free blocks is empty.");
	}

} 