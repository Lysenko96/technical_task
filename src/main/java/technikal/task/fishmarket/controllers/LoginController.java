package technikal.task.fishmarket.controllers;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@Getter
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
