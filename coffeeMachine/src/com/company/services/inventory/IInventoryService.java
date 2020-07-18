package com.company.services.inventory;

import com.company.enums.Ingredient;
import com.company.models.ItemsUnavailableException;

import java.util.List;
import java.util.Map;

public interface IInventoryService {

    /**
     * increases the item stock in machine.
     * @param ingredient for which to increase
     * @param quantity to increase
     */
    void addInventory(Ingredient ingredient, double quantity);

    /**
     * decreases the inventory in machine.
     * @param inventoryToConsume inventories to reduce
     * @throws ItemsUnavailableException if inventory is unavailable
     */
    void consumeInventory(Map<Ingredient, Double> inventoryToConsume) throws ItemsUnavailableException;

    /**
     * checks if all inventory is present
     * @param inventoryToCheck inventory to check
     * @return unavailable inventory
     */
    List<Ingredient> checkInventoryAvailability(Map<Ingredient, Double> inventoryToCheck);

    /**
     * @param ingredient for which to set threshold
     * @param thresholdValue to set
     */
    void setThresholdForIngredient(Ingredient ingredient, Double thresholdValue);

}
