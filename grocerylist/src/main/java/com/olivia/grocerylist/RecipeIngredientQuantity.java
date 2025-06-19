package com.olivia.grocerylist;

import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class RecipeIngredientQuantity {
    private Long ingredientId;
    private String ingredientName;
    private String quantity;
    private String unitOfMeasure;
}
