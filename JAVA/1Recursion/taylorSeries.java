import java.util.Scanner;

public class taylorSeries {
    // using fact and power fun

    public static int fact(int n) {
        if (n == 0) {
            return 1;
        } else {
            return fact(n - 1) * n;
        }
    }

    public static int pow(int m, int n) {
        if (n == 0) {
            return 1;
        } else {
            return pow(m, n - 1) * m;
        }
    }

    public static void main(String args[]) {
        int x, n;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter value:");
        x = sc.nextInt();
        n = sc.nextInt();
        sc.close();

        System.out.println(e(x, n));
    }

    public static double e(int x, int n) {
        if (n == 0) {
            return 1;
        } else {
            return pow(x, n) / fact(n) + e(x, n - 1);
        }
    }
}
