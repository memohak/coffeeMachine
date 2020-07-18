package com.company.enums;

import static com.company.enums.MeasurementUnit.ML;

public enum Ingredient {
    HOT_WATER("Hot Water", ML), HOT_MILK("Hot Milk", ML), GINGER_SYRUP("Ginger Syrup", ML), SUGAR_SYRUP("Sugar Syrup", ML), TEA_LEAVES_SYRUP("Tea Leaves Syrup", ML);

    private String name;
    private MeasurementUnit baseUnit;
    Ingredient(String name, MeasurementUnit baseUnit) {
        this.name = name;
        this.baseUnit = baseUnit;
    }

    public String getName() {
        return name;
    }
    public MeasurementUnit getBaseUnit() {
        return baseUnit;
    }
}
