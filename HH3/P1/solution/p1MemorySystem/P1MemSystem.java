/**
 * 
 * Nelian E. Colon Collazo
 * 802-09-1384
 * ICOM 4035 Sec. 100
 * 13 de marzo de 2011
 * 
 */

package p1MemorySystem;

import implementedClasses.MSMControlStructure;
import interfaces.MemorySystem;
import memoryClasses.Memory;
import memoryClasses.Register;
import exceptionClasses.BlockBoundaryViolationException;
import exceptionClasses.FullMemoryException;
import exceptionClasses.InvalidAddressException;
import exceptionClasses.SpaceNotReservedException;

public class P1MemSystem implements MemorySystem {
	private static final int DEFMEMSIZE = 64;
	private Memory mem; // the memory
	private MSMControlStructure mcs; // to manage free space
	// ... other internal fields ...
	private Register dReg;

	public P1MemSystem() {
		this(DEFMEMSIZE);
		// ... default initialization of other internal fields...
	}

	public P1MemSystem(int msize) {
		if (msize <= 0)
			msize = DEFMEMSIZE;
		mem = new Memory(msize);
		mcs = new MSMControlStructure(msize);
		// ... initialization of other internal fields...
		dReg = new Register();
	}

	// ... implementation of other required methods ...

	/** Trata de reservar bloque de memoria que consiste de
	 * tantos bytes como se especifica. Esos bytes tienen
	 * que estar disponibles en el momento.
	 * If successful, all reserved byte are initialized
	 * to contain the char value '1'.
	 * @param size the number of contiguous bytes to reserve
	 * @throws FullMemoryException if memory has no free
	 * left.
	 * @return a positive integer value corresponding of the
	 * address of the first byte in the block that has been
	 * reserved. This would mean that there was at least one
	 * contiguous block of "size" bytes that were free.
	 * those bytes will then be recorded as reserved.
	 * If no such block of contiguous free blocks is found,
	 * the value to return is -1 and no further action is
	 * done by this method.
	 **/
	public int reserveMemory(int size) throws FullMemoryException {
		if(mcs.memIsFull())
			throw new FullMemoryException("Memory is Full.");

		int address = mcs.reserveMemSpace(size);
		int returnAd = address;

		if(address != -1){

			if(address % 4 == 0){

				if(size % 4 != 0){

					int j;

					for (j = address; j < address + size - 4; j += 4) {

						for(int i = 0;i < 4;i++)
							dReg.setByte(i, (byte) 1 );

						mem.setInput(dReg);
						mem.setAddress(j);
						mem.write();

					}

					address = j;
					mem.setAddress(address);
					mem.read();
					dReg = mem.getOutput();

					for(int i = 0; i < size % 4;i++)
						dReg.setByte(i, (byte) 1 );

					mem.setInput(dReg);
					mem.write();
				}

				else{
					mem.read();
					dReg = mem.getOutput();

					for(int j = address; j < address + size; j += 4)
					{
						for(int i = 0;i < 4;i++)
							dReg.setByte(i, (byte) 1 );

						mem.setInput(dReg);
						mem.setAddress(j);
						mem.write(); 
					}
				}
			}

			else{

				if(size%4 == 0){
					int mod = address % 4;

					address = address/4 * 4;
					mem.setAddress(address);

					mem.read();
					dReg = mem.getOutput();

					for(int i = mod; i < 4;i++){

						dReg.setByte(i, (byte) 1 );
						size--;

					}


					mem.setInput(dReg);
					mem.write();
					address += 4;

					int j;

					for (j = address; j < address + size - 4; j += 4) {

						for(int i = 0;i < 4;i++)
							dReg.setByte(i, (byte) 1 );

						mem.setInput(dReg); 
						mem.setAddress(j);
						mem.write();

					}

					address = j;
					mem.setAddress(address);
					mem.read();
					dReg = mem.getOutput();

					for(int i = 0; i < size % 4;i++)
						dReg.setByte(i, (byte) 1 );

					mem.setInput(dReg);
					mem.write();
				}

				else{

					int mod = address % 4;
					address = address/4 * 4;

					mem.setAddress(address);
					mem.read();
					dReg = mem.getOutput();

					for(int i = mod;i < 4;i++){

						dReg.setByte(i, (byte) 1 );
						size--;

					}


					mem.setInput(dReg);
					mem.write();

					address += 4;

					if(address % 4 == 0 && size % 4 != 0){

						int j;

						for (j = address;j < address + size - 4; j += 4) {

							for(int i = 0;i < 4;i++)
								dReg.setByte(i, (byte) 1 );

							mem.setInput(dReg); 
							mem.setAddress(j);
							mem.write();

						}

						address = j;

						mem.setAddress(address);
						mem.read();
						dReg = mem.getOutput();

						for(int i = 0; i < size % 4;i++)
							dReg.setByte(i, (byte) 1 );

						mem.setInput(dReg);

						mem.write();
					}

					else if(address % 4 == 0 && size % 4 == 0)
					{

						dReg = mem.getOutput();

						for(int j = address; j < address + size; j += 4)
						{

							for(int i = 0;i < 4;i++)
								dReg.setByte(i, (byte) 1 );

							mem.setInput(dReg); 
							mem.setAddress(j);
							mem.write();
						}

					}

				}

			}

		}

		return returnAd;
	}

