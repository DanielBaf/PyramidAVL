package Application.Objects.AVLTree;

/**
 *
 * @author jefemayoneso
 */
public class AVLTreeInserter<T> {

    private final AVLTreeFlipper<T> flipper;

    public AVLTreeInserter() {
        this.flipper = new AVLTreeFlipper<>();
    }

    /**
     * Insert a node into a existing AVL Tree
     *
     * @param value
     * @param data
     */
    public AVLNode<T> insert(AVLNode<T> toInsert, AVLNode<T> root) {
        if (root == null) {
            return toInsert;
        } else {
            return insertAVL(toInsert, root);
        }
    }

    /**
     * Insert a new AVL node
     *
     * @param newNode
     * @param subTree
     * @return
     */
    public AVLNode<T> insertAVL(AVLNode<T> newNode, AVLNode<T> subTree) {
        AVLNode<T> newParent = subTree;
        if (newNode.getValue() < subTree.getValue()) {
            newParent = checkLeftInsert(subTree, newNode, newParent);
        } else if (newNode.getValue() > subTree.getValue()) {
            newParent = checkRightInsert(subTree, newNode, newParent);
        } else {
            System.out.println("Error, node already exists");
        }
        // update EF
        updateEF(subTree);
        return newParent;
    }

    private AVLNode<T> checkLeftInsert(AVLNode<T> subTree, AVLNode<T> newNode, AVLNode<T> newParent) {
        if (subTree.getLeftChild() == null) {
            subTree.setLeftChild(newNode);
        } else {
            subTree.setLeftChild(insertAVL(newNode, subTree.getLeftChild()));
            if ((this.flipper.getEF(subTree.getLeftChild()) - this.flipper.getEF(subTree.getRightChild()) == 2)) {
                if (newNode.getValue() < subTree.getLeftChild().getValue()) {
                    newParent = this.flipper.flip(subTree, 0);
                } else {
                    newParent = this.flipper.flip(subTree, 3);
                }
            }
        }
        return newParent;
    }

    private AVLNode<T> checkRightInsert(AVLNode<T> subTree, AVLNode<T> newNode, AVLNode<T> newParent) {
        if (subTree.getRightChild() == null) {
            subTree.setRightChild(newNode);
        } else {
            subTree.setRightChild(insertAVL(newNode, subTree.getRightChild()));
            if ((this.flipper.getEF(subTree.getRightChild()) - this.flipper.getEF(subTree.getLeftChild()) == 2)) {
                if (newNode.getValue() > subTree.getRightChild().getValue()) {
                    newParent = this.flipper.flip(subTree, 1);
                } else {
                    newParent = this.flipper.flip(subTree, 4);
                }
            }
        }
        return newParent;
    }

    private void updateEF(AVLNode<T> subTree) {
        if ((subTree.getLeftChild() == null) && (subTree.getRightChild() != null)) {
            subTree.setEf(subTree.getRightChild().getEf() + 1);
        } else if ((subTree.getRightChild() == null) && (subTree.getLeftChild() != null)) {
            subTree.setEf(subTree.getLeftChild().getEf() + 1);
        } else {
            subTree.setEf(Math.max(this.flipper.getEF(subTree.getLeftChild()), this.flipper.getEF(subTree.getRightChild())) + 1);
        }
    }

}
