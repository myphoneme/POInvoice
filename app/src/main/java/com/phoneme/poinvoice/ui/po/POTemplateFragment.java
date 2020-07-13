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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phoneme.poinvoice.R;
import com.phoneme.poinvoice.ui.po.adapter.GeneratedListAdapter;
import com.phoneme.poinvoice.ui.po.adapter.POTemplateAdapter;

public class POTemplateFragment extends Fragment {

    private POTemplateViewModel POTemplateViewModel;
    private RecyclerView recyclerView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        POTemplateViewModel =
                ViewModelProviders.of(this).get(POTemplateViewModel.class);
        View root = inflater.inflate(R.layout.fragment_potemplate, container, false);
//        final TextView textView = root.findViewById(R.id.text_tools);
//        POTemplateViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview_po_template);
        POTemplateAdapter adapter=new POTemplateAdapter(getContext());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearVertical = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearVertical);
    }
}