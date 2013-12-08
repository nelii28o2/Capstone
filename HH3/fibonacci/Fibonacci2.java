import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static BigInteger[] M;

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        M = new BigInteger[n+1];

        M[0] = BigInteger.ZERO;
        
        if(n>0){
            M[1] = BigInteger.ONE;
        }

        System.out.println(Fibonacci(n));

    }

    private static BigInteger Fibonacci(int n){
        if (n == 0)
            return M[0];

        if (n == 1)
            return M[1];

        if (M[n-2] == null)
            Fibonacci(n-2);

        if(M[n-1]==null)
            Fibonacci(n-1);

        M[n] = M[n-2].add(M[n-1]);

        return M[n];
    }

}