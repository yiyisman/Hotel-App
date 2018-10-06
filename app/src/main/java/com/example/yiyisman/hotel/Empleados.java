package com.example.yiyisman.hotel;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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


public class Empleados extends Fragment{

    Button btnconsultar, btnGuardar, btnModificar, btnEliminar;
    EditText etDocumento, etNombres, etHotel, etTipo;
    View rootView;
    String[] datos1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.activity_empleados, container, false);

        Button vol = (Button)rootView.findViewById(R.id.button11);


        vol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(),Gestion.class);
                startActivity(i);

            }
        });


        btnconsultar = (Button)rootView.findViewById(R.id.btnConsultar);
        btnGuardar = (Button)rootView.findViewById(R.id.btnGuardar);
        btnModificar = (Button)rootView.findViewById(R.id.btnModificar);
        btnEliminar = (Button)rootView.findViewById(R.id.btnEliminar);
        etDocumento = (EditText)rootView.findViewById(R.id.etDocumento);
        etNombres = (EditText)rootView.findViewById(R.id.etNombre);
        etHotel = (EditText)rootView.findViewById(R.id.etHotel);
        etTipo = (EditText)rootView.findViewById(R.id.etTipo);

        btnconsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new ConsultarDatos().execute("http://yiyisman.esy.es/Hotel/consultaE.php?documento="+etDocumento.getText().toString());


            }
        });


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etNombres.getText().toString().equals("")|| etDocumento.getText().toString().equals("")||etHotel.getText().toString().equals("")||etTipo.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Faltan campos", Toast.LENGTH_LONG).show();


                }else{
                    new CargarDatos().execute("http://yiyisman.esy.es/Hotel/registroE.php?nombre="+etNombres.getText().toString()+"&documento="+etDocumento.getText().toString()+"&idHotel="+etHotel.getText().toString()+"&idTipoEmp="+etTipo.getText().toString());
                    Intent i = new Intent(getActivity(),tabs.class);
                    startActivity(i);
                }

            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new EliminarDatos().execute("http://yiyisman.esy.es/Hotel/eliminarE.php?documento="+etDocumento.getText().toString());
                Intent i = new Intent(getActivity(),tabs.class);
                startActivity(i);
            }
        });
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new ModificarDatos().execute("http://yiyisman.esy.es/Hotel/modificarE.php?nombre="+etNombres.getText().toString()+"&documento="+etDocumento.getText().toString()+"&idHotel="+etHotel.getText().toString()+"&idTipoEmp="+etTipo.getText().toString());
                Intent i = new Intent(getActivity(),tabs.class);
                startActivity(i);
            }
        });


        return rootView;
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

            Toast.makeText(getActivity(), "Se almacenaron los datos correctamente", Toast.LENGTH_LONG).show();

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

            Toast.makeText(getActivity(), "Se elimino correctamente", Toast.LENGTH_LONG).show();

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

            Toast.makeText(getActivity(), "Se modificaron los datos correctamente", Toast.LENGTH_LONG).show();

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

            try {

                ja = new JSONArray(result);

                etNombres.setText(ja.getString(1));
                etDocumento.setText(ja.getString(2));
                etHotel.setText(ja.getString(3));
                etTipo.setText(ja.getString(4));

            } catch (JSONException e) {
                e.printStackTrace();
            }

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
