package Application.Web.Actions;

import Application.JSON.JsonReader;
import Application.Objects.AVLTree.AVLTree;
import Application.Objects.Cards.CardType;
import Application.Objects.Cards.PokerCard;
import Application.Web.Exceptions.ApiRequestException;
import java.util.HashMap;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 *
 * @author jefemayoneso
 */
@Data
public class GameActioner {

    private final JsonReader jsonReader;
    private final AVLTree<PokerCard> gameTree;

    public GameActioner() {
        this.jsonReader = new JsonReader();
        // create tree
        this.gameTree = new AVLTree<>();
    }

    /**
     * Create a new AVL tree with the initial cards
     *
     * @param json
     */
    public void createGame(String json) {
        // READ JSON
        HashMap<String, String> nodes = this.jsonReader.getJsonAsHash(json);
        // create objects and insert each new node
        nodes.keySet().forEach(name -> {
            try {
                String value = nodes.get(name);
                this.gameTree.insertCheckDataRepeated(Integer.valueOf(name),
                        createCard(
                                value.substring(0, value.length() - 1), getTypeFromString(value.substring(value.length() - 1,
                                value.length()))));
            } catch (NumberFormatException e) {
                throw new ApiRequestException("Valor invalido en JSON, llave=" + name + " valor=" + nodes.get(name), HttpStatus.BAD_REQUEST); // status 400
            }
        });
        // throw status OK
        throw new ApiRequestException("Game saved", HttpStatus.OK);
    }

    public void insertCard(String json) {
        // get data of insert
        HashMap<String, String> data = this.jsonReader.getJsonAsHash(json);
        // insert new data
        data.keySet().forEach(name -> {
            try {
                // get data
                String value = data.get(name);
                this.gameTree.insertCheckDataRepeated(this.gameTree.getHighestItem() + 1, createCard(
                        value.substring(0, value.length() - 1), getTypeFromString(value.substring(value.length() - 1,
                        value.length()))));
            } catch (ApiRequestException ex) {
                throw ex;
            } catch (Exception e) {
                throw new ApiRequestException("Valor invalido en JSON, llave=" + name + " valor=" + data.get(name), HttpStatus.BAD_REQUEST); // status 400
            }
        });
        // OK status
        throw new ApiRequestException("Card has been added...", HttpStatus.OK);
    }

    /**
     * Create a new Poker Card based on basic data
     *
     * @param value
     * @param cardType
     * @return
     */
    private PokerCard createCard(String value, CardType cardType) {
        PokerCard card;
        int val;
        // calc value of card
        switch (value) {
            case "K":
            case "k":
                val = 13;
                break;
            case "Q":
            case "q":
                val = 12;
                break;
            case "J":
            case "j":
                val = 11;
                break;
            default: // integer
                val = Integer.valueOf(value.trim());
        }
        if (val < 14 && val > 0) {
            card = new PokerCard(value.toUpperCase() + cardType.getIcon(), val, cardType);
            return card;
        } else {
            throw new ApiRequestException("El valor de la carte debe estar en el rango [1,10] o {J,Q,K}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get the card type from icon
     *
     * @param icon
     * @return
     */
    private CardType getTypeFromString(String icon) {
        try {
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
        } catch (Exception e) {
            throw new ApiRequestException("Tipo de carta invalido: " + icon, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
