package com.example.unipool.ui.manage;

public class ModelClass {
    private int rvPicture, rvGenderIcon;
    private String rvName;
    private String rvNumber;
    private String rvFees;
    private String rvLocation;
    private String rvDestination;
    private int rvRating;
    private String rvExtraNotes;
    private int id;

    public ModelClass(int rvPicture, int rvGenderIcon, String rvName, String rvNumber, String rvFees, String rvLocation, String rvDestination, int id) {
        this.rvPicture = rvPicture;
        this.rvGenderIcon = rvGenderIcon;
        this.rvName = rvName;
        this.rvNumber = rvNumber;
        this.rvFees = rvFees;
        this.rvLocation = rvLocation;
        this.rvDestination = rvDestination;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRvPicture() {
        return rvPicture;
    }

    public int getRvGenderIcon() {
        return rvGenderIcon;
    }

    public String getRvName() {
        return rvName;
    }

    public String getRvNumber() {
        return rvNumber;
    }

    public String getRvFees() {
        return rvFees;
    }

    public String getRvLocation() {
        return rvLocation;
    }

    public String getRvDestination() {
        return rvDestination;
    }

    public int getRvRating() {
        return rvRating;
    }

    public String getRvExtraNotes() {
        return rvExtraNotes;
    }
}
