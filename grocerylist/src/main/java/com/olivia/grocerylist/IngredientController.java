package com.olivia.grocerylist;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
