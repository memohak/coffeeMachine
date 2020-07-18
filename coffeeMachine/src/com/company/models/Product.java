package com.company.models;

import com.company.enums.Ingredient;

import java.util.Map;

public class Product {
    String code;
    String name;
    Map<Ingredient, Double> ingredientsRequired; // ingredient code to quantity

    public Product(String code, String name, Map<Ingredient, Double> ingredientsRequired) {
        this.code = code;
        this.name = name;
        this.ingredientsRequired = ingredientsRequired;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Ingredient, Double> getIngredientsRequired() {
        return ingredientsRequired;
    }

    public void setIngredientsRequired(Map<Ingredient, Double> ingredientsRequired) {
        this.ingredientsRequired = ingredientsRequired;
    }
}
