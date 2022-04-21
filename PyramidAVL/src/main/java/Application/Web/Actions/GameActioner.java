package Application.Web.Actions;

import Application.Objects.AVLTree.AVLTree;
import Application.Objects.Cards.CardType;
import Application.Objects.Cards.PokerCard;

/**
 *
 * @author jefemayoneso
 */
public class GameActioner {

    public AVLTree<PokerCard> createGame() {

        try {
            // create tree
            AVLTree<PokerCard> tree = new AVLTree<>();
            tree.insertCheckDataRepeated(2, createCard("J", CardType.HEARTS));
            tree.insertCheckDataRepeated(3, createCard("K", CardType.SPADES));
            tree.insertCheckDataRepeated(4, createCard("2", CardType.CLUBS));
            tree.insertCheckDataRepeated(5, createCard("7", CardType.DIAMONDS));
            tree.insertCheckDataRepeated(0, createCard("4", CardType.SPADES));
            tree.insertCheckDataRepeated(1, createCard("10", CardType.CLUBS));
            tree.insertCheckDataRepeated(6, createCard("1", CardType.SPADES));
            // return tree
            return tree;
        } catch (Exception e) { // TODO validate status
            return null;
        }
    }

    private PokerCard createCard(String value, CardType cardType) {
        try {
            PokerCard card;
            int val;
            // calc value of card
            switch (value) {
                case "K":
                    val = 13;
                    break;
                case "Q":
                    val = 12;
                    break;
                case "J":
                    val = 11;
                    break;
                default: // integer
                    val = Integer.valueOf(value.trim());
            }
            card = new PokerCard(value + cardType.getIcon(), val, cardType);
            return card;
        } catch (NumberFormatException e) {
            System.out.println("Unable to create card with val: " + value + "... " + e.getMessage());
            return null;
        }
    }
}