	/** Tries to free the block of memory of the size given
	 * at the address given. If possible, the block of bytes
	 * is freed and properly recorded. If not successful,
	 * it prints proper error message.
	 * If successful, all bytes that were freed are assigned
	 * the char value '0'.
	 **/
	public void freeMemory(int address, int size) {
		boolean flag = true;

		try {
			mcs.freeSpace(address, size);
		} catch (InvalidAddressException e) {
			System.out.println(e);
			flag = false;
		} catch (BlockBoundaryViolationException e) {
			System.out.println(e);
			flag = false;
		} catch (SpaceNotReservedException e) {
			System.out.println(e);
			flag = false;
		}

		if(flag){

			if(address % 4 == 0)
			{
				if(size%4==0){
					dReg = mem.getOutput();

					for(int j = address; j < address + size; j += 4)
					{

						for(int i = 0;i < 4;i++)
							dReg.setByte(i, (byte) 0 );

						mem.setInput(dReg); 
						mem.setAddress(j);
						mem.write();
					}

				}

				else {

					int j;

					for (j = address; j < address + size - 4; j += 4) {

						for(int i = 0;i < 4;i++)
							dReg.setByte(i, (byte) 0 );

						mem.setInput(dReg); 
						mem.setAddress(j);
						mem.write();

					}

					mem.setAddress(j);
					mem.read();
					dReg = mem.getOutput();

					for(int i = 0; i < size % 4;i++)
						dReg.setByte(i, (byte) 0 );

					mem.setInput(dReg);
					mem.setAddress(address);
					mem.write();
				}
			}

			else{
				if(size%4==0){
					int mod = address % 4;
					address = address/4 * 4;
					mem.setAddress(address);
					mem.read();
					dReg = mem.getOutput();

					for(int i = mod; i < 4;i++){

						dReg.setByte(i, (byte) 0 );
						size--;

					}

					mem.setInput(dReg);
					mem.write();

					address += 4;

					int j;

					for (j = address; j < address + size - 4; j += 4) {

						for(int i = 0;i < 4; i++)
							dReg.setByte(i, (byte) 0 );

						mem.setInput(dReg); 
						mem.setAddress(j);
						mem.write();

					}

					address = j;
					mem.setAddress(address);
					mem.read();
					dReg = mem.getOutput();

					for(int i = 0; i < size % 4; i++)
						dReg.setByte(i, (byte) 0 );

					mem.setInput(dReg);
					mem.write();
				}


				else{

					int mod = address % 4;
					address = address/4 * 4;
					mem.setAddress(address);
					mem.read();
					dReg = mem.getOutput();

					for(int i = mod; i < 4; i++){

						dReg.setByte(i, (byte) 0 );
						size--;

					}

					mem.setInput(dReg);
					mem.write();

					address += 4;

					if(address % 4 == 0 && size % 4 == 0)
					{

						dReg = mem.getOutput();

						for(int j = address; j < address + size; j += 4)
						{

							for(int i = 0;i < 4;i++)
								dReg.setByte(i, (byte) 0 );

							mem.setInput(dReg); 
							mem.setAddress(j);
							mem.write(); 
						}

					}
					else if(address % 4 == 0 && size % 4 != 0){

						int j;

						for (j = address;j < address + size - 4; j += 4) {

							for(int i = 0;i < 4;i++)
								dReg.setByte(i, (byte) 0 );

							mem.setInput(dReg); 
							mem.setAddress(j);
							mem.write();

						}

						address = j;
						mem.setAddress(address);
						mem.read();
						dReg = mem.getOutput();

						for(int i = 0;i < size % 4;i++)
							dReg.setByte(i, (byte) 0 );

						mem.setInput(dReg);
						mem.write();
					}

				}
			}
		}

	}

