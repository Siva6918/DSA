
/*public class indirectRecursion {
    public static void A(int n) {
        if (n > 0) {
            System.out.print(n + " ");
            B(n - 1);

        }

    }

    public static void B(int n) {
        if (n > 1) {
            System.out.print(n + " ");
            A(n / 2);
        }
    }

    public static void main(String args[]) {
        int n = 20;
        A(n);
    }
}*/
/*Using function prototyping
public class indirectRe */

public class indirectRecursion {

    public static void main(String args[]) {
        int n = 20;
        A(n);
    }

    public static void A(int n) {
        if (n > 0) {
            System.out.print(n + " ");
            B(n - 1);

        }

    }

    public static void B(int n) {
        if (n > 1) {
            System.out.print(n + " ");
            A(n / 2);
        }
    }
}