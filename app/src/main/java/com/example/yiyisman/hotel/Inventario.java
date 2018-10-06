package com.example.yiyisman.hotel;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
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

public class Inventario extends AppCompatActivity {
    ListView lista;
    ListView lista2;
    String [] hoteles;
    String [] tipos;
    String [] material;
    String [] stock;
    Spinner spinH;
    Spinner spinT;
    String NombreH;
    String TipoM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);

        lista=(ListView) findViewById(R.id.list);
        lista2=(ListView) findViewById(R.id.list2);
        spinH =(Spinner)findViewById(R.id.spinner3);
        spinT =(Spinner)findViewById(R.id.spinner4);
        ImageButton volve =(ImageButton)findViewById(R.id.vol) ;
        volve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Inventario.this,GestionInventario.class);
                startActivity(i);

            }
        });
        new ConsultarDatos().execute("http://yiyisman.esy.es/Hotel/geallHotel.php?");
        new ConsultarDatos2().execute("http://yiyisman.esy.es/Hotel/getTipoM.php?");
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

                String spl=result;


                String a=spl.replaceAll("\"", "");;
                String car1=a.replaceAll("\\]",",");
                String car=car1.replaceAll("\\[","");
                String res= a.replaceAll(",", "       ");

                hoteles = car.split(",");

                ArrayAdapter<String> adaptador = new ArrayAdapter<>(Inventario.this, android.R.layout.simple_spinner_item,hoteles);
                spinH.setAdapter(adaptador);


                spinH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (i){
                            case 0:
                                NombreH =hoteles[i];
                                break;
                            case 1:
                                NombreH =hoteles[i];

                                break;
                            case 2:
                                NombreH =hoteles[i];

                                break;
                            case 3:
                                NombreH =hoteles[i];

                                break;
                            case 4:
                                NombreH =hoteles[i];

                                break;
                            case 5:
                                NombreH =hoteles[i];

                                break;
                        }

                    }

                    public void onNothingSelected(AdapterView<?> adapterView) {
                        return;
                    }
                });


            } catch (JSONException e) {
                e.printStackTrace();
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

            JSONArray ja = null;
            try {

                ja = new JSONArray(result);
                String spl=result;


                String a=spl.replaceAll("\"", "");;
                String car1=a.replaceAll("\\]",",");
                String car=car1.replaceAll("\\[","");
                String res= a.replaceAll(",", "       ");

                tipos = car.split(",");

                ArrayAdapter<String> adaptador2 = new ArrayAdapter<>(Inventario.this, android.R.layout.simple_spinner_item,tipos);
                spinT.setAdapter(adaptador2);


                spinT.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (i){
                            case 0:
                                TipoM =tipos[i];
                                new ConsultarDatos3().execute("http://yiyisman.esy.es/Hotel/getallMateria.php?tipo="+TipoM+"&hotel="+NombreH);
                                new ConsultarDatos4().execute("http://yiyisman.esy.es/Hotel/getallStock.php?tipo="+TipoM+"&hotel="+NombreH);
                                break;
                            case 1:
                                TipoM =tipos[i];
                                new ConsultarDatos3().execute("http://yiyisman.esy.es/Hotel/getallMateria.php?tipo="+TipoM+"&hotel="+NombreH);
                                new ConsultarDatos4().execute("http://yiyisman.esy.es/Hotel/getallStock.php?tipo="+TipoM+"&hotel="+NombreH);
                                break;
                            case 2:
                                TipoM =tipos[i];
                                new ConsultarDatos3().execute("http://yiyisman.esy.es/Hotel/getallMateria.php?tipo="+TipoM+"&hotel="+NombreH);
                                new ConsultarDatos4().execute("http://yiyisman.esy.es/Hotel/getallStock.php?tipo="+TipoM+"&hotel="+NombreH);

                                break;
                            case 3:
                                TipoM =tipos[i];
                                new ConsultarDatos3().execute("http://yiyisman.esy.es/Hotel/getallMateria.php?tipo="+TipoM+"&hotel="+NombreH);
                                new ConsultarDatos4().execute("http://yiyisman.esy.es/Hotel/getallStock.php?tipo="+TipoM+"&hotel="+NombreH);

                                break;
                            case 4:
                                TipoM =tipos[i];
                                new ConsultarDatos3().execute("http://yiyisman.esy.es/Hotel/getallMateria.php?tipo="+TipoM+"&hotel="+NombreH);
                                new ConsultarDatos4().execute("http://yiyisman.esy.es/Hotel/getallStock.php?tipo="+TipoM+"&hotel="+NombreH);

                                break;
                            case 5:
                                TipoM =tipos[i];
                                new ConsultarDatos3().execute("http://yiyisman.esy.es/Hotel/getallMateria.php?tipo="+TipoM+"&hotel="+NombreH);
                                new ConsultarDatos4().execute("http://yiyisman.esy.es/Hotel/getallStock.php?tipo="+TipoM+"&hotel="+NombreH);

                                break;
                        }

                    }

                    public void onNothingSelected(AdapterView<?> adapterView) {
                        return;
                    }
                });


            } catch (JSONException e) {

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


                String spl=result;


                String a=spl.replaceAll("\"", "");;
                String car1=a.replaceAll("\\]",",");
                String car=car1.replaceAll("\\[","");
                String res= a.replaceAll(",", "       ");

                material = car.split(",");

                ArrayAdapter<String> adaptador3 = new ArrayAdapter<>(Inventario.this, android.R.layout.simple_spinner_item,material);
                lista.setAdapter(adaptador3);




            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(),
                        "No existe materia de este tipo", Toast.LENGTH_SHORT);
            }

        }
    }
    public class ConsultarDatos4 extends AsyncTask<String, Void, String> {
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
                String spl=result;


                String a=spl.replaceAll("\"", "");;
                String car1=a.replaceAll("\\]",",");
                String car=car1.replaceAll("\\[","");
                String res= a.replaceAll(",", "       ");

                stock = car.split(",");

                ArrayAdapter<String> adaptador4 = new ArrayAdapter<>(Inventario.this, android.R.layout.simple_spinner_item,stock);
                lista2.setAdapter(adaptador4);




            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    public class ModificarDatos extends AsyncTask<String, Void, String> {
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

            Toast.makeText(getApplicationContext(), "Se modificaron los datos correctamente", Toast.LENGTH_LONG).show();

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
    public String EliminaCaracteres(String s_cadena, String s_caracteres)
    {
        String nueva_cadena = "";
        Character caracter = null;
        boolean valido = true;

  /* Va recorriendo la cadena s_cadena y copia a la cadena que va a regresar,
     sólo los caracteres que no estén en la cadena s_caracteres */
        for (int i=0; i<s_cadena.length(); i++)
        {
            valido = true;
            for (int j=0; j<s_caracteres.length(); j++)
            {
                caracter = s_caracteres.charAt(j);

                if (s_cadena.charAt(i) == caracter)
                {
                    valido = false;
                    break;
                }
            }
            if (valido)
                nueva_cadena += s_cadena.charAt(i);
        }

        return nueva_cadena;
    }
}
