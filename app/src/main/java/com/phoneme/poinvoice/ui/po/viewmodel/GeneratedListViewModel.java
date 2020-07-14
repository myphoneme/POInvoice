package com.phoneme.poinvoice.ui.po.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GeneratedListViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GeneratedListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}