package com.olivia.grocerylist.controller;

import com.olivia.grocerylist.db.Ingredient;
import com.olivia.grocerylist.service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/all")
    public List<Ingredient> getAllIngredients() {
        return ingredientService.getIngredientList();
    }

    @PostMapping ("/create-ingredient")
    public ResponseEntity<Ingredient> saveIngredient(@RequestBody Ingredient ingredient) throws Exception {
        if (ingredient.getName().isEmpty()) {
            throw new Exception("Ingredient name must not be empty");
        }
        return ResponseEntity.ok().body(ingredientService.saveIngredient(ingredient));
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("ingredientId") Long id) {
        ingredientService.deleteById(id);
        return "redirect:/ingredients/list";
    }
}
