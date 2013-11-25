package interfaces;

import exceptionClasses.FullMemoryException;

public interface MemorySystem {
	/** Trata de reservar bloque de memoria que consiste de
	 * tantos bytes como se especifica. Esos bytes tienen
	 * que estar disponible en el momento.
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
	int reserveMemory(int size) throws FullMemoryException;

	/** Tries to free the block of memory of the size given
	 * at the address given. If possible, the block of bytes
	 * is freed and properly recorded. If not successful,
	 * it prints proper error message.
	 * If successful, all bytes that were freed are assigned
	 * the char value '0'.
	 **/
	void freeMemory(int address, int size);
	/** Displays the content of memory from a given initial
	 * address up to a final address. The content must show
	 * the address of the byte and its content as a
	 * a character - it shows the char value of the content
	 * treated as an ASCII code.
	 * @param initialAddress the address of the first byte
	 * @param finalAddress the address of the last byte
	 * PRE: 0 <= initialAddress <= finalAddress < memory_size.
	 **/
	void showMMASCII(int initialAddress, int finalAddress);
}