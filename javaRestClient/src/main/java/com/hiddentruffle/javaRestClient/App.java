package com.hiddentruffle.javaRestClient;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
		HttpClient cleint = new DefaultHttpClient();

        System.out.println( "Hello World!" );
    }
}
