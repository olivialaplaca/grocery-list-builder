package com.olivia.grocerylist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
public class RecipeControllerITest {

    @Autowired
    private WebTestClient client;

    @Test
    public void getIngredientTest() {

    }
}
