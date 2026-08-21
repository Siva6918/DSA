import java.util.Scanner;

public class taylorSeries2 {
    static double s = 1;

    public static double e(int x, int n) {
        if (n == 0) {
            return s;
        } else {
            s = 1 + ((double) x / n) * s;
            return e(x, n - 1);
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
