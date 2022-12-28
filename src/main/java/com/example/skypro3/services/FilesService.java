package com.example.skypro3.services;

public interface FilesService {

    boolean saveIngredientsToFile(String json);

    String readIngredientsFromFile();

    boolean saveRecipesToFile(String json);

    String readRecipesFromFile();
}
