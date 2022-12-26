package com.example.skypro3.controllers;

import com.example.skypro3.model.Ingredient;
import com.example.skypro3.model.Recipe;
import com.example.skypro3.services.RecipeServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipes")
public class RecipesController {
    RecipeServiceImpl recipeService;

    public RecipesController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/addIngredientToCatalogue")
    public void addIngredientToCatalogue(@RequestBody Ingredient ingredient) {
        recipeService.addIngredientToCatalogue(ingredient);
    }
    @PostMapping("/addRecipeToCatalogue")
    public void addRecipeToCatalogue(@RequestBody Recipe recipe) {
        recipeService.addRecipeToCatalogue(recipe);
    }

    @GetMapping("/getIngredientById/{id}")
    public Ingredient getIngredientById(@PathVariable ("id") int id){
        return recipeService.getIngredientById(id);
    }
    @GetMapping("/getRecipeById/{id}")
    public Recipe getRecipeById(@PathVariable ("id") int id){
        return recipeService.getRecipeById(id);
    }

}
