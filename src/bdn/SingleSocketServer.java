package bdn;

import java.net.*;
import java.io.*;
import java.util.*;

public class SingleSocketServer {
	
	static ServerSocket socket1;
	protected final static int port = 1600;
	static Socket connection;
	
	static boolean first;
	static StringBuffer process;
	static String TimeStamp;
	
	public static void main(String[] args) {
		try {
			socket1 = new ServerSocket(port);
			System.out.println("SingleSocketServer Initialized");
			int character;
			
			while (true) {
				connection = socket1.accept();
				BufferedInputStream is = new BufferedInputStream(connection.getInputStream());
				InputStreamReader isr = new InputStreamReader(is);
				process = new StringBuffer();
				while((character = isr.read()) != 13) {
					process.append((char)character);
					System.out.println(character);
				}
				System.out.println(process);
				try {
					Thread.sleep(10000);
				}
				catch (Exception e) {}
				TimeStamp = new java.util.Date().toString();
				String returnCode = "SingleSocketServer responded at"+TimeStamp+(char) 13;
				BufferedOutputStream os = new BufferedOutputStream(connection.getOutputStream());
				OutputStreamWriter osw = new OutputStreamWriter(os,"US-ASCII");
				osw.write(returnCode);
				osw.flush();
			}
		}
		catch (IOException e) {}
		try {
			connection.close();
		}
		catch (IOException e) {}
		
	}

}
