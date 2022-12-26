package com.example.skypro3.services.impl;

import com.example.skypro3.model.Ingredient;
import com.example.skypro3.model.Recipe;
import com.example.skypro3.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private int ingredientId = 0;
    private int recipeId = 0;
    private final Map<Integer, Ingredient> ingredientsCatalogue = new HashMap<>();

    private final Map<Integer, Recipe> recipeCatalogue = new HashMap<>();

    @Override
    public int addIngredientToCatalogue(Ingredient ingredient) {
        ingredientsCatalogue.put(ingredientId, ingredient);
        return ingredientId++;
    }

    @Override
    public Ingredient getIngredientById(int id) {
        if (ingredientsCatalogue.containsKey(id)) {
            return ingredientsCatalogue.get(id);
        } else {
            return null;
        }
    }

    @Override
    public Ingredient editIngredient(int id, Ingredient ingredient) {
        if (ingredientsCatalogue.containsKey(id)) {
            ingredientsCatalogue.put(id, ingredient);
            return ingredient;
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteIngredient(int id) {
        if (ingredientsCatalogue.containsKey(id)) {
            ingredientsCatalogue.remove(id);
            return true;
        }
        return false;
    }
    @Override
    public Map<Integer, Ingredient> getAllIngredients(){
        return ingredientsCatalogue;
    }

    @Override
    public int addRecipeToCatalogue(Recipe recipe) {
        recipeCatalogue.put(recipeId, recipe);
        return recipeId++;
    }

    @Override
    public Recipe getRecipeById(int id) {
        if (recipeCatalogue.containsKey(id)) {
            return recipeCatalogue.get(id);
        } else {
            return null;
        }
    }

    @Override
    public Recipe editRecipeById(int id, Recipe recipe) {
        if (recipeCatalogue.containsKey(id)){
            recipeCatalogue.put(id, recipe);
            return recipe;
        }
        return null;
    }

    @Override
    public boolean deleteRecipe(int id) {
        if (recipeCatalogue.containsKey(id)) {
            recipeCatalogue.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public Map<Integer, Recipe> getAllRecipes(){
        return recipeCatalogue;
    }
}
