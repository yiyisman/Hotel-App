package com.example.yiyisman.hotel;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Factura extends AppCompatActivity {
    Statics s;
    String hoy,nombre, hot, costo;
    TextView  fecha,nom,hote, num,costn,costet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);
        fecha=(TextView)findViewById(R.id.fecha);
        nom=(TextView)findViewById(R.id.nombre);
        hote=(TextView)findViewById(R.id.hotel);
        num=(TextView)findViewById(R.id.num);
        costn=(TextView)findViewById(R.id.coston);
        costet=(TextView)findViewById(R.id.costt);
        Button finalizar=(Button)findViewById(R.id.button4);


        Calendar c = new GregorianCalendar();
        String dias = Integer.toString(c.get(Calendar.DATE));
        String mes = Integer.toString(c.get(Calendar.MONTH)+1);
        String annio = Integer.toString(c.get(Calendar.YEAR));
        hoy=annio+"-"+mes+"-"+dias;
        fecha.setText("Fecha Factura: "+hoy);
        nom.setText("Nombre: "+Statics2.getNombreC()+" "+Statics2.getApellidoC());
        hote.setText("Hotel: "+Statics.getCalHotel());
        num.setText("Habitacion: "+Statics.getCalHab());
        costn.setText("Coste por noche: "+Statics.getCostn());
        costet.setText("Costo total: "+Statics.getCostt());
        ImageButton volve =(ImageButton)findViewById(R.id.vol) ;
        volve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Factura.this,MiHabitacion.class);
                startActivity(i);

            }
        });
        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ConsultarDatos2().execute("http://yiyisman.esy.es/Hotel/getidH.php?numero="+Statics.getCalHab()+"&hotel="+Statics.getCalHotel());
                Intent i = new Intent(Factura.this,Cliente.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Gracias, vuelva pronto", Toast.LENGTH_LONG).show();

            }
        });
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
