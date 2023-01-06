package com.example.skypro3.services;

import com.example.skypro3.model.Ingredient;
import com.example.skypro3.model.Recipe;

import java.util.Map;

public interface RecipeService {

    int addRecipeToCatalogue(Recipe recipe);

    Recipe getRecipeById(int id);

    Recipe editRecipeById(int id, Recipe recipe);

    boolean deleteRecipe(int id);

    Map<Integer, Recipe> getAllRecipes();

    void saveToFile();

    void readFromFile();
}
