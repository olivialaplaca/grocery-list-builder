package com.olivia.grocerylist.db;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue
    private Integer recipeId;
    private String name;

    @OneToMany(mappedBy = "recipe")
    Set<RecipeIngredient> recipeIngredients;
}