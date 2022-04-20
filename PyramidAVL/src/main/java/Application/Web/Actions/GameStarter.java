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
        // create data
        PokerCard c1 = new PokerCard("10♣", 10, CardType.CLUBS);
        PokerCard c2 = new PokerCard("7♦", 7, CardType.DIAMONDS);
        PokerCard c3 = new PokerCard("J♥", 11, CardType.HEARTS);
        PokerCard c4 = new PokerCard("K♠", 13, CardType.SPADES);
        PokerCard c5 = new PokerCard("2♣", 2, CardType.CLUBS);
        // create tree
        AVLTree<PokerCard> tree = new AVLTree<>();
        tree.insert(1, c1);
        tree.insert(2, c3);
        tree.insert(3, c4);
        tree.insert(4, c5);
        tree.insert(5, c2);
        // print
        return tree.print(1);
    }
}
