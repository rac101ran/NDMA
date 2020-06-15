package com.example.disastermanagementfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class Floods extends AppCompatActivity {
    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floods);
        webview=findViewById(R.id.web2);

        webview=new WebView(this);
        setContentView(webview);
        webview.loadUrl("https://www.gdacs.org/report.aspx?eventid=1100417&episodeid=5&eventtype=FL");
    }
}
