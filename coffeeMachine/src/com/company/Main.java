package com.company;

import com.company.enums.Ingredient;
import com.company.models.Product;
import com.company.services.coffeemachine.CoffeeMachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); ) {
            String outletsString = reader.readLine();
            int numberOfOutlets = Integer.parseInt(outletsString);
            CoffeeMachine coffeeMachine = new CoffeeMachine(numberOfOutlets);

            coffeeMachine.addIngredientInventory(Ingredient.HOT_WATER, 500.0);
            coffeeMachine.addIngredientInventory(Ingredient.HOT_MILK, 500.0);
            coffeeMachine.addIngredientInventory(Ingredient.GINGER_SYRUP, 100.0);
            coffeeMachine.addIngredientInventory(Ingredient.SUGAR_SYRUP, 100.0);
            coffeeMachine.addIngredientInventory(Ingredient.TEA_LEAVES_SYRUP, 100.0);

            coffeeMachine.setThresholdForIngredient(Ingredient.HOT_WATER, 100.0);
            coffeeMachine.setThresholdForIngredient(Ingredient.HOT_MILK, 100.0);
            coffeeMachine.setThresholdForIngredient(Ingredient.GINGER_SYRUP, 10.0);
            coffeeMachine.setThresholdForIngredient(Ingredient.SUGAR_SYRUP, 45.0);
            coffeeMachine.setThresholdForIngredient(Ingredient.TEA_LEAVES_SYRUP, 10.0);

            Map<Ingredient, Double> hotTeaRecipe = new HashMap<>();
            hotTeaRecipe.put(Ingredient.HOT_WATER, 200.0);
            hotTeaRecipe.put(Ingredient.HOT_MILK, 100.0);
            hotTeaRecipe.put(Ingredient.GINGER_SYRUP, 10.0);
            hotTeaRecipe.put(Ingredient.SUGAR_SYRUP, 10.0);
            hotTeaRecipe.put(Ingredient.TEA_LEAVES_SYRUP, 30.0);
            coffeeMachine.addNewBeverage(new Product("001", "Hot Tea", hotTeaRecipe));

            Map<Ingredient, Double> blackTea = new HashMap<>();
            blackTea.put(Ingredient.HOT_WATER, 300.0);
            blackTea.put(Ingredient.GINGER_SYRUP, 30.0);
            blackTea.put(Ingredient.SUGAR_SYRUP, 50.0);
            blackTea.put(Ingredient.TEA_LEAVES_SYRUP, 30.0);
            coffeeMachine.addNewBeverage(new Product("002", "Black Tea", blackTea));

            Map<Ingredient, Double> hotCoffee = new HashMap<>();
            hotCoffee.put(Ingredient.HOT_WATER, 100.0);
            hotCoffee.put(Ingredient.GINGER_SYRUP, 30.0);
            hotCoffee.put(Ingredient.HOT_MILK, 400.0);
            hotCoffee.put(Ingredient.SUGAR_SYRUP, 50.0);
            hotCoffee.put(Ingredient.TEA_LEAVES_SYRUP, 30.0);
            coffeeMachine.addNewBeverage(new Product("003", "Hot Coffee", hotCoffee));

            Map<Ingredient, Double> greenTea = new HashMap<>();
            greenTea.put(Ingredient.HOT_WATER, 100.0);
            greenTea.put(Ingredient.GINGER_SYRUP, 30.0);
            greenTea.put(Ingredient.SUGAR_SYRUP, 50.0);
            greenTea.put(Ingredient.TEA_LEAVES_SYRUP, 30.0);
            coffeeMachine.addNewBeverage(new Product("004", "Green Tea", greenTea));

            coffeeMachine.pourBeverage("001", 2);
            coffeeMachine.pourBeverage("002", 1);
            coffeeMachine.pourBeverage("003", 3);
            coffeeMachine.pourBeverage("004", 2);

        } catch (IOException e) {
            // error handling
        }
	// write your code here
    }
}
