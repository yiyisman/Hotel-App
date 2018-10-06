package com.example.yiyisman.hotel;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Gestion extends ActionBarActivity {

    Statics S;
    ListViewAdapter adapter;
    String[] titulos = new String[]{
            "Empleados",
            "Clientes",
            "Inventario",
            "Estadisticas"
    };

    String[] subtitulos = new String[]{
            "Gestion de empleados.",
            "Gestion de clientes.",
            "Manejo de inventario",
            "Grafica con la cantidad de clientes por hotel en tiempo real"
    };

    int[] imagenes = {
            R.drawable.admin,
            R.drawable.clientes,
            R.drawable.inv,
            R.drawable.graf
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion);
        ActionBar actionBar = getSupportActionBar();


        /**INDICAR TITULO Y SUBTITULO**/
        actionBar.setTitle("Admin conectado: "+S.getSUsuarioA());
        TextView tv =(TextView)findViewById(R.id.textView2);
        tv.setText("");
        final ListView lista = (ListView) findViewById(R.id.listView2);
        adapter = new ListViewAdapter(this, titulos, subtitulos, imagenes);
        lista.setAdapter(adapter);
        ImageButton vol = (ImageButton)findViewById(R.id.imageButton);
        ImageButton cer = (ImageButton)findViewById(R.id.imageButton9);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
                        Intent a = new Intent(Gestion.this,tabs.class);
                        startActivity(a);

                        break;
                    case 1:

                        Intent b = new Intent(Gestion.this,MainActivity.class);
                        startActivity(b);
                        break;
                    case 2:

                        Intent c = new Intent(Gestion.this,GestionInventario.class);
                        startActivity(c);
                        break;
                    case 3:
                        new ConsultarDatos().execute("http://yiyisman.esy.es/Hotel/getcuenta.php");

                        break;
                }
            }

        });


        vol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Gestion.this,Login.class);
                startActivity(i);

            }
        });

        cer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Gestion.this,Login.class);
                startActivity(i);
                S.setSUsuarioA(null);
                Toast.makeText(getApplicationContext(), "Sesion cerrada correctamente", Toast.LENGTH_LONG).show();

            }
        });
    }
    public class ListViewAdapter extends BaseAdapter {
        // Declare Variables
        Context context;
        String[] titulos;
        String[] subtitulos;
        int[] imagenes;
        LayoutInflater inflater;

        public ListViewAdapter(Context context, String[] titulos,String[] subtitulos, int[] imagenes) {
            this.context = context;
            this.titulos = titulos;
            this.subtitulos = subtitulos;
            this.imagenes = imagenes;
        }

        @Override
        public int getCount() {
            return titulos.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            // Declare Variables
            TextView txtTitle;
            TextView txtSubTitle;
            ImageView imgImg;

            //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View itemView = inflater.inflate(R.layout.list_row, parent, false);

            // Locate the TextViews in listview_item.xml
            txtTitle = (TextView) itemView.findViewById(R.id.titulo);
            txtSubTitle = (TextView) itemView.findViewById(R.id.subtitulo);
            imgImg = (ImageView) itemView.findViewById(R.id.imagen_tv);

            // Capture position and set to the TextViews
            txtTitle.setText(titulos[position]);
            txtSubTitle.setText(subtitulos[position]);
            imgImg.setImageResource(imagenes[position]);

            return itemView;
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
                String spl=result;


                String a=spl.replaceAll("\"", "");;
                String car1=a.replaceAll("\\]",",");
                String car=car1.replaceAll("\\[","");


                String [] stock = car.split(",");
                float b=Float.parseFloat(stock[0]);
                float c=Float.parseFloat(stock[1]);
                float d=Float.parseFloat(stock[2]);
                float [] stock1={b,c,d};

                Statics.setCantidad(stock1);
            Intent intent= new Intent(Gestion.this,grafica.class);
            startActivity(intent);






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
