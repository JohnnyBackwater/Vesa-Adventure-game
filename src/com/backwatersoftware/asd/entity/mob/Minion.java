package com.backwatersoftware.asd.entity.mob;

import com.backwatersoftware.asd.GUI.infoBox;
import com.backwatersoftware.asd.Hitbox.Hitbox;
import com.backwatersoftware.asd.entity.Entity;
import com.backwatersoftware.asd.entity.projectile.Needle;
import com.backwatersoftware.asd.graphics.Sprite;
import com.backwatersoftware.physics.Point;

public class Minion extends Mob {

	private int animation = 0;

	/**
	 * 
	 * @param x
	 * @param y
	 * @param speed
	 * @param attackspeed
	 * @param range ranged attack
	 * @param agro get closer
	 * @param dmg
	 * @param sprite
	 */
	public Minion(Point p, double speed, int attackspeed, int range, int agro, double dmg, Sprite sprite) {
		this.sprite = sprite;
		this.attackRange = range;
		this.agroRange = agro;
		this.hostile = true;
		this.speed = speed;
		this.point = p;
		this.attackSpeed = attackspeed;
		this.damage = dmg;
		this.hitbox = new Hitbox(p, p.x()+this.XSIZE, p.y()+this.YSIZE);
		this.name = "Minion";
		this.info = new infoBox(p,name);
		this.attackSprite = this.sprite;
		this.XSIZE = this.sprite.XSIZE;
		this.YSIZE = this.sprite.YSIZE;
		makeHitPixels();

	}

	@Override
	public void update() {
		this.point.update();
		if(this.hp <= 0){
			destroy();
		}
		this.time++;
		if (this.animation > 1000) {
			this.animation = 0;

		}
		this.animation++;
		hitOtherMobs();
		try {
			updateAttack();
		} catch (NullPointerException e) {
			//TODO
		}
		
		// update all these to same target
		Player nearest = (Player)this.level.getNearestPlayer(this);
		if(nearest != null){
			stop();
		if (this.point.distance(nearest.point()) < this.agroRange) {
			this.point.setVector(nearest.point(), this.speed);
		}
		if (this.point.distance(nearest.point()) >= this.agroRange) {
			stop();
		}
		if (this.point.distance(nearest.point()) < this.agroRange/2) {
			if(this.point.distance(this.level.getNearestPlayer(this).point()) < this.attackRange - 20){
				stop();
			}
		}
		}else{
			stop();
		}
	}


	/**
	 * Sets projectile go from point 1 to point 2 direction
	 * @param p1
	 * @param p2
	 * @param entity
	 */
	protected void shoot(Point p1, Point p2, Entity entity) {
		//TODO: individual attacks
		double dir = p1.direction(p2);
		this.level.add(new Needle(p1, dir, entity));
	}

	protected void updateAttack() throws NullPointerException {
		if (this.time % (100 - this.attackSpeed) != 0) {
			return;
		}
		Mob target = this.level.getNearestPlayer(this);
		if(target != null){
			if (this.point.distance(target.point()) < this.attackRange) {
				shoot(this.point(), target.point(), this);
			}
		}
	}

}
