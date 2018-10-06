package com.example.yiyisman.hotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HabitacionNormal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habitacion_normal);
        ImageButton vol = (ImageButton)findViewById(R.id.imageButton7);
        Button reser = (Button)findViewById(R.id.reservar);
        vol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HabitacionNormal.this,ReservarHabitacion.class);
                startActivity(i);

            }
        });
        reser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HabitacionNormal.this,Reservar.class);
                startActivity(i);

            }
        });
    }
}
