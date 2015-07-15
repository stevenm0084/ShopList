package com.example.pc1.shoplist;

/**
 * Created by PC1 on 15-Jul-15.
 */
public class Item {

    Category category;
    String name;

    public Item(String name, Category category) {
        this.name = name;
        this.category = category;

    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
