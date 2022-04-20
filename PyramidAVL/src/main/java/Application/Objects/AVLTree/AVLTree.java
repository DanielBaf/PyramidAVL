package Application.Objects.AVLTree;

import java.util.ArrayList;
import java.util.Collections;
import lombok.Data;

/**
 *
 * @author jefemayoneso
 */
@Data
public class AVLTree<T> {

    private AVLNode<T> root;

    public AVLTree() {
        this.root = null;
    }

    /**
     * Search a node into an AVL tree
     *
     * @param data
     * @param value
     * @param toFind
     * @return
     */
    public AVLNode<T> search(T data, int value, AVLNode<T> toFind) {
        if (toFind == null) {
            return null;
        } else if (toFind.getValue() == value) { // TODO create method to find by data
            return toFind;
        } else if (toFind.getValue() < value) {
            return search(data, value, toFind.getRightChild());
        } else {
            return search(data, value, toFind.getLeftChild());
        }
    }

    /**
     * Flip values from a tree to balance a tree
     *
     * @param node
     * @param flipType
     * @return
     */
    public AVLNode<T> flip(AVLNode<T> node, int flipType) {
        AVLTreeFlipper<T> flipper = new AVLTreeFlipper<>();
        switch (flipType) {
            case 1:
                return flipper.flipLeft(node);
            case 2:
                return flipper.flipRight(node);
            case 3:
                return flipper.doubleFlipLeft(node);
            default:
                return flipper.doubleFlipRight(node);
        }
    }

    /**
     * Insert a node into an AVL tree
     */
    public void insert(int value, T data) {
        AVLNode<T> newNode = new AVLNode<>(data, value);
        if (this.root == null) {
            this.root = newNode;
        } else {
            this.root = new AVLTreeInserter<T>().insert(newNode, this.root);
        }
    }

    public String print(int type) {
        String result = "";
        ArrayList<String> data = new ArrayList<>();
        AVLTreePrinter<T> printer = new AVLTreePrinter<>();
        switch (type) {
            case 1:
                printer.inOrder(this.root, data);
            case 2:
                printer.preOrder(this.root, data);
            default:
                printer.preOrder(this.root, data);
        }
        // check array and remove nulls
        data.removeAll(Collections.singleton(null));
        if (!data.isEmpty()) {
            System.out.println("SIZE: " + data.size());
            result = data.stream().map(line -> "<br>" + line).reduce(result, String::concat);
        }
        return result;
    }

    /**
     * get the Equilibrium factor of a tree
     *
     * @param node
     * @return
     */
    public int getEF(AVLNode<T> node) {
        if (node == null) {
            return -1;
        } else {
            return node.getEquilibriumFactor();
        }
    }
}
