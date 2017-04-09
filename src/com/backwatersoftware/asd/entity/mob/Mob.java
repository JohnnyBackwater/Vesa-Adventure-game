package com.backwatersoftware.asd.entity.mob;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.backwatersoftware.asd.Game;
import com.backwatersoftware.asd.Buffs.Buff;
import com.backwatersoftware.asd.Buffs.SpeedBoost;
import com.backwatersoftware.asd.Animations.Explosion;
import com.backwatersoftware.asd.Hitbox.Hitbox;
import com.backwatersoftware.asd.entity.Entity;
import com.backwatersoftware.asd.entity.Drops.Drop;
import com.backwatersoftware.asd.entity.Drops.HealthDrop;
import com.backwatersoftware.asd.entity.Obstacles.Obstacle;
import com.backwatersoftware.asd.entity.projectile.RedWizardProjectile;
import com.backwatersoftware.asd.entity.projectile.boltFire;
import com.backwatersoftware.asd.graphics.Screen;
import com.backwatersoftware.asd.graphics.Sprite;
import com.backwatersoftware.physics.Point;

public abstract class Mob extends Entity {

	protected Hitbox hitbox;
	protected int xOrigin;
	protected int yOrigin;
	protected double distance;
	protected double angle;
	protected double speed;
	protected Sprite sprite;
	protected Sprite attackSprite;
	protected int attackSpeed;
	protected int AttackCooldown;
	//public int ZSIZE = 16;
	protected boolean up;
	protected boolean down;
	protected boolean left;
	protected boolean right;
	protected int dir = 0;
	protected boolean moving = false;
	protected int shootTime;
	protected int attackRange;
	protected int agroRange;
	public double damage;
	public int hp, maxHp;
	public int mana, maxMana;
	public int kills;
	public String name;
	
	
	public Sprite[] movingAnimation;
	protected int MovingAnimation = 0;

	public ArrayList<Buff> buffs = new ArrayList<Buff>();
	public List<hitPixel> hitPixels  = new ArrayList<hitPixel>();
	
	
	private void setMoving() {
		
	 if(this.point.moving()){
		 this.moving = true;
	 }
	 else{
		 this.moving = false;
	 }
	 
	 //sets animation to right
	 if(this.movingAnimation == null)return;
	 if(this.time % (120/(int)this.speed) == 0){
			this.MovingAnimation++;
			if(this.MovingAnimation >= this.movingAnimation.length) this.MovingAnimation = 0;
		}
		
	}

	public void addBuff(Buff b) {
		for (int i = 0; i < this.buffs.size(); i++) {
			if(this.buffs.get(i).buffID() == b.buffID()){
				if(!b.stackable()){
					this.buffs.get(i).refresh();
					return;
				}
				this.buffs.add(b);
				return;
			}
		}
		this.buffs.add(b);
		
	}

	

	@Override
	public void update() {
		setMoving();
		updateBuffs();
		updateSkills();
		regenHP();
		regenMana();
		this.hitbox.update(this);
		if (this.hp <= 0) {
			destroy();
		}
		hitOtherMobs();
		this.point.update();
	}
	private void updateSkills() {
		//todo
	}

	protected void updateBuffs() {
		for (int i = 0; i < this.buffs.size(); i++) {
			if (this.buffs.get(i).removed()) {
				this.buffs.remove(i);
			}
		}
		for (int i = 0; i < this.buffs.size(); i++) {
			this.buffs.get(i).update(this);
		}

	}

	private void regenHP() {
		if (this.time % 160 == 0) {
			this.hp++;
		}

	}

	protected void regenMana() {
		if (this.mana <= this.maxMana && this.time % 60 == 0) {
			this.mana++;
		}

	}

	public void damage(double dmg) {
		this.hp -= dmg;
	}

	protected void hitOtherMobs() {
		if (isPlayer()) {
			return;
		}
		for (int i = 0; i < this.level.players.size(); i++) {
			if (!this.level.players.get(i).hostile() && !hostile()) {
				continue;
			}
			Player p = this.level.players.get(i);
			if (this.point.distance(p.point) < 16) {
				destroy();
				damage(p);
			}
		}

	}

