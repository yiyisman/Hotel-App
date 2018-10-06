package com.example.yiyisman.hotel;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MiHabitacion extends AppCompatActivity {
    TextView hab, fe, fs, hot;
    String ehab,ehot,ab,cost;
    EditText califi, observ;
    Float costet,coste;
    String hoy, idre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_habitacion);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        /**INDICAR TITULO Y SUBTITULO**/
        actionBar.setTitle("Cliente conectado: "+Statics.getSUsuario());
        Button irse=(Button)findViewById(R.id.button10);
        Button cal=(Button)findViewById(R.id.cali);
        hab =(TextView)findViewById(R.id.ha);
        fe =(TextView)findViewById(R.id.fen);
        fs =(TextView)findViewById(R.id.fsa);
        hot =(TextView)findViewById(R.id.hotel);
        new ConsultarDatos().execute("http://yiyisman.esy.es/Hotel/getHabiCl.php?idCli="+Statics.getIdCli());

        irse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CargarDatos().execute("http://yiyisman.esy.es/Hotel/resgistroF.php?coste="+cost+"&fecha="+hoy+"&idre="+idre);


                Intent i = new Intent(MiHabitacion.this,Factura.class);
                startActivity(i);
            }
        });
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MiHabitacion.this,Calificacion.class);
                startActivity(i);
            }
        });


    }
    public class ConsultarDatos extends AsyncTask<String, Void, String> {
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

                hab.setText("Numero de Habitacion: "+ja.getString(0));
                fe.setText("Fecha de entrada: "+ja.getString(1));
                fs.setText("Fecha de salida: "+ja.getString(2));
                hot.setText("Hotel: "+ja.getString(3));
                coste=Float.parseFloat(ja.getString(4));

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String grande = ja.getString(2);
                    String peque = ja.getString(1);
                    Calendar a=Calendar.getInstance();
                    Calendar c = new GregorianCalendar();
                    String dias = Integer.toString(c.get(Calendar.DATE));
                    String mes = Integer.toString(c.get(Calendar.MONTH)+1);
                    String annio = Integer.toString(c.get(Calendar.YEAR));
                    hoy=annio+"-"+mes+"-"+dias;
                    idre=ja.getString(5);

                    try {

                        Date date = formatter.parse(grande);
                        Date date1 = formatter.parse(peque);
                        int dia= diferenciaEnDias2(date,date1);
                        costet=dia*coste;
                        cost=costet.toString();
                        Statics.setCostn(coste.toString());
                        Statics.setCostt(costet.toString());


                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    ehab=ja.getString(0);
                    ehot=ja.getString(3);
                    Statics.setCalHab(ja.getString(0));;
                    Statics.setCalHotel(ja.getString(3));




            } catch (JSONException e) {
                Intent i = new Intent(MiHabitacion.this,Cliente.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(),"Lo sentimos, no hay una reserva", Toast.LENGTH_LONG).show();
            }

        }
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

            Toast.makeText(getApplicationContext(), "Se almacenaron los datos correctamente", Toast.LENGTH_LONG).show();

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

            JSONArray ja = null;

            try {

                ja = new JSONArray(result);
                String a= ja.getString(0);

                new EliminarDatos().execute("http://yiyisman.esy.es/Hotel/BorrarR.php?idC="+Statics.getIdCli()+"&idHa="+a);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
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
                e.printStackTrace();
            }

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
    public static int diferenciaEnDias2(Date fechaMayor, Date fechaMenor) {
        long diferenciaEn_ms = fechaMayor.getTime()-fechaMenor.getTime();
        long dias = diferenciaEn_ms / (1000 * 60 * 60 * 24);
        return (int) dias;
    }
}
