/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Objects.AVLTree;

/**
 *
 * @author jefemayoneso
 */
public class AVLTreeRemover<T> {

    /**
     * Method used to remove nodes, the slice nodes, or nodes with children
     *
     * @param toDelete
     * @param root
     * @return
     */
    public boolean remove(AVLNode<T> toDelete, AVLNode<T> root) {
        AVLNode<T> aux = root, parent = root;
        boolean isLeftChild = true;
        while (aux.getValue() != toDelete.getValue()) { // search the parent of the node to delete
            parent = aux;
            if (toDelete.getValue() < aux.getValue()) {
                isLeftChild = true;
                aux = aux.getLeftChild();
            } else {
                isLeftChild = false;
                aux = aux.getRightChild();
            }
            if (aux == null) { // never found value
                return false;
            }
        } // end while
        if (aux.getLeftChild() == null && aux.getRightChild() == null) { // slice node
            root = aux == root ? null : root; // only one node
            parent.setLeftChild(isLeftChild ? null : parent.getLeftChild());
            parent.setRightChild(isLeftChild ? parent.getRightChild() : null);
        } else if (aux.getRightChild() == null) { // parent found at the right
            root = aux == root ? aux.getLeftChild() : root; // only one node
            parent.setLeftChild(isLeftChild ? aux.getLeftChild() : parent.getLeftChild());
            parent.setRightChild(isLeftChild ? parent.getRightChild() : aux.getLeftChild());
        } else if (aux.getLeftChild() == null) { // parent found at the left
            root = aux == root ? aux.getRightChild() : root; // only one node
            parent.setLeftChild(isLeftChild ? aux.getRightChild() : parent.getLeftChild());
            parent.setRightChild(isLeftChild ? parent.getRightChild() : aux.getLeftChild());
        } else { // need to find a switchable data value
            AVLNode<T> switchNode = findSwitchableNode(aux);
            root = aux == root ? switchNode : root;
            parent.setLeftChild(isLeftChild ? switchNode : parent.getLeftChild());
            parent.setRightChild(isLeftChild ? parent.getRightChild() : switchNode);
            switchNode.setLeftChild(aux.getLeftChild()); // update tree
        }
        return true;
    }

    /**
     * When a node has children, and u want to delete it, this method finds a
     * possible node to take the position of the parent
     *
     * @param aux
     * @return
     */
    private AVLNode<T> findSwitchableNode(AVLNode<T> nodeToReplace) {
        AVLNode<T> parentReplacement = nodeToReplace, replacement = nodeToReplace, aux = nodeToReplace.getRightChild();
        // search a replacement node
        while (aux != null) {
            parentReplacement = replacement;
            replacement = aux;
            aux = aux.getLeftChild();
        }
        // set the change for new node
        if (replacement != nodeToReplace.getRightChild()) {
            parentReplacement.setLeftChild(replacement.getRightChild());
            replacement.setRightChild(nodeToReplace.getRightChild());
        }
        return replacement;
    }

}
