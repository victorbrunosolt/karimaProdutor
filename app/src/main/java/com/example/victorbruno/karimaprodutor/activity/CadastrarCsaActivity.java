package com.example.victorbruno.karimaprodutor.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.victorbruno.karimaprodutor.R;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private ParseFile imagemCSA;
    private ParseGeoPoint localizacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_csa);

        recuperasDadosTela();
    }

    public void recuperasDadosTela() {

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
        salvarCSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCampos();
                if (imagemCSA != null) {
                    salvarCSA(imagemCSA);
                    Toast.makeText(getApplicationContext(), "Salvando CSA", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Preencher os campos obrigatorios CSA", Toast.LENGTH_LONG).show();

                }


            }
        });

    }

    private void dispatchTakePictureIntent() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //Testar processo de retorno dos dados
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {

            //recuperar local do recurso
            // Uri localImagemSelecionada = data.getData();


            //recupera a imagem do local que foi selecionada
            //  try {
            Bitmap mphoto = (Bitmap) data.getExtras().get("data");
            fotoCSA.setImageBitmap(mphoto);
            // Bitmap imagem = .Images.Media.getBitmap( getContentResolver(), mphoto );

            //comprimir no formato PNG
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            mphoto.compress(Bitmap.CompressFormat.PNG, 75, stream);
            //Cria um array de bytes da imagem
            byte[] byteArray = stream.toByteArray();

            //Criar um arquivo com formato próprio do parse
            SimpleDateFormat dateFormat = new SimpleDateFormat("ddmmaaaahhmmss");
            String nomeImagem = dateFormat.format(new Date());
            imagemCSA = new ParseFile(nomeImagem + "imagem.png", byteArray);

            //   } //catch (IOException e) {
            // e.printStackTrace();
            //  }

        }


    }

    public void salvarCSA(ParseFile arquivoParse) {


        String nomeCsa = nomeCSA.getText().toString();
        String endereco = enderecoCSA.getText().toString();
        String descricao = descricaoCSA.getText().toString();
        int preco = Integer.parseInt(valor.getText().toString());
        int capacidade = Integer.parseInt(vagas.getText().toString());
        localizacao = new ParseGeoPoint(0, 0);


        //Monta objeto para salvar no parse


        ParseObject csa = new ParseObject("CSA");
        csa.put("NOME", nomeCsa);
        csa.put("ENDERECO", endereco);
        csa.put("DESCRICAO", descricao);
        csa.put("PRECO", preco);
        csa.put("CAPACIDADE", capacidade);
        csa.put("IMAGEM", arquivoParse);
        csa.put("LOCALIZACAO", localizacao);
        //salvar os dados
        csa.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

                if (e == null) {//sucesso
                    Toast.makeText(getApplicationContext(), "CSA salva com sucesso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CadastrarCsaActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {//erro
                    Toast.makeText(getApplicationContext(), "Erro ao salvar CSA - Tente novamente!"+ e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void validarCampos() {
        if (nomeCSA.getText().toString().length() == 0) {
            nomeCSA.setError("Digite o nome da CSA");
        }
        if (descricaoCSA.getText().toString().length() == 0) {
            descricaoCSA.setError("Digite a descrição da CSA");
        }
        if (enderecoCSA.getText().toString().length() == 0) {
            enderecoCSA.setError("Digite o Endereço da CSA");
        }
    }
}

