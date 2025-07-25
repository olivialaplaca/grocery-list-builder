package com.olivia.grocerylist.request;

import com.olivia.grocerylist.db.RecipeIngredientQuantity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateRecipeRequest {
    private Long recipeId;
    private String recipeName;
    private String servings;
    private List<RecipeIngredientQuantity> recipeIngredients;
}
