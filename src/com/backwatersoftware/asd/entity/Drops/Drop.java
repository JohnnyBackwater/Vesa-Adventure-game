package com.backwatersoftware.asd.entity.Drops;

import java.awt.Color;

import com.backwatersoftware.asd.Animations.Explosion;
import com.backwatersoftware.asd.entity.Entity;
import com.backwatersoftware.asd.graphics.Sprite;
import com.backwatersoftware.physics.Point;

@SuppressWarnings("all")
public abstract class Drop extends Entity {

	protected Sprite sprite;
	protected int maxLife;
	protected boolean shatters;
	public Drop(Point p){
		this.point = p;
	}
	
	@Override
	public void update(){
		this.point.update();
		if(shatters){
			this.time++;
		}
		if(this.time > this.maxLife){
			this.level.add(new Explosion(point(),10,Color.RED, 0));
			remove();
		}
	}
	
	public void render(){
		
	}
	
	public void effect(Entity e){
		if(e.isPlayer()){
			
		}
		if(e.isMob()){
			
		}
	}
	
	@Override
	public boolean isDrop(){
		return true;
	}

}
