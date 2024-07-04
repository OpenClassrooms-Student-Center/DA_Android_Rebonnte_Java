package com.openclassrooms.rebonnte.ui.aisle;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.rebonnte.R;

import java.util.List;

public class AisleAdapter extends RecyclerView.Adapter<AisleAdapter.AisleViewHolder> {
    private List<Aisle> aisles;

    public AisleAdapter(List<Aisle> aisles) {
        this.aisles = aisles;
    }

    @NonNull
    @Override
    public AisleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.aisle_item, parent, false);
        return new AisleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AisleViewHolder holder, int position) {
        Aisle aisle = aisles.get(position);
        holder.nameTextView.setText(aisle.getName());
        holder.itemView.setOnClickListener(view -> startDetailActivity(holder.itemView.getContext(), aisle.getName()));
    }

    private void startDetailActivity(Context context, String name) {
        Intent intent = new Intent(context, AisleDetailActivity.class);
        intent.putExtra("nameAisle", name);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return aisles.size();
    }

    public void updateAisles(List<Aisle> aisles) {
        this.aisles = aisles;
        notifyDataSetChanged();
    }

    static class AisleViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;

        public AisleViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.aisle_name);
        }
    }
}
