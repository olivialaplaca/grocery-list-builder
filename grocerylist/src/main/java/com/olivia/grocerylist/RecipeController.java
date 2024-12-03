package com.olivia.grocerylist;

import com.olivia.grocerylist.db.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {
    
    private final RecipeService recipeService;

    @GetMapping("/get-recipe")
    public ResponseEntity<Optional<Recipe>> getRecipe(Long id){
        return ResponseEntity.ok().body(recipeService.getRecipe(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return ResponseEntity.ok().body(recipeService.getAllRecipes());
    }

    @PostMapping("/create-recipe")
    public ResponseEntity<Recipe> createRecipe(@RequestBody AddRecipeRequest newRecipe) {
        return ResponseEntity.ok().body(recipeService.saveRecipe(newRecipe));
    }
}
