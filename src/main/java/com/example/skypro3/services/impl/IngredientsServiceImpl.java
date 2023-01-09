package com.example.skypro3.services.impl;

import com.example.skypro3.model.Ingredient;
import com.example.skypro3.services.FilesService;
import com.example.skypro3.services.IngredientsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientsServiceImpl implements IngredientsService {
    private Map<Integer, Ingredient> ingredientsCatalogue = new HashMap<>();
    private int ingredientId = 0;
    private FilesService filesService;

    public IngredientsServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }


    @Override
    public int addIngredientToCatalogue(Ingredient ingredient) {
        ingredientsCatalogue.put(ingredientId, ingredient);
        saveToFile();
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
    @PostConstruct
    private void initIngredients(){
        readIngredientsFromFile();
    }

    @Override
    public Ingredient editIngredient(int id, Ingredient ingredient) {
        if (ingredientsCatalogue.containsKey(id)) {
            ingredientsCatalogue.put(id, ingredient);
            saveToFile();
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
    public Map<Integer, Ingredient> getAllIngredients() {
        return ingredientsCatalogue;
    }
    private void saveToFile(){
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientsCatalogue);
            filesService.saveIngredientsToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readIngredientsFromFile(){
        String json = filesService.readIngredientsFromFile();
        try {
            ingredientsCatalogue = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Ingredient>>(){});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
