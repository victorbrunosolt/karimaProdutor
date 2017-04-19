package com.example.victorbruno.karimaprodutor.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.victorbruno.karimaprodutor.R;
import com.parse.ParseUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by VictorBruno on 13/04/17.
 */

public class GestaoAdapter extends ArrayAdapter<ParseUser> {
    private Context mContext;
    private ArrayList<ParseUser> coProdutores;
    private TextView coProdutor;
    private  TextView status;


    public GestaoAdapter(Context c, ArrayList<ParseUser> objects) {

        super(c, 0, objects);
        this.mContext = c;
        this.coProdutores = objects;


    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if(view == null){

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.card_layout, parent, false);
            holder = new ViewHolder();
            holder.imagemUsuario = (ImageView) view.findViewById(R.id.imagem_usuario);
            holder.coProdutor = (TextView) view.findViewById(R.id.text_coProdutor);
            holder.status = (TextView) view.findViewById(R.id.text_status);
            view.setTag(holder);

            // monta a view a partir do xml que vai ser criado


        }else{

            holder = (ViewHolder) view.getTag();
        }


        if(coProdutores.size() > 0 ){

            ImageView imagemUsuario = (ImageView) view.findViewById(R.id.imagem_usuario);
            coProdutor = (TextView) view.findViewById(R.id.text_coProdutor);
            status = (TextView) view.findViewById(R.id.text_status);

            ParseUser parseUser = coProdutores.get(position);

            String nome = parseUser.getUsername();

            holder.coProdutor.setText(nome);
            holder.status.setText(parseUser.get("STATUS").toString());


            Picasso.with(mContext)
                    .load(parseUser.getParseFile("IMAGEM").getUrl())
                    .fit()
                    .into(imagemUsuario);



        }

        return view;

    }


    static class ViewHolder {
        TextView coProdutor;
        TextView status;
        ImageView imagemUsuario;
    }
}
