package com.example.victorbruno.karimaprodutor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.victorbruno.karimaprodutor.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class CriarContaActivity extends AppCompatActivity {

    private Button cadastrar;
    private EditText nome;
    private EditText email;
    private EditText senha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);
        cadastrar = (Button) findViewById(R.id.button_cadastrar);
        nome = (EditText) findViewById(R.id.editText_nome);
        email = (EditText) findViewById(R.id.editText_email);
        senha = (EditText) findViewById(R.id.editText_senha);


        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                criarConta(nome.getText().toString(), senha.getText().toString(), email.getText().toString() );
                Intent intent = new Intent(CriarContaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
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
