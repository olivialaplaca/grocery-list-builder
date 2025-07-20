package com.olivia.grocerylist.service;

import com.olivia.grocerylist.AddRecipeRequest;
import com.olivia.grocerylist.RecipeIngredientQuantity;
import com.olivia.grocerylist.UpdateRecipeRequest;
import com.olivia.grocerylist.db.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        for (var item : newRecipe.getRecipeIngredients()) {
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

    public List<GetRecipeRequest> getAllRecipes() {
        var recipes = recipeRepository.findAll();
        var recipeList = new ArrayList<GetRecipeRequest>();
        for (var recipe : recipes) {
            var recipeToReturn = new GetRecipeRequest();
            recipeToReturn.setRecipeId(recipe.getRecipeId());
            recipeToReturn.setRecipeName(recipe.getName());
            recipeToReturn.setServings(recipe.getServings());
            var ingredientList = getIngredients(recipe);
            recipeToReturn.setRecipeIngredients(ingredientList);
            recipeList.add(recipeToReturn);
        }
        return recipeList;
    }

    private static ArrayList<RecipeIngredientQuantity> getIngredients(Recipe recipe) {
        var ingredientList = new ArrayList<RecipeIngredientQuantity>();
        for (var ingredient : recipe.getRecipeIngredients()) {
            var recipeIngredient = new RecipeIngredientQuantity();
            recipeIngredient.setIngredientId(ingredient.getIngredient().getIngredientId());
            recipeIngredient.setIngredientName(ingredient.getIngredient().getName());
            recipeIngredient.setQuantity(ingredient.getQuantity());
            recipeIngredient.setUnitOfMeasure(ingredient.getUnitOfMeasure());
            ingredientList.add(recipeIngredient);
        }
        return ingredientList;
    }

    @Transactional
    public Recipe updateRecipe(UpdateRecipeRequest recipe) {
        var recipeToUpdate = recipeRepository.findById(recipe.getRecipeId()).orElseThrow();
        recipeToUpdate.setName(recipe.getRecipeName());
        if (recipe.getServings() != null) {
            recipeToUpdate.setServings(Integer.valueOf(recipe.getServings()));
        }
        recipeRepository.save(recipeToUpdate);
        for (var item : recipe.getRecipeIngredients()) {
            if (item.getIngredientName() == null || item.getIngredientName().isEmpty() ) {
                continue;
            }
            var ingredient = ingredientRepository.findByName(item.getIngredientName());
            if (ingredient != null && recipeIngredientRepository.findByRecipeIngredientKey(recipeToUpdate.getRecipeId(),ingredient.getIngredientId()) != null) {
                var recipeIngredient = recipeIngredientRepository.findByRecipeIngredientKey(recipeToUpdate.getRecipeId(),ingredient.getIngredientId());
                recipeIngredient.setQuantity(item.getQuantity());
                recipeIngredient.setUnitOfMeasure(item.getUnitOfMeasure());
            } else if (ingredient != null) {
                var recipeIngredientRecord = new RecipeIngredient(new RecipeIngredientKey(), item.getQuantity(), item.getUnitOfMeasure());
                recipeIngredientRecord.setRecipe(recipeToUpdate);
                recipeIngredientRecord.setIngredient(ingredient);
                recipeIngredientRepository.save(recipeIngredientRecord);
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
        for (var oldIngredient : recipeToUpdate.getRecipeIngredients()) {
            var newIngredientList = recipe.getRecipeIngredients().stream().map(RecipeIngredientQuantity::getIngredientName).toList();
            if (!newIngredientList.contains(oldIngredient.getIngredient().getName())) {
                recipeIngredientRepository.deleteById(oldIngredient.getId());
            }
        }
        entityManager.flush();
        entityManager.refresh(recipeToUpdate);
        return recipeToUpdate;
    }

    public String deleteById(Long id) {
        var recipeToDelete = recipeRepository.findById(id);
        if (recipeToDelete.isPresent()) {
            var ingredientsToDelete = recipeToDelete.get().getRecipeIngredients();
            for (var ingredient : ingredientsToDelete) {
                recipeIngredientRepository.deleteById(ingredient.getId());
            }
        }
        recipeRepository.deleteById(id);
        return "recipe successfully deleted";
    }
}
