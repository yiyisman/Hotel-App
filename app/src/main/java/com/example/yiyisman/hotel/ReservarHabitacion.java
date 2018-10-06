package com.example.yiyisman.hotel;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReservarHabitacion extends AppCompatActivity {

    Statics S;
    ListViewAdapter adapter;
    String[] titulos = new String[]{
            "Suite",
            "Habitacion Normal",
            "Habitacion economica"
    };

    String[] subtitulos = new String[]{
            "Es la habitacion mas exclusiva.",
            "Hermosa habitacion con gran espacio y TV",
            "Vas de paso, perfecta para ti"
    };

    int[] imagenes = {
            R.drawable.suite,
            R.drawable.habitacion,
            R.drawable.camahotel
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar_habitacion);
        ImageButton vol = (ImageButton)findViewById(R.id.vol);


        final ListView lista = (ListView) findViewById(R.id.listView1);
        adapter = new ListViewAdapter(this, titulos, subtitulos, imagenes);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
                        S.setTipoH(1);
                        String al=(""+S.getTipoH());
                        Intent a = new Intent(ReservarHabitacion.this,Suite.class);
                        startActivity(a);

                    break;
                    case 1:
                        S.setTipoH(2);
                        String al2=(""+S.getTipoH());
                        Intent b = new Intent(ReservarHabitacion.this,HabitacionNormal.class);
                        startActivity(b);
                    break;
                    case 2:
                        S.setTipoH(3);
                        String al3=(""+S.getTipoH());
                        Intent c = new Intent(ReservarHabitacion.this,HabitacionEconomica.class);
                        startActivity(c);
                        break;
                }

            }

        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "presiono LARGO " + i, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        vol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ReservarHabitacion.this,Cliente.class);
                startActivity(i);

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
