package com.olivia.grocerylist.controller;

import com.olivia.grocerylist.IngredientService;
import com.olivia.grocerylist.db.Ingredient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController (IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/list")
    public String listIngredients(Model theModel) {
        //get ingredients from db
        var ingredientList = ingredientService.getIngredientList();

        //add to the spring model
        theModel.addAttribute("ingredients", ingredientList);

        //return name of view page
        return "list-ingredients";
    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model theModel) {
        var newIngredient = new Ingredient();
        theModel.addAttribute("ingredient", newIngredient);
        return "ingredient-form";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(@RequestParam("ingredientId") Long id, Model theModel) {
        var ingredient = ingredientService.getIngredientById(id);
        theModel.addAttribute("ingredient", ingredient);
        return "ingredient-form";
    }

    @PostMapping ("/save")
    public String saveIngredient(@ModelAttribute("ingredient") Ingredient ingredient) {
        ingredientService.saveIngredient(ingredient);
        return "redirect:/ingredients/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("ingredientId") Long id) {
        ingredientService.deleteById(id);
        return "redirect:/ingredients/list";
    }
}
