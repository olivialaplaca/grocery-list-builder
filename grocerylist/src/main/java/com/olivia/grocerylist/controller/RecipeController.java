package com.olivia.grocerylist.controller;

import com.olivia.grocerylist.AddRecipeRequest;
import com.olivia.grocerylist.RecipeService;
import com.olivia.grocerylist.db.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {
    
    private final RecipeService recipeService;

    @GetMapping("/get-recipe")
    public ResponseEntity<Optional<Recipe>> getRecipe(Long id){
        return ResponseEntity.ok().body(recipeService.getRecipe(id));
    }

    @GetMapping("/list")
    public String listIngredients(Model theModel) {
        var recipeList = recipeService.getAllRecipes();
        theModel.addAttribute("recipes", recipeList);
        return "list-recipes";
    }
//    public ResponseEntity<List<Recipe>> getAllRecipes() {
//        return ResponseEntity.ok().body(recipeService.getAllRecipes());
//    }

    @PostMapping("/create-recipe")
    public ResponseEntity<Recipe> createRecipe(@RequestBody AddRecipeRequest newRecipe) {
        return ResponseEntity.ok().body(recipeService.saveRecipe(newRecipe));
    }
}
