package com.example.skypro3.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@AllArgsConstructor
@Data
public class Recipe {
    private final String recipeName;
    private final int cookingTime;
    private List<Ingredient> ingredientsList;
    private List<String> cookingSteps;


    public void addIngredient(Ingredient ingredient) {
        ingredientsList.add(ingredient);
    }



}
