/**
 * Nelian E. Colon Collazo
 * 802-09-1384
 * ICOM 4035 Sec. 100
 * 13 de marzo de 2011
 * 
 */

package implementedClasses;

import interfaces.LinkedList;
import interfaces.Node;
import exceptionClasses.NodeOutOfBoundsException;


/**
 * Doubly linked list with nodes of type DNode
 * 
 * @author Nelian E. Colon Collazo
 *
 * @param <E> the type to be stored
 */
public class DLDHDTList<E> implements LinkedList<E> {
	protected DNode<E> dHeader, dTrailer; 
	private int size; 

	/**
	 * Constructor.  Creates an empty list.
	 */
	public DLDHDTList() { 
		dHeader = new DNode<E>();
		dTrailer = new DNode<E>();
		dTrailer.setPrev(dHeader);
		dHeader.setNext(dTrailer);
		size = 0;
	}

	/**
	 * Inserts the given node at the head of the list. 
	 * 
	 * @param nuevo the node to be inserted
	 */
	public void addFirstNode(Node<E> nuevo) {
		addNodeAfter(dHeader, nuevo); 
	}

	/**
	 * Inserts the given node "nuevo" after the target node.
	 * 
	 * @param nuevo the node to be inserted
	 * @param target the node after which "nuevo" is inserted
	 */
	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		DNode<E> dnuevo = (DNode<E>) nuevo; 
		DNode<E> nBefore = (DNode<E>) target; 
		DNode<E> nAfter = nBefore.getNext(); 
		nBefore.setNext(dnuevo); 
		nAfter.setPrev(dnuevo); 
		dnuevo.setPrev(nBefore); 
		dnuevo.setNext(nAfter); 
		size++; 
	}

	/**
	 * Inserts the given node "nuevo" before the target node.
	 * 
	 * @param nuevo the node to be inserted
	 * @param target the node before which "nuevo" is inserted
	 */
	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		DNode<E> dnuevo = (DNode<E>) nuevo; 
		DNode<E> nAfter = (DNode<E>) target; 
		DNode<E> nBefore = nAfter.getPrev(); 
		nBefore.setNext(dnuevo); 
		nAfter.setPrev(dnuevo); 
		dnuevo.setPrev(nBefore); 
		dnuevo.setNext(nAfter); 
		size++; 
	}

	/**
	 * Creates a new node of type DNode. 
	 * 
	 * @return reference to the new node instance. 
	 */
	public Node<E> createNewNode() {
		return new DNode<E>();
	}

	/**
	 * Returns a reference to the first node in the list
	 * 
	 * @return reference to the first node of the linked list
	 * @throws INodeOutOfBoundsException if the linked list is empty
	 */
	public Node<E> getFirstNode() throws NodeOutOfBoundsException {
		if (size == 0) 
			throw new NodeOutOfBoundsException("List is empty."); 
		return dHeader.getNext();
	}

	/**
	 * Returns a reference to the last node in the list
	 * 
	 * @return reference to the last node of the linked list
	 * @throws INodeOutOfBoundsException if the linked list is empty
	 */
	public Node<E> getLastNode() throws NodeOutOfBoundsException {
		if (size == 0) 
			throw new NodeOutOfBoundsException("List is empty."); 
		return dTrailer.getPrev();
	}

	/**
	 * Returns the reference to the node that is after the given target node
	 * 
	 * @param target the node previous to the node wanted
	 * @return the node after the target node
	 * @throws NodeOutOfBoundsException if target is the last node
	 */
	public Node<E> getNodeAfter(Node<E> target)
	throws NodeOutOfBoundsException {
		// ADD CODE HERE AND MODIFY RETURN ACCORDINGLY
		DNode<E> nextNode = ((DNode<E>) target).getNext(); 

		if (nextNode == dTrailer)  {
			throw new NodeOutOfBoundsException("getNextNode(...) : target is the last node.");
		}

		else 
			return nextNode;
	}

	/**
	 * Returns the reference to the node that is before the given target node
	 * 
	 * @param target the node after the node wanted
	 * @return the node previous to the target node
	 * @throws NodeOutOfBoundsException if target is the first node
	 */
	public Node<E> getNodeBefore(Node<E> target)
	throws NodeOutOfBoundsException {
		// ADD CODE HERE AND MODIFY RETURN ACCORDINGLY
		if (target == dHeader.getNext())  
			throw new NodeOutOfBoundsException("getNextNode(...) : target is the first node.");
		else {

			return ((DNode<E>) target).getPrev(); 
		}
	}

	/**
	 * Determines the number of nodes currently in the linked list. 
	 * @return integer value (>= 0) corresponding to the number of
	 * nodes actually in the linked list.
	 */
	public int length() {
		return size;
	}

	/**
	 * Removes the first node in the list and returns the reference to it.
	 * 
	 * @return the node removed from the list
	 * @throws NodeOutOfBoundsException if list is empty.
	 */
	public Node<E> removeFirstNode() throws NodeOutOfBoundsException {
		if (size == 0) 
			throw new NodeOutOfBoundsException("List is empty."); 
		// ADD CODE HERE AND MODIFY RETURN ACCORDINGLY

		DNode<E> ntr = dHeader.getNext(); 

		dHeader.setNext(ntr.getNext()); 

		ntr.getNext().setPrev(dHeader);

		size--; 
		ntr.cleanLinks();
		return ntr;
	}

	/**
	 * Removes the last node in the list and returns the reference to it.
	 * 
	 * @return the node removed from the list
	 * @throws NodeOutOfBoundsException if list is empty.
	 */
	public Node<E> removeLastNode() throws NodeOutOfBoundsException {
		if (size == 0) 
			throw new NodeOutOfBoundsException("List is empty."); 
		DNode<E> ntd = dTrailer.getPrev();
		dTrailer = ntd.getPrev(); 
		ntd.getPrev().setNext(dTrailer); 
		size--; 
		ntd.cleanLinks(); 
		return ntd; 
	}

	/**
	 * Removes the specified node from the list
	 * 
	 * @param target the node to be removed from the list
	 */
	public void removeNode(Node<E> target) {
		if (target == dHeader.getNext() & size!=1) 
			this.removeFirstNode(); 
		else if (target == dTrailer.getPrev()){
			this.removeLastNode();
		}
		else { 
			DNode<E> prevNode = (DNode<E>) this.getNodeBefore(target); 
			DNode<E> aNode = (DNode<E>) getNodeAfter(target);
			prevNode.setNext(aNode); 
			aNode.setPrev(prevNode);

			((DNode<E>)target).cleanLinks();

			size--; 
		}
	}

	/**
	 * Removes the node after the target node
	 * 
	 * @param target the node before the node to be removed
	 * @return reference to the node that was removed
	 * @throws NodeOutOfBoundsException if target is the last node.
	 */
	public Node<E> removeNodeAfter(Node<E> target)
	throws NodeOutOfBoundsException 
	{
		DNode<E> dtarget = (DNode<E>) target; 
		if (dtarget.getNext() == dTrailer) 
			throw new NodeOutOfBoundsException("Target node is the last node."); 			
		removeNode(dtarget.getNext());
		return dtarget; 
	}

	/**
	 * Removes the node before the target node
	 * 
	 * @param target the node after the node to be removed
	 * @return reference to the node that was removed
	 * @throws NodeOutOfBoundsException if target is the first node.
	 */
	public Node<E> removeNodeBefore(Node<E> target)
	throws NodeOutOfBoundsException 
	{
		DNode<E> dtarget = (DNode<E>) target; 
		if (dtarget.getPrev() == dHeader) 
			throw new NodeOutOfBoundsException("Target node is the first node."); 			
		removeNode(dtarget.getPrev());
		return dtarget; 
	}

	/**
	 * Prepares every node so that the garbage collector can free 
	 * its memory space, at least from the point of view of the
	 * list. This method is supposed to be used whenever the 
	 * list object is not going to be used anymore. Removes all
	 * physical nodes (data nodes and control nodes, if any)
	 * from the linked list
	 */
	private void removeAll() {
		while (dHeader != null) { 
			DNode<E> nnode = dHeader.getNext(); 
			dHeader.setElement(null); 
			dHeader.cleanLinks(); 
			dHeader = nnode; 
		}
	}

	/**
	 * The execution of this method removes all the data nodes
	 * from the current instance of the list. 
	 */
	public void makeEmpty() { 
		DNode<E> temp = dHeader.getNext();
		while(dHeader.getNext()!=dTrailer){
			dHeader.setNext(dHeader.getNext().getNext());
			temp.setElement(null);
			temp.cleanLinks();
			temp = dHeader.getNext();

		}
	}

	/**
	 * Finalize the list.  Removes all the content and release the memory.
	 * 
	 */
	protected void finalize() throws Throwable {
		try {
			this.removeAll(); 
		} finally {
			super.finalize();
		}
	}

}
