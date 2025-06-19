package com.olivia.grocerylist.db;

import com.olivia.grocerylist.RecipeIngredientQuantity;
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
    private String name;
    private Integer servings;
    private List<RecipeIngredientQuantity> recipeIngredients;
}
