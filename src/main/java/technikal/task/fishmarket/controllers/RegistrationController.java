package technikal.task.fishmarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import technikal.task.fishmarket.models.RegistrationForm;
import technikal.task.fishmarket.services.UserService;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String processUser(RegistrationForm form) {
        userService.save(form.getUser());
        return "redirect:/login";
    }
}
