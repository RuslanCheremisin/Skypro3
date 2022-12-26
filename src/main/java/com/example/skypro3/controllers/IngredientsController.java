package com.example.skypro3.controllers;

import com.example.skypro3.model.Ingredient;
import com.example.skypro3.model.Recipe;
import com.example.skypro3.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {
    public IngredientsController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    RecipeService recipeService;

    @GetMapping("/{id}")
    public ResponseEntity getIngredientById(@PathVariable int id) {
        Ingredient ingredient = recipeService.getIngredientById(id);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }
    @GetMapping
    public ResponseEntity getAllIngredients(){
        Map<Integer, Ingredient> ingredients = recipeService.getAllIngredients();
        if (ingredients == null) {
            return ResponseEntity.ok().body("List is empty");
        }
        return ResponseEntity.ok().body(ingredients);
    }

    @PostMapping
    public ResponseEntity addIngredientToCatalogue(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok().body(recipeService.addIngredientToCatalogue(ingredient));
    }

    @PutMapping("/{id}")
    public ResponseEntity editIngredient(@PathVariable int id, @RequestBody Ingredient newIngredient){
        Ingredient ingredient = recipeService.editIngredient(id, newIngredient);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteIngredient(@PathVariable int id){
        boolean deleted = recipeService.deleteIngredient(id);
        if (deleted) {
            return ResponseEntity.ok().body("Ingredient " + id + " is deleted");
        }
        return ResponseEntity.notFound().build();
    }
}
