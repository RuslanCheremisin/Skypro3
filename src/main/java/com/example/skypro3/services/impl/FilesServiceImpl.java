package com.example.skypro3.services.impl;

import com.example.skypro3.services.FilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesServiceImpl implements FilesService {
    @Value("${path.to.ingredients.file}")
    private String ingredientsFilePath;
    @Value("${name.of.ingredients.file}")
    private String ingredientsFileName; 
    @Value("${path.to.recipes.file}")
    private String recipesFilePath;
    @Value("${name.of.recipes.file}")
    private String recipesFileName;

    @Override
    public boolean saveIngredientsToFile(String json){
        try {
            cleanIngredientsFile();
            Files.writeString(Path.of(ingredientsFilePath, ingredientsFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public String readIngredientsFromFile(){
        try {
            return Files.readString(Path.of(ingredientsFilePath, ingredientsFileName));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private boolean cleanIngredientsFile(){
        try {
            Files.deleteIfExists(Path.of(ingredientsFilePath, ingredientsFileName));
            Files.createFile(Path.of(ingredientsFilePath));
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    @Override
    public boolean saveRecipesToFile(String json){
        try {
            cleanRecipesFile();
            Files.writeString(Path.of(recipesFilePath, recipesFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public String readRecipesFromFile(){
        try {
            return Files.readString(Path.of(recipesFilePath, recipesFileName));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    private boolean cleanRecipesFile(){
        try {
            Files.deleteIfExists(Path.of(recipesFilePath, recipesFileName));
            Files.createFile(Path.of(recipesFilePath));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
