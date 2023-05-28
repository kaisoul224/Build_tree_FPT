package BSTDemo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author NguyenQuocAnh CE170483
 */
public class BSTree {

    BSTNode root;
    String result;
    ArrayList<BSTNode> path;
    ArrayList<NodeData> treeData;

//    for drawing
    int screenWidth;
    int yMin;

    /**
     * Constructor for BSTree with no parameter
     */
    public BSTree() {
        root = null;
        this.screenWidth = 0;
        this.yMin = 0;
        result = "";
        path = new ArrayList<>();
        treeData = new ArrayList<>();
    }

    /**
     * Constructor for BSTree with two parameters
     * @param screenWidth
     * @param yMin
     */
    public BSTree(int screenWidth, int yMin) {
        root = null;
        this.screenWidth = screenWidth;
        this.yMin = yMin;
        path = new ArrayList<>();
        treeData = new ArrayList<>();
    }

    /**
     *  get root from BSTree
     * @return root
     */
    public BSTNode getRoot() {
        return this.root;
    }

    /**
     * get Path from BSTree
     * @return
     */
    public ArrayList<BSTNode> getPath() {
        return path;
    }

    /**
     * add new node to BSTree 
     * @param data
     */
    public void addNode(int data) {
        if (root == null) {
            root = new BSTNode(data, yMin, screenWidth);
        } else {
            boolean isAdded = false;
            BSTNode node = root;
            while (!isAdded) {
                if (data < node.getData()) {
                    if (node.hasLeftChild()) {
                        node = node.getLeftChild();
                    } else {
                        node.setLeftChild(new BSTNode(data));
                        isAdded = true;
                    }
                } else if (data > node.getData()) {
                    if (node.hasRightChild()) {
                        node = node.getRightChild();
                    } else {
                        node.setRightChild(new BSTNode(data));
                        isAdded = true;
                    }

                } else {
                    node.setCount(node.getCount() + 1);
                    isAdded = true;
                }
            }
        }
    }

    /**
     * add new node to BSTree
     * @param data
     * @param count
     */
    public void addNode(int data, int count) {
        if (root == null) {
            root = new BSTNode(data, yMin, screenWidth);
        } else {
            boolean isAdded = false;
            BSTNode node = root;
            while (!isAdded) {
                if (data < node.getData()) {
                    if (node.hasLeftChild()) {
                        node = node.getLeftChild();
                    } else {
                        node.setLeftChild(new BSTNode(data, count));
                        isAdded = true;
                    }
                } else if (data > node.getData()) {
                    if (node.hasRightChild()) {
                        node = node.getRightChild();
                    } else {
                        node.setRightChild(new BSTNode(data, count));
                        isAdded = true;
                    }

                } else {
                    node.setCount(node.getCount() + 1);
                    isAdded = true;
                }
            }
        }
    }

    /**
     * remove a node in BSTree base on the node's data parameter
     * @param data
     * @return
     */
    public boolean removeNode(int data) {
        BSTNode node = findNode(data);
        return removeNode(node);
    }

    /**
     * remove a node in BSTree base on the node parameter 
     * @param node
     * @return
     */
    public boolean removeNode(BSTNode node) {
        if (node == null) {
            return false;
        }

        node.setCount(node.getCount() - 1);

        if (node.getCount() == 0) {
            if (node.isLeaf()) {
                node.getParent().removeLeafChild(node);
                return true;
            } else {
                BSTNode incomer = null;
                if (node.hasLeftChild()) {
                    incomer = node.getLeftChild().findMaxNode();
                } else {
                    incomer = node.getRightChild().findMinNode();
                }
                node.setData(incomer.getData());
                node.setCount(incomer.getCount());

                incomer.setCount(1);
                return removeNode(incomer);
            }
        } else {
            return true;
        }
    }

    /**
     * get traversal result
     * @return
     */
    public String getTraversalResult() {
        return this.result;
    }

    /**
     * get the path return by findNode function 
     * @return
     */
    public String getFindNodeResult() {
        return this.result;
    }

    /**
     * call recursive to get Pre-order traversal 
     */
    public void preOrder() {
        result = "";
        preOrder(root);

    }

