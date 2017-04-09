package com.backwatersoftware.asd.entity.mob;

import java.awt.Color;

import com.backwatersoftware.asd.Hitbox.Hitbox;
import com.backwatersoftware.asd.entity.Entity;
import com.backwatersoftware.asd.entity.projectile.RedWizardProjectile;
import com.backwatersoftware.asd.graphics.Screen;
import com.backwatersoftware.asd.graphics.Sprite;
import com.backwatersoftware.asd.level.NoPlayersException;
import com.backwatersoftware.physics.Point;

public class Morottaja extends Mob {
	private int charge;

	public Morottaja(Point p) {
		this.point = p;
		this.sprite = Sprite.morottaja;
		this.attackSprite = Sprite.morottajaAttack;
		this.speed = 10;
		this.time = 0;
		this.charge = 0;
		this.hostile = true;
		this.maxHp = 2500;
		this.hp = 2500;
		this.damage = 1;
		this.XSIZE = this.sprite.XSIZE;
		this.YSIZE = this.sprite.YSIZE;
		this.hitbox = new Hitbox(this.point,this.XSIZE,this.YSIZE);
	}

	@Override
	public void update() {
		this.point.update();
		Player nearest = this.level.getNearestPlayer(this.point);
		if (this.point.distance(nearest.point()) > 200) {
			this.point.setVector(nearest.point(), speed);
		}
		
		if (this.hp <= 0) {
			destroy();
		}
		if (this.time > 80 * 6 && this.time % 30 == 0) {
			this.level.add(RandomMinion());
		}
		this.time++;
		if (this.time > 80 * 8) {
			this.charge = 0;
			this.time = 0;
		}
		if (this.time % 80 == 0) {
			this.point.setVector(Math.PI, speed);
		}
		if (this.time % 160 == 0) {
			this.point.setVector(Math.PI/4, speed);
		}
		if (this.time > 80 * 4) {
			this.charge++;
		}
		if (this.charge > 120 && this.time % 5 == 0 && this.time < 80 * 6) {
			double dire = this.point.direction(nearest.point());
			shoot(this.point(), dire, this);
			
		}
		if(this.time == 80 * 8 -1){
			NovaStrike(this.point(), 0, this);
		}
		
		hitOtherMobs();
	}

	

	private Entity RandomMinion() {
		Entity e;
		if(random.nextInt(100) < 70){
			try {
				e = new RandomMinion(this.point(-50,20), this.level.getHighestLevelPlayer().level());
			} catch (NoPlayersException e1) {
				e = new RandomMinion(this.point(-50,20), 1);
			}
		}
		else{
			e = new Ganja(this.point(-50,20));
		}
		return e;
	}

	@Override
	protected void hitOtherMobs() {
		if (isPlayer()) {
			return;
		}
		for (int i = 0; i < this.level.players.size(); i++) {
			if (!this.level.players.get(i).hostile() && !hostile()) {
				continue;
			}
			Player p = this.level.players.get(i);
			if (this.point.distance(p.point()) < 32) {
				p.damage(this.damage);
			}
		}

	}

	
	protected void shoot(Point p, double dirr, Entity entity) {
		
		this.level.add(new RedWizardProjectile(this.point(8,20), dirr, entity));
		this.level.add(new RedWizardProjectile(this.point(8,20), dirr + .22, entity));
		this.level.add(new RedWizardProjectile(this.point(8,20), dirr - .22, entity));
		this.level.add(new RedWizardProjectile(this.point(8,20), dirr - .42, entity));
		this.level.add(new RedWizardProjectile(this.point(8,20), dirr + .42, entity));
		this.level.add(new RedWizardProjectile(this.point(8,20), dirr + .62, entity));
		this.level.add(new RedWizardProjectile(this.point(8,20), dirr - .62, entity));
		this.level.add(new RedWizardProjectile(this.point(8,20), dirr + .70, entity));
		this.level.add(new RedWizardProjectile(this.point(8,20), dirr - .70, entity));
	}
	
	@Override
	public void render(Screen screen) {
		Player nearest = this.level.getNearestPlayer(this.point);
		if(nearest == null){
			screen.renderSprite(this.point(), this.sprite, true, false, true);
		}
		if (nearest.point().x() < this.point.x()) {
			screen.renderSprite(this.point(), this.sprite, true, false, true);
		}
		if (nearest.point().x() > this.point.x()) {
			screen.renderSprite(this.point(), this.sprite, false, false, true);
		}
		if (this.charge > 0) {
			if (nearest.point().x() < this.point().x()) {
				screen.renderSprite(this.point(), this.attackSprite, true, false, true);
			}
			if (nearest.point().x() > this.point().x()) {
				screen.renderSprite(this.point(), this.attackSprite, false, false, true);
			}
		}
		renderHpBar(screen);
	}

	@Override
	public void renderHpBar(Screen screen) {

		for (int j = 0; j < 5; j++) {
			screen.drawLine(new Point(point.x(), point.y(), point.z()), 1, this.hp / 25, 0, Color.GREEN, true);
		}

	}
	protected void NovaStrike(Point p, double dirr, Entity entity) {
		for (double i = 0; i < Math.PI * 2; i += 0.3) {
			
			this.level.add(new RedWizardProjectile(this.point(8, 20), i + dirr, entity));
		}

	}
}
