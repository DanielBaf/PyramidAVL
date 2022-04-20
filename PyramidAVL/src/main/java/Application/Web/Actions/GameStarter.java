package Application.Web.Actions;

import Application.Objects.AVLTree.AVLTree;
import Application.Objects.Cards.CardType;
import Application.Objects.Cards.PokerCard;

/**
 *
 * @author jefemayoneso
 */
public class GameStarter {

    public String create() {
        // create tree
        AVLTree<PokerCard> tree = new AVLTree<>();
        tree.insert(2, new PokerCard("J♥", 11, CardType.HEARTS));
        tree.insert(3, new PokerCard("K♠", 13, CardType.SPADES));
        tree.insert(4, new PokerCard("2♣", 2, CardType.CLUBS));
        tree.insert(5, new PokerCard("7♦", 7, CardType.DIAMONDS));
        tree.insert(0, new PokerCard("4♣", 4, CardType.SPADES));
        tree.insert(1, new PokerCard("10♣", 10, CardType.CLUBS));
        tree.insert(6, new PokerCard("1♦", 1, CardType.SPADES));
        // print
        return tree.print(0) + tree.print(1) + tree.print(2);
    }
}
