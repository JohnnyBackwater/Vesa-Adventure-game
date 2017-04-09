package com.backwatersoftware.asd.entity.Particles;

import java.awt.Color;

import com.backwatersoftware.asd.entity.Entity;
import com.backwatersoftware.asd.graphics.Screen;

public class ParticleSpawner extends Entity {

	
	protected Particle[] particles;
	protected boolean continous;
	protected Color particleColor;
	protected int plife;
	protected int maxLife;
	protected double particleDirection;
	protected double particleDirectionScatter;
	protected double xForce,yForce;
	protected int intensity;
	
	
	@Override
	public void render(Screen screen){
		for (int i = 0; i < this.particles.length; i++) {
			this.particles[i].update();
		}
		for (int i = 0; i < this.particles.length; i++) {
			this.particles[i].render(screen);
		}
		this.lifetime++;
		if(this.lifetime > this.maxLife){
			remove();
		}
	}
	
	public boolean isParticleSpawner(){
		return true;
	}
}
