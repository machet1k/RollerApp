package ru.seniorjava.protei.kmb.client.objects;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Boot implements Serializable {

	private String name;
	private String material;
	private String mounting;
	private String cuff;
	
	public Boot(){}
	
	public Boot(String name, String material, String mounting, String cuff) {
		this.name = name;
		this.material = material;
		this.mounting = mounting;
		this.cuff = cuff;
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
	
	public String getMounting() {
		return mounting;
	}
	public void setMounting(String mounting) {
		this.mounting = mounting;
	}
	
	public String getCuff() {
		return cuff;
	}
	public void setCuff(String cuff) {
		this.cuff = cuff;
	}
	
}
