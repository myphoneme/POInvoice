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
import com.phoneme.poinvoice.ui.po.viewmodel.VendorListViewModel;
import com.phoneme.poinvoice.ui.po.adapter.VendorListAdapter;

public class VendorListFragment extends Fragment implements VendorListAdapter.OnItemClickListener{

    private VendorListViewModel vendorListViewModel;
    private RecyclerView recyclerView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vendorListViewModel =
                ViewModelProviders.of(this).get(VendorListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_vendorlist, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        vendorListViewModel.getText().observe(this, new Observer<String>() {
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

        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview_vendor_list);
        //VendorListAdapter adapter=new VendorListAdapter(getContext());
        VendorListAdapter adapter=new VendorListAdapter(getContext(),this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearVertical = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearVertical);

        Button button=(Button)view.findViewById(R.id.add_vendor_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.nav_vendor_add);
            }
        });
    }

    public void onItemClick(int position){

        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.nav_vendor_edit);
    }
    public void onItemClick2(int position){

    }
}