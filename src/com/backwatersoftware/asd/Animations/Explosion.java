package com.backwatersoftware.asd.Animations;

import java.awt.Color;

import com.backwatersoftware.asd.entity.Entity;
import com.backwatersoftware.asd.entity.Particles.ParticleExplosion;
import com.backwatersoftware.asd.entity.mob.Mob;
import com.backwatersoftware.asd.entity.mob.Player;
import com.backwatersoftware.physics.Point;

public class Explosion extends Entity {

	protected double radius;
	protected Entity origin;
	private Color color;
	private double damage;

	/**
	 * 
	 * @param x
	 *            position
	 * @param y
	 *            position
	 * @param size
	 * @param origin 
	 * @param hostile
	 */
	public Explosion(Point p, int size, Entity origin, double damage) {		
		this.origin = origin;
		this.radius = size;
		this.point = p;
		this.hostile = origin.hostile();
		this.lifetime = 0;
		this.damage = damage;
	}
	
	public Explosion(Point p, int size, Color col, double damage) {
		this.radius = size*3;
		this.point = p;
		this.lifetime = 0;
		this.color = col;
		this.damage = damage;
	}

	@Override
	public void update() {
		if(this.lifetime == this.radius/2){
			damageOthers();
		}
		if(this.lifetime == 0 && this.origin != null){
			this.level.add(new ParticleExplosion(this.point, this.origin.getMainColor(), (int)this.radius));
		}
		
		if(this.origin == null){
			this.level.add(new ParticleExplosion(this.point, this.color, (int)this.radius));
		}
		
		if(this.lifetime > (int)this.radius){
			remove();
		}
		this.lifetime++;
		
	}



	private void damageOthers() {
		if(this.hostile){
			for (int i = 0; i < this.level.players.size(); i++) {
				if(this.point.distance(this.level.players.get(i).point()) < this.radius){
					this.level.players.get(i).getDamage(this.damage);
				}
			}
		}
		if(!this.hostile){
			for (int i = 0; i < this.level.mobs.size(); i++) {
				if(this.point.distance(this.level.mobs.get(i).point()) < this.radius){
					this.level.mobs.get(i).getDamage(this.damage);
				}
			}
		}
		
	}

	

	@Override
	public boolean isExplosion() {
		return true;
	}

}
