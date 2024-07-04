package com.openclassrooms.rebonnte.ui.medicine;

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

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder> {
    private List<Medicine> medicines;

    public MedicineAdapter(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.medicine_item, parent, false);
        return new MedicineViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineViewHolder holder, int position) {
        Medicine medicine = medicines.get(position);
        holder.nameTextView.setText(medicine.getName());
        holder.stockTextView.setText("stock : " + medicine.getStock());
        holder.itemView.setOnClickListener(view -> startDetailActivity(holder.itemView.getContext(), medicine.getName()));
    }

    private void startDetailActivity(Context context, String name) {
        Intent intent = new Intent(context, MedicineDetailActivity.class);
        intent.putExtra("nameMedicine", name);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return medicines.size();
    }

    public void updateMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
        notifyDataSetChanged();
    }

    static class MedicineViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView stockTextView;

        public MedicineViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.medicine_name);
            stockTextView = itemView.findViewById(R.id.medicine_stock);
        }
    }
}