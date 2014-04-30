package com.hiddentruffle.javaRestClient;

import java.io.BufferedOutputStream;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Driver {
	
	// Use an 8K output buffer
	public static int OUTPUT_BUFFER_SIZE = 1024 * 8;
	
	
	public static void main(String[] args) {
		CloseableHttpClient client = HttpClients.createDefault();

		HttpGet httpGet = new HttpGet(
				"http://www.google.com/search?hl=en&q=httpclient&btnG=Google+Search&aq=f&oq=");

		CloseableHttpResponse response = null;
		BufferedOutputStream bufferedOutput = null;
		try {

			System.out.println("Connecting to: " + httpGet.getURI());
			response = client.execute(httpGet);

			System.out.println("Status: " + response.getStatusLine());

			HttpEntity entity = response.getEntity();

			if (entity != null) {

				bufferedOutput = new BufferedOutputStream(System.out, OUTPUT_BUFFER_SIZE);
				entity.writeTo(bufferedOutput);

				bufferedOutput.flush();
				bufferedOutput.close();

			}

			System.out.println("Response: " + response);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
