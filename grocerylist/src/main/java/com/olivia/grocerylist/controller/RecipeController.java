package com.olivia.grocerylist.controller;

import com.olivia.grocerylist.request.AddRecipeRequest;
import com.olivia.grocerylist.request.UpdateRecipeRequest;
import com.olivia.grocerylist.request.GetRecipeRequest;
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

    @GetMapping("/get-recipe/{id}")
    public Optional<Recipe> getRecipe(@PathVariable Long id){
        return recipeService.getRecipe(id);
    }

    @GetMapping("/all")
    public List<GetRecipeRequest> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @PostMapping("/create-recipe")
    public Recipe createRecipe(@RequestBody AddRecipeRequest newRecipe) {
        return recipeService.saveRecipe(newRecipe);
    }

    @PutMapping("/update-recipe")
    public Recipe updateRecipe(@RequestBody UpdateRecipeRequest recipe) {
        return recipeService.updateRecipe(recipe);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable Long id) {
        return recipeService.deleteById(id);
    }
}
