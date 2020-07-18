package com.company.services.coffeemachine;

import com.company.enums.Ingredient;
import com.company.models.ItemsUnavailableException;
import com.company.models.Product;
import com.company.services.inventory.InventoryService;

import java.util.HashMap;
import java.util.Map;

public class CoffeeMachine implements ICoffeeMachine {

    private Map<String, Product> menu; // product code to product
    private final InventoryService inventoryService;
    private int outlets;

    public CoffeeMachine(int outlets) {
        this.inventoryService = new InventoryService();
        this.menu = new HashMap<>();
        this.outlets = outlets;
    }

    @Override
    public void pourBeverage(String productCode, int outletNumber) {
        Product productToBeServed = menu.get(productCode);
        if (productToBeServed == null) {
            throw new RuntimeException("productCode code: " + productCode + " not available on menu");
        }

        if (outletNumber > this.outlets) {
            throw new RuntimeException("outletNumber: " + outletNumber + " not available");
        }

        try {
            // reduce inventory for ingredients
            this.inventoryService.consumeInventory(productToBeServed.getIngredientsRequired());
            System.out.println(productToBeServed.getName() + " is prepared");
        } catch (ItemsUnavailableException e) {
            System.out.println(productToBeServed.getName() + " cannot be prepared because " + e.getUnavailableIngredients().toString() + " are unavailable");
        }
    }

    @Override
    public void addIngredientInventory(Ingredient ingredient, Double quantity) {
        this.inventoryService.addInventory(ingredient, quantity);
    }

    @Override
    public void setThresholdForIngredient(Ingredient ingredient, Double threshold) {
        this.inventoryService.setThresholdForIngredient(ingredient, threshold);
    }

    @Override
    public void addNewBeverage(Product product) {
        this.menu.put(product.getCode(), product);
    }
}
