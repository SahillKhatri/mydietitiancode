package com.helper;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ParseException;
import android.telephony.TelephonyManager;
import android.util.Log;

public class HttpView {
	static String TAG = "HttpView";

	public static Bitmap drawable_from_url(String url) {
		Bitmap x = null;
		try {
			Log.v(TAG, url);
			HttpURLConnection connection = (HttpURLConnection) new URL(url)
					.openConnection();
			connection.setRequestProperty("User-agent", "Mozilla/4.0");
			connection.connect();
			InputStream input = connection.getInputStream();
			x = BitmapFactory.decodeStream(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}
	
	public static String connect2Server2(String url) {
		StringBuffer res = new StringBuffer();
		Log.v(TAG, url);
		URL u;
		try {
			System.out.println("Hitting URL ");
			u = new URL(url);
			URLConnection uc = u.openConnection();

			Scanner scanner = new Scanner(uc.getInputStream());
			String row = "";
			while (scanner.hasNext()) {
				row = StringHelper.n2s(scanner.nextLine());
				res.append(row);
			}
			scanner.close();
			u = null;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return res.toString();
	}

	public static String result[][] = new String[500][];
	public static int count = -1;

	public static String connectToServer(String url) {
		System.out.println("HERE!!!!!!!");
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);
		HttpResponse response;
		String s = "";
		HttpView.count = -1;
		try {
			System.out.println("HERE!!!!!!!try");
			int i = 0;
			for (i = 0; i < result.length; i++) {
				result[i] = null;
			}

			Log.e(TAG, "UPLOAD: about to execute");
			response = httpclient.execute(httppost);
			Log.e(TAG, "UPLOAD: executed");
			System.out.println("HERE!!!!!!!exec");
			s = HttpView.getResponseBody(response);
			System.out.println("HERE!!!!!!!resp");
			System.out.println("httppost is ::: "+httppost.toString());
			System.out.println("s is ::: "+s);
			s = s.trim();
			String rows[] = s.split("\n");

			System.out.println("rows.length " + rows.length);
			for (String string : rows) {
				String cols[] = string.split(",");
				result[++count] = cols;

			}

			System.out.println("Data " + s);
			// System.out.println("Data in result "+result[i]);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("HERE!!!!!!!other");
		}
		return s;
	}

	public static String getResponseBody(HttpResponse response) {
		System.out.println(response.toString());
		String response_text = null;
		HttpEntity entity = null;
		try {
			entity = response.getEntity();
			response_text = _getResponseBody(entity);

		} catch (Exception e) {
			if (entity != null) {
				try {
					entity.consumeContent();
				} catch (Exception e1) {
				}
			}
		}
		return response_text;
	}

	public static String _getResponseBody(final HttpEntity entity)
			throws IOException, ParseException {
		if (entity == null) {
			throw new IllegalArgumentException("HTTP entity may not be null");
		}
		InputStream instream = entity.getContent();
		if (instream == null) {
			return "";
		}

		if (entity.getContentLength() > Integer.MAX_VALUE) {
			throw new IllegalArgumentException(

			"HTTP entity too large to be buffered in memory");
		}
		String charset = getContentCharSet(entity);
		if (charset == null) {
			charset = HTTP.DEFAULT_CONTENT_CHARSET;
		}
		Reader reader = new InputStreamReader(instream, charset);
		StringBuilder buffer = new StringBuilder();
		try {

			char[] tmp = new char[1024];
			int l;
			while ((l = reader.read(tmp)) != -1) {
				buffer.append(tmp, 0, l);
			}

		} finally {
			reader.close();
		}
		return buffer.toString();
	}

	public static String getContentCharSet(final HttpEntity entity)
			throws ParseException {

		if (entity == null) {
			throw new IllegalArgumentException("HTTP entity may not be null");
		}

		String charset = null;

		if (entity.getContentType() != null) {

			HeaderElement values[] = entity.getContentType().getElements();

			if (values.length > 0) {

				NameValuePair param = values[0].getParameterByName("charset");

				if (param != null) {

					charset = param.getValue();

				}

			}

		}

		return charset;

	}

	public static String uploadFile(String sourceFileUri, String upLoadServerUri) {
		String result = null;
		String fileName = sourceFileUri;
		HttpURLConnection conn = null;
		DataOutputStream dos = null;
		String lineEnd = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****";
		int bytesRead, bytesAvailable, bufferSize;
		byte[] buffer;
		int maxBufferSize = 1 * 1024 * 1024;
		File sourceFile = new File(sourceFileUri);
		if (!sourceFile.isFile()) {
			return "-1";
		} else {
			try {
				// open a URL connection to the Servlet
				FileInputStream fileInputStream = new FileInputStream(
						sourceFile);
				URL url = new URL(upLoadServerUri);
				// Open a HTTP connection to the URL
				conn = (HttpURLConnection) url.openConnection();
				conn.setDoInput(true); // Allow Inputs
				conn.setDoOutput(true); // Allow Outputs
				conn.setUseCaches(false); // Don't use a Cached Copy
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Connection", "Keep-Alive");
				conn.setRequestProperty("ENCTYPE", "multipart/form-data");
				conn.setRequestProperty("Content-Type",
						"multipart/form-data;boundary=" + boundary);
				conn.setRequestProperty("uploaded_file", fileName);

				dos = new DataOutputStream(conn.getOutputStream());

				dos.writeBytes(twoHyphens + boundary + lineEnd);
				dos.writeBytes("Content-Disposition: form-data; name=uploaded_file;filename=\""
						+ fileName + "\"" + lineEnd);

				dos.writeBytes(lineEnd);

				// create a buffer of maximum size
				bytesAvailable = fileInputStream.available();

				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				buffer = new byte[bufferSize];

				// read file and write it into form...
				bytesRead = fileInputStream.read(buffer, 0, bufferSize);

				while (bytesRead > 0) {

					dos.write(buffer, 0, bufferSize);
					bytesAvailable = fileInputStream.available();
					bufferSize = Math.min(bytesAvailable, maxBufferSize);
					bytesRead = fileInputStream.read(buffer, 0, bufferSize);
				}

				// send multipart form data necesssary after file data...
				dos.writeBytes(lineEnd);
				dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
				// Responses from the server (code and message)
				int serverResponseCode = conn.getResponseCode();
				String serverResponseMessage = conn.getResponseMessage();

				Log.i("uploadFile", "HTTP Response is : "
						+ serverResponseMessage + ": " + serverResponseCode);

				if (serverResponseCode == 200) {
					InputStream is = conn.getInputStream();
					byte[] b = new byte[1024];
					is.read(b);
					result = new String(b);
					result = result.trim();
					Log.i("uploadFile", "HTTP Response is : " + result);
				}

				// close the streams //
				fileInputStream.close();
				dos.flush();
				dos.close();

			} catch (MalformedURLException ex) {

				ex.printStackTrace();

				System.out
						.println("MalformedURLException Exception : check script url.");

				Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
			} catch (Exception e) {

				e.printStackTrace();

				Log.e("Upload file to server Exception",
						"Exception : " + e.getMessage(), e);
			}

			return result;

		} // End else block
	}

	public static boolean checkConnectivityServer(String ip, int port) {
		boolean success = false;
		try {
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

}
