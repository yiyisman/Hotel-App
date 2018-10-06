package com.example.yiyisman.hotel;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegistrarCliente extends AppCompatActivity {

    Button vol, btRegis;
    EditText etDocumento, etNombre, etTelefono, etApellido, etUsuario, etContra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cliente);
        vol = (Button)findViewById(R.id.vol);
        btRegis = (Button)findViewById(R.id.btRegistrar);
        etDocumento = (EditText)findViewById(R.id.etDocumento);
        etNombre = (EditText)findViewById(R.id.etNombre);
        etApellido = (EditText)findViewById(R.id.etHotel);
        etTelefono = (EditText)findViewById(R.id.etTelefono);
        etUsuario = (EditText)findViewById(R.id.etUsuario);
        etContra = (EditText)findViewById(R.id.etContra);
        vol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(RegistrarCliente.this,LoginCliente.class);
                startActivity(i);

            }
        });
        btRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int lusu=etUsuario.getText().toString().length();
                int lcon=etContra.getText().toString().length();
                int ldoc=etDocumento.getText().toString().length();
                int lnom=etNombre.getText().toString().length();
                int lape=etApellido.getText().toString().length();
                int ltel=etTelefono.getText().toString().length();
                if(lusu>1&&lcon>1&&ldoc>1&&lnom>1&&lape>1&&ltel>1){
                    new CargarDatos().execute("http://yiyisman.esy.es/Hotel/registroup.php?nombre="+etNombre.getText().toString()+"&apellido="+etApellido.getText().toString()+"&documento="+etDocumento.getText().toString()+"&telefono="+etTelefono.getText().toString()+"&usuario="+etUsuario.getText().toString()+"&contraseña="+etContra.getText().toString());
                    Intent i = new Intent(RegistrarCliente.this,LoginCliente.class);
                    Toast.makeText(getApplicationContext(), "Se ha registrado exitosamente", Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "Introduzca usuario y contraseña", Toast.LENGTH_LONG).show();
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(), "Faltan campos", Toast.LENGTH_LONG).show();
                }


            }
        });

    }
    private class CargarDatos extends AsyncTask<String, Void, String> {
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
