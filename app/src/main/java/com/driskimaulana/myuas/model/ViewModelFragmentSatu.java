package com.driskimaulana.myuas.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelFragmentSatu extends ViewModel {
    public MutableLiveData<String> data;
    public MutableLiveData<BoredModel> boreds;
    public LiveData<String> getData() {

        if (boreds == null) {
            boreds = new MutableLiveData<BoredModel>();
        }

//        if (data == null) {
//            data = new MutableLiveData<String>();
//            loadData();
//        }
        return data;
    }

    public void setData(String newData) {
        this.data.setValue(newData);
    }

    private void loadData(){

    }
}