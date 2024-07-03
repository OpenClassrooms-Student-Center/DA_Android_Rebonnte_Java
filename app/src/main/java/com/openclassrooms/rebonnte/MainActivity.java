package com.openclassrooms.rebonnte;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.rebonnte.databinding.ActivityMainBinding;
import com.openclassrooms.rebonnte.ui.aisle.AisleViewModel;
import com.openclassrooms.rebonnte.ui.medicine.MedicineViewModel;

public class MainActivity extends AppCompatActivity {

    public static MainActivity mainActivity;

    private ActivityMainBinding binding;
    private MenuItem searchMenuItem;
    private SearchView searchView;
    private MyBroadcastReceiver myBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = this;
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_aisle, R.id.navigation_medicine)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        FloatingActionButton fab = findViewById(R.id.fab);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.navigation_medicine) {
                fab.show();
                fab.setOnClickListener(view -> {
                    // Ajout de médicament aléatoire
                    MedicineViewModel viewModel = new ViewModelProvider(this).get(MedicineViewModel.class);
                    AisleViewModel viewModelAisle = new ViewModelProvider(this).get(AisleViewModel.class);
                    viewModel.addRandomMedicine(viewModelAisle.getAisles().getValue());
                });
            } else if (destination.getId() == R.id.navigation_aisle) {
                fab.show();
                fab.setOnClickListener(view -> {
                    // Ajout de rayon aléatoire
                    AisleViewModel viewModel = new ViewModelProvider(this).get(AisleViewModel.class);
                    viewModel.addRandomAisle();
                });
            } else {
                fab.hide();
            }

            // Mise à jour de la toolbar
            invalidateOptionsMenu();
        });
        startBroadcastReceiver();
    }

    private void startBroadcastReceiver() {
        myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.rebonnte.ACTION_UPDATE");
        registerReceiver(myBroadcastReceiver, filter);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent("com.rebonnte.ACTION_UPDATE");
            sendBroadcast(intent);
        }, 5000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        searchMenuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchMenuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Pas de traitement spécifique pour le moment
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filtrer par nom
                MedicineViewModel viewModel = new ViewModelProvider(MainActivity.this).get(MedicineViewModel.class);
                viewModel.filterByName(newText);
                return false;
            }
        });

        MenuItem sortNone = menu.findItem(R.id.action_sort_none);
        MenuItem sortByName = menu.findItem(R.id.action_sort_name);
        MenuItem sortByStock = menu.findItem(R.id.action_sort_stock);

        sortNone.setOnMenuItemClickListener(item -> {
            MedicineViewModel viewModel = new ViewModelProvider(MainActivity.this).get(MedicineViewModel.class);
            viewModel.sortByNone();
            return true;
        });

        sortByName.setOnMenuItemClickListener(item -> {
            MedicineViewModel viewModel = new ViewModelProvider(MainActivity.this).get(MedicineViewModel.class);
            viewModel.sortByName();
            return true;
        });

        sortByStock.setOnMenuItemClickListener(item -> {
            MedicineViewModel viewModel = new ViewModelProvider(MainActivity.this).get(MedicineViewModel.class);
            viewModel.sortByStock();
            return true;
        });

        // Afficher les options de menu seulement dans le fragment medicine
        searchMenuItem.setVisible(false);
        sortNone.setVisible(false);
        sortByName.setVisible(false);
        sortByStock.setVisible(false);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        if (navController.getCurrentDestination().getId() == R.id.navigation_medicine) {
            searchMenuItem.setVisible(true);
            sortNone.setVisible(true);
            sortByName.setVisible(true);
            sortByStock.setVisible(true);
        }

        return true;
    }

    private class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(MainActivity.this, "Update reçu", Toast.LENGTH_SHORT).show();
        }
    }
}