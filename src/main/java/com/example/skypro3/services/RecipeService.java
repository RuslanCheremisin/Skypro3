package com.example.skypro3.services;

import com.example.skypro3.model.Ingredient;
import com.example.skypro3.model.Recipe;

public interface RecipeService {


    void addIngredientToCatalogue(Ingredient ingredient);

    void addRecipeToCatalogue(Recipe recipe);

    Ingredient getIngredientById(int id);
    Recipe getRecipeById(int id);
}
