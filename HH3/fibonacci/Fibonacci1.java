import java.math.BigInteger;
import java.util.Scanner;

public class Fibonacci1 {

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        System.out.println(Fibonacci(n));
	}

	private static BigInteger Fibonacci(int n){
		if(n == 0){
			return BigInteger.ZERO;
		}

		if(n == 1){
			return BigInteger.ONE;
		}

		return Fibonacci(n-2).add(Fibonacci(n-1));
	}

}