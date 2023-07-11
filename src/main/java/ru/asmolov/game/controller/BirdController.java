package ru.asmolov.game.controller;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.asmolov.game.model.Bird;
import ru.asmolov.game.service.BirdService;

import java.util.List;

@Controller
@AllArgsConstructor
public class BirdController {

    private BirdService birdService;

    @GetMapping("/shop")
    public String showBirds(Model model) {
        List<Bird> birds = birdService.getAllBirds();
        model.addAttribute("birds", birds);
        return "shop";
    }
}