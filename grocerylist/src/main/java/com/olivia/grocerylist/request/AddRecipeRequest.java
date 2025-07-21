package com.olivia.grocerylist.request;

import com.olivia.grocerylist.db.RecipeIngredientQuantity;
import lombok.Data;

import java.util.List;

@Data
public class AddRecipeRequest {
    private String recipeName;
    private String servings;
    private List<RecipeIngredientQuantity> recipeIngredients;
}
