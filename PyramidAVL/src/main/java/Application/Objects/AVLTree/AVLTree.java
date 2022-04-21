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
        // check find or nulls
        if (this.root == null || node == null) {
            return null;
        } else if (node.getValue() == value) { // search when finding by data is false, use ID insted
            return node;
        } else if (node.getValue() < value) { // recursively
            return search(value, node.getRightChild());
        } else {
            return search(value, node.getLeftChild());
        }
    }

    public AVLNode<T> searchByData(T data, AVLNode<T> node) {
        if (this.root != null && node != null) {
            // check same
            if (node.getData().equals(data)) {
                return node;
            } else {
                // recursive call
                AVLNode<T> temp = searchByData(data, node.getLeftChild());
                if (temp != null) {
                    return temp;
                } else {
                    return searchByData(data, node.getRightChild());
                }
            }
        }
        return null;
    }

    /**
     * Insert a new node
     *
     * @param value
     * @param data
     */
    public void insert(int value, T data) {
        // check data is not declared previously
        AVLTreeInserter<T> inserter = new AVLTreeInserter<>();
        AVLNode<T> toInsert = new AVLNode<>(value, data);
        this.root = inserter.insert(toInsert, this.root);
    }

    /**
     * Insert a new node, but check if a OBject is previously repeated
     *
     * @param value
     * @param data
     */
    public void insertCheckDataRepeated(int value, T data) {
        if (searchByData(data, this.root) == null) {
            insert(value, data);
        }
    }

    /**
     * Return a String with all the info of the tree
     *
     * @param type
     * @return
     */
    public String getAvlTree(int type) {
        AVLTreePrinter<T> printer = new AVLTreePrinter<>();
        return printer.getData(type, this.root);
    }

}
