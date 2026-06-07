package com.example.dndhepler;

public class CategoryWeapons {
    public static final int TYPE_CATEGORY = 0;
    public static final int TYPE_WEAPONS = 1;
    private final int type;
    private final String categoryName;
    private final Weapons weapons;

    public CategoryWeapons(int type,
                           String categoryName,
                           Weapons weapons) {
        this.type = type;
        this.categoryName = categoryName;
        this.weapons = weapons;
    }

    public int getType() {
        return type;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Weapons getWeapons() {
        return weapons;
    }
}
