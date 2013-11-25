package memoryClasses;

public class Register {
	private byte[] regByte; 
	// less significant part of the register is regByte[0]
	// most significant part is regByte[regByte.length-1]

	public Register() { 
		regByte = new byte[Register.length()]; 
	}

	public static int length() { 
		return Memory.WORDSIZE; 
	}

	public byte readByte(int index) throws IndexOutOfBoundsException { 
		if (index < 0 || index >= regByte.length) 
			throw new IndexOutOfBoundsException("readByte: Byte index is out of bounds."); 

		return regByte[index]; 
	}

	public void setByte(int index, byte nByte) throws IndexOutOfBoundsException { 
		if (index < 0 || index >= regByte.length) 
			throw new IndexOutOfBoundsException("setByte: Byte index is out of bounds."); 

		regByte[index] = nByte; 
	}

	public Register makeCopy() { 
		Register reg = new Register(); 

		for (int i=0; i < Register.length(); i++) 
			reg.setByte(i, this.readByte(i));  

		return reg; 
	}

	public int getIntFromRegister() { 
		// address being referenced is valid to produce an integer value
		int value = 0; 
		int lSB; 
		for (int index = regByte.length - 1; index >= 0; index--) { 
			value = value << Memory.BYTESIZE; 
			lSB = 0x000000ff & regByte[index];
			value = value | lSB; 
		}

		return value; 

	}

	public void copyIntToRegister(int value) { 
		for (int index = 0; index < regByte.length; index++) { 
			regByte[index] = (byte) (value & 0x000000ff); 
			value = value >> Memory.BYTESIZE; 
		}

	}

	public static void copyRegisterToRegister(Register sReg, Register dReg) { 
		for (int index = 0; index < Register.length(); index++) { 
			sReg.setByte(index, sReg.readByte(index)); 
		}

	}

}
