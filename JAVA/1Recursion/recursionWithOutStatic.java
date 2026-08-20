
public class recursionWithOutStatic {
    public static int display(int n) {
        if (n > 0) {
            return display(n - 1) + n;

        }
        return 0;

    }

    public static void main(String args[]) {
        int n = 5;
        System.out.println(display(n));
    }
}
