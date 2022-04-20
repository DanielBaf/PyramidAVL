/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Objects.AVLTree;

import java.util.ArrayList;

/**
 *
 * @author jefemayoneso
 */
public class AVLTreeB<T> {
    
    AVLNode<T> root;
    
    public AVLTreeB() {
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
     * Insert a new AVL node
     *
     * @param newNode
     * @param subTree
     * @return
     */
    public AVLNode<T> insertAVL(AVLNode<T> newNode, AVLNode<T> subTree) {
        AVLNode<T> newParent = subTree;
        if (newNode.getValue() < subTree.getValue()) {
            if (subTree.getLeftChild() == null) {
                subTree.setLeftChild(newNode);
            } else {
                subTree.setLeftChild(insertAVL(newNode, subTree.getLeftChild()));
                if ((getEF(subTree.getLeftChild()) - getEF(subTree.getRightChild()) == 2)) {
                    if (newNode.getValue() < subTree.getLeftChild().getValue()) {
                        newParent = flipLeft(subTree);
                    } else {
                        newParent = doubleFlipLeft(subTree);
                    }
                }
            }
        } else if (newNode.getValue() > subTree.getValue()) {
            if (subTree.getRightChild() == null) {
                subTree.setRightChild(newNode);
            } else {
                subTree.setRightChild(insertAVL(newNode, subTree.getRightChild()));
                if ((getEF(subTree.getRightChild()) - getEF(subTree.getLeftChild()) == 2)) {
                    if (newNode.getValue() > subTree.getRightChild().getValue()) {
                        newParent = flipRight(subTree);
                    } else {
                        newParent = doubleFlipRight(subTree);
                    }
                }
            }
        } else {
            System.out.println("Error, node already exists");
        }
        //actualizar altura
        if ((subTree.getLeftChild() == null) && (subTree.getRightChild() != null)) {
            subTree.setEf(subTree.getRightChild().getEf() + 1);
        } else if ((subTree.getRightChild() == null) && (subTree.getLeftChild() != null)) {
            subTree.setEf(subTree.getLeftChild().getEf() + 1);
        } else {
            subTree.setEf(Math.max(getEF(subTree.getLeftChild()), getEF(subTree.getRightChild())) + 1);
        }
        
        return newParent;
    }
    
    public void insert(int value, T data) {
        AVLNode<T> toINsert = new AVLNode<>(value, data);
        if (this.root == null) {
            this.root = toINsert;
        } else {
            this.root = insertAVL(toINsert, this.root);
        }
    }

    // PRINTING
    private void inOrden(AVLNode<T> current) {
        if (current != null) {
            inOrden(current.getLeftChild());
//            lines.add("I: " + current.getValue() + " D: " + current.getData().toString());
            System.out.println("I: " + current.getValue() + " D: " + current.getData().toString());
            inOrden(current.getRightChild());
        }
    }

    //recorrer en preorden
    private void preorden(AVLNode<T> current, ArrayList<String> lines) {
        if (current != null) {
            lines.add("I: " + current.getValue() + " D: " + current.getData().toString());
            preorden(current.getLeftChild(), lines);
            preorden(current.getRightChild(), lines);
        }
    }

    //recorrer postorden
    private void postOrden(AVLNode<T> current, ArrayList<String> lines) {
        if (current != null) {
            postOrden(current.getLeftChild(), lines);
            postOrden(current.getRightChild(), lines);
            lines.add("I: " + current.getValue() + " D: " + current.getData().toString());
        }
    }
    
    public void print() {
        inOrden(this.root);
    }
}
