package com.example.victorbruno.karimaprodutor.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.victorbruno.karimaprodutor.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {

    private EditText login;
    private EditText senha;
    private TextView criar_conta;
    private Button logar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ParseUser.getCurrentUser() != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);


        } else{

            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        pegaDadosPorId();
        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(login.getText().toString(), senha.getText().toString());


            }
        });

        criar_conta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CriarContaActivity.class);
                startActivity(intent);


            }
        });
    }

}

private void pegaDadosPorId(){

    login = (EditText) findViewById(R.id.edit_text_login);
    senha = (EditText) findViewById(R.id.edit_text_senha);
    logar = (Button) findViewById(R.id.button_login);
    criar_conta = (TextView) findViewById(R.id.text_criar_conta);


}

private void login(String login, String senha){

    ParseUser.logInInBackground(login,senha, new LogInCallback() {
        public void done(ParseUser user, ParseException e) {
            if (user != null) {
                // Hooray! The user is logged in.
                Toast.makeText(getApplicationContext(),"login realizado com sucesso", Toast.LENGTH_SHORT).show();
            } else {
                // Signup failed. Look at the ParseException to see what happened.
                Toast.makeText(getApplicationContext(),"erro ao fazer login" + e, Toast.LENGTH_SHORT).show();
            }
        }
    });
}


}

