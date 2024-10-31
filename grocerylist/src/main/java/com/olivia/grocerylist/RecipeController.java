package com.olivia.grocerylist;

import com.olivia.grocerylist.db.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {
    
    private final RecipeService recipeService;

    @GetMapping("/get-recipe")
    public ResponseEntity<Optional<Recipe>> getRecipe(Integer id){
        return ResponseEntity.ok().body(recipeService.getRecipe(id));
    }

    @PostMapping("/create-recipe")
    public ResponseEntity<Optional<Recipe>> createRecipe(@RequestBody AddRecipeResponse newRecipe) {
        return ResponseEntity.ok().body(recipeService.saveRecipe(newRecipe));
    }
}
