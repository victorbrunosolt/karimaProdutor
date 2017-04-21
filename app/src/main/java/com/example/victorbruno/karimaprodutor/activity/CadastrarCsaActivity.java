package com.example.victorbruno.karimaprodutor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.victorbruno.karimaprodutor.R;

public class CadastrarCsaActivity extends AppCompatActivity {
    private TextView nomeCSA;
    private TextView enderecoCSA;
    private TextView descricaoCSA;
    private TextView valor;
    private TextView vagas;
    private ImageView fotoCSA;
    private Button localizacaoCSA;
    private Button salvarCSA;
    static final int REQUEST_IMAGE_CAPTURE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_csa);

        recuperasDadosTela();
    }

    public void recuperasDadosTela(){

        nomeCSA = (TextView) findViewById(R.id.editText_nome_csa);
        enderecoCSA = (TextView) findViewById(R.id.editText_endereco);
        descricaoCSA = (TextView) findViewById(R.id.editText_descricaco_csa);
        valor = (TextView) findViewById(R.id.editText_valor_csa);
        vagas = (TextView) findViewById(R.id.editText_vagas);
        fotoCSA = (ImageView) findViewById(R.id.imageView_imagem_csa);
        localizacaoCSA = (Button) findViewById(R.id.button_localizacao);
        salvarCSA = (Button) findViewById(R.id.button_salvar_csa);

        fotoCSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dispatchTakePictureIntent();

            }
        });

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
}
