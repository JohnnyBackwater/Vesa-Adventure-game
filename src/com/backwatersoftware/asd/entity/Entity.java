package com.backwatersoftware.asd.entity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.backwatersoftware.asd.Buffs.Buff;
import com.backwatersoftware.asd.Buffs.SpeedBoost;
import com.backwatersoftware.asd.GUI.infoBox;
import com.backwatersoftware.asd.entity.mob.Mob;
import com.backwatersoftware.asd.entity.mob.hitPixel;
import com.backwatersoftware.asd.entity.projectile.Projectiles;
import com.backwatersoftware.asd.graphics.Screen;
import com.backwatersoftware.asd.input.Mouse;
import com.backwatersoftware.asd.level.Level;
import com.backwatersoftware.physics.Point;

@SuppressWarnings("all")
public abstract class Entity {

	protected Point point;
	public static Projectiles projectiles = new Projectiles();
	protected boolean hostile;
	//protected double x;
	//protected double y;
	//protected double z;
	protected int lifetime;
	protected int time;
	public boolean removed = false;
	public Level level;
	public int XSIZE;
	public int YSIZE;
	protected final static Random random = new Random();
	@SuppressWarnings("unused")

	protected infoBox info;
	
	public void update() {
		//TODO
	}
	public void render(Screen screen) {
		//TODO
	}
	/**
	 * Remove from level
	 */
	public void remove() {
		this.removed = true;
	}
	public boolean isRemoved() {
		return this.removed;
	}
	public void init(Level level){
		this.level = level;
	}
	public boolean solid(){
		return false;
	}
	
	/*
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}*/
	
	public boolean isPlayer() {
		return false;
	}
	public boolean hostile(){
		return this.hostile;
	}
	public void pull(double force) {
				
	}
	public boolean isProjectile() {
		return false;
	}
	public boolean isMob() {
		return false;
	}
	public boolean isObstacle(){
		return false;
	}
	public boolean isExplosion() {
		return false;
	}
	public boolean isParticleSpawner() {
		return false;
	}
	public boolean isDrop() {
		return false;
	}
	public boolean isFloatMessage(){
		return false;
	}
	public Color getMainColor() {
		return new Color(0);
	}
	public void gainKill(Mob kill) {
		
	}
	public void addHP(int a) {
		
	}
	public int getXSize(){
		return this.XSIZE;
	}
	public int getYSize(){
		return this.YSIZE;
	}
	public void addBuff(Buff b) {		
	}
	public boolean equals(Object e){
		return false;
	}
	
	/**
	 * Returns new Point object from the original point. 
	 * Only x, y, z coordinates are coming with it
	 * @return
	 */
	public Point point(){
		Point p = new Point(this.point.x(), this.point.y(), this.point.z());
		return p;
	}
	
	/**
	 * Returns new Point object from the original point. 
	 * Only x, y, z coordinates are coming with it
	 * 
	 * @param x double: amount of x offset from the original point
	 * @param y double: amount of y offset from the original point
	 * @return Point
	 */
	public Point point(double x, double y){
		Point p = new Point(this.point.x() + x, this.point.y() + y, this.point.z());
		return p;
	}
	
	
	

}
