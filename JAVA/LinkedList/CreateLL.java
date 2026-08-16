
public class CreateLL {
    public static class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    public static Node head;

    public static void printLL() {
        Node n = head;
        while (n != null) {
            System.out.print(n.data + "->");
            n = n.next;
        }
        System.out.println("null");
    }

    public static void MidElement() {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        System.out.println("\nMid Element: " + slow.data);
    }

    public static void main(String[] args) {
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        // head.next.next.next.next.next = head.next;
        printLL();
        MidElement();
    }

}
