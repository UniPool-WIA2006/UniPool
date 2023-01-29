package com.example.unipool.ui.manage;

public class HomeData {
    private int rvHomePicture;
    private String rvHomeName, rvHomeNumber, rvHomeFees, rvHomeLocation, rvHomeDestination, rvTrustPoint, rvHomeExtraNotes;
    private int id;

    public HomeData(String rvHomeName, String rvHomeNumber, String rvHomeLocation, String rvHomeDestination, String rvHomeFees, int id) {
        this.rvHomeName = rvHomeName;
        this.rvHomeNumber = rvHomeNumber;
        this.rvHomeLocation = rvHomeLocation;
        this.rvHomeDestination = rvHomeDestination;
        this.rvHomeFees = rvHomeFees;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}