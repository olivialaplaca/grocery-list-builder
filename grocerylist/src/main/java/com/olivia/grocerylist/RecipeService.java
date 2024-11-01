package com.olivia.grocerylist;

import com.olivia.grocerylist.db.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RecipeService {

    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;
    private RecipeIngredientRepository recipeIngredientRepository;
    private EntityManager entityManager;

    @Transactional
    public Recipe saveRecipe(AddRecipeRequest newRecipe){
        var recipe = new Recipe();
        recipe.setName(newRecipe.getRecipeName());
        recipeRepository.save(recipe);
        for (var item : newRecipe.getIngredientList()) {
            var ingredient = ingredientRepository.findByName(item.getIngredientName());
            //create recipeIngredient record in db
            var recipeIngredientRecord = new RecipeIngredient(new RecipeIngredientKey(),item.getQuantity());
            recipeIngredientRecord.setRecipe(recipe);
            recipeIngredientRecord.setIngredient(ingredient);
            recipeIngredientRepository.save(recipeIngredientRecord);
        }
        entityManager.flush();
        entityManager.refresh(recipe);
        return recipe;
    }

    public Optional<Recipe> getRecipe(Long id) {
        return recipeRepository.findById(id);
    }
}
