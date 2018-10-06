package com.example.yiyisman.hotel;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class LoginCliente extends ActionBarActivity {

    EditText etUsuario, etContra;
    Statics S;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_cliente);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        /**INDICAR TITULO Y SUBTITULO**/
        actionBar.setTitle("Login cliente");
        Button vol = (Button)findViewById(R.id.loginc);
        Button reg = (Button)findViewById(R.id.register);

        etUsuario = (EditText)findViewById(R.id.username);
        etContra = (EditText)findViewById(R.id.password);
        vol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int lusu=etUsuario.getText().toString().length();
                int lcon=etContra.getText().toString().length();
                if(lusu>1&&lcon>1){
                    new ConsultarDatos().execute("http://yiyisman.esy.es/Hotel/loginC.php?usuario=" + etUsuario.getText().toString() + "&contraseña=" + etContra.getText().toString());

                }
                else{
                    Toast.makeText(getApplicationContext(), "Faltan campos", Toast.LENGTH_LONG).show();
                }


            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(LoginCliente.this,RegistrarCliente.class);
                startActivity(i);

            }
        });
    }
    private class ConsultarDatos extends AsyncTask<String, Void, String> {
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
                String id =ja.getString(0);
                Statics.setIdcli1(ja.getString(0));
                String usu= ja.getString(5);
                String con= ja.getString(6);
                String nom= ja.getString(1);
                String ape= ja.getString(2);
                if(usu!=""&&con!=""){
                    Statics2.setNombreC(nom);
                    Statics2.setApellidoC(ape);
                    Toast.makeText(getApplicationContext(), "Hola "+nom+" "+ape, Toast.LENGTH_LONG).show();
                    S.setIdCli(id);
                    S.setSUsuario(etUsuario.getText().toString());
                    Intent i = new Intent(LoginCliente.this,Cliente.class);
                    startActivity(i);

                }
                if(usu==""&&con==""){
                    Toast.makeText(getApplicationContext(), "Lo siento", Toast.LENGTH_LONG).show();
                }


            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "No existe el usuario", Toast.LENGTH_LONG).show();
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
