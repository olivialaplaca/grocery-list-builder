package com.olivia.grocerylist.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class IngredientRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private IngredientRepository repository;

    @Test
    void findIngredientByName() {
        var ingredient = new Ingredient();
        ingredient.setName("potato");
        ingredient.setPackageSize("whole");
        entityManager.persist(ingredient);
        var savedIngredient = repository.findByName("potato");
        assertThat(ingredient.getIngredientId()).isEqualTo(savedIngredient.getIngredientId());
    }
}
