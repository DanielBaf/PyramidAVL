package Application.Objects.AVLTree;

import java.util.ArrayList;

/**
 *
 * @author jefemayoneso
 */
public class AVLTreePrinter<T> {

    /**
     * Print a tree inOrder
     *
     * @param node
     * @return
     */
    public void inOrder(AVLNode<T> node, ArrayList<String> data) {
        if (node != null) {
            inOrder(node.getLeftChild(), data);
            data.add(node.getValue() + ": " + node.getData().toString());
            inOrder(node.getRightChild(), data);
        }
    }

    /**
     * Print a tree preOrder
     *
     * @param node
     * @return
     */
    public void preOrder(AVLNode<T> node, ArrayList<String> data) {
        if (node != null) {
            data.add(node.getValue() + ": " + node.getData().toString());
            preOrder(node.getLeftChild(), data);
            preOrder(node.getRightChild(), data);
        }
    }

    /**
     * Print a tree postOrder
     *
     * @param node
     * @return
     */
    public void postOrder(AVLNode<T> node, ArrayList<String> data) {
        if (node != null) {
            postOrder(node.getLeftChild(), data);
            postOrder(node.getRightChild(), data);
            data.add(node.getValue() + ": " + node.getData().toString());
        }
    }
}
