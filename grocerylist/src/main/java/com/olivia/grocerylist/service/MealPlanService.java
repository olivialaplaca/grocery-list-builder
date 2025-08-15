package com.olivia.grocerylist.service;

import com.olivia.grocerylist.request.CreateGroceryListRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MealPlanService {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    public MealPlanService(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    public List<GroceryListElement> generateGroceryList(CreateGroceryListRequest groceryListRequest) {
        var recipeCounts = getRecipeOccurrences(groceryListRequest);
        var groceryList = new ArrayList<GroceryListElement>();

        for (var recipeId : recipeCounts.keySet()) {
            var recipe = recipeService.getRecipe(Long.valueOf(recipeId));
            for (var ingredient : recipe.getRecipeIngredients()) {
                var ingredientName = ingredientService.getIngredientById(ingredient.getIngredient().getIngredientId()).orElseThrow().getName();
                var listItem = new GroceryListElement();
                listItem.setItemName(ingredientName);
                listItem.setQuantity(ingredient.getQuantity());
                listItem.setUnitOfMeasure(ingredient.getUnitOfMeasure());
                groceryList.add(listItem);
            }
        }
        groceryList.sort(GroceryListElement::compareTo);
        return groceryList;
    }





    private Boolean areAllRecipesUnique(HashMap<String, Integer> recipeCounts) {
        for (var key : recipeCounts.keySet()) {
            if (recipeCounts.get(key) > 1) {
                return false;
            }
        }
        return true;
    }

    private Boolean doesRecipeMakeEnough(Integer requiredServings, Long recipeId) {
        var recipeServings = this.recipeService.getRecipe(recipeId).getServings();
        return recipeServings >= requiredServings;
    }

    private Integer determineIngredientMultiplier(Integer requiredServings, Long recipeId) {
        var recipeServings = this.recipeService.getRecipe(recipeId).getServings();
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
            recipeCounts.put(String.valueOf(recipeId), count);
        }
        return recipeCounts;
    }
}
