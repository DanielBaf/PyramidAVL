/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Objects.Cards;

import lombok.Data;

/**
 *
 * @author jefemayoneso
 */
@Data
public class PokerCard {
    private String text;
    private int value;
    private CardType type;
}
