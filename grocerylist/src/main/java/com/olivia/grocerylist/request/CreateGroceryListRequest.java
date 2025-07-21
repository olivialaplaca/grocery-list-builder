package com.olivia.grocerylist.request;

import lombok.Data;

import java.util.Map;

@Data
public class CreateGroceryListRequest {
    private Map<String, String> mealPlanRecipes;
    private Integer numberOfPeople;
    private Boolean useLeftovers;
}
