package interfaces;

import java.security.InvalidParameterException;

import exceptionClasses.BlockBoundaryViolationException;
import exceptionClasses.InvalidAddressException;
import exceptionClasses.SpaceNotReservedException;

public interface MemControlStructure {
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
	int reserveMemSpace(int size)
	throws InvalidParameterException;
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
	void freeSpace(int address, int blockSize) throws
	InvalidAddressException,
	BlockBoundaryViolationException,
	SpaceNotReservedException;
}