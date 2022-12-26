package com.example.skypro3.services;

import com.example.skypro3.model.Ingredient;
import com.example.skypro3.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private int ingredientId = 0;
    private int recipeId = 0;
    private final Map<Integer, Ingredient> ingredientsCataloque = new LinkedHashMap<>();

    private final Map<Integer, Recipe> recipeCataloque = new LinkedHashMap<>();

    @Override
    public void addIngredientToCatalogue(Ingredient ingredient) {
        ingredientId++;
        ingredientsCataloque.put(ingredientId, ingredient);
    }

    @Override
    public void addRecipeToCatalogue(Recipe recipe) {
        recipeId++;
        recipeCataloque.put(recipeId, recipe);
    }

    @Override
    public Ingredient getIngredientById(int id) {
        if (ingredientsCataloque.containsKey(id)) {
            return ingredientsCataloque.get(id);
        }else {
            return null;
        }
    }

    @Override
    public Recipe getRecipeById(int id) {
        if (recipeCataloque.containsKey(id)) {
            return recipeCataloque.get(id);
        }else {
            return null;
        }
    }
}
