package com.backwatersoftware.asd.entity.Drops;

import java.awt.Color;

import com.backwatersoftware.asd.Buffs.*;
import com.backwatersoftware.asd.entity.Entity;
import com.backwatersoftware.asd.graphics.Screen;
import com.backwatersoftware.physics.Point;

public class HealthDrop extends Drop {

	public int power;

	public HealthDrop(Point p) {
		super(p);
		this.power = 30;
	}

	@Override
	public void effect(Entity e) {
		this.level.newMessageFrom("30HP", this, Color.RED, 30);
		if (e.isPlayer()) {
			e.addHP(this.power);
		}
		if (e.isMob()) {
			return;
		}
	}

	@Override
	public void render(Screen screen) {
		if(this.time > 420 && this.time % 20 > 10){
			return;
		}
		
		for (double i = 0; i < Math.PI * 2; i += 0.1) {
			screen.drawLine(point, 1, 5, i, Color.red, true);
		}
		screen.drawLine(point, 1, 3, 0, Color.white, true);
		screen.drawLine(point, 1, 3, Math.PI, Color.white, true);
		screen.drawLine(point, 1, 3, Math.PI / 2, Color.white, true);
		screen.drawLine(point, 1, 3, 3 * (Math.PI) / 2, Color.white, true);
	}
}
