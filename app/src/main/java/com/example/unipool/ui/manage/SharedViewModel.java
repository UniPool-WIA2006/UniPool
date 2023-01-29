package com.example.unipool.ui.manage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<Integer> data = new MutableLiveData<Integer>();

    public void setData(Integer item) {
        data.setValue(item);
    }

    public LiveData<Integer> getData() {
        return data;
    }
}
