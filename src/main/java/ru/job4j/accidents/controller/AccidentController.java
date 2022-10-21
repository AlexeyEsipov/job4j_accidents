package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
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
        model.addAttribute("user", "Alex");
        model.addAttribute("accidents", accidentService.findAll());
        return "accidents";
    }

    @GetMapping("/addAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("user", "Alex");
        Collection<AccidentType> types = accidentTypeService.findAll();
        Collection<Rule> rules = ruleService.findAll();
        model.addAttribute("types", types);
        model.addAttribute("rules", rules);
        return "createAccident";
    }

    @GetMapping("/formUpdateAccident")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", "Alex");
        Collection<AccidentType> types = accidentTypeService.findAll();
        Collection<Rule> rules = ruleService.findAll();
        model.addAttribute("types", types);
        model.addAttribute("accident", accidentService.findById(id));
        model.addAttribute("rules", rules);
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
}