package com.openclassrooms.rebonnte.ui.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.rebonnte.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private List<History> histories;

    public HistoryAdapter(List<History> histories) {
        this.histories = histories;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.history_item, parent, false);
        return new HistoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        History history = histories.get(position);
        holder.nameTextView.setText(history.getMedicineName());
        holder.userTextView.setText("User: " + history.getUserId());
        holder.dateTextView.setText("Date: " + history.getDate());
        holder.detailsTextView.setText("Details : " + history.getDetails());
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    public void updateHistorys(List<History> histories) {
        this.histories = histories;
        notifyDataSetChanged();
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView userTextView;
        TextView dateTextView;
        TextView detailsTextView;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.medicine_name);
            userTextView = itemView.findViewById(R.id.user_name);
            dateTextView = itemView.findViewById(R.id.date);
            detailsTextView = itemView.findViewById(R.id.details);
        }
    }
}