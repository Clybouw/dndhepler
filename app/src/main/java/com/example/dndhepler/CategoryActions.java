package com.example.dndhepler;

public class CategoryActions {
    public static final int TYPE_CATEGORY = 0;
    public static final int TYPE_ACTIONS = 1;
    private final int type;
    private final String name;
    private final String description;
    private final Actions actions;

    public CategoryActions (int type,
                            String name,
                            String description,
                            Actions actions) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.actions = actions;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Actions getActions() {
        return actions;
    }
}
