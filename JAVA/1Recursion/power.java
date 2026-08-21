import java.util.Scanner;

public class power {
    public static void main(String[] args) {
        int m, n;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter value:");
        m = sc.nextInt();
        n = sc.nextInt();
        sc.close();

        System.out.println(pow(m, n));
    }

    public static int pow(int m, int n) {
        if (n == 0) {
            return 1;
        } else {
            return pow(m, n - 1) * m;
        }
    }
}
