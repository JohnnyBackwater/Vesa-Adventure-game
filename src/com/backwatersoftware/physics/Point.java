package com.backwatersoftware.physics;

import java.util.ArrayList;

/**
 * Physical point object for moving and stationary objects.
 * @author Jukka
 *
 */
public class Point {
	private int tickrate;
	private Vector acc = new Vector(0,0,0);
	private Vector v = new Vector(0,0,0);
	private double x;
	private double y;
	private double z;
	
	public Point(double xx, double yy, double zz){
		this.x = xx;
		this.y = yy;
		this.z = zz;
	}
	
	public Point(Point p, int xx, int yy) {
		this.x = p.x + xx;
		this.y = p.y + yy;
	}

	public double x(){
		return this.x;
	}
	public double y(){
		return this.y;
	}
	public double z(){
		return this.z;
	}
	
	/**
	 * Sets the acceleration to given xyz
	 */
	public void setAcceleration(double xa, double ya, double za){
		this.acc = new Vector(xa, ya, za);
	}
	/**
	 * Sets the vector to given xyz values. Using this is not encouraged, prefer acceleration and forces
	 */
	public void setVector(double xa, double ya, double za){
		this.v = new Vector(xa, ya, za);
	}
	
	
	public void update(){
		this.v.update(this.acc);
		this.x += this.v.x();
		this.y += this.v.y();
		this.z += this.v.z();
	}
	
	/**
	 * Calculates the distance between this point and other point object
	 * Only on (x,y) space for now...
	 * @param Point p
	 * @return Distance to given point
	 */
	public double distance(Point p){
		double dist = Math.pow(Math.pow(this.x - p.x(), 2) + Math.pow(this.y - p.y(), 2), 0.5);
		return dist;
	}

	/**
	 * Sets velocity vector pointing at given point with given speed
	 * @param p
	 * @param speed
	 */
	public void setVector(Point p, double speed) {
		double dir = Math.atan2(p.y - this.y, p.x - this.x);
		setVector(Math.cos(dir)*speed, Math.sin(dir)*speed, 0);
	}
	
	/**
	 * Sets velocity vector to given direction with given speed
	 * @param dir
	 * @param speed
	 */
	public void setVector(double dir, double speed) {
		setVector(Math.cos(dir)*speed, Math.sin(dir)*speed, 0);
	}
	
	
/**
 * Temporary for use with backwater software VEngine screen offset
 * @param xOffset
 * @param yOffset
 */
	public void setOffset(int xOffset, int yOffset) {
		this.x -= xOffset;
		this.y -= yOffset;		
	}

public double direction(Point p2) {
	double dir = Math.atan2(p2.y() - this.y, p2.x() - this.x);
	return dir;
}

public boolean moving() {
	if(new Point(this.v.x(), this.v.y(), this.v.z()).distance(new Point(0,0,0)) < 0.1){
		return true;
	}
	return false;
}

public void addForce(double xforce, double yforce) {
	this.acc = new Vector(xforce,yforce,0);
	
}


}

