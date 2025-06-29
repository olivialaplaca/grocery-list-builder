package com.olivia.grocerylist;

import com.olivia.grocerylist.db.Recipe;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UpdateRecipeRequest {
    private Long recipeId;
    private String recipeName;
    private String servings;
    private Set<Recipe.MealCategory> mealCategories;
    private List<RecipeIngredientQuantity> recipeIngredients;
}
