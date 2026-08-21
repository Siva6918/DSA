
import java.util.Scanner;

public class Factorial {
    public static void main(String args[]) {
        int n;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter value:");
        n = sc.nextInt();
        sc.close();

        System.out.println(fact(n));
    }

    public static int fact(int n) {
        if (n == 0) {
            return 1;
        } else {
            return fact(n - 1) * n;
        }
    }
}
