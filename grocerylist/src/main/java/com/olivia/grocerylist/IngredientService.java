package com.olivia.grocerylist;

import com.olivia.grocerylist.db.Ingredient;
import com.olivia.grocerylist.db.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class IngredientService {

    private IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientRepository.findAll();
    }
    public Optional<Ingredient> getIngredientById(Integer id) {
        return ingredientRepository.findById(id);
    }

    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepository.saveAndFlush(ingredient);
    }

    public Ingredient getIngredientByName(String name) {
        return ingredientRepository.findByName(name);
    }
}
