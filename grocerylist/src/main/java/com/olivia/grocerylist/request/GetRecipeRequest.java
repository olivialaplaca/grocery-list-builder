package com.olivia.grocerylist.request;

import com.olivia.grocerylist.db.RecipeIngredientQuantity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class GetRecipeRequest {
    @Id
    private Long recipeId;
    private String recipeName;
    private Integer servings;
    private List<RecipeIngredientQuantity> recipeIngredients;
}
