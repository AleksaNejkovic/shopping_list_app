package com.example.listazakupovinu;

import java.io.Serializable;

public class Ingredient implements Serializable {

    private String ingredient;

    public Ingredient(String ingredient) {

        this.ingredient = ingredient;
    }

    public String getIngredient() {
        return ingredient;
    }


}