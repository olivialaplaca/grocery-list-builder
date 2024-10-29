package com.olivia.grocerylist;

import com.olivia.grocerylist.db.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeService {

    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;
    private RecipeIngredientRepository recipeIngredientRepository;

    public RecipeService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, RecipeIngredientRepository recipeIngredientRepository) {
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
    }

    public Recipe saveRecipe(AddRecipeResponse newRecipe){
        //set recipe
        var recipeRecord = new Recipe();
        recipeRecord.setName(newRecipe.getRecipeName());
        var recipe = recipeRepository.saveAndFlush(recipeRecord);
        //save ingredients to db
        for (var item : newRecipe.getIngredientList()) {
            var newIngredient = new Ingredient();
            newIngredient.setName(item.getIngredientName());
            newIngredient.setPackageSize(item.getPackageSize());
            var ingredientId = ingredientRepository.saveAndFlush(newIngredient).getIngredientId();
            var recipeIngredientKey = new RecipeIngredientKey(recipe.getRecipeId(), ingredientId);
            //create recipeIngredient record in db
            var recipeIngredientRecord = new RecipeIngredient(recipeIngredientKey,item.getQuantity());
            recipeIngredientRepository.saveAndFlush(recipeIngredientRecord);
        }
        return recipe;
    }

    public Optional<Recipe> getRecipe(Integer id) {
        return recipeRepository.findById(id);
    }
}
