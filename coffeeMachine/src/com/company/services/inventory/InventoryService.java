package com.company.services.inventory;

import com.company.enums.Ingredient;
import com.company.models.ItemsUnavailableException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryService implements IInventoryService {

    private final Map<Ingredient, Double> ingredientToAvailableQuantity;
    private final Map<Ingredient, Double> ingredientThresholdQuantity;

    public InventoryService() {
        this.ingredientToAvailableQuantity = new HashMap<>();
        this.ingredientThresholdQuantity = new HashMap<>();
    }

    @Override
    public void addInventory(Ingredient ingredient, double quantity) {
        if (ingredient == null) {
            return;
        }

        synchronized (this.ingredientToAvailableQuantity) { // lock inventory for stock updation
            Double presentAvailableQuantity = this.ingredientToAvailableQuantity.getOrDefault(ingredient, 0.0);
            Double newAvailableQuantity = presentAvailableQuantity + quantity;
            this.ingredientToAvailableQuantity.put(ingredient, newAvailableQuantity);
        }
    }

    @Override
    public void consumeInventory(Map<Ingredient, Double> inventoryToConsume) throws ItemsUnavailableException {
        if (inventoryToConsume == null || inventoryToConsume.size() == 0) {
            return;
        }
        synchronized (this.ingredientToAvailableQuantity) { // lock inventory for consumption
            // check if all items are available
            List<Ingredient> unavailableIngredients = this.checkInventoryAvailability(inventoryToConsume);
            if (unavailableIngredients != null && unavailableIngredients.size() > 0) { // inventory not available
                throw new ItemsUnavailableException(unavailableIngredients);
            }

            // consume inventory
            for (Map.Entry<Ingredient, Double> stockEntry : inventoryToConsume.entrySet()) {
                Double presentAvailableQuantity = this.ingredientToAvailableQuantity.getOrDefault(stockEntry.getKey(), 0.0);
                Double newAvailableQuantity = presentAvailableQuantity - stockEntry.getValue();
                this.ingredientToAvailableQuantity.put(stockEntry.getKey(), newAvailableQuantity);

                // raise stock alerts if any ingredients is below threshold value
                checkAndRaiseLowInventoryAlert(stockEntry.getKey(), newAvailableQuantity);
            }
        }
    }

    @Override
    public List<Ingredient> checkInventoryAvailability(Map<Ingredient, Double> inventoryToCheck) {
        List<Ingredient> unavailableInventory = new ArrayList<>();
        if (inventoryToCheck == null || inventoryToCheck.size() == 0) {
            return unavailableInventory;
        }
        for (Map.Entry<Ingredient, Double> stockEntry : inventoryToCheck.entrySet()) {
            Double presentAvailableQuantity = this.ingredientToAvailableQuantity.getOrDefault(stockEntry.getKey(), 0.0);
            if (presentAvailableQuantity < stockEntry.getValue()) {
                unavailableInventory.add(stockEntry.getKey());
            }
        }
        return unavailableInventory;
    }

    @Override
    public void setThresholdForIngredient(Ingredient ingredient, Double thresholdValue) {
        this.ingredientThresholdQuantity.put(ingredient, thresholdValue);
    }

    // ---------------------------------------------- PRIVATE METHODS ----------------------------------------------//

    // raise low stock alerts from here
    private void checkAndRaiseLowInventoryAlert(Ingredient ingredient, Double newAvailableQuantity) {
        if (this.ingredientThresholdQuantity.get(ingredient) != null &&
                this.ingredientThresholdQuantity.get(ingredient) > newAvailableQuantity) {
            System.out.println(ingredient.toString() + " is running low in quantity");
        }
    }
}
