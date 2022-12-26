package com.example.skypro3.model;

import com.example.skypro3.services.ValidateUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
@Data
public class Recipe {
    private final String recipeName;
    private final int cookingTime;
    private final ArrayList<Ingredient> ingredientsList;
    private final LinkedList<String> cookingSteps;

    public Recipe(String recipeName, int cookingTime) {
        this.recipeName = ValidateUtil.validateString(recipeName);
        this.cookingTime = ValidateUtil.validateInt(cookingTime);
        this.ingredientsList = new ArrayList<>();
        this.cookingSteps = new LinkedList<>();
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

    public ArrayList<Ingredient> getIngredientsList() {
        return ingredientsList;
    }

    public LinkedList<String> getCookingSteps() {
        return cookingSteps;
    }

}
