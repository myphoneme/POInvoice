package com.phoneme.poinvoice.ui.invoice.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phoneme.poinvoice.R;
import com.phoneme.poinvoice.ui.invoice.adapter.InvoiceManagementAdapter;

public class InvoiceManagementFragment extends Fragment implements InvoiceManagementAdapter.OnItemClickListener {
    private RecyclerView recyclerview;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_invoice_management, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerview=(RecyclerView)view.findViewById(R.id.invoice_management);
        setAdapter();
    }
    private void setAdapter(){
        InvoiceManagementAdapter adapter=new InvoiceManagementAdapter(getContext(),this);
        recyclerview.setAdapter(adapter);

        GridLayoutManager manager;
        manager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);

        recyclerview.setLayoutManager(manager);
    }

    public void onItemClick(int position){}
    public void onItemClick2(int position){}
    public void onItemClick3(int position){}
}
