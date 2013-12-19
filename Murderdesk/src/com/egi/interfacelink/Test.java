package com.egi.interfacelink;

public class Test {

	public static void main(String[] args) {
		System.out.println("Start");
		InterfaceLink il = new InterfaceLink("127.0.0.1",2222);
		il.start();

	}

}
