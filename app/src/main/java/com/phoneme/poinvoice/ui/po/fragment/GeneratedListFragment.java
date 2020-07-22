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
import com.phoneme.poinvoice.ui.po.viewmodel.GeneratedListViewModel;
import com.phoneme.poinvoice.ui.po.adapter.GeneratedListAdapter;

public class GeneratedListFragment extends Fragment implements GeneratedListAdapter.OnItemClickListener{

    private GeneratedListViewModel generatedListViewModel;
    private RecyclerView recyclerView;
    private Button generateNewPOButton;
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview_generated_list);
        generateNewPOButton=(Button)view.findViewById(R.id.add_vendor_button);
        generateNewPOButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.nav_create_po);
            }
        });
        //GeneratedListAdapter adapter=new GeneratedListAdapter(getContext());
        GeneratedListAdapter adapter=new GeneratedListAdapter(getContext(),this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearVertical = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearVertical);
    }
    public void onItemClick(int position){
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.nav_payment_upload);
    }
    public void onItemClick2(int position){
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.nav_invoice_add_upload);
    }
}