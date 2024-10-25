package com.olivia.grocerylist;

import lombok.Data;

import java.util.List;

@Data
public class AddRecipeResponse {
    private String recipeName;
    private List<RecipeIngredientQuantity> ingredientList;
}
