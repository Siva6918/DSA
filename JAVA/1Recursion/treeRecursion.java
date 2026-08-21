public class treeRecursion {
    public static void display(int n) {
        if (n > 0) {
            System.out.print(n + " ");
            display(n - 1);
            display(n - 1);
        }
    }

    public static void main(String args[]) {
        int n = 3;
        display(n);
    }
}