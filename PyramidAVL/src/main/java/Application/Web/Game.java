package Application.Web;

import Application.Web.Actions.GameStarter;
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

    @RequestMapping(value = "/Game/start", method = RequestMethod.POST)
    public String start(String text) {
        log.info("Starting game...");
        // create object and return result
        GameStarter game = new GameStarter();
        //return text + "\n\n" + game.create();
        return game.create();
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