	private void damage(Mob p) {
		p.hp -= (int) this.damage;
	}

	public void destroy() {
		if (random.nextInt(500) < 100) {
			this.level.add(new HealthDrop(this.point()));
		}
		explode();
		remove();

	}

	protected void explode() {
		this.level.add(new Explosion(this.point(), this.XSIZE + this.YSIZE, this,0));
		remove();
	}

	@Override
	public void render(Screen screen) {
		if (this.left) {
			screen.renderSprite(this.point, this.sprite, false, false, true);
		}
		if (this.right) {
			screen.renderSprite(this.point, this.sprite, true, false, true);
		}
		if (!this.right && !this.left) {
			screen.renderSprite(this.point, this.sprite, true, false, true);
		}
		renderHpBar(screen);
		renderManaBar(screen);
		this.info.render(screen);
	}

	public void renderHpBar(Screen screen) {
		double c = this.hp;
		double max = this.maxHp;
		for (int j = 0; j < 2; j++) {
			screen.drawGreenLine(new Point(this.point.x(), this.point.y() - j - 1,0), 1, (c / max) * 12, 0, true);
		}

	}

	@SuppressWarnings("unused")
	private void updateAttack() {
		// TODO
	}

	

	

	public void stop() {
		this.point.setVector(0,0,0);
	}

	public int getSize() {
		return (this.sprite.XSIZE + this.sprite.YSIZE) / 2;
	}
	public List<hitPixel> getHitpixels(){
		return this.hitPixels;
	}
	protected void makeHitPixels() {

		for (int i = 0; i <= this.XSIZE; i += 4) {
			this.hitPixels.add(new hitPixel(i, 0));
			this.hitPixels.add(new hitPixel(i, this.YSIZE));
		}
			
		for (int i = 0; i <= this.YSIZE; i += 4) {
			this.hitPixels.add(new hitPixel(0, i));
			this.hitPixels.add(new hitPixel(this.YSIZE, i));
		}
		
		

	
	}

	protected void alustaSuunnat() {
		this.right = false;
		this.left = false;
		this.down = false;
		this.up = false;
	}

	

	@Override
	public boolean isMob() {
		return true;
	}

	@Override
	public Color getMainColor() {
		return new Color(this.sprite.pixels[this.XSIZE / 2 + this.sprite.YSIZE * this.XSIZE / 2]);
	}

	public void renderManaBar(Screen screen) {
		for (int j = 0; j < 2; j++) {
			screen.drawGreenLine(new Point(this.point.x(), this.point.y() - j - 2,0), 1, this.mana, 0, true);
		}
	}

	public boolean dies() {
		if (this.hp <= 0) {
			return true;
		}
		return false;
	}


	@Override
	public void addHP(int a) {
		this.hp += a;
		if (this.hp > this.maxHp) {
			this.hp = this.maxHp;
		}
	}

	public int value() {
		int value = this.maxHp;
		value += this.agroRange;
		value += this.attackSpeed;
		value += this.speed;
		return value;
	}

	@Override
	public int getXSize() {
		return this.XSIZE;
	}

	@Override
	public int getYSize() {
		return this.YSIZE;
	}

	public double getSpeed() {
		return this.speed;
	}

	public void setSpeed(double i) {
		this.speed = i;
	}
	
	private void pickDrops() {
		for (int i = 0; i < this.level.drops.size(); i++) {
			if (this.point.distance(this.level.drops.get(i).point()) < 16) {
				this.level.drops.get(i).effect(this);
				this.level.drops.get(i).remove();
			}
		}

	}
	
	public void fireMissile(boltFire boltFire) {
		this.level.add(boltFire);
		
	}
	public String toString(){
		return this.name + " at ("+ this.point.x()+","+this.point.y()+")";
	}

	public void getDamage(double damage) {
		this.hp -= damage;
	}
}
