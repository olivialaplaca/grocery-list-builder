package com.olivia.grocerylist.db;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
public class RecipeIngredientKey implements Serializable {

    @Column(name = "recipe_id")
    Integer recipeId;

    @Column(name = "ingredient_id")
    Integer ingredientId;

    public RecipeIngredientKey(Integer recipeId, Integer ingredientId) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
    }
}
