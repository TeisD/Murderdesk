package com.egi.interfacelink;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class InterfaceLink {
	
	private ServerSocket serverSocket;
	
	public InterfaceLink(String address, int port){
		try {
			serverSocket = new ServerSocket(port, 1, InetAddress.getByName(address));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start(){
		try {
			// wait for client and accept connection if he shows up
			Socket clientSocket = serverSocket.accept();
			System.out.println("connected!");
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try(){
			
		}
		
	}
	
}
