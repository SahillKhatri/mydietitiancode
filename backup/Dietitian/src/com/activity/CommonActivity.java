package com.activity;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Scanner;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.helper.AndroidConstants;
//import com.helper.CustomListViewAdapter;
//import com.helper.CustomListViewObject;
import com.helper.HttpView;
import com.helper.StringHelper;


@SuppressLint("NewApi")
public class CommonActivity extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		android.os.StrictMode.ThreadPolicy tp = android.os.StrictMode.ThreadPolicy.LAX;
		android.os.StrictMode.setThreadPolicy(tp);
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case R.id.action_settings:
			go(ConfigTabActivity.class);
			break;
		case R.id.exit:
			finished();
			break;
		}

		return super.onOptionsItemSelected(item);
	}
	
	public void toast(String message) {
		Toast t = Toast.makeText(CommonActivity.this, message, 3000);
		t.show();
	}
	
	public void go(Class c) {
		Intent intent = new Intent(CommonActivity.this, c);
		startActivity(intent);
	}
	public  boolean checkConnectivityServer() {
		boolean success=checkConnectivityServer(AndroidConstants.MAIN_SERVER_IP,AndroidConstants.MAIN_SERVER_PORT);
			return success;
				
	}
	public static boolean checkConnectivityServer(String ip, String por) {
		int port = Integer.parseInt(por);
		boolean success = false;
		try {
			System.out.println("Checking Connectivity With "+ip+" "+port);
			Socket soc = new Socket();
			SocketAddress socketAddress = new InetSocketAddress(ip, port);
			soc.connect(socketAddress, 3000);
			success = true;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println(" Connecting to server " + success);
		return success;

	}
	
	public  String getIMEI(){
		  TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
	        String imei=telephonyManager.getDeviceId();
	        System.out.println("Device IMEI is "+imei);
	        return imei;

	}
	
	public void finished() {
		try {
			System.runFinalizersOnExit(true);
			finish();
			super.finish();
			super.onDestroy();
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			android.os.Process.killProcess(android.os.Process.myPid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	Context c;
	int HELLO_ID = (int) (Math.random() * 1000);
	public void notifyme(String contentTitle, String contentText) {
		if (c == null) {
		c = getApplicationContext();
		}
		String ns = Context.NOTIFICATION_SERVICE;
		NotificationManager mNotificationManager = (NotificationManager) c
		.getSystemService(ns);
		int icon = android.R.drawable.ic_dialog_alert;
		CharSequence tickerText = contentTitle;
		long when = System.currentTimeMillis();
		Notification notification = new Notification(icon, tickerText, when);
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		PendingIntent contentIntent = PendingIntent.getActivity(c, 0,
		new Intent(), 0);
		notification.setLatestEventInfo(c, contentTitle, contentText,
		contentIntent);
		mNotificationManager.notify(HELLO_ID, notification);
		}
	
	AlertDialog alertDialog;
	ProgressDialog progressDialog;

	class CheckConnectivityAsyncTask extends AsyncTask<String, String, String> {
		String message = "";
		String title = "";
		String action = "";

		@Override
		protected void onPreExecute() {
			System.out.println("In Aysnc");
			progressDialog = ProgressDialog.show(CommonActivity.this,
					"Please Wait", "Loading....", true);
			alertDialog = new AlertDialog.Builder(CommonActivity.this).create();
		}

		@Override
		protected String doInBackground(String... params) {
			String ip = params[0];
			int port = StringHelper.nullObjectToIntegerEmpty(params[1]);
			boolean success = HttpView.checkConnectivityServer(ip, port);
			if (success) {
				title = "Success";
				if (params.length > 2 && params[2].equalsIgnoreCase("UpdateIp")) {
					action = "1";
					message = "Connection established with the Main Server.";
					AndroidConstants.MAIN_SERVER_IP = ip;
					AndroidConstants.MAIN_SERVER_PORT = port + "";
				} else {
					message = "Internet Connection Successful!";
				}
			} else {
				action = "";
				message = "Error Connecting to Server http://" + ip + ":"
						+ port;
				title = "Connectivity Error";
			}
			return success + "";
		}

		@Override
		protected void onPostExecute(String result) {
			progressDialog.dismiss();
			alertDialog.setTitle(title);
			alertDialog.setMessage(message);
			alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					alertDialog.hide();
					if (action.length() > 0) {
						Intent main = new Intent(CommonActivity.this,
								MainActivity.class);
						startActivity(main);
						finish();
					}
				}
			});
			alertDialog.show();

		};

	}
}
