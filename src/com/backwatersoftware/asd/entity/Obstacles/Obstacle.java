package com.backwatersoftware.asd.entity.Obstacles;

import com.backwatersoftware.asd.Hitbox.Hitbox;
import com.backwatersoftware.asd.entity.Entity;
import com.backwatersoftware.asd.graphics.Screen;
import com.backwatersoftware.asd.graphics.Sprite;

@SuppressWarnings("all")
public abstract class Obstacle extends Entity {
	
	protected Sprite sprite;
		
	@Override
	public void render(Screen screen){
		screen.renderSprite(this.point, this.sprite, false, false, true);
	}
	
	
	
	@Override
	public boolean isObstacle(){
		return true;
	}

}
