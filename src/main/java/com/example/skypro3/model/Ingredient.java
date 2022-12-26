package com.example.skypro3.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Ingredient {
    private final String ingredientName;
    private int ingredientQuantity;
    private final String measureUnit;

}
