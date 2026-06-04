package com.example.dndhepler;

public class Spells {
    public String name;
    public String level;
    public String casttime;
    public String range;
    public String components;
    public String duraction;
    public String classes;
    public String subclasses;
    public String description;
    public String lvlup;

    public Spells(
            String name,
            String level,
            String casttime,
            String range,
            String components,
            String duraction,
            String classes,
            String subclasses,
            String description,
            String lvlup
    ) {
        this.name = name;
        this.level = level;
        this.casttime = casttime;
        this.range = range;
        this.components = components;
        this.duraction = duraction;
        this.classes = classes;
        this.subclasses= subclasses;
        this.description = description;
        this.lvlup = lvlup;
    }
}
