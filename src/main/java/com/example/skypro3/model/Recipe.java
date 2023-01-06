package com.example.skypro3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Recipe {
    private String recipeName;
    private int cookingTime;
    private List<Ingredient> ingredientsList;
    private List<String> cookingSteps;


    public void addIngredient(Ingredient ingredient) {
        ingredientsList.add(ingredient);
    }



}
