package com.example.victorbruno.karimaprodutor.fragment;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.victorbruno.karimaprodutor.R;
import com.example.victorbruno.karimaprodutor.adapter.TabsAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class GestaoFragment extends Fragment {
    private CollapsingToolbarLayout collapsingToolbarLayout;



    public GestaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment




        return inflater.inflate(R.layout.fragment_gestao, container, false);


    }

}
