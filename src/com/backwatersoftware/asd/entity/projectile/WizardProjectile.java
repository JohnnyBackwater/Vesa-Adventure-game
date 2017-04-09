package com.backwatersoftware.asd.entity.projectile;

import com.backwatersoftware.asd.entity.Entity;
import com.backwatersoftware.asd.graphics.Screen;
import com.backwatersoftware.asd.graphics.Sprite;
import com.backwatersoftware.physics.Point;

public class WizardProjectile extends Projectile {
	public WizardProjectile(Point p, double dir, Entity origin, double dmg) {
		super(p, dir, origin);
		this.speed = 1.;
		if(origin.isPlayer()){
			this.speed += 4.;
		}
		this.range = 400;
		this.damage = dmg;
		this.sprite = Sprite.BluePlasmaBall;
		this.XSIZE = this.sprite.XSIZE;
		this.YSIZE = this.sprite.YSIZE;
		makeHitPixels();
		this.time = 0;
		this.hostile = origin.hostile();
		this.point.setVector(dir, speed);
		
	}

}