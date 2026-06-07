package com.example.dndhepler;

public class Weapons {
    private final String name;
    private final String description;
    private final String price;
    private final String damage;
    private final String weight;
    private final String property;

    public Weapons(String name,
                   String description,
                   String price,
                   String damage,
                   String weight,
                   String property) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.damage = damage;
        this.weight = weight;
        this.property = property;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getPrice() {
        return price;
    }
    public String getDamage() {
        return damage;
    }
    public String getWeight() {
        return weight;
    }

    public String getProperty() {
        return property;
    }
}
