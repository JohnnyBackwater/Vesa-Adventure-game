package com.backwatersoftware.asd.entity.Particles;

import java.awt.Color;

import com.backwatersoftware.physics.Point;

public class ParticleExplosion extends ParticleSpawner {

	public ParticleExplosion(Point p, Color color, int size) {
		this.time = 0;
		this.point = p;
		this.particleColor = color;
		this.particleDirection = 0;
		this.particleDirectionScatter = Math.PI * 2;
		this.maxLife = size*3;
		this.intensity = size * 3;
		this.particles = new Particle[this.intensity];
		for (int i = 0; i < this.particles.length; i++) {
			this.particles[i] = new Particle(new Point(p.x(),p.y(),0), color, this.lifetime, 0.96, 0, 0, this.particleDirection, this.particleDirectionScatter);
		}
	}
}
