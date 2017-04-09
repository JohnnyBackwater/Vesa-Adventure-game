package com.backwatersoftware.asd.entity.Drops;

import java.awt.Color;

import com.backwatersoftware.asd.entity.Entity;
import com.backwatersoftware.asd.entity.mob.Player;
import com.backwatersoftware.asd.graphics.Screen;
import com.backwatersoftware.asd.graphics.Sprite;
import com.backwatersoftware.physics.Point;

public class LevelUp extends Drop {

	
	public LevelUp(Point p) {
		super(p);
		this.sprite = Sprite.xpBook;
	}

	@Override
	public void effect(Entity e) {
		
		if (e.isPlayer()) {
			Player t = (Player)e;
			t.levelUP();
			remove();
		}
		if (e.isMob()) {
			return;
		}
	}

	@Override
	public void render(Screen screen) {
			screen.renderSprite(this.point, sprite, false, false, true);	
	}
}

