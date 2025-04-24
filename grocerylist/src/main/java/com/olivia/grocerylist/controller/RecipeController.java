package com.olivia.grocerylist.controller;

import com.olivia.grocerylist.AddRecipeRequest;
import com.olivia.grocerylist.service.RecipeService;
import com.olivia.grocerylist.db.Recipe;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/get-recipe")
    public Optional<Recipe> getRecipe(Long id){
        return recipeService.getRecipe(id);
    }

    @GetMapping("/all")
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @PostMapping("/create-recipe")
    public Recipe createRecipe(@RequestBody AddRecipeRequest newRecipe) {
        return recipeService.saveRecipe(newRecipe);
    }
}
