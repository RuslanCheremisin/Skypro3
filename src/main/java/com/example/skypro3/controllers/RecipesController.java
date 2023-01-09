package com.example.skypro3.controllers;

import com.example.skypro3.model.Recipe;
import com.example.skypro3.services.FilesService;
import com.example.skypro3.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Map;

@RestController
@RequestMapping("/recipes")
@Tag(name = "Рецепты", description = "Здесь живут лучшие рецепты мира")
public class RecipesController {
    RecipeService recipeService;

    public RecipesController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    @Operation(summary = "Здесь вы можете добавить рецепт в список",
            description = "Обратите внимание на корректное заполнение всех полей рецепта")
    public ResponseEntity addRecipeToCatalogue(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.addRecipeToCatalogue(recipe));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Здесь вы можете найти рецепт по его ID",
            description = "ID водится целыми числами")
    public ResponseEntity getRecipeById(@PathVariable int id) {
        Recipe recipe = recipeService.getRecipeById(id);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(recipe);
    }

    @GetMapping
    @Operation(summary = "Здесь вы можете получить список всех рецептов",
            description = "Обратите внимание на описание рецепта")
    public ResponseEntity getAllRecipes() {
        Map<Integer, Recipe> recipes = recipeService.getAllRecipes();
        if (recipes == null) {
            return ResponseEntity.ok().body("List is empty");
        }
        return ResponseEntity.ok().body(recipeService.getAllRecipes());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Здесь вы можете изменить данные об ингредиенте",
            description = "Обратите внимание на корректное заполнение всех полей ингредиентаа")
    public ResponseEntity editRecipe(@PathVariable int id, @RequestBody Recipe newRecipe) {
        Recipe recipe = recipeService.editRecipeById(id, newRecipe);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(recipe);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Здесь вы можете удалить ингредиент из списка по его ID",
            description = "ID водится целыми числами")
    public ResponseEntity deleteRecipe(@PathVariable int id) {
        if (recipeService.deleteRecipe(id) == true) {
            return ResponseEntity.ok().body("Recipe " + id + " is deleted");
        }
        return ResponseEntity.notFound().build();
    }


}
