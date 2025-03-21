package com.olivia.grocerylist;

import lombok.Data;

@Data
public class RecipeIngredientQuantity {
    private String ingredientName;
    private String quantity;
    private String unitOfMeasure;
}
