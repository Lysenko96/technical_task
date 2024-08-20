package technikal.task.fishmarket.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import technikal.task.fishmarket.models.Fish;
import technikal.task.fishmarket.models.FishDto;
import technikal.task.fishmarket.models.Role;
import technikal.task.fishmarket.models.User;
import technikal.task.fishmarket.services.FishService;

import java.util.List;

@Controller
@RequestMapping("/fish")
@RequiredArgsConstructor
public class FishController {

    private static final Logger log = LoggerFactory.getLogger(FishController.class);
    private final FishService fishService;

    @PreAuthorize(value = "hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping({"", "/"})
    public String showFishList(@AuthenticationPrincipal User user, Model model) {
        List<Fish> fishlist = fishService.findAll();
        model.addAttribute("fishlist", fishlist);
        Role role = user.getRoles().stream().filter(r -> r.equals(Role.ADMIN)).findFirst().orElse(Role.USER);
        model.addAttribute("role", role.name());
        return "index";
    }

    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @GetMapping("/create")
    public String showCreatePage(Model model) {
        FishDto fishDto = new FishDto();
        model.addAttribute("fishDto", fishDto);
        return "createFish";
    }

    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @GetMapping("/delete")
    public String deleteFish(@RequestParam int id) {
        fishService.deleteFish(id);
        return "redirect:/fish";
    }

    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @PostMapping("/create")
    public String addFish(@Valid @ModelAttribute FishDto fishDto, BindingResult result) {
        if (fishDto.getImageFiles().isEmpty()) {
            result.addError(new FieldError("fishDto", "imageFiles", "Потрібне фото рибки"));
        }
        if (result.hasErrors()) {
            return "createFish";
        }
        fishService.save(fishDto);
        return "redirect:/fish";
    }

}
