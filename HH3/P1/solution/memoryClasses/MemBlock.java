package memoryClasses;

public class MemBlock {
	private int firstAddress, lastAddress;

	public MemBlock(int fa, int la) {
		firstAddress = fa;
		lastAddress = la;
	}

	public int getAddress() {
		return firstAddress;
	}

	public void setAddress(int a) {
		firstAddress = a;
	}

	public int getLastAddress() {
		return lastAddress;
	}

	public void setLastAddress(int lastAddress) {
		this.lastAddress = lastAddress;
	}

	public int getSize() {
		return lastAddress - firstAddress + 1;
	}

	public String toString() {
		return "[" + firstAddress + ", " + lastAddress + "]";
	}
}