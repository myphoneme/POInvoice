package com.phoneme.poinvoice.ui.po.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.phoneme.poinvoice.R;
import com.phoneme.poinvoice.ui.po.viewmodel.VendorListViewModel;

public class VendorEditFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_edit_vendor, container, false);
        return root;
    }
}
