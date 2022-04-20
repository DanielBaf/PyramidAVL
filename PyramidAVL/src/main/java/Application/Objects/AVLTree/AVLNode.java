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
public class AVLNode {

    private int equilibriumFactor;
    private PokerCard data;
    // children
    private AVLNode leftChild, rightChild;

    AVLNode(PokerCard data) {
        this.data = data;
        this.equilibriumFactor = 0;
        this.leftChild = this.rightChild = null;
    }

}
