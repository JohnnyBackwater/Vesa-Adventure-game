package com.backwatersoftware.physics;

public class Gravity {

	public double force;
	
	public Gravity(double force){
		this.force = force;
	}
	
	public double addPull(){
		return this.force;
	}
}
