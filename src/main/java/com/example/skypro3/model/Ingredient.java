package com.example.skypro3.model;

public class Ingredient {
    private final String ingredientName;
    private int ingredientQuantity;
    private final String measureUnit;

    public Ingredient(String ingredientName, int ingredientQuantity, String measureUnit) {
        this.ingredientName = ingredientName;
        this.ingredientQuantity = ingredientQuantity;
        this.measureUnit = measureUnit;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public int getIngredientQuantity() {
        return ingredientQuantity;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setIngredientQuantity(int ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }
}
