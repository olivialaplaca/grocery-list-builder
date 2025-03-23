package com.olivia.grocerylist;

import com.olivia.grocerylist.db.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final EntityManager entityManager;

    public RecipeService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, RecipeIngredientRepository recipeIngredientRepository, EntityManager entityManager) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.entityManager = entityManager;
    }

    @Transactional
    public Recipe saveRecipe(AddRecipeRequest newRecipe){
        var recipe = new Recipe();
        recipe.setName(newRecipe.getRecipeName());
        recipeRepository.save(recipe);
        for (var item : newRecipe.getIngredientList()) {
            var ingredient = ingredientRepository.findByName(item.getIngredientName());
            if (ingredient != null) {
                var recipeIngredientRecord = new RecipeIngredient(new RecipeIngredientKey(), item.getQuantity(), item.getUnitOfMeasure());
                recipeIngredientRecord.setRecipe(recipe);
                recipeIngredientRecord.setIngredient(ingredient);
                recipeIngredientRepository.save(recipeIngredientRecord);
            }

        }
        entityManager.flush();
        entityManager.refresh(recipe);
        return recipe;
    }

    public Optional<Recipe> getRecipe(Long id) {
        return recipeRepository.findById(id);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }
}
