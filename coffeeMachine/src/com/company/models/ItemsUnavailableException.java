package com.company.models;

import com.company.enums.Ingredient;

import java.util.List;

public class ItemsUnavailableException extends Exception {

    List<Ingredient> unavailableIngredients;

    public ItemsUnavailableException(List<Ingredient> unavailableIngredients) {
        this.unavailableIngredients = unavailableIngredients;
    }

    public List<Ingredient> getUnavailableIngredients() {
        return unavailableIngredients;
    }

    public void setUnavailableIngredients(List<Ingredient> unavailableIngredients) {
        this.unavailableIngredients = unavailableIngredients;
    }
}
