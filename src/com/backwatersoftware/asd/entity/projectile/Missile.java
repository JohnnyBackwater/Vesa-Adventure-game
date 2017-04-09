package com.backwatersoftware.asd.entity.projectile;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


import com.backwatersoftware.asd.Animations.Explosion;
import com.backwatersoftware.asd.entity.Entity;
import com.backwatersoftware.asd.entity.mob.Mob;
import com.backwatersoftware.asd.entity.mob.hitPixel;
import com.backwatersoftware.asd.graphics.Screen;
import com.backwatersoftware.asd.graphics.Sprite;
import com.backwatersoftware.physics.Point;

public class Missile extends Entity {

	protected Mob target;
	public Mob origin;
	protected int XSIZE;
	protected int YSIZE;
	protected double angle;
	protected Sprite sprite;
	protected double speed;
	protected double range, damage;
	public List<hitPixel> hitPixels  = new ArrayList<hitPixel>();
	
	public Missile(Point p, double dir, Mob origin, Mob target) {
		
		this.angle = dir;
		this.origin = origin;
		this.target = target;
		this.point = p;
		this.speed = 1;
	}

	@Override
	public void render(Screen screen) {
		screen.renderSprite(point(), this.sprite, false, false, true);
	}

	@Override
	public void update() {
		this.time++;
		makeMoveVector();
		this.point.update();
		collide();
	}

	private void makeMoveVector() {
		this.point.setVector(this.target.point(), this.speed);
	}


	public void collide() {
		damageOthers();
	}

	public void damageOthers() {
		if (!this.origin.isPlayer()) {
			//TODO
		}

		if (this.origin.isPlayer()) {
			double dist = this.point.distance(this.target.point());
				if(dist < 16){
					this.target.damage(this.damage);
					explode();
				}
			
		}
	}

	
	public void explode() {
		
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
	
	@Override
	public Color getMainColor(){
		if(this.sprite == null){
			return Color.white;
		}
		return new Color(this.sprite.pixels[this.XSIZE/2 + this.sprite.YSIZE*this.XSIZE/2]);
	}
}
