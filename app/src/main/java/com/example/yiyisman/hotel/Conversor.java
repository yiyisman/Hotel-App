package com.example.yiyisman.hotel;

import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Conversor extends Activity  {
    public static final String TAG = "XMLParsingECB";

    public void log(String s) {
        Log.d(TAG, s);
    }
    TextView view1;
    String datos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversor);
        Button btn = (Button)findViewById(R.id.btn_convert);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Conver().execute("http://www.ecb.int/stats/eurofxref/eurofxref-daily.xml");

            }
        });






    }
    private class Conver extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {

                URL url = new URL(urls[0]);

                log("URL: " + url);

                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();

                NodeList nodeList = doc.getElementsByTagName("Cube");
                log("nodeList.getlength(): " + nodeList.getLength());
                // Quitar currenci para obtener numero
                for (int i = 0; i < 3; i++) {
                    Node node = nodeList.item(i);

                    log("i=" + i + " node: " + node);

                    if (node instanceof Element) {
                        log("es un Element");
                    } else {
                        log("no es un Element");
                    }

                    Element element = (Element) node;

                    String currency = element.getAttribute("currency");
                    String rate = element.getAttribute("rate");

                    log("currency=" + currency + " rate=" + rate);

                    datos=currency+rate;

                }


            } catch (Exception e) {
                log("XML Pasing Excpetion = " + e);
            }

            return datos;

        }
        protected void onPostExecute(String datos1) {
            view1 = (TextView) findViewById(R.id.view);

            view1.setText(datos1);
        }
    }
}