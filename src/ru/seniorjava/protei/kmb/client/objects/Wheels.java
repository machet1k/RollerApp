package ru.seniorjava.protei.kmb.client.objects;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Wheels implements Serializable {

	private String name;
	private String size;
	private String hardness;

	public Wheels(){}
	
	public Wheels(String name, String size, String hardness) {
		this.name = name;
		this.size = size;
		this.hardness = hardness;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
	public String getHardness() {
		return hardness;
	}
	public void setHardness(String hardness) {
		this.hardness = hardness;
	}
	
}
