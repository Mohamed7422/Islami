package com.mhm.islami.ui;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mhm.islami.Constants;
import com.mhm.islami.R;
import com.mhm.islami.adapters.SurasRecyclerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuraanFragment extends Fragment {

    RecyclerView recyclerView ;
    RecyclerView.LayoutManager layoutManager;
    SurasRecyclerAdapter adapter;

    public QuraanFragment() {
      //required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quraan,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            layoutManager = new GridLayoutManager(getContext(),3,RecyclerView.HORIZONTAL,false);
        }
        adapter = new SurasRecyclerAdapter(Constants.ArSuras);
        adapter.setOnItemClickListener(new SurasRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String name) {

                Intent intent = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    intent = new Intent(getContext(), SuraaContentActivity.class);
                }
                intent.putExtra(Constants.EXTRA_POSITION,position);
                intent.putExtra(Constants.EXTRA_TITLE,name);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }
}