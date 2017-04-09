package com.backwatersoftware.asd.entity.mob;

import com.backwatersoftware.asd.graphics.Sprite;
import com.backwatersoftware.physics.Point;

public class Hayman extends Minion {

	public Hayman(Point p){
		super(p, 0, 0, 0, 0, 0, Sprite.Hayman);
		this.name = "Hayman";
		this.hp = 500;
		this.maxHp = 500;
	}
}
