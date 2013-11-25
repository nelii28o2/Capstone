import p1MemorySystem.P1MemSystem;

public class P1MemSystemTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final int MS = 64; 
		P1MemSystem p1ms = new P1MemSystem(MS); 
		p1ms.showMMASCII(0, MS-1); 
		testReserveMemory(p1ms, 30); 
		p1ms.showMMASCII(0, MS-1); 

		testFreeMemory(p1ms, 22, 6); 
		p1ms.showMMASCII(0, MS-1); 
		testFreeMemory(p1ms, 22, 6); 
		p1ms.showMMASCII(0, MS-1); 

		testFreeMemory(p1ms, 20, 10); 
		testFreeMemory(p1ms, 23, 10);
		testFreeMemory(p1ms, 60, 10); 

		testFreeMemory(p1ms, 2, 3); 
		p1ms.showMMASCII(0, MS-1); 


		testReserveMemory(p1ms, 20); 
		p1ms.showMMASCII(0, MS-1); 

		testReserveMemory(p1ms, 50); 
		p1ms.showMMASCII(0, MS-1); 
		testReserveMemory(p1ms, 10); 
		p1ms.showMMASCII(0, MS-1); 
		testReserveMemory(p1ms, 10); 
		p1ms.showMMASCII(5, 11); 


	}

	private static void testReserveMemory(P1MemSystem ms, int size) { 
		System.out.println("\nReserving Memory : "+size + " bytes."); 
		int addr = ms.reserveMemory(size); 
		if (addr == -1) 
			System.out.println("No block available of size >= " + size); 
		else 
			System.out.println("Block of size "+ size 
					+ " has been reserved at address " + addr); 
	}

	private static void testFreeMemory(P1MemSystem ms, int addr, int size) {
		System.out.println("\nReleasing "+size+ " bytes @"+addr); 
		ms.freeMemory(addr, size); 
	}

}
