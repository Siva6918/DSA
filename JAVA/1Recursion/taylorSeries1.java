import java.util.Scanner;

public class taylorSeries1 {
    static double p = 1, f = 1;
    static double r;

    public static double e(int x, int n) {
        if (n == 0) {
            return 1;
        } else {
            r = e(x, n - 1);
            p = p * x;
            f = f * n;
            return r + p / f;
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

}
