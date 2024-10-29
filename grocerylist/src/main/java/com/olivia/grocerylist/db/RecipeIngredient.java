package com.olivia.grocerylist.db;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class RecipeIngredient {

    @EmbeddedId
    RecipeIngredientKey id;

    @ManyToOne
    @MapsId("recipeId")
    @JoinColumn(name = "recipe_id")
    Recipe recipe;

    @ManyToOne
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient_id")
    Ingredient ingredient;

    String quantity;

    public RecipeIngredient(RecipeIngredientKey recipeIngredientKey, String quantity) {
        this.id = recipeIngredientKey;
        this.quantity = quantity;
    }
}
