import java.util.Scanner;

public class sumOfNaturalNumbers {
    public static void main(String args[]) {
        int n;
        Scanner sc = new Scanner(System.in);
        System.out.println("enter value:");
        n = sc.nextInt();
        sc.close();
        System.out.print(sum(n) + " ");
    }

    public static int sum(int n) {
        if (n == 0) {
            return 0;
        } else {
            return sum(n - 1) + n;
        }
    }

}
