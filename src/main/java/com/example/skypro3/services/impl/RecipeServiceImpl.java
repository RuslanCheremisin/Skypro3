package com.example.skypro3.services.impl;

import com.example.skypro3.model.Ingredient;
import com.example.skypro3.model.Recipe;
import com.example.skypro3.services.FilesService;
import com.example.skypro3.services.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private int recipeId = 0;

    private Map<Integer, Recipe> recipeCatalogue = new HashMap<>();
    private FilesService filesService;

    public RecipeServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }


    @Override
    public int addRecipeToCatalogue(Recipe recipe) {
        recipeCatalogue.put(recipeId, recipe);
        saveToFile();
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

    @PostConstruct
    private void initRecipes() {
        readFromFile();
    }

    @Override
    public Recipe editRecipeById(int id, Recipe recipe) {
        if (recipeCatalogue.containsKey(id)) {
            recipeCatalogue.put(id, recipe);
            saveToFile();
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
    public Map<Integer, Recipe> getAllRecipes() {
        return recipeCatalogue;
    }

    @Override
    public Path getAllRecipesForDownload() {
        Path path = filesService.createTempFile();
        for (Recipe recipe : recipeCatalogue.values()) {
            try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                writer.append(recipe.getRecipeName() + "\n" +
                        "Время приготовления " + recipe.getCookingTime() + " минут." + "\n" +
                        "Ингредиенты:\n");
                for (Ingredient ingredient : recipe.getIngredientsList()) {
                    writer.append(ingredient.getIngredientName()
                            + " - "
                            + ingredient.getIngredientQuantity()
                            + " "
                            + ingredient.getMeasureUnit()
                            + "\n");
                }
                int i = 1;
                for (String step : recipe.getCookingSteps()) {
                    writer.append(i + ". " + step + "\n");
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return path;
    }

    @Override
    public void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeCatalogue);
            filesService.saveRecipesToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void readFromFile() {
        String json = filesService.readRecipesFromFile();
        try {
            recipeCatalogue = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
