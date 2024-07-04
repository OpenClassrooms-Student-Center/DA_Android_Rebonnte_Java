package com.openclassrooms.rebonnte.ui.medicine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.rebonnte.databinding.FragmentMedicineBinding;

public class MedicineFragment extends Fragment {

    private FragmentMedicineBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MedicineViewModel viewModel =
                new ViewModelProvider(requireActivity()).get(MedicineViewModel.class);

        binding = FragmentMedicineBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MedicineAdapter adapter = new MedicineAdapter(viewModel.getMedicines().getValue());
        recyclerView.setAdapter(adapter);

        viewModel.getMedicines().observe(requireActivity(), adapter::updateMedicines);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}