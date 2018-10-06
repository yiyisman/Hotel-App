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
import android.widget.Spinner;
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
import java.util.ArrayList;

public class NuevoM extends AppCompatActivity {
    TextView tv;
    String Material1;
    String str;
    ArrayList<String> datos1 =new ArrayList<>();
    String [] datos;
    String [] material;
    Spinner lista;
    Spinner lista2;
    Spinner lista3;
    int NombreH;
    String Tipo;
    EditText nume;
    Spinner spinH;
    String [] hoteles;
    Statics S;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_m);
        ImageButton vol =(ImageButton)findViewById(R.id.imageButton12);
        vol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NuevoM.this, GestionInventario.class);
                startActivity(i);



            }
        });

        lista=(Spinner)findViewById(R.id.spinner);
        lista2=(Spinner)findViewById(R.id.spinner7);
        lista3=(Spinner)findViewById(R.id.mat);
        new ConsultarDatosH().execute("http://yiyisman.esy.es/Hotel/geallHotel.php?");
        new ConsultarDatos().execute("http://yiyisman.esy.es/Hotel/getTipoM.php");




    }
    public class ConsultarDatosH extends AsyncTask<String, Void, String> {
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
                ArrayAdapter<String> adaptador = new ArrayAdapter<>(NuevoM.this, android.R.layout.simple_spinner_item,hoteles);
                lista.setAdapter(adaptador);


                lista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (i){
                            case 0:
                                NombreH =1;
                                break;
                            case 1:
                                NombreH =2;

                                break;
                            case 2:
                                NombreH =3;


                                break;
                            case 3:
                                NombreH =4;

                                break;
                            case 4:
                                NombreH =5;


                                break;
                            case 5:
                                NombreH =6;

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
            JSONArray ja1 = null;
            try {

                ja = new JSONArray(result);
                String spl=result;

                String a=spl.replaceAll("\"", "");;
                String car1=a.replaceAll("\\]",",");
                String car=car1.replaceAll("\\[","");
                String res= a.replaceAll(",", "       ");

                datos = car.split(",");
                ArrayAdapter<String> adaptador2 = new ArrayAdapter<>(NuevoM.this, android.R.layout.simple_spinner_item,datos);
                lista2.setAdapter(adaptador2);


                lista2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (i){
                            case 0:
                                Tipo =datos[i];
                                new ConsultarDatos2().execute("http://yiyisman.esy.es/Hotel/getMateria.php?tipo="+Tipo);
                                break;
                            case 1:

                                Tipo =datos[i];
                                new ConsultarDatos2().execute("http://yiyisman.esy.es/Hotel/getMateria.php?tipo="+Tipo);
                                break;
                            case 2:
                                Tipo =datos[i];
                                new ConsultarDatos2().execute("http://yiyisman.esy.es/Hotel/getMateria.php?tipo="+Tipo);
                                break;
                            case 3:

                                Tipo =datos[i];
                                new ConsultarDatos2().execute("http://yiyisman.esy.es/Hotel/getMateria.php?tipo="+Tipo);
                                break;
                            case 4:
                                Tipo =datos[i];
                                new ConsultarDatos2().execute("http://yiyisman.esy.es/Hotel/getMateria.php?tipo="+Tipo);
                                break;
                            case 5:
                                Tipo =datos[i];
                                new ConsultarDatos2().execute("http://yiyisman.esy.es/Hotel/getMateria.php?tipo="+Tipo);
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

                material = car.split(",");
                Button btnModificar = (Button)findViewById(R.id.button8);
                Button btnModificar1 = (Button)findViewById(R.id.button3);
                ArrayAdapter<String> adaptador1 = new ArrayAdapter<>(NuevoM.this, android.R.layout.simple_spinner_item,material);
                lista3.setAdapter(adaptador1);


                lista3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (i){
                            case 0:
                                Material1 =material[i];
                                break;
                            case 1:
                                Material1 =material[i];
                                break;
                            case 2:
                                Material1 =material[i];
                                break;
                            case 3:
                                Material1 =material[i];
                                break;
                            case 4:
                                Material1 =material[i];
                                break;
                            case 5:
                                Material1 =material[i];
                                break;
                        }

                    }

                    public void onNothingSelected(AdapterView<?> adapterView) {
                        return;
                    }
                });
                nume =(EditText)findViewById(R.id.editText2);
                btnModificar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new ModificarDatos().execute("http://yiyisman.esy.es/Hotel/Sumar.php?numero="+nume.getText().toString()+"&material="+Material1+"&id="+NombreH);

                    }
                });
                btnModificar1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new ModificarDatos().execute("http://yiyisman.esy.es/Hotel/Sumar.php?numero=-"+nume.getText().toString()+"&material="+Material1+"&id="+NombreH);

                    }
                });

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
