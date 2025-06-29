package com.olivia.grocerylist;

import com.olivia.grocerylist.db.Recipe;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class AddRecipeRequest {
    private String recipeName;
    private String servings;
    private Set<Recipe.MealCategory> mealCategories;
    private List<RecipeIngredientQuantity> recipeIngredients;
}
