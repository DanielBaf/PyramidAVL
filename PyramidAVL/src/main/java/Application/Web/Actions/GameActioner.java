package Application.Web.Actions;

import Application.JSON.JsonReader;
import Application.Objects.AVLTree.AVLNode;
import Application.Objects.AVLTree.AVLTree;
import Application.Objects.Cards.PokerCard;
import Application.Utilities.CardGenerator;
import Application.Web.Exceptions.ApiRequestException;
import java.util.ArrayList;
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
        CardGenerator cardGenerator = new CardGenerator();
        HashMap<String, String> nodes = this.jsonReader.getJsonAsHash(json);
        // create objects and insert each new node
        nodes.keySet().forEach(name -> {
            try {
                String value = nodes.get(name);
                PokerCard card = cardGenerator.createCard(
                        value.substring(0, value.length() - 1), cardGenerator.getTypeFromString(value.substring(value.length() - 1,
                        value.length())));
                this.gameTree.insertCheckDataRepeated(card.getValue() + card.getType().getDisplacement(), card);
            } catch (NumberFormatException e) {
                throw new ApiRequestException("Valor invalido en JSON, llave=" + name + " valor=" + nodes.get(name), HttpStatus.BAD_REQUEST); // status 400
            }
        });
        // throw status OK
        throw new ApiRequestException("Game saved", HttpStatus.OK);
    }

    /**
     * Insert a new card to the tree
     *
     * @param json
     */
    public void insertCard(String json) {
        // get data of insert
        HashMap<String, String> data = this.jsonReader.getJsonAsHash(json);
        CardGenerator cardGenerator = new CardGenerator();
        // insert new data
        data.keySet().forEach(name -> {
            try {
                // get data
                String value = data.get(name);
                PokerCard card = cardGenerator.createCard(
                        value.substring(0, value.length() - 1), cardGenerator.getTypeFromString(value.substring(value.length() - 1,
                        value.length())));
                this.gameTree.insertCheckDataRepeated(card.getValue() + card.getType().getDisplacement(), card);
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
     * Return all nodes at level "k" as JSON
     *
     * @param level
     * @return
     */
    public String getNodesAtLevel(int level) {
        ArrayList<String> body = new ArrayList<>();
        this.gameTree.getNodesAtLevel(this.gameTree.getRoot(), 1, level, body);
        String result = "";
        result = body.stream().map(string -> string + "\n").reduce(result, String::concat);
        return "{\n" + result + "}";
    }

    public void deleteNodes(String json) {
        HashMap<String, String> data = this.jsonReader.getJsonAsHash(json);
        AVLNode<PokerCard> toDelete1 = null, toDelete2 = null;
        CardGenerator cardGenerator = new CardGenerator();
        // get nodes
        try {
            if (data.size() <= 2 && data.size() > 0) {
                toDelete1 = this.gameTree.searchByData(cardGenerator.createCard(data.get("delete_1").substring(0, data.get("delete_1").length() - 1), cardGenerator.getTypeFromString(data.get("delete_1").substring(data.get("delete_1").length() - 1))), this.gameTree.getRoot());
                toDelete2 = data.size() == 2 ? this.gameTree.searchByData(cardGenerator.createCard(data.get("delete_2").substring(0, data.get("delete_2").length() - 1), cardGenerator.getTypeFromString(data.get("delete_2").substring(data.get("delete_2").length() - 1))), this.gameTree.getRoot()) : null;
            } else {
                throw new ApiRequestException("El numero de instrucciones \"delete\" es invalido", HttpStatus.BAD_REQUEST);
            }
            // check all data is valid
            isValidForDelete(toDelete1, toDelete2, data.size());
        } catch (ApiRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage() + " revisa el JSON", HttpStatus.BAD_REQUEST);
        }
    }

    private void isValidForDelete(AVLNode<PokerCard> toDelete, AVLNode<PokerCard> toDelete2, int expectedValidCards) {
        if ((expectedValidCards == 1 && toDelete == null) || (expectedValidCards == 2 && (toDelete == null || toDelete2 == null))) { // check inst1 'cause this has to be send always
            throw new ApiRequestException("Card(s) not found", HttpStatus.NOT_FOUND);
        } else {
            // check sum of nodes
            // check the sum is valid
            int sum = toDelete.getData().getValue();
            sum += toDelete2 != null ? toDelete2.getData().getValue() : 0;
            System.out.println("sum: " + sum);
            if (sum != 13) {
                throw new ApiRequestException("La suma de las cartas a borrar no es 13", HttpStatus.NOT_ACCEPTABLE);
            } else {
                // check has no sons
                if ((toDelete.getRightChild() != null || toDelete.getLeftChild() != null) || (toDelete2 != null && (toDelete2.getRightChild() != null || toDelete2.getLeftChild() != null))) {
                    throw new ApiRequestException("Uno de los nodos tiene hijos", HttpStatus.CONFLICT);
                } else {
                    // TODO delete from tree
                    this.gameTree.delete(toDelete, toDelete2);
                    throw new ApiRequestException("Card(s) " + toDelete.getData() + " " + (toDelete2 != null ? toDelete2.getData() : "") + " has been deleted", HttpStatus.OK);
                }
            }
        }
    }
}
