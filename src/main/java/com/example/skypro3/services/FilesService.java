package com.example.skypro3.services;

import java.io.File;
import java.nio.file.Path;

public interface FilesService {

    boolean saveIngredientsToFile(String json);

    String readIngredientsFromFile();

    boolean cleanIngredientsFile();

    File getIngredientsFile();

    boolean saveRecipesToFile(String json);

    String readRecipesFromFile();

    boolean cleanRecipesFile();

    File getRecipesFile();

    Path createTempFile();
}
