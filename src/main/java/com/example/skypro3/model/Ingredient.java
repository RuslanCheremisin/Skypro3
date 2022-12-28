package com.example.skypro3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ingredient {
    private String ingredientName;
    private int ingredientQuantity;
    private String measureUnit;

}