	/** Displays the content of memory from a given initial
	 * address up to a final address. The content must show
	 * the address of the byte and its content as a
	 * a character - it shows the char value of the content
	 * treated as an ASCII code.
	 * @param initialAddress the address of the first byte
	 * @param finalAddress the address of the last byte
	 * PRE: 0 <= initialAddress <= finalAddress < memory_size.
	 **/
	public void showMMASCII(int initialAddress, int finalAddress) {
		int size = finalAddress - initialAddress + 1;

		System.out.println("Address Content");

		if(initialAddress % 4 == 0){

			if(size % 4 == 0){

				for(int j = initialAddress;j < initialAddress + size; j += 4)
				{
					mem.setAddress(j);
					mem.read();
					dReg = mem.getOutput();

					for(int i = 0;i < 4; i++){
						System.out.println((j+i) + " " + dReg.readByte(i));
					}

				}

			}
			else{

				int j;

				for (j = initialAddress;j < initialAddress + size - 4; j += 4) {

					mem.setAddress(j);
					mem.read();
					dReg = mem.getOutput();

					for(int i = 0;i < 4; i++){
						System.out.println((j+i) + " " + dReg.readByte(i));
					}

				}

				initialAddress = j;

				mem.setAddress(initialAddress);
				mem.read();
				dReg = mem.getOutput();

				for(int i = 0;i < size % 4;i++){
					System.out.println((j+i) + " " + dReg.readByte(i));
				}

			}

		}
		else{

			if(size % 4 == 0){

				int mod = initialAddress % 4;
				initialAddress = initialAddress/4 * 4;
				mem.setAddress(initialAddress);
				mem.read();
				dReg = mem.getOutput();

				for(int i = mod;i < 4;i++){
					System.out.println((initialAddress + i) + " " + dReg.readByte(i));
					size--;

				}

				initialAddress += 4;

				int j;

				for (j = initialAddress;j < initialAddress + size - 4; j += 4) {
					mem.setAddress(j);
					mem.read();
					dReg = mem.getOutput();

					for(int i = 0;i < 4;i++)
						System.out.println((j + i) + " " + dReg.readByte(i));



				}

				initialAddress = j;
				mem.setAddress(initialAddress);
				mem.read();
				dReg = mem.getOutput();

				for(int i = 0;i < size % 4;i++)
					System.out.println((initialAddress + i) + " " + dReg.readByte(i));

			}
			else{

				int mod = initialAddress % 4;
				initialAddress = initialAddress/4 * 4;
				mem.setAddress(initialAddress);
				mem.read();
				dReg = mem.getOutput();

				for(int i = mod;i < 4;i++){
					System.out.println((initialAddress + i) + " " + dReg.readByte(i));
					size--;

				}

				initialAddress += 4;

				if(size % 4 == 0){
					for(int j = initialAddress;j < initialAddress + size; j += 4)
					{
						mem.setAddress(j);
						mem.read();
						dReg = mem.getOutput();

						for(int i = 0;i < 4; i++){
							System.out.println((j+i) + " " + dReg.readByte(i));
						}
					}
				}

				else{

					int j;

					for (j = initialAddress;j < initialAddress + size - 4; j += 4) {

						mem.setAddress(j);
						mem.read();
						dReg = mem.getOutput();

						for(int i = 0;i < 4; i++){
							System.out.println((j+i) + " " + dReg.readByte(i));
						}

					}

					initialAddress = j;

					mem.setAddress(initialAddress);
					mem.read();
					dReg = mem.getOutput();

					for(int i = 0;i < size % 4;i++){
						System.out.println((j+i) + " " + dReg.readByte(i));
					}
				}
			}
		}
	}
}