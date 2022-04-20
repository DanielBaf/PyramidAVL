package Application.Objects.AVLTree;

/**
 * This class flips positions into a tree
 *
 * @author jefemayoneso
 */
public class AVLTreeFlipper<T> {

    /**
     * Flip a node into a tree
     *
     * @param toFlip
     * @param type
     * @return
     */
    public AVLNode<T> flip(AVLNode<T> toFlip, int type) {
        switch (type) {
            case 0:
                return flipLeft(toFlip);
            case 1:
                return flipRight(toFlip);
            case 3:
                return doubleFlipLeft(toFlip);
            default:
                return doubleFlipRight(toFlip);
        }
    }

    /**
     * Rotate an AVL tree to the left
     *
     * @param node the node to flip
     * @return
     */
    private AVLNode<T> flipLeft(AVLNode<T> node) {
        AVLNode<T> aux = node.getLeftChild();
        node.setLeftChild(aux.getRightChild());
        aux.setRightChild(node);
        node.setEf(Math.max(getEF(node.getLeftChild()), getEF(node.getRightChild())) + 1);  // calc new max
        aux.setEf(Math.max(getEF(aux.getLeftChild()), getEF(aux.getRightChild())) + 1);
        return aux;
    }

    /**
     * Rotate an AVL tree to the right
     *
     * @param node the node to flip
     * @return
     */
    private AVLNode<T> flipRight(AVLNode<T> node) {
        AVLNode<T> aux = node.getRightChild();
        node.setRightChild(aux.getLeftChild());
        aux.setLeftChild(node);
        node.setEf(Math.max(getEF(node.getLeftChild()), getEF(node.getRightChild())) + 1);  // calc new max
        aux.setEf(Math.max(getEF(aux.getLeftChild()), getEF(aux.getRightChild())) + 1);
        return aux;
    }

    /**
     * Rotate an AVL tree to the left, using a double flip to the left
     *
     * @param node the node to flip
     * @return
     */
    private AVLNode<T> doubleFlipLeft(AVLNode<T> node) {
        AVLNode<T> temp;
        node.setLeftChild(flipRight(node.getLeftChild()));
        temp = flipLeft(node);
        return temp;

    }

    /**
     * Rotate an AVL tree to the right, using a double flip to the right
     *
     * @param node the node to flip
     * @return
     */
    private AVLNode<T> doubleFlipRight(AVLNode<T> node) {
        AVLNode<T> temp;
        node.setRightChild(flipLeft(node.getRightChild()));
        temp = flipRight(node);
        return temp;
    }

    /**
     * get the equilibrium factor of a tree
     *
     * @param node
     * @return
     */
    public int getEF(AVLNode<T> node) {
        if (node == null) {
            return -1;
        } else {
            return node.getEf();
        }
    }

}
