package com.backwatersoftware.asd.entity.mob;

import com.backwatersoftware.asd.graphics.Sprite;
import com.backwatersoftware.physics.Point;

public class RandomMinion extends Minion {

	@SuppressWarnings("static-access")
	public RandomMinion(Point p, int seed){
		super(p, random.nextDouble()*seed, 1+random.nextInt(2*seed), seed*random.nextInt(10), 300, 7 + random.nextDouble()*seed*2, Sprite.randomMinionSprite());
		this.hp = 20 + random.nextInt(seed*5);
		this.maxHp = 20 + random.nextInt(seed*5);

	}
}
