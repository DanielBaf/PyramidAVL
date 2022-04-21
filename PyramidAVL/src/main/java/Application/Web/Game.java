package Application.Web;

import Application.Web.Actions.GameActioner;
import Application.Web.Exceptions.ApiRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

    GameActioner actioner;

    public Game() {
        this.actioner = new GameActioner();
    }

    @RequestMapping(value = "/Game/start", method = RequestMethod.POST)
    public void start(String json) {
        log.info("Starting game... ");
        // create AVLTree and restart game object
        this.actioner = new GameActioner();
        this.actioner.createGame(json); // method throws a response status
    }

    @RequestMapping(value = "/Game/add", method = RequestMethod.POST)
    public void add(String json) {
        log.info("adding to tree");
        // check tree isn't null
        this.actioner.insertCard(json);
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

    @RequestMapping(value = "/Game/avltree", method = RequestMethod.GET)
    public String getAvlTree(String transversal) {
        switch (transversal.toLowerCase()) {
            case "inorder":
                return String.format("{\n%1$s}", this.actioner.getGameTree().getAvlTree(0));
            case "preorder":
                return String.format("{\n%1$s}", this.actioner.getGameTree().getAvlTree(1));
            case "postorder":
                return String.format("{\n%1$s}", this.actioner.getGameTree().getAvlTree(2));
            default:
                throw new ApiRequestException("Solicitud " + transversal + " no reconocdia", HttpStatus.NOT_ACCEPTABLE);

        }
    }

}
