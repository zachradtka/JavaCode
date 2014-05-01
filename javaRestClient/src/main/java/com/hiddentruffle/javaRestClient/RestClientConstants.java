package com.hiddentruffle.javaRestClient;

public class RestClientConstants {
	// Use an 8K output buffer
	public static int OUTPUT_BUFFER_SIZE = 1024 * 8;
	
	// Options to specify a URI
	public static String OPT_URI_SHORT = "u";
	public static String OPT_URI_LONG = "uri";
	public static Boolean OPT_URI_HAS_ARG = true;
	public static String OPT_URI_DESCRIPTION = "A URI upon which to apply a request";
	
	
	// Errors
	public static int ERROR_MALFORMED_COMMAND_LINE_ARG = 1;
	
}
