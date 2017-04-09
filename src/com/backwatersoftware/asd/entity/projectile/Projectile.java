package com.backwatersoftware.asd.entity.projectile;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.backwatersoftware.asd.Animations.Explosion;
import com.backwatersoftware.asd.entity.Entity;
import com.backwatersoftware.asd.entity.mob.Mob;
import com.backwatersoftware.asd.entity.mob.Player;
import com.backwatersoftware.asd.entity.mob.hitPixel;
import com.backwatersoftware.asd.graphics.Screen;
import com.backwatersoftware.asd.graphics.Sprite;
import com.backwatersoftware.physics.Point;

public class Projectile extends Entity {

	public Entity origin;
	protected int XSIZE;
	protected int YSIZE;
	protected double dir;
	protected Sprite sprite;
	protected double range, damage;
	protected double speed;
	public List<hitPixel> hitPixels  = new ArrayList<hitPixel>();
	
	public Projectile(Point p, double dire, Entity origin2) {
		this.point = p;
		this.dir = dire;
		this.origin = origin2;		
	}

	@Override
	public void render(Screen screen) {
		screen.renderShadow(this.point, this.sprite, false, false, true);
		screen.renderSprite(this.point, this.sprite, false, false, true);
	}

	@Override
	public void update() {
		this.time++;
		this.point.update();
		collide();
	}

	public void collide() {
		worldCollision();
		damageOthers();
	}

	public void damageOthers() {
		if (!this.origin.isPlayer()) {
			for (int i = 0; i < this.level.players.size(); i++) {
				double dist = this.point.distance(this.level.mobs.get(i).point());
				if (dist < this.level.players.get(i).XSIZE / 2) {
					this.level.players.get(i).damage(this.damage);
					explode();
				}

			}
		}

		if(this.origin.isPlayer()){
			for(int i = 0, j = this.level.mobs.size(); i < j; i++){
				double dist = this.point.distance(this.level.mobs.get(i).point());
				if(this.level.mobs.get(i).hostile()){
					if (dist < this.level.mobs.get(i).XSIZE / 2) {
						this.level.mobs.get(i).damage(this.damage);
						if(this.level.mobs.get(i).dies()){
							this.origin.gainKill(this.level.mobs.get(i));
						}
						explode();
					}
				}
			}
		}
	}

	private void worldCollision() {
		for (int j = 0; j < this.hitPixels.size(); j++) {
			if (this.level.getTile(((int) this.hitPixels.get(j).x() + (int)this.point.x()) / 16, ((int) this.hitPixels.get(j).y()  + (int)this.point.y())/16).solid()) {
				explode();
				return;
			}
		}

	}

	private void explode() {
		this.level.add(new Explosion(this.point, this.XSIZE+this.YSIZE, this,0));
		remove();
	}

	protected void makeHitPixels() {
		for (int xh = 0; xh < this.XSIZE; xh++) {
			for (int yh = 0; yh < this.YSIZE; yh++) {
				if (this.sprite.pixels[xh + yh * this.XSIZE] != 0xffFF00FF) {
					hitPixel hp = new hitPixel(xh, yh);
					if (this.hitPixels.contains(hp.x())) {
						if (this.hitPixels.contains(hp.y())) {
							break;
						}
					}
					this.hitPixels.add(hp);
					break;
				}
			}
		}

		for (int xh = 0; xh < this.XSIZE; xh++) {
			for (int yh = this.YSIZE - 1; yh >= 0; yh--) {
				if (this.sprite.pixels[xh + yh * this.XSIZE] != 0xffFF00FF) {
					hitPixel hp = new hitPixel(xh, yh);
					if (this.hitPixels.contains(hp.x())) {
						if (this.hitPixels.contains(hp.y())) {
							break;
						}
					}
					this.hitPixels.add(hp);
					break;
				}
			}
		}

		for (int yh = 0; yh < this.YSIZE; yh++) {
			for (int xh = 0; xh < this.XSIZE; xh++) {
				if (this.sprite.pixels[xh + yh * this.XSIZE] != 0xffFF00FF) {
					hitPixel hp = new hitPixel(xh, yh);
					if (this.hitPixels.contains(hp.x())) {
						if (this.hitPixels.contains(hp.y())) {
							break;
						}
					}
					this.hitPixels.add(hp);
					break;
				}
			}
		}

		for (int yh = 0; yh < this.YSIZE; yh++) {
			for (int xh = this.XSIZE - 1; xh >= 0; xh--) {
				if (this.sprite.pixels[xh + yh * this.XSIZE] != 0xffFF00FF) {
					hitPixel hp = new hitPixel(xh, yh);
					if (this.hitPixels.contains(hp.x())) {
						if (this.hitPixels.contains(hp.y())) {
							break;
						}
					}
					this.hitPixels.add(hp);
					break;
				}
			}
		}

	}

	public Entity origin() {
		if (this.origin != null) {
			return this.origin;
		}
		return null;
	}

	public void setOrigin(Entity player) {
		this.origin = player;
		this.hostile = player.hostile();
	}

	@Override
	public boolean isProjectile() {
		return true;
	}
	
	@Override
	public Color getMainColor(){
		if(this.sprite == null){
			return Color.white;
		}
		return new Color(this.sprite.pixels[this.XSIZE/2 + this.sprite.YSIZE*this.XSIZE/2]);
	}
}
