package GeeksforGeeks;

/**
 * Problem: Maximum difference between node and its ancestor (GeeksforGeeks)
 * Link: https://www.geeksforgeeks.org/problems/maximum-difference-between-node-and-its-ancestor/1
 *
 * Description:
 * Given a Binary Tree, find the maximum value of (ancestor.data - descendant.data)
 * such that ancestor is an ancestor of descendant.
 */

// Definition for a binary tree node.
class Node {
    int data;
    Node left, right;

    Node(int item) {
        data = item;
        left = right = null;
    }
}

public class MaximumDifferenceBetweenNodeAndItsAncestor {

    // Helper class or variable to store the global maximum difference.
    // Why use an instance variable or array?
    // In Java, primitive types (like int) are passed by value. To update a running
    // maximum across recursive calls, we can either use an instance variable,
    // an array of size 1 (e.g. int[] res), or return custom objects.
    private int maxDiff;

    /**
     * Post-Order Traversal Helper Function
     * 
     * @param root Current node being visited
     * @return The minimum value found in the subtree rooted at 'root'
     */
    private int findMinAndMaxDiff(Node root) {
        // Base Case 1: Empty node contributes infinity so it doesn't affect Math.min()
        if (root == null) {
            return Integer.MAX_VALUE;
        }

        // Base Case 2: Leaf node has no descendants.
        // It cannot act as an ancestor, so we do not calculate difference here.
        // We simply return its own value as the minimum of this leaf subtree.
        if (root.left == null && root.right == null) {
            return root.data;
        }

        // Recursive Step: Post-order (Process left and right subtrees first)
        int leftMin = findMinAndMaxDiff(root.left);
        int rightMin = findMinAndMaxDiff(root.right);

        // Find the minimum value among all descendants of current node
        int minDescendant = Math.min(leftMin, rightMin);

        // Calculate potential maximum difference with current node as ancestor
        // and update global maxDiff
        maxDiff = Math.max(maxDiff, root.data - minDescendant);

        // Return minimum value in the current subtree (including root itself)
        return Math.min(root.data, minDescendant);
    }

    /**
     * Main function to be called
     * @param root Root of the binary tree
     * @return Maximum difference between an ancestor and its descendant
     */
    public int maxDiff(Node root) {
        // Initialize maxDiff to minimum possible value
        maxDiff = Integer.MIN_VALUE;

        // Perform post-order recursion
        findMinAndMaxDiff(root);

        return maxDiff;
    }

    // Main method for local testing
    public static void main(String[] args) {
        /*
                  8
                /   \
               3     10
             /   \     \
            1     6     14
                 / \    /
                4   7  13

           Ancestor pairs examples:
           (8, 1) -> 8 - 1 = 7
           (10, 13) -> 10 - 13 = -3
           (8, 4) -> 8 - 4 = 4
           Max difference = 8 - 1 = 7
        */

        Node root = new Node(8);
        root.left = new Node(3);
        root.right = new Node(10);
        root.left.left = new Node(1);
        root.left.right = new Node(6);
        root.left.right.left = new Node(4);
        root.left.right.right = new Node(7);
        root.right.right = new Node(14);
        root.right.right.left = new Node(13);

        MaximumDifferenceBetweenNodeAndItsAncestor solver = new MaximumDifferenceBetweenNodeAndItsAncestor();
        int result = solver.maxDiff(root);

        System.out.println("Maximum Difference between Node and Ancestor: " + result);
    }
}
