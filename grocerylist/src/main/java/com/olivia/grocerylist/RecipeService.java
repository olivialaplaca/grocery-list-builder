package com.olivia.grocerylist;

import com.olivia.grocerylist.db.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RecipeService {

    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;
    private RecipeIngredientRepository recipeIngredientRepository;

    public Optional<Recipe> saveRecipe(AddRecipeResponse newRecipe){
        //set recipe
        var recipeRecord = new Recipe();
        recipeRecord.setName(newRecipe.getRecipeName());
        var recipe = recipeRepository.saveAndFlush(recipeRecord);
        //save ingredients to db sdkfjhes
        for (var item : newRecipe.getIngredientList()) {
            var newIngredient = new Ingredient();
            newIngredient.setName(item.getIngredientName());
            newIngredient.setPackageSize(item.getPackageSize());
            var ingredient = ingredientRepository.saveAndFlush(newIngredient);
            var recipeIngredientKey = new RecipeIngredientKey(recipe.getRecipeId(), ingredient.getIngredientId());
            //create recipeIngredient record in db
            var recipeIngredientRecord = new RecipeIngredient(recipeIngredientKey,item.getQuantity());
            recipeIngredientRecord.setRecipe(recipe);
            recipeIngredientRecord.setIngredient(ingredient);
            recipeIngredientRepository.saveAndFlush(recipeIngredientRecord);
        }
        var ret = recipeRepository.findById(recipe.getRecipeId());
    return ret;
    }

    public Optional<Recipe> getRecipe(Integer id) {
        return recipeRepository.findById(id);
    }
}
