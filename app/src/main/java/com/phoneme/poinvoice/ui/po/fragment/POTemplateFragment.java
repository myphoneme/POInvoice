package com.phoneme.poinvoice.ui.po.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phoneme.poinvoice.R;
import com.phoneme.poinvoice.ui.po.viewmodel.POTemplateViewModel;
import com.phoneme.poinvoice.ui.po.adapter.POTemplateAdapter;

public class POTemplateFragment extends Fragment implements POTemplateAdapter.OnItemClickListener{

    private com.phoneme.poinvoice.ui.po.viewmodel.POTemplateViewModel POTemplateViewModel;
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
        Button createpotemplate=(Button)view.findViewById(R.id.add_new_po_template);
        createpotemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.nav_create_new_po_templatge);
            }
        });
        //POTemplateAdapter adapter=new POTemplateAdapter(getContext());
        POTemplateAdapter adapter=new POTemplateAdapter(getContext(),this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearVertical = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearVertical);
    }

    public void onItemClick(int position){
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.nav_po_template_edit);
    }
    public void onItemClick2(int position){}
}