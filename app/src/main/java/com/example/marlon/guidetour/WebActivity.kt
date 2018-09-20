package com.example.marlon.guidetour

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView

class WebActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        val webView =findViewById<WebView>(R.id.web_page_view)
        webView.settings.javaScriptEnabled = true
        // Receiving url from the fragment
        val url= intent.dataString
        // Open url in the web view
        webView.loadUrl(url)
    }
}