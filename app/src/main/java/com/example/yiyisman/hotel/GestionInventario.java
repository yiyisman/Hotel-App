package com.example.yiyisman.hotel;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class GestionInventario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_inventario);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        ImageButton agr =(ImageButton)findViewById(R.id.imageButton10);
        ImageButton inv =(ImageButton)findViewById(R.id.imageButton11);
        agr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GestionInventario.this, NuevoM.class);
                startActivity(i);



            }
        });
        inv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GestionInventario.this, Inventario.class);
                startActivity(i);



            }
        });
    }
}
