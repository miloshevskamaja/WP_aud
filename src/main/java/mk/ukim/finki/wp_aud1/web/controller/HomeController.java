package mk.ukim.finki.wp_aud1.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String getHomePage(Model model){

        model.addAttribute("bodyContent","home");
        return "master-template";
    }

    @GetMapping("/access-denied")
    public String getAccessDeniedPage(Model model){
        model.addAttribute("bodyContent","access-denied");
        return "master-template";
    }


}
