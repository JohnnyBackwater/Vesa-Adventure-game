package com.backwatersoftware.asd.entity.projectile;

import java.awt.Color;

import com.backwatersoftware.asd.Animations.Explosion;
import com.backwatersoftware.asd.entity.Particles.ParticleSpawner;
import com.backwatersoftware.asd.entity.Particles.ParticleStream;
import com.backwatersoftware.asd.entity.mob.Mob;
import com.backwatersoftware.asd.graphics.Sprite;

public class boltFire extends Missile {

	double dir = 0;
	public boltFire(Mob user, Mob target, double damage){
		super(user.point(), user.point().direction(target.point()), user, target);
		this.damage = damage;
		this.speed = 5;
		this.sprite = Sprite.fireBall;
	}
	
	@Override
	public void update() {
		this.time++;
		makeMoveVector();
		collide();
		this.point.update();
		
	}

	@Override
	public void explode() {
		this.level.add(new Explosion(point(), 2, Color.RED, this.damage/3));
		this.level.add(new Explosion(point(), 3, Color.ORANGE, this.damage/3));
		this.level.add(new Explosion(point(), 4, Color.YELLOW, this.damage/3));
		remove();
	}
	
	private void makeMoveVector() {
		this.dir = this.point.direction(target.point());
		this.point.setVector(dir, speed);
	}
}
