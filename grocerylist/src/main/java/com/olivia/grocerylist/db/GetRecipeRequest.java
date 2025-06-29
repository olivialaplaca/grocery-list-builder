package com.olivia.grocerylist.db;

import com.olivia.grocerylist.RecipeIngredientQuantity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class GetRecipeRequest {
    @Id
    private Long recipeId;
    private String recipeName;
    private Integer servings;
    private Set<Recipe.MealCategory> mealCategories;
    private List<RecipeIngredientQuantity> recipeIngredients;
}
