package com.example.victorbruno.karimaprodutor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.victorbruno.karimaprodutor.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CriarContaActivity extends AppCompatActivity {


    @BindView(R.id.editText_nome) EditText nome;
    @BindView(R.id.editText_email) EditText email;
    @BindView(R.id.editText_senha) EditText senha;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);
        ButterKnife.bind(this);

        email = (EditText) findViewById(R.id.editText_email);
        senha = (EditText) findViewById(R.id.editText_senha);

    }

    @OnClick(R.id.button_cadastrar)
    public void cadastrar() {
        if(nome.getText().length() != 0){
            criarConta(nome.getText().toString(), senha.getText().toString(), email.getText().toString() );
        }else {

            Toast.makeText(this,"Preencha o campo nome", Toast.LENGTH_SHORT).show();
        }

    }



    private void criarConta(String nome, String senha, String email){

        ParseUser user = new ParseUser();
        user.setUsername(nome);
        user.setPassword(senha);
        user.setEmail(email);

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(getApplicationContext(),"conta criada com sucesso", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(CriarContaActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {

                    Toast.makeText(getApplicationContext(),"não foi possivel criar a conta" + e, Toast.LENGTH_SHORT).show();
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                }
            }
        });


    }
}
