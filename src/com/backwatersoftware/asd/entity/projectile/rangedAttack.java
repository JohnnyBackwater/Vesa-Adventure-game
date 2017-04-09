package com.backwatersoftware.asd.entity.projectile;

import com.backwatersoftware.asd.entity.mob.Mob;
import com.backwatersoftware.asd.graphics.Sprite;
import com.backwatersoftware.physics.Point;

public class rangedAttack extends Missile {

	public rangedAttack(Point p, Mob origin, Mob target){
		super(p, 0, origin, target);
		this.damage = origin.damage;
		this.speed = 20;
		this.sprite = Sprite.Ganja;
	}
}
