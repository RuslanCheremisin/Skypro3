package com.example.skypro3.controllers;

import com.example.skypro3.model.Recipe;
import com.example.skypro3.services.impl.RecipeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/recipes")
public class RecipesController {
    RecipeServiceImpl recipeService;

    public RecipesController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }
    @PostMapping
    public ResponseEntity addRecipeToCatalogue(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.addRecipeToCatalogue(recipe));
    }
    @GetMapping("/{id}")
    public ResponseEntity getRecipeById(@PathVariable int id){
        Recipe recipe =  recipeService.getRecipeById(id);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(recipe);
    }
    @GetMapping
    public ResponseEntity getAllRecipes(){
        Map<Integer, Recipe> recipes = recipeService.getAllRecipes();
        if (recipes == null) {
            return ResponseEntity.ok().body("List is empty");
        }
        return ResponseEntity.ok().body(recipeService.getAllRecipes());
    }

    @PutMapping("/{id}")
    public ResponseEntity editRecipe(@PathVariable int id, @RequestBody Recipe newRecipe) {
        Recipe recipe = recipeService.editRecipeById(id, newRecipe);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(recipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRecipe(@PathVariable int id){
        if (recipeService.deleteRecipe(id)==true){
            return ResponseEntity.ok().body("Recipe " + id + " is deleted");
        }
        return ResponseEntity.notFound().build();
    }






}
