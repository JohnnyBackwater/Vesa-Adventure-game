package com.backwatersoftware.asd.Attacks;

import com.backwatersoftware.asd.entity.Entity;
import com.backwatersoftware.asd.entity.projectile.RedWizardProjectile;
import com.backwatersoftware.physics.Point;

public class Attacks {

	public static void NovaStrike(Point p, double dir, Entity entity) {
		for (double i = 0; i < Math.PI * 2; i += 0.3) {
			entity.level.add(new RedWizardProjectile(p, i+dir, entity));
		}
	}
}
