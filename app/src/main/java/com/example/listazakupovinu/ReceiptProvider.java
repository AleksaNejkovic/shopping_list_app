package com.example.listazakupovinu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReceiptProvider {
    private static List<Receipt> receipts;

    private static boolean initialized = false;

    public static List<Receipt> getReceipts() {
        return receipts;
    }

    public static boolean isInitialized() {
        return initialized;
    }

    public static void initialize(JSONArray response) {
        receipts = new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject js = response.getJSONObject(i);
                JSONArray ingredients = js.getJSONArray("ingredients");
                List<Ingredients> ingredientsList = new ArrayList<Ingredients>();
                for (int j = 0; j < ingredients.length(); j++) {
                    JSONObject js1 = ingredients.getJSONObject(j);
                    String title = js1.getString("title");
                    JSONArray item = js1.getJSONArray("item");
                    List<Ingredient> itemList = new ArrayList<Ingredient>();
                    for (int k = 0; k < item.length(); k++) {
                        JSONObject ingredientobj = item.getJSONObject(k);
                        String ingredient = ingredientobj.getString("ingredient");
                        itemList.add(new Ingredient(ingredient));
                    }
                    ingredientsList.add(new Ingredients(title, itemList));
                }
                receipts.add(new Receipt(js.getString("name"), ingredientsList));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        initialized = true;
    }
}
