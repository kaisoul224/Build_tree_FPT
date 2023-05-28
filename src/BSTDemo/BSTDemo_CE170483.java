package BSTDemo;

/**
 *
 * @author NguyenQuocAnh CE170483
 */
public class BSTDemo_CE170483 {

    public static void main(String[] args) {
        BSTree tree = new BSTree();

        tree.addNode(15);
        tree.addNode(12);
        tree.addNode(27);
        tree.addNode(5);
        tree.addNode(13);
        tree.addNode(19);
        tree.addNode(36);
        tree.addNode(11);
        tree.addNode(14);
        tree.addNode(22);
        tree.addNode(33);
        tree.addNode(48);
        tree.addNode(25);
        tree.addNode(28);

        System.out.print("Pre-order: ");
        tree.preOrder();

        System.out.print("\nIn-order: ");
        tree.inOrder();

        System.out.print("\nPost-order: ");
        tree.postOrder();
    }
}
