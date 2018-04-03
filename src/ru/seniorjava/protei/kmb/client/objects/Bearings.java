package ru.seniorjava.protei.kmb.client.objects;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Bearings implements Serializable {

	private String name;
	private String classification;
	private String material;
	private String balls;
	private String materialBalls;
	
	public Bearings(){}
	
	public Bearings(String name, String classification, String material, String balls, String materialBalls) {
		this.name = name;
		this.classification = classification;
		this.material = material;
		this.balls = balls;
		this.materialBalls = materialBalls;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	
	public String getBalls() {
		return balls;
	}
	public void setBalls(String balls) {
		this.balls = balls;
	}
	
	public String getMaterialBalls() {
		return materialBalls;
	}
	public void setMaterialBalls(String materialBalls) {
		this.materialBalls = materialBalls;
	}
	
}