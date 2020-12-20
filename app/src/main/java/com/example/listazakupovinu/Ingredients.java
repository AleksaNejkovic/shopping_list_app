package com.example.listazakupovinu;

import java.io.Serializable;
import java.util.List;

public class Ingredients implements Serializable {
    private String title;
    private List<Ingredient> items;

    public Ingredients(String title, List<Ingredient> items) {
        this.title = title;
        this.items = items;
    }

    public String getTitle() {
        return title;
    }
    public List<Ingredient> getItems() {
        return items;
    }
}

