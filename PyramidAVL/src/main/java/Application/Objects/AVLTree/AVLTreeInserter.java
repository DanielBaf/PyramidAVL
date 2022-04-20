package Application.Objects.AVLTree;

/**
 *
 * @author jefemayoneso
 */
public class AVLTreeInserter<T> {

    AVLTreeFlipper<T> flipper;

    public AVLNode<T> insertAVL(AVLNode<T> newNode, AVLNode<T> subTree) {
        AVLNode<T> newParent = subTree;
        try {
            if (newNode.getValue() < subTree.getValue()) {
                checkLeftInsert(subTree, newNode, newParent);
            } else if (newNode.getValue() > subTree.getValue()) {
                checkRightInsert(subTree, newNode, newParent);
            } else {
                System.out.println("Error, node already exists");
            }
            //actualizar altura
            checkNewEf(subTree);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return newParent;
    }

    private void checkLeftInsert(AVLNode<T> subTree, AVLNode<T> newNode, AVLNode<T> newParent) {
        if (subTree.getLeftChild() == null) {
            subTree.setLeftChild(newNode);
        } else {
            subTree.setLeftChild(insertAVL(newNode, subTree.getLeftChild()));
            if ((this.flipper.getEF(subTree.getLeftChild()) - this.flipper.getEF(subTree.getRightChild()) == 2)) {
                if (newNode.getValue() < subTree.getLeftChild().getValue()) {
                    newParent = this.flipper.flip(0, subTree);
                } else {
                    newParent = this.flipper.flip(2, subTree);
                }
            }
        }
    }

    private void checkRightInsert(AVLNode<T> subTree, AVLNode<T> newNode, AVLNode<T> newParent) {
        if (subTree.getRightChild() == null) {
            subTree.setRightChild(newNode);
        } else {
            subTree.setRightChild(insertAVL(newNode, subTree.getRightChild()));
            if ((this.flipper.getEF(subTree.getRightChild()) - this.flipper.getEF(subTree.getLeftChild()) == 2)) {
                if (newNode.getValue() > subTree.getRightChild().getValue()) {
                    newParent = this.flipper.flip(1, subTree);
                } else {
                    newParent = this.flipper.flip(4, subTree);
                }
            }
        }
    }

    private void checkNewEf(AVLNode<T> subTree) {
        if ((subTree.getLeftChild() == null) && (subTree.getRightChild() != null)) {
            subTree.setEf(subTree.getRightChild().getEf() + 1);
        } else if ((subTree.getRightChild() == null) && (subTree.getLeftChild() != null)) {
            subTree.setEf(subTree.getLeftChild().getEf() + 1);
        } else {
            subTree.setEf(Math.max(this.flipper.getEF(subTree.getLeftChild()), this.flipper.getEF(subTree.getRightChild())) + 1);
        }
    }
}
