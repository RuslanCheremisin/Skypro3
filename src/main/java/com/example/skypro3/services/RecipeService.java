package com.example.skypro3.services;

import com.example.skypro3.model.Ingredient;
import com.example.skypro3.model.Recipe;

import java.util.Map;

public interface RecipeService {


    int addIngredientToCatalogue(Ingredient ingredient);

    Ingredient editIngredient(int id, Ingredient ingredient);

    boolean deleteIngredient(int id);

    Map<Integer, Ingredient> getAllIngredients();

    int addRecipeToCatalogue(Recipe recipe);

    Ingredient getIngredientById(int id);
    Recipe getRecipeById(int id);

    Recipe editRecipeById(int id, Recipe recipe);

    boolean deleteRecipe(int id);

    Map<Integer, Recipe> getAllRecipes();
}
