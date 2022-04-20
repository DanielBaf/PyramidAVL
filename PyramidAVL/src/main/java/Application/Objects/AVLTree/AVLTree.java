/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Objects.AVLTree;

import Application.Objects.Cards.PokerCard;
import lombok.Data;

/**
 *
 * @author jefemayoneso
 */
@Data
public class AVLTree {

    private AVLNode root;// main node

    // search
    public AVLNode search(PokerCard data, AVLNode toFind) {
        if (toFind == null) {
            return null;
        } else if (toFind.getData().equals(data)) {
            return toFind;
        } else if (toFind.getData().getValue() < data.getValue()) {
            return search(data, toFind.getRightChild());
        } else {
            return search(data, toFind.getRightChild());
        }
    }

    // get equilibrium factor
    public int getEF(AVLNode node) {
        if (node == null) {
            return -1;
        } else {
            return node.getEquilibriumFactor();
        }
    }

    // simple rotation left
    public AVLNode flipLeft(AVLNode node) {
        AVLNode aux = node.getLeftChild(); // data recover
        node.setLeftChild(aux.getRightChild());
        aux.setRightChild(node); // switch
        node.setEquilibriumFactor(Math.max(getEF(node.getLeftChild()), getEF(node.getRightChild())) + 1); // update equilibrium factor
        aux.setEquilibriumFactor(Math.max(getEF(aux.getLeftChild()), getEF(aux.getRightChild())) + 1); // update equilibrium factor
        return aux; // return switched value
    }

    // simple rotation right
    public AVLNode flipRight(AVLNode node) {
        AVLNode aux = node.getRightChild(); // data recover
        node.setRightChild(aux.getLeftChild());
        aux.setLeftChild(node); // switch
        node.setEquilibriumFactor(Math.max(getEF(node.getLeftChild()), getEF(node.getRightChild())) + 1); // update equilibrium factor
        aux.setEquilibriumFactor(Math.max(getEF(aux.getLeftChild()), getEF(aux.getRightChild())) + 1); // update equilibrium factor
        return aux; // return switched value
    }

    // double rotation left
    public AVLNode doubleFlipLeft(AVLNode node) {
        AVLNode aux;
        node.setLeftChild(flipRight(node.getLeftChild())); // flip with previous method
        aux = flipRight(node);
        return aux;
    }

    // double rotation right
    public AVLNode doubleFlipRight(AVLNode node) {
        AVLNode aux;
        node.setRightChild(flipLeft(node.getRightChild()));
        aux = flipLeft(node);
        return aux;
    }
}
