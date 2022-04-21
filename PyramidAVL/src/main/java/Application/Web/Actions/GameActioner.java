package Application.Web.Actions;

import Application.JSON.JsonReader;
import Application.Objects.AVLTree.AVLTree;
import Application.Objects.Cards.CardType;
import Application.Objects.Cards.PokerCard;
import java.util.HashMap;

/**
 *
 * @author jefemayoneso
 */
public class GameActioner {

    private final JsonReader jsonReader;

    public GameActioner() {
        this.jsonReader = new JsonReader();
    }

    public AVLTree<PokerCard> createGame(String json) {

        try {
            // create tree
            AVLTree<PokerCard> tree = new AVLTree<>();

            // READ JSON
            HashMap<String, String> nodes = this.jsonReader.getJsonAsHash(json);
            // create objects and insert each new node
            nodes.keySet().forEach(name -> {
                try {
                    String value = nodes.get(name);
                    tree.insertCheckDataRepeated(Integer.valueOf(name), createCard(value.substring(0, value.length() - 1), getTypeFromString(value.substring(value.length() - 1, value.length()))));
                } catch (NumberFormatException e) {
                }
            });
            // return tree
            return tree;
        } catch (Exception e) { // TODO validate status
            return null;
        }
    }

    /**
     * Create a new Poker Card based on basic data
     *
     * @param value
     * @param cardType
     * @return
     */
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

    /**
     * Get the card type from icon
     *
     * @param icon
     * @return
     */
    private CardType getTypeFromString(String icon) {
        if (icon.equals(CardType.CLUBS.getIcon())) {
            return CardType.CLUBS;
        } else if (icon.equals(CardType.DIAMONDS.getIcon())) {
            return CardType.DIAMONDS;
        } else if (icon.equals(CardType.HEARTS.getIcon())) {
            return CardType.HEARTS;
        } else if (icon.equals(CardType.SPADES.getIcon())) {
            return CardType.SPADES;
        } else {
            return CardType.INVALID;
        }
    }
}
