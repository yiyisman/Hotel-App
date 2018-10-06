package com.example.yiyisman.hotel;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
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

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class CalificacionesH extends AppCompatActivity {
Statics S;
    Statics2 sa;
    ListView listc;

    protected String ab;
    String [] califi;
    String [] titulos ={
            "calificacion: "+sa.getA()+"/5",
            "calificacion: "+sa.getB()+"/5",
            "calificacion: "+sa.getC()+"/5",
            "calificacion: "+sa.getD()+"/5",
            "calificacion: "+sa.getE()+"/5"};
    String[] subtitulos = new String[]{sa.getOa(),sa.getOb(),sa.getOc(),sa.getOd(),sa.getOe()
    };
    int[] imagenes = {
            R.drawable.calificaciones,
            R.drawable.calificaciones,
            R.drawable.calificaciones,
            R.drawable.calificaciones,
            R.drawable.calificaciones,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificaciones_h);
        ActionBar actionBar = getSupportActionBar();

        /**INDICAR TITULO Y SUBTITULO**/
        actionBar.setTitle("Habitacion: "+S.getcHab()+"/ Hotel: "+S.getcHotel());
        Button vol = (Button)findViewById(R.id.loginc);
        String hab=S.getcHab();
        String hot=S.getcHotel();

        listc =(ListView)findViewById(R.id.listc);
        ListViewAdapter adapter = new ListViewAdapter(this, titulos, subtitulos, imagenes);
        listc.setAdapter(adapter);
        ImageButton volve =(ImageButton)findViewById(R.id.vol) ;
        imagenes.length;
        volve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(CalificacionesH.this,Reservar.class);
                startActivity(i);

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
                S.setAb(ja.getString(0));



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
            try {

                ja = new JSONArray(result);

                String spl=result;


                String a=spl.replaceAll("\"", "");;
                String car1=a.replaceAll("\\]",",");
                String car=car1.replaceAll("\\[","");

                titulos = car.split(",");


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

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
