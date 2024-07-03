package com.openclassrooms.rebonnte.ui.history;

public class History {
    private String medicineName;
    private String userId;
    private String date;
    private String details;

    public History(String medicineName, String userId, String date, String details) {
        this.medicineName = medicineName;
        this.userId = userId;
        this.date = date;
        this.details = details;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
