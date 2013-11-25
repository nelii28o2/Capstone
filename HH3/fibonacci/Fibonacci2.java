public class Fibonacci2 {

	public static int[] M;

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		M = new int[n];
		M[0] = 0;
		M[1] = 1;

		System.out.println(Fibonacci(n));
	}

	private static int Fibonacci(int n){
    if (n == 0)
        return M[0];

    if (n == 1)
        return M[1];

    if (M[n-2]==0)
        Fib(n-2);

    if(M[n-1]==0)
        Fib(n-1);

    M[n] = M[n-1] + M[n-2] 

    return M[n];
  }

}