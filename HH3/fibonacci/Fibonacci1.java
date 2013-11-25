public class Fibonacci1 {

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		System.out.println(Fibonacci(n));
	}

	private static int Fibonacci(int n){
		if(n == 0){
			return 0;
		}

		if(n == 1){
			return 1;
		}

		return Fibonacci(n-2) + Fibonacci(n-1);
	}

}