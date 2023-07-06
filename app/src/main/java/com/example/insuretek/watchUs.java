package com.example.insuretek;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class watchUs extends AppCompatActivity {

    private static final Integer VIDEO_ID = 798891880;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_us);
        webView=findViewById(R.id.webView1);
//        webView.loadData("<iframe src=\"http://player.vimeo.com/video/"+VIDEO_ID+"\" width=\"180px\" height=\"180px\" frameborder=\"0\" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>", "text/html", "utf-8");


//
//        webView.getSettings().setJavaScriptEnabled(true);
//
//        String yourData = "<div id='made-in-ny'></div>\n" +
//                "\n" +
//                "<script src='https://player.vimeo.com/api/player.js'></script>\n" +
//                "<script>\n" +
//                "    var options = {\n" +
//                "        id: 798891880,\n" +
//                "        width: 540,\n" +
//                "        loop: true\n" +
//                "    };\n" +
//                "\n" +
//                "    var player = new Vimeo.Player('made-in-ny', options);\n" +
//                "\n" +
//                "    player.setVolume(0);\n" +
//                "\n" +
//                "    player.on('play', function() {\n" +
//                "        console.log('played the video!');\n" +
//                "    });\n" +
//                "</script>";


        String vimeoVideo = "<html><body><iframe width=\"400\" height=\"315\" src=\"https://www.biztvevents.com/live/\" frameborder=\"0\" allowfullscreen></iframe></body></html>";



        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest request) {

                webView.loadUrl(request.getUrl().toString());
                return true;
            }
        });
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadData(vimeoVideo, "text/html", "utf-8");
    }


}