package com.example.skypro3.model;

import com.example.skypro3.services.ValidateUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Recipe {
    private final String recipeName;
    private final int cookingTime;
    private List<Ingredient> ingredientsList;
    private List<String> cookingSteps;

    public Recipe(String recipeName, int cookingTime, List ingredientsList, List cookingSteps) {
        this.recipeName = ValidateUtil.validateString(recipeName);
        this.cookingTime = ValidateUtil.validateInt(cookingTime);
        this.ingredientsList = ingredientsList;
        this.cookingSteps = cookingSteps;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredientsList.add(ingredient);
    }

    public String getRecipeName() {
        return recipeName;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public List<Ingredient> getIngredientsList() {
        return ingredientsList;
    }

    public List<String> getCookingSteps() {
        return cookingSteps;
    }

}
