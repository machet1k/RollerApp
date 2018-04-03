package ru.seniorjava.protei.kmb.client.objects;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Frame implements Serializable {
	
	private String name;
	private String material;
	private String setup;
	private String length;
	private String mounting;
	
	public Frame(){}
	
	public Frame(String name, String material, String setup, String length, String mounting) {
		this.name = name;
		this.material = material;
		this.setup = setup;
		this.length = length;
		this.mounting = mounting;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	
	public String getSetup() {
		return setup;
	}
	public void setSetup(String setup) {
		this.setup = setup;
	}
	
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	
	public String getMounting() {
		return mounting;
	}
	public void setMounting(String mounting) {
		this.mounting = mounting;
	}
	
}
