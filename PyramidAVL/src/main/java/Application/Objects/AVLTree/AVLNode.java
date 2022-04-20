package Application.Objects.AVLTree;

import lombok.Data;

/**
 * This class is a node from an AVL tree
 *
 * @author jefemayoneso
 */
@Data
public class AVLNode<T> {

    private int equilibriumFactor, value;
    private T data;
    // children
    private AVLNode leftChild, rightChild;

    AVLNode(T data, int value) {
        this.data = data;
        this.value = value;
        this.equilibriumFactor = 0;
        this.leftChild = this.rightChild = null;
    }

    public void checkNewEF() {
        if (this.leftChild == null && this.rightChild != null) {
            this.equilibriumFactor = this.rightChild.getEquilibriumFactor() + 1;
        } else if (this.leftChild != null && this.rightChild == null) {
            this.equilibriumFactor = this.leftChild.getEquilibriumFactor() + 1;
        } else if (this.leftChild != null && this.rightChild != null){
            this.equilibriumFactor = Math.max(this.leftChild.getEquilibriumFactor(), this.rightChild.getEquilibriumFactor()) + 1;
        } else {
            this.equilibriumFactor = -1;
        }
    }
}
