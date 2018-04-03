package ru.seniorjava.protei.kmb.client.objects;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RollerSkates implements Serializable {

	private String name;
	private Frame frame;
	private Wheels wheels;
	private Bearings bearings;
	private Boot boot;
	
	public RollerSkates(){}
	
	public RollerSkates(String name, Frame frame, Wheels wheels, Bearings bearings, Boot boot) {
		this.name = name;
		this.frame = frame;
		this.wheels = wheels;
		this.bearings = bearings;
		this.boot = boot;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Frame getFrame() {
		return frame;
	}
	public void setFrame(Frame frame) {
		this.frame = frame;
	}
	
	public Wheels getWheels() {
		return wheels;
	}
	public void setWheels(Wheels wheels) {
		this.wheels = wheels;
	}
	
	public Bearings getBearings() {
		return bearings;
	}
	public void setBearings(Bearings bearings) {
		this.bearings = bearings;
	}
	
	public Boot getBoot() {
		return boot;
	}
	public void setBoot(Boot boot) {
		this.boot = boot;
	}

}
