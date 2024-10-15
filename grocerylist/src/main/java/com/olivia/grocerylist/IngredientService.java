package com.olivia.grocerylist;

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
}
