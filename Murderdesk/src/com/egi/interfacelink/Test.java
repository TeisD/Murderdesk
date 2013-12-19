package com.egi.interfacelink;

public class Test {
	
	public static InterfaceLink il;

	public static void main(String[] args) {
		System.out.println("Before");
		
		
		il = new InterfaceLink("127.0.0.1",2222);
		il.comments = true;
		il.start();
		il.isConnected();
		il.getBoolean(0);
		il.getDouble(0);
		il.getInt(0);
		
		System.out.println("After!");
		
		// Graceful shutdown
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	    		il.kill();
	        }
	    }, "Shutdown-thread"));
		


	}

}
