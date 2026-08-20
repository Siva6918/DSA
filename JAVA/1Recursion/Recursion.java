public class Recursion {
    // printing numbers using recursion.
    public static void display(int n) {
        if (n > 0) {
            System.out.print(n + " ");
            display(n - 1);
        }

    }

    public static void main(String args[]) {
        int n = 10;
        display(n);
    }
}