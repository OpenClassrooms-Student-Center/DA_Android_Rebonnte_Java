package com.openclassrooms.rebonnte.ui.aisle;

import static com.openclassrooms.rebonnte.MainActivity.mainActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.rebonnte.databinding.ActivityAisleDetailBinding;
import com.openclassrooms.rebonnte.ui.medicine.MedicineAdapter;
import com.openclassrooms.rebonnte.ui.medicine.MedicineViewModel;

import java.util.Objects;
import java.util.stream.Collectors;

public class AisleDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityAisleDetailBinding binding = ActivityAisleDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MedicineViewModel viewModel = new ViewModelProvider(mainActivity).get(MedicineViewModel.class);

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MedicineAdapter adapter = new MedicineAdapter(viewModel.getMedicines().getValue());
        recyclerView.setAdapter(adapter);

        getSupportActionBar().setTitle(getIntent().getStringExtra("nameAisle"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewModel.getMedicines().observe(this, (medicines -> {
            adapter.updateMedicines(medicines.stream().filter((med) -> Objects.equals(med.getNameAisle(), getIntent().getStringExtra("nameAisle"))).collect(Collectors.toList()));
        }));
    }
}