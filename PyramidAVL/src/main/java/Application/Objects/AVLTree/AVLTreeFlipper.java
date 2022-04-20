package Application.Objects.AVLTree;

/**
 * This class flips positions into a tree
 *
 * @author jefemayoneso
 */
public class AVLTreeFlipper<T> {

    public AVLNode<T> flip(int type, AVLNode<T> toFlip) {
        switch (type) {
            case 0:
                return flipLeft(toFlip);
            case 1:
                return flipRight(toFlip);
            case 2:
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
        AVLNode<T> auxiliar = node.getLeftChild();
        node.setLeftChild(auxiliar.getRightChild());
        auxiliar.setRightChild(node);
        node.setEf(Math.max(getEF(node.getLeftChild()), getEF(node.getRightChild())) + 1);  //obtiene el maximo
        auxiliar.setEf(Math.max(getEF(auxiliar.getLeftChild()), getEF(auxiliar.getRightChild())) + 1);
        return auxiliar;
    }

    /**
     * Rotate an AVL tree to the right
     *
     * @param node the node to flip
     * @return
     */
    private AVLNode<T> flipRight(AVLNode<T> node) {
        AVLNode<T> auxiliar = node.getRightChild();
        node.setRightChild(auxiliar.getLeftChild());
        auxiliar.setLeftChild(node);
        node.setEf(Math.max(getEF(node.getLeftChild()), getEF(node.getRightChild())) + 1);  //obtiene el maximo
        auxiliar.setEf(Math.max(getEF(auxiliar.getLeftChild()), getEF(auxiliar.getRightChild())) + 1);
        return auxiliar;
    }

    /**
     * Rotate an AVL tree to the left, using a double flip to the left
     *
     * @param node the node to flip
     * @return
     */
    private AVLNode<T> doubleFlipLeft(AVLNode<T> node) {
        AVLNode<T> temporal;
        node.setLeftChild(flipRight(node.getLeftChild()));
        temporal = flipLeft(node);
        return temporal;

    }

    /**
     * Rotate an AVL tree to the right, using a double flip to the right
     *
     * @param node the node to flip
     * @return
     */
    private AVLNode<T> doubleFlipRight(AVLNode<T> node) {
        AVLNode<T> temporal;
        node.setRightChild(flipLeft(node.getRightChild()));
        temporal = flipRight(node);
        return temporal;
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
