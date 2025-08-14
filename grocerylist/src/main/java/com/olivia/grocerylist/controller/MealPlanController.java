package com.olivia.grocerylist.controller;

import com.olivia.grocerylist.request.CreateGroceryListRequest;
import com.olivia.grocerylist.service.GroceryListElement;
import com.olivia.grocerylist.service.MealPlanService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/meal-plan")
public class MealPlanController {

    private final MealPlanService mealPlanService;

    public MealPlanController(MealPlanService mealPlanService) {
        this.mealPlanService = mealPlanService;
    }

    @PostMapping("/generate-grocery-list")
    public List<GroceryListElement> generateGroceryList(@RequestBody CreateGroceryListRequest groceryListRequest){
        return mealPlanService.generateGroceryList(groceryListRequest);
    }
}
