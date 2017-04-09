package com.backwatersoftware.physics;

public class Vector {

	private double x, y, z;
	public Vector(double xa, double ya, double za) {
		this.x = xa;
		this.y = ya;
		this.z = za;
	}
	
	
	public void update(double xa, double ya, double za){
		this.x += xa;
		this.y += ya;
		this.z += za;
	}
	public double x(){
		return x;
	}
	public double y(){
		return y;
	}
	public double z(){
		return z;
	}


	public void update(Vector acc) {
		this.x += acc.x();
		this.y += acc.y();
		this.z += acc.z();		
	}
}
