package ca.sheridancollege.malhojay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ca.sheridancollege.malhojay.beans.Drink;

@Controller
@AllArgsConstructor
public class DrinkController {
    private DrinkRepository drinkRepo;

    @GetMapping("/")
    public String loadRoot() {
        return "home.html";
    }

    @GetMapping("/add")
    public String goAddDrinks(Model model) {
        model.addAttribute("drink", new Drink());
        return "addDrink.html";
    }

    @PostMapping("/add")
    public String processAddDrink(@ModelAttribute Drink drink, Model model) {
        drinkRepo.addDrink(drink);
        return "redirect:/add"; // Sends status code 302
    }

    @GetMapping("/view")
    public String viewDrinks(Model model) {
        model.addAttribute("drinks", drinkRepo.getDrinks());
        return "view.html";
    }

    @GetMapping("/editLink/{id}")
    public String editLink(Model model, @PathVariable int id) {
        Drink d = drinkRepo.getDrinkById(id);
        model.addAttribute("drink", d);
        return "editDrink.html";
    }

    @GetMapping("/modify")
    public String modifyDrink(@RequestParam int id, @RequestParam String name, Model model) {
        Drink d = new Drink(id, name);
        drinkRepo.updateDrink(d);
        model.addAttribute("myDrinks", drinkRepo.getDrinks());
        return "viewDrinks.html";
    }

    @GetMapping("/deletelink/{id}")
    public String deletePage(@PathVariable int id, Model model) {
        drinkRepo.deleteDrink(id);
        model.addAttribute("myDrinks", drinkRepo.getDrinks());
        return "viewDrinks.html";
    }
}
