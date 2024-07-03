package com.openclassrooms.rebonnte.ui.medicine;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.openclassrooms.rebonnte.ui.aisle.Aisle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class MedicineViewModel extends ViewModel {

    private final MutableLiveData<List<Medicine>> medicines;

    public MedicineViewModel() {
        medicines = new MutableLiveData<>();
        medicines.setValue(new ArrayList<>()); // Initialiser avec une liste vide
    }

    public LiveData<List<Medicine>> getMedicines() {
        return medicines;
    }

    public void addRandomMedicine(List<Aisle> aisles) {
        List<Medicine> currentMedicines = medicines.getValue();
        currentMedicines.add(new Medicine("Medicine " + (currentMedicines.size() + 1), new Random().nextInt(100), aisles.get(new Random().nextInt(aisles.size())).getName(), Collections.emptyList()));
        medicines.setValue(currentMedicines);
    }

    public void filterByName(String name) {
        List<Medicine> currentMedicines = medicines.getValue();
        List<Medicine> filteredMedicines = new ArrayList<>();
        for (Medicine medicine : currentMedicines) {
            if (medicine.getName().toLowerCase().contains(name.toLowerCase())) {
                filteredMedicines.add(medicine);
            }
        }
        medicines.setValue(filteredMedicines);
    }

    public void sortByNone() {
        medicines.setValue(medicines.getValue()); // Pas de tri
    }

    public void sortByName() {
        List<Medicine> currentMedicines = medicines.getValue();
        currentMedicines.sort(Comparator.comparing(Medicine::getName));
        medicines.setValue(currentMedicines);
    }

    public void sortByStock() {
        List<Medicine> currentMedicines = medicines.getValue();
        currentMedicines.sort(Comparator.comparingInt(Medicine::getStock));
        medicines.setValue(currentMedicines);
    }
}

