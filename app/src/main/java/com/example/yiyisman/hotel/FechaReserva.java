package com.example.yiyisman.hotel;

        import android.content.Intent;
        import android.os.AsyncTask;
        import android.support.v7.app.ActionBar;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.DatePicker;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.Reader;
        import java.io.UnsupportedEncodingException;
        import java.net.HttpURLConnection;
        import java.net.URL;

public class FechaReserva extends AppCompatActivity {
    Statics S;
    DatePicker datep;
    TextView text;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fecha_reserva);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        /**INDICAR TITULO Y SUBTITULO**/
        actionBar.setTitle("Fecha de llegada");
        b =(Button)findViewById(R.id.select);
        datep = (DatePicker)findViewById(R.id.datePicker);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                S.setFe(getdate());
                Intent k = new Intent(FechaReserva.this,FechaReserva2.class);
                startActivity(k);



            }
        });
    }



    public void showDate(View View)
    {
        text.setText(getdate());
    }

    public String getdate()
    {
        String date_text= "";
        date_text = "" + datep.getYear() +"-"+(datep.getMonth()+1)+ "-" + datep.getDayOfMonth() + "" ;
        S.setFeY(""+datep.getYear());
        S.setFeM(""+(datep.getMonth()+1));
        S.setFeD(""+datep.getDayOfMonth());
        return date_text;
    }

    public void next_page(View view)
    {

        Intent in = new Intent(this,FechaReserva.class);
        startActivity(in);
    }

}