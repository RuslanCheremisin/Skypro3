package com.example.skypro3.controllers;

import com.example.skypro3.model.Ingredient;

import com.example.skypro3.services.IngredientsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ingredients")
@Tag(name = "Ингредиенты", description = "Здесь мы храним ингредиенты для лучших рецептов мира")
public class IngredientsController {
    IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Здесь вы можете найти ингредиент по его ID",
            description = "ID водится целыми числами")
    public ResponseEntity getIngredientById(@PathVariable int id) {
        Ingredient ingredient = ingredientsService.getIngredientById(id);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }
    @GetMapping
    @Operation(summary = "Здесь вы можете получить список всех ингредиентов",
            description = "Обратите внимание на описание ингредиента")
    public ResponseEntity getAllIngredients(){
        Map<Integer, Ingredient> ingredients = ingredientsService.getAllIngredients();
        if (ingredients == null) {
            return ResponseEntity.ok().body("List is empty");
        }
        return ResponseEntity.ok().body(ingredients);
    }

    @PostMapping
    @Operation(summary = "Здесь вы можете добавить ингредиент в список",
            description = "Обратите внимание на корректное заполнение всех полей ингредиента")
    public ResponseEntity addIngredientToCatalogue(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok().body(ingredientsService.addIngredientToCatalogue(ingredient));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Здесь вы можете изменить данные об ингредиенте",
            description = "Обратите внимание на корректное заполнение всех полей ингредиентаа")
    public ResponseEntity editIngredient(@PathVariable int id, @RequestBody Ingredient newIngredient){
        Ingredient ingredient = ingredientsService.editIngredient(id, newIngredient);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Здесь вы можете удалить ингредиент из списка по его ID",
            description = "ID водится целыми числами")
    public ResponseEntity deleteIngredient(@PathVariable int id){
        boolean deleted = ingredientsService.deleteIngredient(id);
        if (deleted) {
            return ResponseEntity.ok().body("Ingredient " + id + " is deleted");
        }
        return ResponseEntity.notFound().build();
    }
}
