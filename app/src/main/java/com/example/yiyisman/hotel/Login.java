package com.example.yiyisman.hotel;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import static android.R.string.ok;

public class Login extends AppCompatActivity {

    Statics S;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();


        /**INDICAR TITULO Y SUBTITULO**/
        actionBar.setTitle("Inicio");

        ImageButton admin = (ImageButton)findViewById(R.id.button);
        ImageButton cli = (ImageButton)findViewById(R.id.button2);
        ImageButton about = (ImageButton)findViewById(R.id.imageButton4);
        ImageButton entry = (ImageButton) findViewById(R.id.imageButton5);

        entry.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://andrespuertoblog.wordpress.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(S.getSUsuarioA()==null){
                    Intent i = new Intent(Login.this, LoginAdmin.class);
                    startActivity(i);

                }else {
                    Intent i = new Intent(Login.this,Gestion.class);
                    startActivity(i);
                }

            }
        });
        cli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(S.getSUsuario()==null){
                    Intent i = new Intent(Login.this, LoginCliente.class);
                    startActivity(i);

                }else {
                    Intent i = new Intent(Login.this,Cliente.class);
                    startActivity(i);
                }
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Login.this,AboutUS.class);
                startActivity(i);

            }
        });
    }
}
