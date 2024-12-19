package mk.ukim.finki.wp_aud1.web.controller;

import mk.ukim.finki.wp_aud1.model.enumerations.Role;
import mk.ukim.finki.wp_aud1.model.exceptions.InvalidArgumentException;
import mk.ukim.finki.wp_aud1.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.wp_aud1.service.AuthService;
import mk.ukim.finki.wp_aud1.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController( UserService userService) {
        this.userService = userService;

    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model){
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);

        }
        model.addAttribute("bodyContent","register");
        return "master-template";
    }
    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                            @RequestParam Role role){

        try{
            this.userService.register(username,password,repeatPassword,name,surname,role);
            return "redirect:/login";
        } catch (InvalidArgumentException | PasswordsDoNotMatchException ex){
            return "redirect:/register?error=" + ex.getMessage();
        }
    }

}
