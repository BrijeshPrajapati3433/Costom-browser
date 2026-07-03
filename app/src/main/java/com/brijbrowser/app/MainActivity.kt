package com.brijbrowser.app

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var web: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        web = findViewById(R.id.webView)

        val url = findViewById<EditText>(R.id.urlBox)

        val back = findViewById<ImageButton>(R.id.backBtn)

        val next = findViewById<ImageButton>(R.id.forwardBtn)

        val go = findViewById<ImageButton>(R.id.goBtn)

        val refresh = findViewById<ImageButton>(R.id.refreshBtn)

        val progress = findViewById<ProgressBar>(R.id.progressBar)

        web.settings.javaScriptEnabled = true
        web.settings.domStorageEnabled = true
        web.settings.loadsImagesAutomatically = true
        web.settings.cacheMode = WebSettings.LOAD_DEFAULT
        web.settings.useWideViewPort = true
        web.settings.loadWithOverviewMode = true

        web.webViewClient = WebViewClient()

        web.webChromeClient = object : WebChromeClient() {

            override fun onProgressChanged(view: WebView?, newProgress: Int) {

                progress.progress = newProgress

                if (newProgress == 100)

                    progress.visibility = ProgressBar.GONE

                else

                    progress.visibility = ProgressBar.VISIBLE

            }

        }

        web.loadUrl("https://www.google.com")

        go.setOnClickListener {

            var text = url.text.toString()

            if (!text.startsWith("http"))

                text = "https://www.google.com/search?q=$text"

            web.loadUrl(text)

        }

        back.setOnClickListener {

            if (web.canGoBack())

                web.goBack()

        }

        next.setOnClickListener {

            if (web.canGoForward())

                web.goForward()

        }

        refresh.setOnClickListener {

            web.reload()

        }

    }

}
