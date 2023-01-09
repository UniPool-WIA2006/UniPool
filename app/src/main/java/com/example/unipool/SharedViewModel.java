package com.example.unipool;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    private MutableLiveData<String> requestDestination, requestLocation, requestFees, requestExtraNotes, requestDelete, requestCreate, offerDestination, offerLocation, offerFees, offerExtraNotes, offerDelete, offerCreate, requestDestination1, requestLocation1, requestFees1, requestExtraNotes1, requestDelete1, requestCreate1, offerDestination1, offerLocation1, offerFees1, offerExtraNotes1, offerDelete1, offerCreate1;

    public void setRequestLocation(String str) {
        requestLocation.setValue(str);
    }
    public void setRequestDestination(String str) {
        requestDestination.setValue(str);
    }
    public void setRequestFees(String str) {
        requestFees.setValue(str);
    }
    public void setRequestExtraNotes(String str) {
        requestExtraNotes.setValue(str);
    }
    public void setRequestDelete(String str) {
        requestDelete.setValue(str);
    }
    public void setRequestCreate(String str) {
        requestCreate.setValue(str);
    }

    public void setRequestLocation1(String str) {
        requestLocation1.setValue(str);
    }
    public void setRequestDestination1(String str) {
        requestDestination1.setValue(str);
    }
    public void setRequestFees1(String str) {
        requestFees1.setValue(str);
    }
    public void setRequestExtraNotes1(String str) {
        requestExtraNotes1.setValue(str);
    }
    public void setRequestDelete1(String str) {
        requestDelete1.setValue(str);
    }
    public void setRequestCreate1(String str) {
        requestCreate1.setValue(str);
    }


    public void setOfferLocation(String str) {
        offerLocation.setValue(str);
    }
    public void setOfferDestination(String str) {
        offerDestination.setValue(str);
    }
    public void setOfferFees(String str) {
        offerFees.setValue(str);
    }
    public void setOfferExtraNotes(String str) {
        offerExtraNotes.setValue(str);
    }
    public void setOfferDelete(String str) {
        requestDelete.setValue(str);
    }
    public void setOfferCreate(String str) {
        offerCreate.setValue(str);
    }

    public void setOfferLocation1(String str) {
        offerLocation1.setValue(str);
    }
    public void setOfferDestination1(String str) {
        offerDestination1.setValue(str);
    }
    public void setOfferFees1(String str) {
        offerFees1.setValue(str);
    }
    public void setOfferExtraNotes1(String str) {
        offerExtraNotes1.setValue(str);
    }
    public void setOfferDelete1(String str) {
        requestDelete1.setValue(str);
    }
    public void setOfferCreate1(String str) {
        offerCreate1.setValue(str);
    }


    public MutableLiveData<String> getRequestLocation() {
        if(requestLocation == null) {
            requestLocation = new MutableLiveData<>();
        }
        return requestLocation;
    }
    public MutableLiveData<String> getRequestDestination() {
        if(requestDestination == null) {
            requestDestination = new MutableLiveData<>();
        }
        return requestDestination;
    }
    public MutableLiveData<String> getRequestFees() {
        if(requestFees == null) {
            requestFees = new MutableLiveData<>();
        }
        return requestFees;
    }
    public MutableLiveData<String> getRequestExtraNotes() {
        if(requestExtraNotes == null) {
            requestExtraNotes = new MutableLiveData<>();
        }
        return requestExtraNotes;
    }
    public MutableLiveData<String> getRequestDelete() {
        if(requestDelete == null) {
            requestDelete = new MutableLiveData<>();
        }
        return requestDelete;
    }
    public MutableLiveData<String> getRequestCreate() {
        if(requestCreate == null) {
            requestCreate = new MutableLiveData<>();
        }
        return requestCreate;
    }

    public MutableLiveData<String> getOfferLocation() {
        if(offerLocation == null) {
            offerLocation = new MutableLiveData<>();
        }
        return offerLocation;
    }
    public MutableLiveData<String> getOfferDestination() {
        if(offerDestination == null) {
            offerDestination = new MutableLiveData<>();
        }
        return offerDestination;
    }
    public MutableLiveData<String> getOfferFees() {
        if(offerFees == null) {
            offerFees = new MutableLiveData<>();
        }
        return offerFees;
    }
    public MutableLiveData<String> getOfferExtraNotes() {
        if(offerExtraNotes == null) {
            offerExtraNotes = new MutableLiveData<>();
        }
        return offerExtraNotes;
    }
    public MutableLiveData<String> getOfferDelete() {
        if(offerDelete == null) {
            offerDelete = new MutableLiveData<>();
        }
        return offerDelete;
    }
    public MutableLiveData<String> getOfferCreate() {
        if(offerCreate == null) {
            offerCreate = new MutableLiveData<>();
        }
        return offerCreate;
    }
//  edit & delete usage
    public MutableLiveData<String> getRequestLocation1() {
        if(requestLocation1 == null) {
            requestLocation1 = new MutableLiveData<>();
        }
        return requestLocation1;
    }
    public MutableLiveData<String> getRequestDestination1() {
        if(requestDestination1 == null) {
            requestDestination1 = new MutableLiveData<>();
        }
        return requestDestination1;
    }
    public MutableLiveData<String> getRequestFees1() {
        if(requestFees1 == null) {
            requestFees1 = new MutableLiveData<>();
        }
        return requestFees1;
    }
    public MutableLiveData<String> getRequestExtraNotes1() {
        if(requestExtraNotes1 == null) {
            requestExtraNotes1 = new MutableLiveData<>();
        }
        return requestExtraNotes1;
    }
    public MutableLiveData<String> getRequestDelete1() {
        if(requestDelete1 == null) {
            requestDelete1 = new MutableLiveData<>();
        }
        return requestDelete1;
    }
    public MutableLiveData<String> getRequestCreate1() {
        if(requestCreate1 == null) {
            requestCreate1 = new MutableLiveData<>();
        }
        return requestCreate1;
    }

    public MutableLiveData<String> getOfferLocation1() {
        if(offerLocation1 == null) {
            offerLocation1 = new MutableLiveData<>();
        }
        return offerLocation1;
    }
    public MutableLiveData<String> getOfferDestination1() {
        if(offerDestination1 == null) {
            offerDestination1 = new MutableLiveData<>();
        }
        return offerDestination1;
    }
    public MutableLiveData<String> getOfferFees1() {
        if(offerFees1 == null) {
            offerFees1 = new MutableLiveData<>();
        }
        return offerFees1;
    }
    public MutableLiveData<String> getOfferExtraNotes1() {
        if(offerExtraNotes1 == null) {
            offerExtraNotes1 = new MutableLiveData<>();
        }
        return offerExtraNotes1;
    }
    public MutableLiveData<String> getOfferDelete1() {
        if(offerDelete1 == null) {
            offerDelete1 = new MutableLiveData<>();
        }
        return offerDelete1;
    }
    public MutableLiveData<String> getOfferCreate1() {
        if(offerCreate1 == null) {
            offerCreate1 = new MutableLiveData<>();
        }
        return offerCreate1;
    }
}
