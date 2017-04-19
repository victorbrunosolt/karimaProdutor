package com.example.victorbruno.karimaprodutor.fragment;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.victorbruno.karimaprodutor.R;
import com.example.victorbruno.karimaprodutor.adapter.GestaoAdapter;
import com.example.victorbruno.karimaprodutor.adapter.TabsAdapter;
import com.example.victorbruno.karimaprodutor.adapter.TimeLineAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GestaoFragment extends Fragment {
    private ArrayList<ParseUser> coProdutores;
    private ArrayAdapter<ParseUser> adapter;
    private ListView listView;



    public GestaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_gestao, container, false);

        coProdutores = new ArrayList<>();
        listView = (ListView) view.findViewById(R.id.listView_gestao);
        adapter = new GestaoAdapter(getActivity(),coProdutores);
        listView.setAdapter(adapter);
        getCoProdutores();




        return view;


    }

    private void getCoProdutores() {

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("CSA_VINCULADA", ParseUser.getCurrentUser().get("CSA_RESPONSAVEL"));
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> objects, ParseException e) {

                if (e == null) {
                    if (objects.size() > 0) {
                        coProdutores.clear();
                        for (ParseUser parseUsers : objects) {
                            coProdutores.add(parseUsers);
                        }
                        adapter.notifyDataSetChanged();
                    }

                }else {
                    // Something went wrong.
                }
            }
        });

    }

}