    /**
     * call recursive to get In-order traversal 
     */
    public void inOrder() {
        result = "";
        inOrder(root);

    }

    /**
     * call recursive to get Post-order traversal 
     */
    public void postOrder() {
        result = "";
        postOrder(root);

    }
    
    private void preOrder(BSTNode node) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < node.getCount(); i++) {
            result += node.getData() + ", ";
//            System.out.print(node.getData() + ", ");
        }
        preOrder(node.getLeftChild());
        preOrder(node.getRightChild());
    }

    private void inOrder(BSTNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.getLeftChild());
        for (int i = 0; i < node.getCount(); i++) {
            result += node.getData() + ", ";
//            System.out.print(node.getData() + ", ");
        }
        inOrder(node.getRightChild());
    }

    private void postOrder(BSTNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.getLeftChild());
        postOrder(node.getRightChild());
        for (int i = 0; i < node.getCount(); i++) {
            result += node.getData() + ", ";
//            System.out.print(node.getData() + ", ");
        }
    }

    /**
     * find a node in tree base on the node's data parameter
     * @param data
     * @return
     */
    public BSTNode findNode(int data) {
        BSTNode node = this.root;
        result = "";
        path.clear();
        while (node != null) {
            result += node.getData() + " -> ";
            path.add(node);
            if (data == node.getData()) {
                return node;
            } else if (data < node.getData()) {
                node = node.getLeftChild();
            } else {
                node = node.getRightChild();
            }
        }
        path.clear();
        return null;
    }

    /**
     * call recursive function to clear  all node in the BSTree
     */
    public void clear() {
        clear(this.root);
        this.root = null;

    }

    /**
     * clear each node in the BSTree
     * @param node
     */
    public void clear(BSTNode node) {
        if (node == null) {
            return;
        }
        if (node.isLeaf()) {
            node.getParent().removeLeafChild(node);
        } else {
            clear(node.getLeftChild());
            clear(node.getRightChild());
        }
    }

    /**
     * DFS Traversal
     */
    public void DFS() {
        this.result = "";
        Stack<BSTNode> s = new Stack<>();
        s.add(root);

        BSTNode node;
        while (!s.isEmpty()) {
            node = s.pop();
            if (node != null) {
                for (int i = 0; i < node.getCount(); i++) {
                    System.out.println(node.getData() + ", ");
                    this.result += node.getData() + ", ";
                }
                s.add(node.getRightChild());
                s.add(node.getLeftChild());
            }

        }
    }

    /**
     * BFS Traversal 
     */
    public void BFS() {
        this.result = "";
        Queue<BSTNode> q = new LinkedList<>();
        q.add(root);

        BSTNode node;
        while (!q.isEmpty()) {
            node = q.poll();
            if (node != null) {
                for (int i = 0; i < node.getCount(); i++) {
                    System.out.println(node.getData() + ", ");
                    this.result += node.getData() + ", ";
                }
                q.add(node.getLeftChild());
                q.add(node.getRightChild());
            }

        }
    }

    private void BST2Array(BSTNode node) {
        if (node == null) {
            return;
        }
        BST2Array(node.getLeftChild());
        treeData.add(new NodeData(node.getData(), node.getCount()));
        BST2Array(node.getRightChild());
    }

    /**
     * balancing the BSTree
     */
    public void balancing() {
        treeData.clear();
        BST2Array(this.root); // store BST into ascending ordered array

        this.clear();// remove all node from this BST
        Queue<BSTRange> q = new LinkedList<>();
        q.add(new BSTRange(0, treeData.size() - 1));
        BSTRange range;
        NodeData nodeData;
        int middleIndex, leftIndex, rightIndex;

        while (!q.isEmpty()) {
            range = q.poll();
            leftIndex = range.getLeftIndex();
            rightIndex = range.getRightIndex();
            if (leftIndex < rightIndex) {
                middleIndex = (leftIndex + rightIndex) / 2;
                nodeData = treeData.get(middleIndex);
                this.addNode(nodeData.getData(), nodeData.getCount());

                q.add(new BSTRange(leftIndex, middleIndex - 1));
                q.add(new BSTRange(middleIndex + 1, rightIndex));
            }
        }
    }
}
