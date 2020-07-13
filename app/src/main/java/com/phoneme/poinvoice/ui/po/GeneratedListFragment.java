package com.phoneme.poinvoice.ui.po;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.phoneme.poinvoice.R;

public class GeneratedListFragment extends Fragment {

    private GeneratedListViewModel generatedListViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        generatedListViewModel =
                ViewModelProviders.of(this).get(GeneratedListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_generatedlist, container, false);
//        final TextView textView = root.findViewById(R.id.text_slideshow);
//        generatedListViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}