package com.backwatersoftware.asd.entity.mob;
import com.backwatersoftware.asd.Hitbox.Hitbox;
import com.backwatersoftware.asd.entity.Entity;
import com.backwatersoftware.asd.entity.projectile.Needle;
import com.backwatersoftware.asd.graphics.Sprite;
import com.backwatersoftware.physics.Point;

public class Ganja extends Minion {

	@SuppressWarnings("static-access")
	public Ganja(Point p) {
		super(p, 1, 1, 200, 150, 1, Sprite.Ganja);
		this.maxHp = 10;
		this.hp = this.maxHp;
		this.hitbox = new Hitbox(p,p.x()+this.XSIZE,p.y()+this.YSIZE);
		this.name = "Ganja";
	}
	
	
}
