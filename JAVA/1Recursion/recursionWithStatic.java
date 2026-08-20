public class recursionWithStatic {
    static int x = 0;

    public static int display(int n) {

        if (n > 0) {
            x++;
            return display(n - 1) + x;
        }
        return 0;
    }

    public static void main(String args[]) {
        int n = 5;
        System.out.println(display(n));

    }
}