package com.olivia.grocerylist;

import com.olivia.grocerylist.db.Recipe;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController("/recipe")
public class RecipeController {
    
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    
    @GetMapping("/get-recipe")
    public ResponseEntity<Optional<Recipe>> getRecipe(Integer id){
        return ResponseEntity.ok().body(recipeService.getRecipe(id));
    }
}
