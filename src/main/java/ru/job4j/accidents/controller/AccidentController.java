package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;

@Controller
@ThreadSafe
@AllArgsConstructor
public class AccidentController {
    private final AccidentService accidentService;

    @PostMapping("/editAccident")
    public String update(@ModelAttribute Accident accident) {
        accidentService.update(accident);
        return "redirect:/accidents";
    }

    @GetMapping("/accidents")
    public String accidents(Model model) {
        model.addAttribute("user", "Alex");
        model.addAttribute("accidents", accidentService.findAll());
        return "accidents";
    }

    @GetMapping("/addAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("user", "Alex");
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident) {
        accidentService.add(accident);
        return "redirect:/index";
    }

    @GetMapping("/updateAccident")
    public String viewUpdateAccident(Model model) {
        model.addAttribute("user", "Alex");
        return "editAccident";
    }


}