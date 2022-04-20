package Application.Objects.AVLTree;

/**
 * This class has all methods to insert into an AVL Tree
 *
 * @author jefemayoneso
 */
public class AVLTreeInserter<T> extends AVLTree<T> {

    /**
     * Insert a node to the tree
     *
     * @param newNode
     * @param subTree
     * @return
     */
    public AVLNode<T> insert(AVLNode<T> newNode, AVLNode<T> subTree) {
        AVLNode<T> newParent = subTree;
        if (newNode.getValue() < subTree.getValue()) { // check if is lower than actual node
            checkLeftInsert(subTree, newNode, newParent);
        } else if (newNode.getValue() > subTree.getValue()) {
            checkRightInsert(subTree, newNode, newParent);
        } else { // value exists
            System.out.println("Duplicated node"); // TODO validate and return status
        }
        // update equilibrium factor
        subTree.checkNewEF();
        return newParent;
    }

    /**
     * Try to insert a new node at the left, if it isn't balanced, call a method
     * to balance after insert
     *
     * @param subTree
     * @param newNode
     * @param newParent
     */
    private void checkLeftInsert(AVLNode<T> subTree, AVLNode<T> newNode, AVLNode<T> newParent) {
        if (subTree.getLeftChild() == null) { // always try to insert left first
            subTree.setLeftChild(newNode);
        } else { // recursively try to insert at left node
            subTree.setLeftChild(insert(newNode, subTree.getLeftChild()));
            // check non balanced tree
            if ((getEF(subTree.getLeftChild()) - getEF(subTree.getRightChild())) == 2) {
                if (newNode.getValue() < subTree.getLeftChild().getValue()) {
                    newParent = flip(subTree, 1); // simple left flip
                } else {
                    newParent = flip(subTree, 3); // double left flip
                }
            }
        }
    }

    /**
     * Try to insert a new node at the right, if it isn't balanced, call a
     * method to balance after insert
     *
     * @param subTree
     * @param newNode
     * @param newParent
     */
    private void checkRightInsert(AVLNode<T> subTree, AVLNode<T> newNode, AVLNode<T> newParent) {
        if (subTree.getRightChild() == null) { // try to insert right
            subTree.setRightChild(newNode);
        } else { // recursively try to insert at right node
            subTree.setRightChild(insert(newNode, subTree.getRightChild()));
            // check non balanced tree
            if (getEF(subTree.getRightChild()) - getEF(subTree.getLeftChild()) == 2) {
                if (newNode.getValue() > subTree.getRightChild().getValue()) {
                    newParent = flip(subTree, 2); // simple right flip
                } else {
                    newParent = flip(subTree, 4); // double right flip
                }
            }
        }
    }

}
