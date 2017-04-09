package com.backwatersoftware.asd.entity.Particles;

import java.awt.Color;

import com.backwatersoftware.asd.entity.Entity;
import com.backwatersoftware.physics.Point;

public class ParticleStream extends Entity {
	private Color col;
	private int intensity;
	private double direction, scatter;
	private int length;
	private double xforce, yforce;
	private boolean endless;
	public ParticleStream(Point p, Color col, int intensity, double dir, int length, double xforce, double yforce, double scatter){
				this.point = p;
				if(col != null){
					this.col = col;
				}
				
				this.intensity = intensity;
				this.direction = dir;
				this.length = length;
				this.xforce = xforce;
				this.yforce = yforce;
				this.scatter = scatter;
				this.endless = true;
		
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @param col
	 * @param intensity
	 * @param dir
	 * @param length
	 * @param xforce
	 * @param yforce
	 * @param scatter
	 * @param int lifetime
	 */
	public ParticleStream(Point p, Color col, int intensity, double dir, int length, double xforce, double yforce, double scatter, int lifetime){
		this.point = p;
		if(col != null){
			this.col = col;
		}
		
		this.intensity = intensity;
		this.direction = dir;
		this.length = length;
		this.xforce = xforce;
		this.yforce = yforce;
		this.scatter = scatter;
		this.lifetime = lifetime;
		this.time = 0;
		this.endless = false;
}
	
	

	public void update() {

		this.point.update();
		// (int x, int y, Color color, int maxLife, double acc, double xforce,
		// double yforce, double direction, double dirScatter)
		if(!this.endless){
			this.time++;
		}
		if(this.time > this.lifetime){
			remove();
		}
	
		if (this.col != null) {
			for (int i = 0; i < this.intensity; i++) {

				this.level.add(new Particle(this.point(), this.col, this.length, 1, this.xforce, this.yforce, this.direction, this.scatter));
			}
		} else {
			for (int i = 0; i < this.intensity; i++) {

				this.level.add(new Particle(this.point(), new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)),
						this.length, 1, this.xforce, this.yforce,
						this.direction, this.scatter));
			}

		}
	}
	
}
