package com.example.unipool;

public class ModelClass {
    private int rvPicture, rvGenderIcon;
    private String rvName;
    private String rvNumber;
    private String rvFees;
    private String rvLocation;
    private String rvDestination;

    ModelClass(int rvPicture, int rvGenderIcon, String rvName, String rvNumber, String rvFees, String rvLocation, String rvDestination) {
        this.rvPicture = rvPicture;
        this.rvGenderIcon = rvGenderIcon;
        this.rvName = rvName;
        this.rvNumber = rvNumber;
        this.rvFees = rvFees;
        this.rvLocation = rvLocation;
        this.rvDestination = rvDestination;
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

}
