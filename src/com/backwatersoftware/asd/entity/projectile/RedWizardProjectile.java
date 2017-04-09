package com.backwatersoftware.asd.entity.projectile;

import com.backwatersoftware.asd.entity.Entity;
import com.backwatersoftware.asd.entity.mob.Player;
import com.backwatersoftware.asd.graphics.Sprite;
import com.backwatersoftware.physics.Point;

public class RedWizardProjectile extends Projectile {


	public RedWizardProjectile(Point p, double dir, Entity origin){
		super(p,dir,origin);
		this.speed = 2.;
		if(origin.isPlayer()){
			this.speed += 4.;
		}
		this.range = 1000;
		this.damage = 5;
		if(origin.isPlayer()){
			Player pp = (Player)origin;
			this.damage = pp.damage*5;
		}
		
		this.point.setVector(dir, this.speed);
		this.sprite = Sprite.RedPlasmaBall;
		this.XSIZE = this.sprite.XSIZE;
		this.YSIZE = this.sprite.YSIZE;
		makeHitPixels();
		this.time = 0;
		this.hostile = origin.hostile();
	}
}
