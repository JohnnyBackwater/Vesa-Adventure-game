package com.backwatersoftware.asd.entity.Obstacles;


import com.backwatersoftware.asd.graphics.Screen;
import com.backwatersoftware.asd.graphics.Sprite;
import com.backwatersoftware.physics.Point;

public class Tree extends Obstacle {
	
	public Tree(Point p){
		this.point = p;
		this.sprite = Sprite.tree;
		this.XSIZE = this.sprite.XSIZE;
		this.YSIZE = 16;
	}
	
	@Override
	public void render(Screen screen){
		screen.renderSprite(this.point(0,-16), this.sprite, false, false, true);
	}
	
}
