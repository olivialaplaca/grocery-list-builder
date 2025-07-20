package com.olivia.grocerylist.service;

import com.olivia.grocerylist.CreateGroceryListRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class MealPlanService {

    private final RecipeService recipeService;

    public MealPlanService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    public List<String> generateGroceryList(CreateGroceryListRequest groceryListRequest) {
        //get recipeIds from request
        var recipeCounts = getRecipeOccurrences(groceryListRequest);
        var ingredients = new ArrayList<String>();
        Integer totalRequiredServings;
        //if useLeftovers is true,
        if (groceryListRequest.getUseLeftovers() == true) {
        // then for each recipe check that recipe servings >= number of people * recipe count
            for (var recipe : recipeCounts.keySet()) {
                totalRequiredServings = recipeCounts.get(recipe) * groceryListRequest.getNumberOfPeople();
            }

        }
        //if useLeftovers is false, reduce the recipe

        return ingredients;
    }

    private Boolean doesRecipeMakeEnough(Integer requiredServings, Long recipeId) {
        var recipeServings = this.recipeService.getRecipe(recipeId).get().getServings();
        return recipeServings >= requiredServings;
    }

    private Integer determineIngredientMultiplier(Integer requiredServings, Long recipeId) {
        var recipeServings = this.recipeService.getRecipe(recipeId).get().getServings();
        if (recipeServings == null) {
            return -1;
        }
        int multiplier;
        if (recipeServings < requiredServings) {
            var remainder = requiredServings % recipeServings;
            multiplier = Math.floorDiv(requiredServings, recipeServings) + (remainder / recipeServings);
        } else if (recipeServings.equals(requiredServings)) {
            multiplier = 1;
        } else {
            multiplier = requiredServings / recipeServings;
        }
        return multiplier;
    }

    private static HashMap<String, Integer> getRecipeOccurrences(CreateGroceryListRequest groceryListRequest) {
        var days = groceryListRequest.getMealPlanRecipes().keySet();
        var recipeCounts = new HashMap<String, Integer>();
        for (var day : days) {
            var recipeId = groceryListRequest.getMealPlanRecipes().get(day);
            var count = Collections.frequency(days, recipeId);
            recipeCounts.put(recipeId, count);
        }
        return recipeCounts;
    }
}
