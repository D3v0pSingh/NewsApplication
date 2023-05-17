package com.example.cauroselapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.cauroselapp.databinding.ActivityDetailedViewBinding

class detailedVIew : AppCompatActivity() {
    private lateinit var binding:ActivityDetailedViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailedViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra("URL")
        if (url != null){
            binding.apply {
                webView.settings.javaScriptEnabled = true //enabling all the javaScript Properties that is required
                webView.settings.userAgentString = "\"Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3\""
                //This userAgentString tells the activity from where the response is coming i.e. if the
                //response is from desktop or mobile and in mobile also from tablet or mobile device.
                webView.webViewClient = object: WebViewClient(){
                    //webViewCLient is used to catch the webView page load so that we can tell the app what to do once the page is under loading
                    //and what to do when the page has already been loaded.
                    override fun onPageFinished(view: WebView?, url: String?) {
                        progressBar.visibility = View.GONE
                        webView.visibility = View.VISIBLE
                    }
                }
                //after page is loaded we will run the particualar url
                webView.loadUrl(url)
            }
        }
    }
}