package com.openclassrooms.rebonnte.ui.medicine;

import com.openclassrooms.rebonnte.ui.history.History;

import java.util.List;

public class Medicine {
    private String name;
    private int stock;
    private String nameAisle;
    private List<History> histories;

    public Medicine(String name, int stock, String nameAisle, List<History> histories) {
        this.name = name;
        this.stock = stock;
        this.nameAisle = nameAisle;
        this.histories = histories;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getNameAisle() {
        return nameAisle;
    }

    public void setNameAisle(String nameAisle) {
        this.nameAisle = nameAisle;
    }

    public List<History> getHistories() {
        return histories;
    }
}
