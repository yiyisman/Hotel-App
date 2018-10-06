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

public class FechaReserva2 extends AppCompatActivity {
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
        actionBar.setTitle("Fecha de salida");
        b =(Button)findViewById(R.id.select);
        datep = (DatePicker)findViewById(R.id.datePicker);
;
        final String idCli=S.getIdCli();
        final String idHa=S.getNumeroH();
        final String fe=S.getFe();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String bo=getdate();
                if(bo==""){
                    Toast.makeText(getApplicationContext(), "Se necesita que la fecha de salida sea mayor a la de entrada", Toast.LENGTH_LONG).show();
                }else{
                    new CargarDatos().execute("http://yiyisman.esy.es/Hotel/registroR.php?idC="+idCli+"&idHa="+idHa+"&fe="+fe+"&fs="+getdate());
                    Intent k = new Intent(FechaReserva2.this,Cliente.class);
                    startActivity(k);
                }




            }
        });
    }
    public class CargarDatos extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(getApplicationContext(), "Reserva completada", Toast.LENGTH_LONG).show();

        }
    }


    public void showDate(View View)
    {
        text.setText(getdate());
    }

    public String getdate()
    {
        int f= Integer.parseInt(S.getFeY());
        int fm= Integer.parseInt(S.getFeM());
        int fd= Integer.parseInt(S.getFeD());
        String date_text= "";
        date_text = "" + datep.getYear() +"-"+(datep.getMonth()+1)+ "-" + datep.getDayOfMonth() + "" ;
        if(datep.getYear()>=f){
            if((datep.getMonth()+1)>fm){
                return date_text;

            }else if((datep.getMonth()+1)==fm){
                    if(datep.getDayOfMonth()>fd){
                        return date_text;
                    }
            }

        }
        return "";
    }

    public void next_page(View view)
    {

        Intent in = new Intent(this,FechaReserva.class);
        startActivity(in);
    }
    private String downloadUrl(String myurl) throws IOException {
        Log.i("URL",""+myurl);
        myurl = myurl.replace(" ","%20");
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("respuesta", "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }
}