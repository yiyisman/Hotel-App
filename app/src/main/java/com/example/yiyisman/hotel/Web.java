package com.example.yiyisman.hotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

public class Web extends AppCompatActivity {

    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        ImageButton vol=(ImageButton)findViewById(R.id.vol);
        vol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Web.this,Cliente.class);
                startActivity(i);

            }
        });

        web = (WebView)findViewById(R.id.web1);

        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);

        web.loadUrl("http://yiyisman.esy.es/Hotel/pagina.html");

        web.setWebViewClient(new WebViewClient());


    }
}
