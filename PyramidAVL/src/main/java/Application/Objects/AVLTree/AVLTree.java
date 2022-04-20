/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Objects.AVLTree;

import lombok.Data;

/**
 *
 * @author jefemayoneso
 */
@Data
public class AVLTree<T> {

    AVLNode<T> root;

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
        if (this.root == null) {
            return null;
        } else if (node.getValue() == value) {
            return node;
        } else if (node.getValue() < value) {
            return search(value, node.getRightChild());
        } else {
            return search(value, node.getLeftChild());
        }
    }

    public void insert(int value, T data) {
        AVLTreeInserter<T> inserter = new AVLTreeInserter<>();
        AVLNode<T> toInsert = new AVLNode<>(value, data);
        this.root = inserter.insert(toInsert, this.root);
    }

    public String print(int type) {
        AVLTreePrinter<T> printer = new AVLTreePrinter<>();
        return printer.print(type, this.root);
    }

}
