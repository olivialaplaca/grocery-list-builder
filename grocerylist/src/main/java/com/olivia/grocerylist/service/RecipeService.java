package com.olivia.grocerylist.service;

import com.olivia.grocerylist.AddRecipeRequest;
import com.olivia.grocerylist.UpdateRecipeRequest;
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
        recipe.setServings(Integer.valueOf(newRecipe.getServings()));
        recipeRepository.save(recipe);
        for (var item : newRecipe.getIngredientList()) {
            var ingredient = ingredientRepository.findByName(item.getIngredientName());
            if (ingredient != null) {
                var recipeIngredientRecord = new RecipeIngredient(new RecipeIngredientKey(), item.getQuantity(), item.getUnitOfMeasure());
                recipeIngredientRecord.setRecipe(recipe);
                recipeIngredientRecord.setIngredient(ingredient);
                recipeIngredientRepository.save(recipeIngredientRecord);
            } else {
                var newIngredient = new Ingredient();
                newIngredient.setName(item.getIngredientName());
                ingredientRepository.save(newIngredient);

                var recipeIngredientRecord = new RecipeIngredient(new RecipeIngredientKey(), item.getQuantity(), item.getUnitOfMeasure());
                recipeIngredientRecord.setRecipe(recipe);
                recipeIngredientRecord.setIngredient(newIngredient);
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

    @Transactional
    public Recipe updateRecipe(UpdateRecipeRequest recipe) {
        var recipeToUpdate = recipeRepository.findById(recipe.getRecipeId()).orElseThrow();
        recipeToUpdate.setName(recipe.getRecipeName());
        recipeToUpdate.setServings(Integer.valueOf(recipe.getServings()));
        recipeRepository.save(recipeToUpdate);
        for (var item : recipe.getIngredientList()) {
            var ingredient = ingredientRepository.findByName(item.getIngredientName());
            if (ingredient != null) {
                var recipeIngredient = recipeIngredientRepository.findByRecipeIngredientKey(recipeToUpdate.getRecipeId(),ingredient.getIngredientId());
                recipeIngredient.setQuantity(item.getQuantity());
                recipeIngredient.setUnitOfMeasure(item.getUnitOfMeasure());
            } else {
                var newIngredient = new Ingredient();
                newIngredient.setName(item.getIngredientName());
                ingredientRepository.save(newIngredient);

                var recipeIngredientRecord = new RecipeIngredient(new RecipeIngredientKey(), item.getQuantity(), item.getUnitOfMeasure());
                recipeIngredientRecord.setRecipe(recipeToUpdate);
                recipeIngredientRecord.setIngredient(newIngredient);
                recipeIngredientRepository.save(recipeIngredientRecord);
            }
        }
        entityManager.flush();
        entityManager.refresh(recipeToUpdate);
        return recipeToUpdate;
    }
}
