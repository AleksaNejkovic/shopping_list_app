package com.example.listazakupovinu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv_list;
    ReceiptAdapter adapter;
    ReceiptAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_list=findViewById(R.id.rv_list);

        if (ReceiptProvider.isInitialized()) {
            displayData();
        }
        else {
            getData();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void getData() {
        String json_url="https://api.mocki.io/v1/c1c561f1";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, json_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ReceiptProvider.initialize(response);
                displayData();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag","onErrorResponse"+error.getMessage());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void displayData() {
        setOnClickListener();
        rv_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter=new ReceiptAdapter(getApplicationContext(), ReceiptProvider.getReceipts(), listener);
        rv_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setOnClickListener() {
        listener = new ReceiptAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Receipt receipt = ReceiptProvider.getReceipts().get(position);

                Intent intent=new Intent(getApplicationContext(),ReceiptViewActivity.class);
                intent.putExtra("receipt", receipt);

                startActivity(intent);
            }

            @Override
            public void onDelete(View v, int position) {
                ReceiptProvider.getReceipts().remove(position);
                adapter.notifyItemRemoved(position);
            }
        };
    }
}