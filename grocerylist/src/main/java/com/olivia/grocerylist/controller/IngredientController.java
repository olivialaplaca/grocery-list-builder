package com.olivia.grocerylist.controller;

import com.olivia.grocerylist.IngredientService;
import com.olivia.grocerylist.db.Ingredient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping ("/save")
    public String saveIngredient(@ModelAttribute("ingredient") Ingredient ingredient) {
        ingredientService.saveIngredient(ingredient);
        return "redirect:/ingredients/list";
    }
}
