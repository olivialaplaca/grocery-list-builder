package com.olivia.grocerylist.service;

import lombok.Data;

@Data
public class GroceryListElement implements Comparable<GroceryListElement> {

    private String itemName;
    private String unitOfMeasure;
    private String quantity;

    @Override
    public int compareTo(GroceryListElement o) {
        var item1 = itemName + quantity + unitOfMeasure;
        var item2 = o.itemName + o.quantity + o.unitOfMeasure;
        return item1.compareTo(item2);
    }
}
