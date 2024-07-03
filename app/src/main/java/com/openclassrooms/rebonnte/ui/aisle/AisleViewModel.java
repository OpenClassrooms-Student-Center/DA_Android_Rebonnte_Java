package com.openclassrooms.rebonnte.ui.aisle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AisleViewModel extends ViewModel {

    private final MutableLiveData<List<Aisle>> aisles;

    public AisleViewModel() {
        aisles = new MutableLiveData<>();
        aisles.setValue(Collections.singletonList(new Aisle("Main Aisle")));
    }

    public LiveData<List<Aisle>> getAisles() {
        return aisles;
    }

    public void addRandomAisle() {
        List<Aisle> currentAisles = new ArrayList<>(aisles.getValue());
        currentAisles.add(new Aisle("Aisle " + (currentAisles.size() + 1)));
        aisles.setValue(currentAisles);
    }
}

