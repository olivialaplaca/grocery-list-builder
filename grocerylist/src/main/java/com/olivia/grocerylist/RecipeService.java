package com.olivia.grocerylist;

import com.olivia.grocerylist.db.Ingredient;
import com.olivia.grocerylist.db.Recipe;
import com.olivia.grocerylist.db.RecipeIngredient;
import com.olivia.grocerylist.db.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeService {

    private RecipeRepository recipeRepository;

    public Recipe saveRecipe(AddRecipeResponse newRecipe){
        //set recipe
        var recipeRecord = new Recipe();
        recipeRecord.setName(newRecipe.getRecipeName());
        //set ingredients
        for (var item : newRecipe.getIngredientList()) {
            var newIngredient = new Ingredient();
            newIngredient.setName(item.getIngredientName());
            newIngredient.setPackageSize(item.getPackageSize());
            var newRecipeIngredient = new RecipeIngredient();
            newRecipeIngredient.setId();
        }
        //tie ingredients to recipe with recipe quantity
    }

    public Optional<Recipe> getRecipe(Integer id) {
        return recipeRepository.findById(id);
    }
}
