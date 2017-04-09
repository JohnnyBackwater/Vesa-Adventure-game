package com.backwatersoftware.asd.entity.projectile;

import java.awt.Color;

import com.backwatersoftware.asd.entity.Entity;
import com.backwatersoftware.asd.graphics.Screen;
import com.backwatersoftware.asd.graphics.Sprite;
import com.backwatersoftware.physics.Point;

public class Needle extends Projectile {

	public Needle(Point p1, double dir, Entity origin){
		super(p1,dir,origin);
		this.range = 1000;
		this.damage = 5;
		this.speed = 3.;
		this.XSIZE = 1;
		this.YSIZE = 1;
		this.time = 0;
		this.hostile = origin.hostile();
		this.point.setVector(dir, speed);
	}
	
	@Override
	public void render(Screen screen){
		screen.drawLine(this.point, 1, 10, this.dir, Color.WHITE, true);
	}
}
