package com.hiddentruffle;

import java.io.BufferedOutputStream;
import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class RestClient {
	
	/**
	 * The available command line options
	 */
	private static Options options;
	
	private static String uri;
	
	public static void main(String[] args) {
		
		parseCommandLine(args);

		
		
		
		
		CloseableHttpClient client = HttpClients.createDefault();

		HttpGet httpGet = new HttpGet(uri);

		CloseableHttpResponse response = null;
		BufferedOutputStream bufferedOutput = null;
		try {

			System.out.println("Connecting to: " + httpGet.getURI());
			response = client.execute(httpGet);

			System.out.println("Status: " + response.getStatusLine());

			HttpEntity entity = response.getEntity();

			if (entity != null) {

				bufferedOutput = new BufferedOutputStream(System.out, RestClientConstants.OUTPUT_BUFFER_SIZE);
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
	
	private static void parseCommandLine(String[] args) {

		HelpFormatter formatter = new HelpFormatter();

		
		// Initialize the command line options
		initializeOptions();

		// Initialize the parser
		CommandLineParser cliParser = new DefaultParser();


		CommandLine cmd = null;

		// Parse the command line options
		try {
			cmd = cliParser.parse(options, args);
		} catch (ParseException e1) {
			System.err.println(e1.getMessage());
			formatter.printHelp(RestClient.class.getSimpleName(), options);
			System.exit(RestClientConstants.ERROR_MALFORMED_COMMAND_LINE_ARG);
		}
		
		
		
		if(cmd.hasOption(RestClientConstants.OPT_URI_SHORT)
				|| cmd.hasOption(RestClientConstants.OPT_URI_LONG)) {
			uri = cmd.getOptionValue(RestClientConstants.OPT_URI_SHORT);
		} else {
			formatter.printHelp(RestClient.class.getSimpleName(), options);
			System.exit(RestClientConstants.ERROR_MALFORMED_COMMAND_LINE_ARG);
		}
		
	}

	/**
	 * Initialize the command line options for the REST client
	 * @param options The available options for the REST client
	 */
	private static void initializeOptions() {

		// Initialize the options object
		options = new Options();
		
		// Create an option for the URI
		Option optUri = Option.builder(RestClientConstants.OPT_URI_SHORT)
			.longOpt(RestClientConstants.OPT_URI_LONG)
			.desc(RestClientConstants.OPT_URI_DESCRIPTION)
			.hasArg()
			.required()
			.build();
		
		// Add the URI option to the list of options
		options.addOption(optUri);
		
		
	}
}
