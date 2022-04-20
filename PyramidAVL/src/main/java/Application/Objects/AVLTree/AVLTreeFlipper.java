package Application.Objects.AVLTree;

/**
 * This class flips nodes to balance an AVL tree
 *
 * @author jefemayoneso
 */
public class AVLTreeFlipper<T> extends AVLTree<T> {

    /**
     * Execute a simple rigth flip
     *
     * @param node
     * @return
     */
    public AVLNode<T> flipLeft(AVLNode<T> node) {
        AVLNode<T> aux = node.getLeftChild(); // data recover
        node.setLeftChild(aux.getRightChild());
        aux.setRightChild(node); // switch
        // update equilibrium factor
        setNewEF(node, aux);
        return aux; // return switched value
    }

    /**
     * Execute a simple right flip
     *
     * @param node
     * @return
     */
    public AVLNode<T> flipRight(AVLNode<T> node) {
        AVLNode<T> aux = node.getRightChild(); // data recover
        node.setRightChild(aux.getLeftChild());
        aux.setLeftChild(node); // switch
        setNewEF(node, aux);
        return aux; // return switched value
    }

    /**
     * Execute a double left flip by calling a right flip method 2 times
     *
     * @param node
     * @return
     */
    public AVLNode<T> doubleFlipLeft(AVLNode<T> node) {
        AVLNode<T> aux;
        node.setLeftChild(flipRight(node.getLeftChild())); // flip with previous method
        aux = flipLeft(node);
        return aux;
    }

    /**
     * Execute a double right flip by calling a left flip method
     *
     * @param node
     * @return
     */
    public AVLNode<T> doubleFlipRight(AVLNode<T> node) {
        AVLNode<T> aux;
        node.setRightChild(flipLeft(node.getRightChild()));
        aux = flipRight(node);
        return aux;
    }

    private void setNewEF(AVLNode<T>... nodes) {
        for (AVLNode<T> node : nodes) {
            node.setEquilibriumFactor(Math.max(getEF(node.getLeftChild()), getEF(node.getRightChild())) + 1); // update equilibrium factor
        }
    }

}
