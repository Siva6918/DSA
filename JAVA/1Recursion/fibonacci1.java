import java.util.Arrays;
import java.util.Scanner;

public class fibonacci1 {

    public static int fib(int n, int[] arr) {
        if (n <= 1) {
            arr[n] = n;
            return n;
        } else {
            if (arr[n - 2] == -1) {
                arr[n - 2] = fib(n - 2, arr);
            }
            if (arr[n - 1] == -1) {
                arr[n - 1] = fib(n - 1, arr);
            }
            arr[n] = arr[n - 2] + arr[n - 1];
            return arr[n];
        }
    }

    public static void main(String args[]) {
        int n;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n: ");
        n = sc.nextInt();
        sc.close();

        int[] arr = new int[n + 1];
        Arrays.fill(arr, -1);

        int k = fib(n, arr);
        System.out.println("Fibonacci(" + n + ") = " + k);
        System.out.println("Memoization Array: " + Arrays.toString(arr));
    }
}