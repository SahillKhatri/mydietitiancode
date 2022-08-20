package com.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.preference.PreferenceManager;
import com.helper.AndroidConstants;

@SuppressLint("NewApi")
public class WelcomeActivity extends CommonActivity {

	protected int _splashTime = 3000;
	public static String TAG = "WelcomeActivity";

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcomescreen);
		toast("Server IP " + AndroidConstants.MAIN_SERVER_IP);
		ThreadPolicy tp = ThreadPolicy.LAX;
		StrictMode.setThreadPolicy(tp);
		setTitle("Welcome To Toll User App");
		Thread splashTread = new Thread() {
			@Override
			public void run() {
				try {
					sleep(_splashTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
					// do nothing
				} finally {
					checkConnectivity();
					finish();

				}
			}
		};
		splashTread.start();
	}

	public void checkConnectivity() {
		SharedPreferences s = PreferenceManager
				.getDefaultSharedPreferences(WelcomeActivity.this);
		boolean success = checkConnectivityServer();
		if (success) {
			SharedPreferences.Editor editor = s.edit();
			editor.putString("MAIN_SERVER_IP", AndroidConstants.MAIN_SERVER_IP
					+ "");
			editor.putString("MAIN_SERVER_PORT",
					AndroidConstants.MAIN_SERVER_PORT + "");
			editor.commit();
			go(MainActivity.class);

		} else {
			String MAIN_SERVER_IP = s.getString("MAIN_SERVER_IP",
					AndroidConstants.MAIN_SERVER_IP);
			String MAIN_SERVER_PORT = s.getString("MAIN_SERVER_PORT",
					AndroidConstants.MAIN_SERVER_PORT);
			if (!MAIN_SERVER_IP
					.equalsIgnoreCase(AndroidConstants.MAIN_SERVER_IP)
					|| !MAIN_SERVER_PORT
							.equalsIgnoreCase(AndroidConstants.MAIN_SERVER_PORT)) {
				success = checkConnectivityServer(MAIN_SERVER_IP,
						MAIN_SERVER_PORT);
				if (success) {
					AndroidConstants.MAIN_SERVER_IP = MAIN_SERVER_IP;
					AndroidConstants.MAIN_SERVER_PORT = MAIN_SERVER_PORT;
					go(MainActivity.class);
				} else {
					System.out.println("Redirecting to Config 1");
					go(ConfigTabActivity.class);
				}
			} else {
				System.out.println("Redirecting to Config 2");
				go(ConfigTabActivity.class);
			}
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

	}

}
