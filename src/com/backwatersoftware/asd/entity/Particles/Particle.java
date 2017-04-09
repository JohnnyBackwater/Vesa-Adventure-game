package com.backwatersoftware.asd.entity.Particles;

import java.awt.Color;

import com.backwatersoftware.asd.entity.Entity;
import com.backwatersoftware.asd.graphics.Screen;
import com.backwatersoftware.physics.Point;

public class Particle extends Entity {
	protected double velocity, zvelocity;
	protected Color color;
	private int maxLife;
	
		
	/**
	 * Particle with x or y acceleration
	 * @param x starting position x
	 * @param y starting position y
	 * @param color particle color
	 * @param maxLife amount of ticks the particle lives
	 * @param Acc negative values for negative acceleraiton
	 * @param xforce for x axel force
	 * @param yforce for y axel force
	 * @param direction travel direction in radians
	 * @param dirScatter values from 0..1, 0 = no scatter, 1 = full random
	 */
	public Particle(Point p, Color color, int maxLife ,double acc, double xforce, double yforce, double direction, double dirScatter) {
		this.maxLife = maxLife;
		this.removed = false;
		this.lifetime = 0;
		this.color = color;
		this.velocity = 0.2 + random.nextDouble();
		this.zvelocity = 0;
		this.point = p;
		double scattering = Math.PI * dirScatter;
		double dir = direction + this.random.nextDouble() * Math.PI * 2 * dirScatter - scattering;
		this.point.setVector(Math.cos(dir) * this.velocity, Math.sin(dir) * this.velocity, 0);
		this.point.addForce(xforce, yforce);
	}

	@Override
	public void update() {
		this.point.update();
		if (this.lifetime > this.maxLife) {
			remove();
		}
		this.lifetime++;
	}

	
	@Override
	public void render(Screen screen) {
	//	screen.drawPixel(this.x, this.y, Color.black);
		screen.drawPixel(this.point, this.color);
		
	}
}
