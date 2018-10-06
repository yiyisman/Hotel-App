package com.example.yiyisman.hotel;

import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.net.Uri;

/**
 * Created by Yiyisman on 02/05/2017.
 */

public class MyAppWebViewClient extends WebViewClient{

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url){
        if(Uri.parse(url).getHost().endsWith("yiysman.esy.es")){
            return false;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        view.getContext().startActivity(intent);
        return true;
    }

}
