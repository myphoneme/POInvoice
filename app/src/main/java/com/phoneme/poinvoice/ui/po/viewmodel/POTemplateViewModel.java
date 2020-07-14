package com.phoneme.poinvoice.ui.po.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class POTemplateViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public POTemplateViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}