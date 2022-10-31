package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.AccidentTypeService;
import ru.job4j.accidents.service.RuleService;


@Controller
@ThreadSafe
@AllArgsConstructor
public class AccidentController {
    private final AccidentService accidentService;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    @GetMapping("/accidents")
    public String accidents(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext()
                                                .getAuthentication().getPrincipal())
            .addAttribute("accidents", accidentService.findAll());
        return "accidents";
    }

    @GetMapping("/addAccident")
    public String viewCreateAccident(Model model) {
        fillModel(model);
        return "createAccident";
    }

    @GetMapping("/formUpdateAccident")
    public String update(@RequestParam("id") int id, Model model) {
        fillModel(model).addAttribute("accident", accidentService.findById(id));
        return "editAccident";
    }

    @PostMapping("/updateAccident")
    public String update(@ModelAttribute Accident accident,
                         @RequestParam("type.id") int id,
                         HttpServletRequest req) {
        AccidentType type = accidentTypeService.findById(id);
        String[] ids = req.getParameterValues("rIds");
        Set<Rule> rules = new HashSet<>();
        for (String statId : ids) {
            rules.add(ruleService.findById(Integer.parseInt(statId)));
        }
        accident.setType(type);
        accident.setRules(rules);
        accidentService.update(accident);
        return "redirect:/accidents";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident,
                       @RequestParam("type.id") int id,
                       HttpServletRequest req) {
        AccidentType type = accidentTypeService.findById(id);
        String[] ids = req.getParameterValues("rIds");
        Set<Rule> rules = new HashSet<>();
        for (String statId : ids) {
            rules.add(ruleService.findById(Integer.parseInt(statId)));
        }
        accident.setType(type);
        accident.setRules(rules);
        accidentService.add(accident);
        return "redirect:/accidents";
    }

    private Model fillModel(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext()
                                                .getAuthentication().getPrincipal())
            .addAttribute("types", accidentTypeService.findAll())
            .addAttribute("rules", ruleService.findAll());
        return model;
    }
}