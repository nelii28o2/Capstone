import java.util.Scanner;

//for big input, wrong output
public class Fibonacci3 {

    public static int[] M;

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        M = new int[n+1];

        M[0] = 0;
        
        if(n > 0){
            M[1] = 1;
        }

        System.out.println(Fibonacci(n));

    }

    private static int Fibonacci(int n){
        if (n == 0)
            return M[0];

        if (n == 1)
            return M[1];

        if (M[n-2] == 0)
            Fibonacci(n-2);

        if(M[n-1] == 0)
            Fibonacci(n-1);

        M[n] = M[n-2] + M[n-1];

        return M[n];
    }

}