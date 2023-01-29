package com.example.unipool.ui.manage;

public class HomeData {
    private int rvHomePicture;
    private String rvHomeName, rvHomeNumber, rvHomeFees, rvHomeLocation, rvHomeDestination, rvTrustPoint, rvHomeExtraNotes;

    public HomeData(String rvHomeLocation, String rvHomeDestination, String rvHomeFees) {
        this.rvHomeLocation = rvHomeLocation;
        this.rvHomeDestination = rvHomeDestination;
        this.rvHomeFees = rvHomeFees;
    }

    public int getRvHomePicture() {
        return rvHomePicture;
    }

    public String getRvHomeName() {
        return rvHomeName;
    }

    public String getRvHomeNumber() {
        return rvHomeNumber;
    }

    public String getRvHomeFees() {
        return rvHomeFees;
    }

    public String getRvHomeLocation() {
        return rvHomeLocation;
    }

    public String getRvHomeDestination() {
        return rvHomeDestination;
    }

    public String getRvTrustPoint() {
        return rvTrustPoint;
    }

    public String getRvHomeExtraNotes() {
        return rvHomeExtraNotes;
    }
}