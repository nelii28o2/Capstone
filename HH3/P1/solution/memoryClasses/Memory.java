package memoryClasses;

import exceptionClasses.AddressOutOfRangeException;
import exceptionClasses.DataObjectOutOfBoundsException;
import exceptionClasses.InvalidAddressException;

public class Memory {

	public static final int WORDSIZE = 4;  // four bytes
	public static final int BYTESIZE = 8;  // eight bits
	private static final int DEFSIZE = 1024; // default memory size

	private final Register memoryAddressRegister;    // MAR       
	// address to be accessed

	private final Register memoryInputDataRegister;        // MIDR
	// data to be written 

	private final Register memoryOutputDataRegister;         // MODR
	// that was read from mem[address]

	private final byte[] mem;    // the memory area

	public Memory() {
		this(DEFSIZE); 
	}

	public Memory(int size) { 
		if (size <= 0) 
			size = DEFSIZE; 
		mem = new byte[size]; 

		memoryAddressRegister = new Register(); 
		memoryInputDataRegister = new Register(); 
		memoryOutputDataRegister = new Register(); 

	}

	/**
	 * Copies the current memory word at the address stored in MAR
	 * into the internal MODR
	 * @throws DataObjectOutOfBoundsException
	 * @throws InvalidAddressException
	 * @throws IndexOutOfBoundsException
	 */
	public void read() throws DataObjectOutOfBoundsException, 
	InvalidAddressException, 
	AddressOutOfRangeException
	{ 
		int address = memoryAddressRegister.getIntFromRegister(); 

		if (address < 0 || address >= mem.length)
			throw new AddressOutOfRangeException("readWord: Address out of range - "
					+ address); 

		if (address % Memory.WORDSIZE != 0) 
			throw new InvalidAddressException("readWord: Address is not multiple of " 
					+ Memory.WORDSIZE); 

		if (address + Memory.WORDSIZE - 1 >= mem.length)
			throw new DataObjectOutOfBoundsException("readWord: Object size out of range from address " 
					+ address); 

		// copy target word to the memory's data register
		for (int memIndex = address, regIndex = 0; regIndex < Memory.WORDSIZE; memIndex++, regIndex++) 
			memoryOutputDataRegister.setByte(regIndex, mem[memIndex]); 

	}

	/**
	 * Copies the current content of the MIDR data register to the 
	 * memory area beginning at address MAR 
	 * @throws DataObjectOutOfBoundsException
	 * @throws InvalidAddressException
	 * @throws IndexOutOfBoundsException
	 */
	public void write() throws DataObjectOutOfBoundsException, 
	InvalidAddressException, 
	AddressOutOfRangeException
	{ 
		int address = memoryAddressRegister.getIntFromRegister(); 

		if (address < 0 || address >= mem.length)
			throw new AddressOutOfRangeException("readWord: Address out of range - "
					+ address); 

		if (address % Memory.WORDSIZE != 0) 
			throw new InvalidAddressException("readWord: Address is not multiple of " 
					+ Memory.WORDSIZE); 

		if (address + Memory.WORDSIZE - 1 >= mem.length)
			throw new DataObjectOutOfBoundsException("readWord: Object size out of range from address " 
					+ address); 

		// copy target word to the memory's data register
		for (int memIndex = address, regIndex = 0; regIndex < Memory.WORDSIZE; memIndex++, regIndex++) 
			mem[memIndex] = memoryInputDataRegister.readByte(regIndex);

	}


	/**
	 * Returns a copy of the current content of MODR
	 * @return
	 */
	public Register getOutput() { 
		Register r = new Register(); 
		for (int b=0; b < WORDSIZE; b++)
			r.setByte(b, memoryOutputDataRegister.readByte(b)); 
		return r; 
	}

	/**
	 * Copies the specified data to the memory's write register
	 * for future recording in memory (when the write operation 
	 * is performed). 
	 * @param data
	 */
	public void setInput(Register data) { 
		for (int b=0; b < WORDSIZE; b++)
			memoryInputDataRegister.setByte(b, data.readByte(b)); 
	}

	/**
	 * Copies the specified data to the memory's data register. 
	 * @param data
	 */
	public void setAddress(int address) { 
		memoryAddressRegister.copyIntToRegister(address); 
	}

	/**
	 * Returns the capacity of this memory (number of bytes). 
	 * @return
	 */
	public int getCapacity() { 
		return this.mem.length; 
	}
}
