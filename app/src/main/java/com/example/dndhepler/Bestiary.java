package com.example.dndhepler;

public class Bestiary {
    public String name;
    public String danger;
    public String type;
    public String ac;
    public String initiative;
    public String hp;
    public String move;
    public String abilities;
    public String skills;
    public String resists;
    public String immuns;
    public String feel;
    public String languages;
    public String area;
    public String exp;
    public String features;
    public String actions;
    public String legendaryActions;
    public  String bonusActions;
    public String reactions;

    public Bestiary(
            String name,
            String danger,
            String type,
            String ac,
            String initiative,
            String hp,
            String move,
            String abilities,
            String skills,
            String resists,
            String immuns,
            String feel,
            String languages,
            String area,
            String exp,
            String features,
            String actions,
            String legendaryActions,
            String bonusActions,
            String reactions
    ) {
        this.name = name;
        this.danger = danger;
        this.type = type;
        this.ac = ac;
        this.initiative = initiative;
        this.hp = hp;
        this.move = move;
        this.abilities = abilities;
        this.skills = skills;
        this.resists = resists;
        this.immuns = immuns;
        this.feel = feel;
        this.languages = languages;
        this.area = area;
        this.exp = exp;
        this.features = features;
        this.actions = actions;
        this.legendaryActions = legendaryActions;
        this.bonusActions = bonusActions;
        this.reactions = reactions;
    }
}