package com.egi.interfacelink;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class InterfaceLink extends Thread{
	
	private static final int SIZE = 2;
	
	private volatile boolean running = true;
	private ServerSocket server;
	private Socket client;
	private BufferedReader reader;
	public Boolean comments = false;
	private Boolean connected = false;
	private InterfaceLinkStorage[] values;
	
	public InterfaceLink(String address, int port){
		// Setup the storage object
		values = new InterfaceLinkStorage[SIZE];
		for(int i = 0; i<SIZE; i++){
			values[i] = new InterfaceLinkStorage();
		}
		
		// try to setup the connection socket
		try {
			server = new ServerSocket(port, 1, InetAddress.getByName(address));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		if(running){
			try{
				client = server.accept();
				comment("[InterfaceLink] New client accepted.");
				reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
				while(true & running){
					String s = reader.readLine();
					if(s != null){
						store(s);
		                comment("[InterfaceLink] "+ s);
					}else {
		                comment("[InterfaceLink] Client disconected. Waiting for new connection...");
						run();
					}
	            }   //end for
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void kill(){
		try {
			running = false;
			if(reader != null){ reader.close(); }
			if(client != null && client.isConnected()){ client.close(); }
			if(server != null){ server.close(); }
			comment("[InterfaceLink] Terminated.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean getBoolean(int id){
		return values[id].getAsBoolean();
	}
	
	public int getInt(int id){
		return values[id].getAsInteger();
	}
	
	public double getDouble(int id){
		return values[id].getAsDouble();
	}
	
	public boolean isConnected(){
		return connected;
	}
	
	private void store(String s){
		String[] input = s.split(":");
		if(isInt(input[0]) && isDouble(input[1])){
			int id = Integer.parseInt(input[0]);
			double value = Double.parseDouble(input[1]);
			values[id].store(value);
		}
	}
	
	private void comment(String s){
		if(comments){
			System.out.println(s);
		}
	}
	
	private boolean isDouble(String s){
		try{
			Double.parseDouble(s);
			return true;
		} catch(NumberFormatException e){
			comment("[InterfaceLink] Parse error: value is not a double.");
			return false;
		}
	}
	
	private boolean isInt(String s){
		try{
			Integer.parseInt(s);
			return true;
		} catch(NumberFormatException e){
			comment("[InterfaceLink] Parse error: value is not an int.");
			return false;
		}
	}
	
}
