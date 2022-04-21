package Application.Web;

import Application.Objects.AVLTree.AVLTree;
import Application.Objects.Cards.PokerCard;
import Application.Web.Actions.GameActioner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jefemayoneso
 */
@RestController
@Slf4j
public class Game {

    AVLTree<PokerCard> game;

    public Game() {
        this.game = null;
    }

    @RequestMapping(value = "/Game/start", method = RequestMethod.POST)
    public String start(String json) {
        log.info("Starting game... ");
        // create AVLTree
        this.game = new GameActioner().createGame(json);
        // check tree and send response
        return "Game saved...\n" + this.game.getAvlTree(1);
    }

    @GetMapping("/Game/add")
    public String add() {
        log.info("adding to tree");
        return "add";
    }

    @GetMapping("/Game/delete")
    public String delete() {
        log.info("deleting from tree");
        return "delete";
    }

    @GetMapping("/Game/status-avltree")
    public String getStatus() {
        log.info("getting AVL tree info");
        return "get avl";
    }

    @GetMapping("Game/get-level")
    public String getLevel(@RequestParam(name = "level") int level) {
        log.info("getting level data, at: " + level);
        return "get level";
    }
}
