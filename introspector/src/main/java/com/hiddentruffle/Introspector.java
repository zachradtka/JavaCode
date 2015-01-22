package com.hiddentruffle;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Hello world!
 *
 */
public class Introspector {
	public static void main(String[] args) {
		// Get the class to interrogate from the command line
		if (args.length != 2) {
			System.err.println("Incorrect number of arguments specified");
			System.err.println("Usage: ");
			System.err.println(Introspector.class.getSimpleName()
					+ "<jarFile> <classToInspect>");
			System.exit(1);
		}

		String jarToLoad = args[0];
		String classToInterrogate = args[1];

		try {

			// Create a URL for the Jar to load
			URL[] urls = { new URL("jar:file:" + jarToLoad + "!/") };

			// Create a class loader for the jar
			URLClassLoader urlClassLoader = URLClassLoader.newInstance(urls);

			// Load the Jar
			Class<?> myClass = urlClassLoader.loadClass(classToInterrogate);

			// Get the constructors for the class
			Constructor<?>[] constructors = myClass.getConstructors();

			System.out.println("Constructors");
			for (Constructor<?> constructor : constructors) {
				System.out.println(constructor);
			}

			// Get the methods for a class
			Method[] methods = myClass.getDeclaredMethods();

			System.out.println("Methods");
			for (Method method : methods) {
				System.out.println(method);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
