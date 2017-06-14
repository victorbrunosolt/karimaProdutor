package com.example.victorbruno.karimaprodutor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.victorbruno.karimaprodutor.R;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetalhesTimeLineActivity extends AppCompatActivity {

    String objectId;
    @BindView(R.id.textView_descricao) TextView descricao;
    private ParseObject postagem;
    @BindView(R.id.textView_nome) TextView nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_time_line);
        Intent intent = getIntent();
        objectId = intent.getStringExtra("objectId");
        String usuario = intent.getStringExtra("nome");
        ButterKnife.bind(this);
        getDetalhes();
        nome.setText(usuario);
    }

public  void getDetalhes(){
     /*
        Recupera csas
       */
    ParseQuery<ParseObject> query = ParseQuery.getQuery("TIMELINE");
    query.getInBackground(objectId, new GetCallback<ParseObject>() {
        public void done(ParseObject object, ParseException e) {

            // caso csa diferente de nula
            if (e == null) {
                postagem = object;
                //recuperando dados da csa
                String nome = (String) postagem.get("DESCRICAO");
                descricao.setText(nome);

            }
        }

    });
}
}
