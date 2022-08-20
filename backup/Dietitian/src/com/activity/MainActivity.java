package com.activity;

import com.helper.AndroidConstants;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressLint("SetJavaScriptEnabled")
public class MainActivity extends CommonActivity {

	private WebView webView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		webView = (WebView) findViewById(R.id.webView1);
		webView.getSettings().setJavaScriptEnabled(true);

		webView.getSettings().setBuiltInZoomControls(false);
		webView.getSettings().setDefaultFontSize(8);
		
		android.os.StrictMode.ThreadPolicy tp = android.os.StrictMode.ThreadPolicy.LAX;
		android.os.StrictMode.setThreadPolicy(tp);
		
		webView.setWebViewClient(new WebViewClient());
		webView.loadUrl(AndroidConstants.MAIN_URL());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
