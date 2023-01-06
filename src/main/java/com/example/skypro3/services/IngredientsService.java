package com.example.skypro3.services;

import com.example.skypro3.model.Ingredient;

import java.util.Map;

public interface IngredientsService {
    int addIngredientToCatalogue(Ingredient ingredient);

    Ingredient getIngredientById(int id);

    Ingredient editIngredient(int id, Ingredient ingredient);

    boolean deleteIngredient(int id);

    Map<Integer, Ingredient> getAllIngredients();


}
