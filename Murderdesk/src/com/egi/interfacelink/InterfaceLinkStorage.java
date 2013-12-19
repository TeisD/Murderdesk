package com.egi.interfacelink;

public class InterfaceLinkStorage {
	
	private double value;
	
	public InterfaceLinkStorage(){
	}
	
	public void store(double v){
		this.value = v;
	}
	
	public boolean getAsBoolean(){
		if(value >= 1){
			return true;
		}
		return false;
	}
	
	public double getAsDouble(){
		return value;
	}
	
	public int getAsInteger(){
		return (int)value;
	}
	
}
