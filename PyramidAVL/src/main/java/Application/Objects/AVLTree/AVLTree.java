/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Objects.AVLTree;

import Application.Web.Exceptions.ApiRequestException;
import java.util.ArrayList;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 *
 * @author jefemayoneso
 */
@Data
public class AVLTree<T> {

    private AVLNode<T> root;
    private int highestItem;

    public AVLTree() {
        this.root = null;
        this.highestItem = 0;
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
        // check highest val to insert
        this.highestItem = value > this.highestItem ? value : this.highestItem;
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
        } else {
            throw new ApiRequestException("La carta " + data + " ya ha sido insertada ", HttpStatus.NOT_ACCEPTABLE);
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

    /**
     * Search for all nodes into a specific level
     *
     * @param current
     * @return
     */
    public void getNodesAtLevel(AVLNode<T> current, int currentLevel, int levelMatch, ArrayList<String> lines) {
        // keep reading while current is not null
        if (current != null && !(currentLevel > levelMatch)) {
            if (currentLevel == levelMatch) {
                lines.add(String.format("\t\"%1$x\": \"%2$s\",", lines.size(), current.getData().toString()));
            } else {
                currentLevel++;
                getNodesAtLevel(current.getLeftChild(), currentLevel, levelMatch, lines);
                getNodesAtLevel(current.getRightChild(), currentLevel, levelMatch, lines);
            }
        }
    }

    public void delete(AVLNode<T>... toDelete) {
        AVLTreeRemover<T> remover = new AVLTreeRemover<>();
        for (AVLNode<T> aVLNode : toDelete) {
            try {
                if (aVLNode != null) {
                    remover.remove(aVLNode, this.root);
                }
            } catch (Exception e) {
                System.out.println("Exception removing node: " + e.getMessage());
            }
        }
    }
}
