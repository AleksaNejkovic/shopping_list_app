package com.example.listazakupovinu;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.MessageFormat;
import java.util.List;


public class ReceiptViewActivity extends AppCompatActivity {
    LayoutInflater layoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        setContentView(R.layout.whole_receipt);

        Receipt receipt = (Receipt)getIntent().getSerializableExtra("receipt");

        TextView name = findViewById(R.id.name);
        name.setText(receipt.getName());

        IngredientsAdapter adapter = new IngredientsAdapter(getApplicationContext(), receipt.getIngredients());
        ListView listView = findViewById(R.id.ingredients);
        listView.setAdapter(adapter);


    }

    class IngredientsAdapter extends ArrayAdapter<Ingredients> {
        List<Ingredients> ingredientsList;

        IngredientsAdapter (Context context, List<Ingredients> ingredients)
        {
            super(context, R.layout.row_in_receipt, R.id.title, ingredients);
            this.ingredientsList =ingredients;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = layoutInflater.inflate(R.layout.row_in_receipt, parent,false);
            Ingredients i = ingredientsList.get(position);

            TextView title = row.findViewById(R.id.title);
            title.setText(i.getTitle());

            ListView items = row.findViewById(R.id.items);
            ItemsAdapter adapter = new ItemsAdapter(getApplicationContext(), i.getItems());
            items.setAdapter(adapter);



            return row;
        }
    }

    class ItemsAdapter extends ArrayAdapter<Ingredient> {
        List<Ingredient> itemsList;

        ItemsAdapter (Context context, List<Ingredient> items)
        {
            super(context, R.layout.single_item, R.id.item, items);
            this.itemsList = items;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = layoutInflater.inflate(R.layout.single_item, parent,false);
            Ingredient i = itemsList.get(position);

            TextView item = row.findViewById(R.id.item);
            item.setText(MessageFormat.format("{0}. {1}", position + 1, i.getIngredient()));

            return row;
        }
    }
}
