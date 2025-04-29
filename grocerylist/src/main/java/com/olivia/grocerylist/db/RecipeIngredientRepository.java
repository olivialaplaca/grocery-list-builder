package com.olivia.grocerylist.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, RecipeIngredientKey> {

    @NativeQuery("select * from recipe_ingredient where recipe_id = ?1 and ingredient_id = ?2")
    RecipeIngredient findByRecipeIngredientKey(Long recipeId, Long ingredientId);
}
