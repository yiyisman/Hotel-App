package com.example.yiyisman.hotel;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class Reservar extends AppCompatActivity {
    Spinner Hotel;
    ListView hab;
    String [] hoteles;
    String [] habitaciones;
    String [] titulos;
    String [] subtitulos;
    String NombreH;
    String Numero;
    Statics S;
    Statics2 sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar);
        hab=(ListView) findViewById(R.id.ha);
        Hotel=(Spinner)findViewById(R.id.spinner6);
        ImageButton volve =(ImageButton)findViewById(R.id.vol) ;
        volve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Reservar.this,ReservarHabitacion.class);
                startActivity(i);

            }
        });


        new ConsultarDatos().execute("http://yiyisman.esy.es/Hotel/geallHotel.php?");
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

                ArrayAdapter<String> adaptador = new ArrayAdapter<>(Reservar.this, android.R.layout.simple_spinner_item,hoteles);
                Hotel.setAdapter(adaptador);


                Hotel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (i){
                            case 0:
                                NombreH =hoteles[i];
                                S.setcHotel(hoteles[i]);
                                new ConsultarDatos3().execute("http://yiyisman.esy.es/Hotel/getallHabitaciones.php?tipo="+ Statics.getTipoH()+"&hotel="+NombreH);
                                break;
                            case 1:
                                NombreH =hoteles[i];
                                S.setcHotel(hoteles[i]);
                                new ConsultarDatos3().execute("http://yiyisman.esy.es/Hotel/getallHabitaciones.php?tipo="+ Statics.getTipoH()+"&hotel="+NombreH);

                                break;
                            case 2:
                                NombreH =hoteles[i];
                                S.setcHotel(hoteles[i]);
                                new ConsultarDatos3().execute("http://yiyisman.esy.es/Hotel/getallHabitaciones.php?tipo="+ Statics.getTipoH()+"&hotel="+NombreH);

                                break;
                            case 3:
                                NombreH =hoteles[i];
                                S.setcHotel(hoteles[i]);
                                new ConsultarDatos3().execute("http://yiyisman.esy.es/Hotel/getallHabitaciones.php?tipo="+ Statics.getTipoH()+"&hotel="+NombreH);

                                break;
                            case 4:
                                NombreH =hoteles[i];
                                S.setcHotel(hoteles[i]);
                                new ConsultarDatos3().execute("http://yiyisman.esy.es/Hotel/getallHabitaciones.php?tipo="+ Statics.getTipoH()+"&hotel="+NombreH);

                                break;
                            case 5:
                                NombreH =hoteles[i];
                                S.setcHotel(hoteles[i]);
                                new ConsultarDatos3().execute("http://yiyisman.esy.es/Hotel/getallHabitaciones.php?tipo="+ Statics.getTipoH()+"&hotel="+NombreH);

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

                habitaciones = car.split(",");

                ArrayAdapter<String> adaptador3 = new ArrayAdapter<>(Reservar.this, android.R.layout.simple_spinner_item,habitaciones);
                hab.setAdapter(adaptador3);

                hab.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (i){
                            case 0:
                                Numero=habitaciones[i];
                                new ConsultarDatos4().execute("http://yiyisman.esy.es/Hotel/getidHab.php?tipo="+ Statics.getTipoH()+"&hotel="+NombreH+"&numero="+Numero);
                                Intent a = new Intent(Reservar.this,FechaReserva.class);
                                startActivity(a);
                                break;
                            case 1:
                                Numero=habitaciones[i];
                                new ConsultarDatos4().execute("http://yiyisman.esy.es/Hotel/getidHab.php?tipo="+ Statics.getTipoH()+"&hotel="+NombreH+"&numero="+Numero);
                                Intent b = new Intent(Reservar.this,FechaReserva.class);
                                startActivity(b);

                                break;
                            case 2:
                                Numero=habitaciones[i];
                                new ConsultarDatos4().execute("http://yiyisman.esy.es/Hotel/getidHab.php?tipo="+ Statics.getTipoH()+"&hotel="+NombreH+"&numero="+Numero);
                                Intent c = new Intent(Reservar.this,FechaReserva.class);
                                startActivity(c);

                                break;
                            case 3:
                                Numero=habitaciones[i];
                                new ConsultarDatos4().execute("http://yiyisman.esy.es/Hotel/getidHab.php?tipo="+ Statics.getTipoH()+"&hotel="+NombreH+"&numero="+Numero);
                                Intent d = new Intent(Reservar.this,FechaReserva.class);
                                startActivity(d);

                                break;
                            case 4:
                                Numero=habitaciones[i];
                                new ConsultarDatos4().execute("http://yiyisman.esy.es/Hotel/getidHab.php?tipo="+ Statics.getTipoH()+"&hotel="+NombreH+"&numero="+Numero);
                                Intent e = new Intent(Reservar.this,FechaReserva.class);
                                startActivity(e);

                                break;
                            case 5:
                                Numero=habitaciones[i];
                                new ConsultarDatos4().execute("http://yiyisman.esy.es/Hotel/getidHab.php?tipo="+ Statics.getTipoH()+"&hotel="+NombreH+"&numero="+Numero);
                                Intent f = new Intent(Reservar.this,FechaReserva.class);
                                startActivity(f);

                                break;
                            case 6:
                                Numero=habitaciones[i];
                                new ConsultarDatos4().execute("http://yiyisman.esy.es/Hotel/getidHab.php?tipo="+ Statics.getTipoH()+"&hotel="+NombreH+"&numero="+Numero);
                                Intent g = new Intent(Reservar.this,FechaReserva.class);
                                startActivity(g);

                                break;
                            case 7:
                                Numero=habitaciones[i];
                                new ConsultarDatos4().execute("http://yiyisman.esy.es/Hotel/getidHab.php?tipo="+ Statics.getTipoH()+"&hotel="+NombreH+"&numero="+Numero);
                                Intent h = new Intent(Reservar.this,FechaReserva.class);
                                startActivity(h);

                                break;
                            case 8:
                                Numero=habitaciones[i];
                                new ConsultarDatos4().execute("http://yiyisman.esy.es/Hotel/getidHab.php?tipo="+ Statics.getTipoH()+"&hotel="+NombreH+"&numero="+Numero);
                                Intent j = new Intent(Reservar.this,FechaReserva.class);
                                startActivity(j);

                                break;
                            case 9:
                                Numero=habitaciones[i];
                                new ConsultarDatos4().execute("http://yiyisman.esy.es/Hotel/getidHab.php?tipo="+ Statics.getTipoH()+"&hotel="+NombreH+"&numero="+Numero);
                                Intent k = new Intent(Reservar.this,FechaReserva.class);
                                startActivity(k);

                                break;
                        }

                    }

                    public void onNothingSelected(AdapterView<?> adapterView) {
                        return;
                    }
                });
                hab.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView parent, View view, int position, long id) {
                        final String i=habitaciones[position];
                        PopupMenu popup = new PopupMenu(Reservar.this, view);
                        popup.getMenuInflater().inflate(R.menu.menu_main, popup.getMenu());
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                            //MENU FILA
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.menu_eliminar:

                                        S.setcHab(i);
                                        new ConsultarDatosh().execute("http://yiyisman.esy.es/Hotel/getidH.php?numero="+S.getcHab()+"&hotel="+S.getcHotel());

                                        Intent k = new Intent(Reservar.this,CalificacionesH.class);
                                        startActivity(k);
                                        break;
                                }
                                return true;
                            }
                            //FIN MENU FILA
                        });
                        popup.show();
                        return true;
                    }

                });





            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    public class ConsultarDatosh extends AsyncTask<String, Void, String> {
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
                new ConsultarDatosti().execute("http://yiyisman.esy.es/Hotel/getcali.php?numero="+ja.getString(0));
                new ConsultarDatosto().execute("http://yiyisman.esy.es/Hotel/getobs.php?numero="+ja.getString(0));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    public class ConsultarDatosti extends AsyncTask<String, Void, String> {
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
                if(titulos.length==5){
                    sa.setA(titulos[0]);
                    sa.setB(titulos[1]);
                    sa.setC(titulos[2]);
                    sa.setD(titulos[3]);
                    sa.setE(titulos[4]);
                }
                if(titulos.length==4){
                    sa.setA(titulos[0]);
                    sa.setB(titulos[1]);
                    sa.setC(titulos[2]);
                    sa.setD(titulos[3]);
                    sa.setE("");
                }
                if(titulos.length==3){
                    sa.setA(titulos[0]);
                    sa.setB(titulos[1]);
                    sa.setC(titulos[2]);
                    sa.setD("");
                    sa.setE("");
                }
                if(titulos.length==2){
                    sa.setA(titulos[0]);
                    sa.setB(titulos[1]);
                    sa.setC("");
                    sa.setD("");
                    sa.setE("");
                }
                if(titulos.length==1){
                    sa.setA(titulos[0]);
                    sa.setB("");
                    sa.setC("");
                    sa.setD("");
                    sa.setE("");
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }
    public class ConsultarDatosto extends AsyncTask<String, Void, String> {
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
                subtitulos = car.split(",");

                if(subtitulos.length==5){
                    sa.setOa(subtitulos[0]);
                    sa.setOb(subtitulos[1]);
                    sa.setOc(subtitulos[2]);
                    sa.setOd(subtitulos[3]);
                    sa.setOe(subtitulos[4]);
                }
                if(subtitulos.length==4){
                    sa.setOa(subtitulos[0]);
                    sa.setOb(subtitulos[1]);
                    sa.setOc(subtitulos[2]);
                    sa.setOd(subtitulos[3]);
                    sa.setOe("");
                }
                if(subtitulos.length==3){
                    sa.setOa(subtitulos[0]);
                    sa.setOb(subtitulos[1]);
                    sa.setOc(subtitulos[2]);
                    sa.setOd("");
                    sa.setOe("");
                }
                if(subtitulos.length==2){
                    sa.setOa(subtitulos[0]);
                    sa.setOb(subtitulos[1]);
                    sa.setOc("");
                    sa.setOd("");
                    sa.setOe("");
                }
                if(subtitulos.length==1){
                    sa.setOa(subtitulos[0]);
                    sa.setOb("");
                    sa.setOc("");
                    sa.setOd("");
                    sa.setOe("");
                }



            } catch (JSONException e) {
                e.printStackTrace();
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
                S.setNumeroH(ja.getString(0));

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
