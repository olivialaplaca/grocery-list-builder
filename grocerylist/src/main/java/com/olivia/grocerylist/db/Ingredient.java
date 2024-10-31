package com.olivia.grocerylist.db;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
@Data
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ingredientId;
    private String name;
    private String packageSize;

    @OneToMany(mappedBy = "ingredient")
    Set<RecipeIngredient> recipeIngredients;
}
