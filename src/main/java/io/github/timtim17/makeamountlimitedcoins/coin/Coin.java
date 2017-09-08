package io.github.timtim17.makeamountlimitedcoins.coin;

public enum Coin {
    PENNY(1, "Penny", "Pennies"), NICKEL(5, "Nickel", "Nickles"), DIME(10, "Dime", "Dimes"),
    QUARTER(25, "Quarter", "Quarters"), HALF_DOLLAR(50, "Half Dollar", "Half Dollars"),
    DOLLAR(100, "Dollar Coin", "Dollar Coins");

    private int value;
    private String name;
    private String pluralName;

    Coin(int value, String name, String pluralName) {
        this.value = value;
        this.name = name;
        this.pluralName = pluralName;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getNamePlural() {
        return pluralName;
    }
}
