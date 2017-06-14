package com.example.victorbruno.karimaprodutor.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.victorbruno.karimaprodutor.R;
import com.example.victorbruno.karimaprodutor.adapter.TimeLineAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimeLineFragment extends Fragment {
    private ArrayList<ParseObject> postagensProdutor;
    private ArrayList<ParseUser> usuariosPostagem;
    private TimeLineAdapter adapter;
    private ParseQuery query;
    @BindView(R.id.recycler_time_line)
    RecyclerView mRecyclerViewTimeLine;


    public TimeLineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_time_line2, container, false);

        ButterKnife.bind(this, view);
        getPostagens();
        // montar list view e adapter

        postagensProdutor = new ArrayList<>();
        usuariosPostagem = new ArrayList<>();
        //  mRecyclerViewTimeLine = (RecyclerView) view.findViewById(R.id.recycler_time_line);
        mRecyclerViewTimeLine.setHasFixedSize(true);
        mRecyclerViewTimeLine.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerViewTimeLine.getLayoutManager();
                TimeLineAdapter timeLineAdapter = (TimeLineAdapter) mRecyclerViewTimeLine.getAdapter();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewTimeLine.setLayoutManager(llm);

        adapter = new TimeLineAdapter(getActivity(), postagensProdutor, usuariosPostagem);
        mRecyclerViewTimeLine.setAdapter(adapter);

        return view;


    }

    private void getPostagens() {

        /*
         Recupera csas
        */
        query = ParseQuery.getQuery("TIMELINE");
        query.orderByDescending("createdAt");
        query.include("USUARIO");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null) {//sucesso

                    if (objects.size() > 0) {
                        postagensProdutor.clear();
                        for (ParseObject parseObject : objects) {
                            ParseUser usuario = parseObject.getParseUser("USUARIO");
                            usuariosPostagem.add(usuario);
                            postagensProdutor.add(parseObject);
                        }
                        adapter.notifyDataSetChanged();
                    }

                } else {//erro
                    e.printStackTrace();
                }

            }
        });

    }


}
