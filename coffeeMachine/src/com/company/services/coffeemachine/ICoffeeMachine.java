package com.company.services.coffeemachine;

import com.company.enums.Ingredient;
import com.company.models.Product;

public interface ICoffeeMachine {

    /**
     * serves the said beverage
     * @param productCode product to serve
     * @param outletNumber outlet through which beverage will be served
     */
    void pourBeverage(String productCode, int outletNumber);

    /**
     * add inventory for ingredient
     * @param ingredient for which to add inventory
     * @param quantity to increase
     */
    void addIngredientInventory(Ingredient ingredient, Double quantity);

    /**
     * add inventory for ingredient
     * @param ingredient for which to set threshold
     * @param threshold to set
     */
    void setThresholdForIngredient(Ingredient ingredient, Double threshold);

    /**
     * adds new product to menu which the machine can serve
     * @param product to add
     */
    void addNewBeverage(Product product);

}
