
public class nestedRecursion {
    public static void main(String args[]) {
        int n = 95;
        System.out.print(fun(n) + " ");
    }

    public static int fun(int n) {
        if (n > 100) {
            return n - 10;
        } else {
            return fun(fun(n + 11));
        }
    }
}
