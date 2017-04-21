package com.example.victorbruno.karimaprodutor.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.victorbruno.karimaprodutor.R;
import com.example.victorbruno.karimaprodutor.activity.LoginActivity;
import com.parse.ParseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContaFragment extends Fragment {
    private Button sair;


    public ContaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_conta, container, false);

        sair = (Button)view.findViewById(R.id.button_sair);
        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
