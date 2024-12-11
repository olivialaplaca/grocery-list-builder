package com.olivia.grocerylist.controller;

import com.olivia.grocerylist.IngredientService;
import com.olivia.grocerylist.db.Ingredient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

//    @GetMapping("/get-ingredients")
//    public ResponseEntity<List<Ingredient>> getIngredientList() {
//        return ResponseEntity.ok().body(ingredientService.getIngredientList());
//    }
//
//    @PostMapping ("/create-ingredient")
//    public ResponseEntity<Ingredient> saveIngredient(@RequestBody Ingredient ingredient) {
//        return ResponseEntity.ok().body(ingredientService.saveIngredient(ingredient));
//    }
}
