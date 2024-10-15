package com.olivia.grocerylist;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Entity
public class Ingredient {

    @Id
    private Integer id;
    private String name;
    private String packageSize;
}
