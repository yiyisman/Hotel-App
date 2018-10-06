package com.example.yiyisman.hotel;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Calificacion extends AppCompatActivity {
    String ab;
    EditText califi,observ;

    Integer [] num={1,2,3,4,5,6,7,8,9,10};
    Float n;
    RatingBar ra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificacion);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        /**INDICAR TITULO Y SUBTITULO**/
        actionBar.setTitle("Calificacion");
        Button cal =(Button)findViewById(R.id.calificar);
        observ =(EditText)findViewById(R.id.editText5);
        ra=(RatingBar)findViewById(R.id.ratingBar);


        new ConsultarDatos3().execute("http://yiyisman.esy.es/Hotel/getidH.php?numero="+Statics.getCalHab()+"&hotel="+Statics.getCalHotel());

        ra.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){
            @Override
            public void onRatingChanged(RatingBar ratingbar, float rating, boolean fromUser){
                n=rating;
            }
        });

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(observ.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Necesitamos tus observaciones", Toast.LENGTH_LONG).show();


                }else {
                    new ConsultarDatos2().execute("http://yiyisman.esy.es/Hotel/registroCal.php?idHab=" + ab + "&cali=" + n + "&observ=" + observ.getText().toString());
                    Toast.makeText(getApplicationContext(), "Gracias por calificar", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Calificacion.this, MiHabitacion.class);
                    startActivity(i);
                }

            }
        });
    }
    public class ConsultarDatos3 extends AsyncTask<String, Void, String> {
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

            JSONArray ja = null;

            try {

                ja = new JSONArray(result);
                ab= ja.getString(0);


            } catch (JSONException e) {
                Intent i = new Intent(Calificacion.this,MiHabitacion.class);
                startActivity(i);
            }

        }
    }
    public class ConsultarDatos2 extends AsyncTask<String, Void, String> {
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


                Toast.makeText(getApplicationContext(), "Calificacion realizada", Toast.LENGTH_LONG).show();



        }
    }
    public class EliminarDatos extends AsyncTask<String, Void, String> {
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

            Toast.makeText(getApplicationContext(), "Se elimino correctamente", Toast.LENGTH_LONG).show();

        }
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
