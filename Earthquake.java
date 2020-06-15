package com.example.disastermanagementfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class Earthquake extends AppCompatActivity {
WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        webView=findViewById(R.id.webview);
        webView=new WebView(this);

        setContentView(webView);
        webView.loadUrl("https://www.gdacs.org/report.aspx?eventid=1212950&episodeid=1304148&eventtype=EQ");
    }
}
