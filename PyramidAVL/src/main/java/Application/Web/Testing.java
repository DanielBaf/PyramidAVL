package Application.Web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author jefemayoneso
 */
@Controller
public class Testing {
    
    @GetMapping("/")
    public String main() {
        return "index";
    }
}
