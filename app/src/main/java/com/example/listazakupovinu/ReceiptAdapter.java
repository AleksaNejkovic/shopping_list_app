package com.example.listazakupovinu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.ReceiptViewHolder> {

    List<Receipt> receipts;
    Context context;
    RecyclerViewClickListener listener;

    public ReceiptAdapter(Context context, List<Receipt> receipts, RecyclerViewClickListener listener) {
        this.context = context;
        this.receipts = receipts;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ReceiptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_in_rv, parent, false);
        return new ReceiptViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptViewHolder holder, int position) {
        Receipt receipt = receipts.get(position);
        holder.name.setText(receipt.getName());

    }

    @Override
    public int getItemCount() {
        return receipts.size();
    }

    public class ReceiptViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        ImageButton deleteBtn;


        public ReceiptViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            deleteBtn = itemView.findViewById(R.id.delete_row);

            itemView.setOnClickListener(this);

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDelete(itemView, getAdapterPosition());
                }
            });
        }


        @Override
        public void onClick(View v) {
            listener.onClick(itemView, getAdapterPosition());
        }

    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);

        void onDelete(View v, int position);
    }

}
