package Application.Objects.AVLTree;

import lombok.Data;

/**
 * This is the master class, used to validate actions from an AVL tree
 *
 * @author jefemayoneso
 */
@Data
public class AVLTree<T> {

    private AVLNode<T> root;
    private AVLTreeInserter<T> inserter;
    private AVLTreePrinter<T> printer;

    public AVLTree() {
        this.root = null;
    }

    /**
     * Search for a node, using the value and return all node
     *
     * @param value the value to find, this is the node ID, cannot repeat
     * @param node the current node to read and find data
     * @return
     */
    public AVLNode<T> search(int value, AVLNode<T> node) {
        if (root == null) {
            return null;
        } else if (node.getValue() == value) {
            return node;
        } else if (node.getValue() < value) {
            return search(value, node.getRightChild());
        } else {
            return search(value, node.getRightChild());
        }
    }

    public void insert(int value, T data) {
        AVLNode<T> newNode = new AVLNode<>(value, data);
        if (this.root == null) {
            this.root = newNode;
        } else {
            this.root = this.inserter.insertAVL(newNode, root);
        }
    }

    public String getPrint(int type) {
        this.printer.getData(type, this.root);
        return "hola";
    }

    /**
     * CHeck if a tree is empty
     *
     * @return
     */
    public boolean isEmpty() {
        return root == null;
    }
}
