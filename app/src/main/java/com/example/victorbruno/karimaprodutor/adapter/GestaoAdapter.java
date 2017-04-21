package com.example.victorbruno.karimaprodutor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.victorbruno.karimaprodutor.R;
import com.parse.ParseUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by VictorBruno on 13/04/17.
 */

public class GestaoAdapter extends RecyclerView.Adapter<GestaoAdapter.myViewHolder> {
    private Context mContext;
    private ArrayList<ParseUser> coProdutores;
    private LayoutInflater mLayoutInflater;


    public GestaoAdapter(Context c, ArrayList<ParseUser> objects) {

        mContext = c;
        coProdutores = objects;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.card_layout, parent, false);
        GestaoAdapter.myViewHolder mvh = new GestaoAdapter.myViewHolder(view);

        return mvh;
    }


    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {


        ParseUser parseUser = coProdutores.get(position);

        String nome = parseUser.getUsername();

        holder.coProdutor.setText(nome);
        holder.status.setText(parseUser.get("STATUS").toString());


        Picasso.with(mContext)
                .load(parseUser.getParseFile("IMAGEM").getUrl())
                .fit()
                .into(holder.coProdutorFoto);

    }

    @Override
    public int getItemCount() {
        return coProdutores.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder {

        private ImageView coProdutorFoto;
        private TextView coProdutor;
        private TextView status;


        public myViewHolder(View itemView) {
            super(itemView);
            coProdutorFoto = (ImageView) itemView.findViewById(R.id.imagem_usuario);
            coProdutor = (TextView) itemView.findViewById(R.id.text_coProdutor);
            status = (TextView) itemView.findViewById(R.id.text_status);

        }
    }
}
