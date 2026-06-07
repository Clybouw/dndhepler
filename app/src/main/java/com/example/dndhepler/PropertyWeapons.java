package com.example.dndhepler;

public class PropertyWeapons {
    private final String name;
    private final String description;

    public PropertyWeapons(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
