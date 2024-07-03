package com.openclassrooms.rebonnte.ui.medicine;

import static com.openclassrooms.rebonnte.MainActivity.mainActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.rebonnte.databinding.ActivityMedicineDetailBinding;
import com.openclassrooms.rebonnte.ui.history.History;
import com.openclassrooms.rebonnte.ui.history.HistoryAdapter;

import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

public class MedicineDetailActivity extends AppCompatActivity {

    Medicine medicine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMedicineDetailBinding binding = ActivityMedicineDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MedicineViewModel viewModel =
                new ViewModelProvider(mainActivity).get(MedicineViewModel.class);

        RecyclerView recyclerView = binding.recyclerHistory;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        getSupportActionBar().setTitle(getIntent().getStringExtra("nameMedicine"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        viewModel.getMedicines().observe(this, (medicines -> {
            medicine = medicines.stream().filter((med) -> Objects.equals(med.getName(), getIntent().getStringExtra("nameMedicine"))).collect(Collectors.toList()).get(0);
            HistoryAdapter adapter = new HistoryAdapter(medicine.getHistories());
            binding.textFieldName.getEditText().setText(medicine.getName());
            binding.textFieldStock.getEditText().setText(String.format("%d", medicine.getStock()));
            binding.textFieldAisle.getEditText().setText(medicine.getNameAisle());
            recyclerView.setAdapter(adapter);
            binding.minusOne.setOnClickListener(v -> {
                int stock = Integer.parseInt(binding.textFieldStock.getEditText().getText().toString());
                if (stock > 0) {
                    stock--;
                    binding.textFieldStock.getEditText().setText(String.valueOf(stock));
                }
                medicine.getHistories().add(new History(medicine.getName(), "efeza56f1e65f", new Date().toString(), "Updated medicine details"));
                adapter.updateHistorys(medicine.getHistories());
            });
            binding.plusOne.setOnClickListener(v -> {
                int stock = Integer.parseInt(binding.textFieldStock.getEditText().getText().toString());
                stock++;
                binding.textFieldStock.getEditText().setText(String.valueOf(stock));
                medicine.getHistories().add(new History(medicine.getName(), "efeza56f1e65f", new Date().toString(), "Updated medicine details"));
                adapter.updateHistorys(medicine.getHistories());
            });
        }));
    }
}