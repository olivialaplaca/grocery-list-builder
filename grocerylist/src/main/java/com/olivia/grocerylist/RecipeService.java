package com.olivia.grocerylist;

import com.olivia.grocerylist.db.Recipe;
import com.olivia.grocerylist.db.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeService {

    private RecipeRepository recipeRepository;

    public void saveRecipe(){
        var newRecipe = new Recipe();
        recipeRepository.save(newRecipe);
    }

    public Optional<Recipe> getRecipe(Integer id) {
        return recipeRepository.findById(id);
    }
}
