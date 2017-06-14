package com.example.victorbruno.karimaprodutor.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.victorbruno.karimaprodutor.R;
import com.example.victorbruno.karimaprodutor.activity.AvaliacoesActivity;
import com.example.victorbruno.karimaprodutor.activity.CadastrarCsaActivity;
import com.example.victorbruno.karimaprodutor.adapter.GestaoAdapter;
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
    private GestaoAdapter adapter;
    private RecyclerView mRecyclerViewGestao;
    private Button avaliacoes;
    private Button cadastrarCsa;
    private Button graficos;
    private ParseObject csa;



    public GestaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gestao, container, false);



            getCoProdutores();
            // inicializa o array de coProdutores
            coProdutores = new ArrayList<>();

            //recyclerView
            mRecyclerViewGestao = (RecyclerView) view.findViewById(R.id.recycler_gestao);
            mRecyclerViewGestao.setHasFixedSize(true);
            mRecyclerViewGestao.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerViewGestao.getLayoutManager();
                    GestaoAdapter timeLineAdapter = (GestaoAdapter) mRecyclerViewGestao.getAdapter();
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                }
            });
            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerViewGestao.setLayoutManager(llm);

            // seta o adapter

            adapter = new GestaoAdapter(getActivity(), coProdutores);
            mRecyclerViewGestao.setAdapter(adapter);

            recuperaElementosTela(view);

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

    @Nullable
    private String getCSA(){


        ParseUser currentUser = ParseUser.getCurrentUser();

       if(currentUser.get("CSA_RESPONSAVEL").toString() != null){
          return currentUser.get("CSA_RESPONSAVEL").toString();
       }else {
           return null;
       }



    }

    public void recuperaElementosTela(View view){


        avaliacoes = (Button) view.findViewById(R.id.button_avaliacoes);
        cadastrarCsa = (Button) view.findViewById(R.id.button_cadastrar_csa);
        graficos = (Button) view.findViewById(R.id.button_graficos);

        avaliacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), AvaliacoesActivity.class);
                startActivity(intent);

            }
        });

        cadastrarCsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), CadastrarCsaActivity.class);
                startActivity(intent);

            }
        });

        graficos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  Intent intent = new Intent(getContext(), GraficosActivity.class);
               // startActivity(intent);

               // Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
              //  startActivityForResult(takePictureIntent, 5678);

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), 1234);

            }
        });






    }

}
