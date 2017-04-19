package com.example.victorbruno.karimaprodutor.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.victorbruno.karimaprodutor.R;
import com.parse.ParseObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VictorBruno on 17/04/17.
 */

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.myViewHolder> {
    private Context mContext;
    private ArrayList<ParseObject> postagensProdutor;
    private LayoutInflater mLayoutInflater;
    //  private TextView titulo;
    //  private  TextView legenda;

    public TimeLineAdapter(Context c, ArrayList<ParseObject> objects) {
        mContext = c;
        postagensProdutor = objects;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.card_layout_time_line, parent, false);
        myViewHolder mvh = new myViewHolder(view);

        return mvh;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {

        holder.titulo.setText("CSA Floresta");
        holder.legenda.setText("olha ai galera!!");

        ParseObject parseObject = postagensProdutor.get(position);

        Picasso.with(mContext)
                .load(parseObject.getParseFile("IMAGEM").getUrl())
                .fit()
                .into(holder.postagem);


    }

    @Override
    public int getItemCount() {
        return postagensProdutor.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder {

        private ImageView postagem;
        private TextView titulo;
        private TextView legenda;


        public myViewHolder(View itemView) {
            super(itemView);
            postagem = (ImageView) itemView.findViewById(R.id.imagem_view_time_line);
            titulo = (TextView) itemView.findViewById(R.id.text_titulo);
            legenda = (TextView) itemView.findViewById(R.id.text_legenda);

        }
    }


}