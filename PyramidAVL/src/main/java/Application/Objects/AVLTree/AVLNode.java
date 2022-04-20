package Application.Objects.AVLTree;

import lombok.Data;

/**
 * This class is a node from an AVL tree
 *
 * @author jefemayoneso
 */
@Data
public class AVLNode<T> {

    int value, ef;
    T data;
    AVLNode<T> leftChild, rightChild;

    public AVLNode(int value, T data) {
        this.value = value;
        this.data = data;
        this.ef = 0;
        this.rightChild = this.leftChild = null;
    }

}
