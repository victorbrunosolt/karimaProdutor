package com.example.victorbruno.karimaprodutor.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.victorbruno.karimaprodutor.R;
import com.example.victorbruno.karimaprodutor.activity.DetalhesTimeLineActivity;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by VictorBruno on 17/04/17.
 */

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.myViewHolder> {
    private Context mContext;
    private ArrayList<ParseObject> postagensProdutor;
    private ArrayList<ParseUser> usuarioPostagem;
    private LayoutInflater mLayoutInflater;


    public TimeLineAdapter(Context c, ArrayList<ParseObject> objects, ArrayList<ParseUser> objectsUsuario) {
        mContext = c;
        postagensProdutor = objects;
        usuarioPostagem = objectsUsuario;
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


        ParseObject parseObject = postagensProdutor.get(position);
        ParseUser parseUser = usuarioPostagem.get(position);

        if (parseUser != null) {
            String nomeUsuario = parseUser.getUsername();
            holder.titulo.setText(nomeUsuario);
        } else {
            holder.titulo.setText("Usuario Excluido");
        }


        String descricao = (String) parseObject.get("DESCRICAO");


        holder.legenda.setText(descricao);

        Glide.with(mContext).load(parseObject.getParseFile("IMAGEM").getUrl())
                .thumbnail(0.5f)
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.postagem);


    }

    @Override
    public int getItemCount() {
        return postagensProdutor.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.imagem_view_time_line) ImageView postagem;
        @BindView(R.id.text_titulo) TextView titulo;
        @BindView(R.id.text_legenda) TextView legenda;



        public myViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        @OnClick
        public void onClick(View v) {
            ParseObject parseObject = postagensProdutor.get(getPosition());
            // envia dados para o activity detalhes da time line
            Intent intent = new Intent(mContext, DetalhesTimeLineActivity.class);
            String objectId = parseObject.getObjectId();
            intent.putExtra("objectId", objectId);
            intent.putExtra("nome", titulo.getText());
            mContext.startActivity(intent);
        }
    }


}