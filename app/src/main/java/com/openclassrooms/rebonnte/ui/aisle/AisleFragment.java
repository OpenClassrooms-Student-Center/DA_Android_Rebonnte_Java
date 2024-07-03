package com.openclassrooms.rebonnte.ui.aisle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.rebonnte.databinding.FragmentAisleBinding;

public class AisleFragment extends Fragment {

    private FragmentAisleBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AisleViewModel aisleViewModel =
                new ViewModelProvider(requireActivity()).get(AisleViewModel.class);

        binding = FragmentAisleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AisleAdapter adapter = new AisleAdapter(aisleViewModel.getAisles().getValue());
        recyclerView.setAdapter(adapter);

        aisleViewModel.getAisles().observe(getViewLifecycleOwner(), aisles -> {
            adapter.updateAisles(aisles);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
