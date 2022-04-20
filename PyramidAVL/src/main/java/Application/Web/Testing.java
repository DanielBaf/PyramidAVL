/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
