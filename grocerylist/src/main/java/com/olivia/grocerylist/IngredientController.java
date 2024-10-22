package com.olivia.grocerylist;

import com.olivia.grocerylist.db.Ingredient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IngredientController {

    private IngredientService ingredientService;

    public IngredientController (IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/get-ingredients")
    public ResponseEntity<List<Ingredient>> getIngredientList() {
        return ResponseEntity.ok().body(ingredientService.getIngredientList());
    }

    @PostMapping ("/create-ingredient")
    public ResponseEntity<Ingredient> saveIngredient(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok().body(ingredientService.saveIngredient(ingredient));
    }
}
