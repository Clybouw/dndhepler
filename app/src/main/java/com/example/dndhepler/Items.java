package com.example.dndhepler;

public class Items {
    public String name;
    public String aligment;
    public String price;
    public String description;
    public String tableDescription;

    public Items (String name,
                  String aligment,
                  String price,
                  String description,
                  String tableDescription) {
        this.name = name;
        this.price = price;
        this.aligment = aligment;
        this.description = description;
        this.tableDescription = tableDescription;
    }

    public String getName() {
        return name;
    }

    public String getAligment() {
        return aligment;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getTableDescription() {
        return tableDescription;
    }
}
