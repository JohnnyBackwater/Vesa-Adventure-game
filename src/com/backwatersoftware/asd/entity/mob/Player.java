package com.backwatersoftware.asd.entity.mob;

import java.awt.Color;

import com.backwatersoftware.asd.Game;
import com.backwatersoftware.asd.Announces.FloatMessage;
import com.backwatersoftware.asd.Buffs.NovaSpam;
import com.backwatersoftware.asd.Hitbox.Hitbox;
import com.backwatersoftware.asd.Skills.ActiveSkill;
import com.backwatersoftware.asd.Skills.Firebolt;
import com.backwatersoftware.asd.Skills.Skill;
import com.backwatersoftware.asd.entity.Entity;
import com.backwatersoftware.asd.entity.Drops.Drop;
import com.backwatersoftware.asd.entity.projectile.WizardProjectile;
import com.backwatersoftware.asd.entity.projectile.rangedAttack;
import com.backwatersoftware.asd.graphics.Screen;
import com.backwatersoftware.asd.graphics.Sprite;
import com.backwatersoftware.asd.input.Keyboard;
import com.backwatersoftware.asd.input.Mouse;
import com.backwatersoftware.physics.Point;

public class Player extends Mob {

	private int deaths = 0;
	private Keyboard input;
	public int clickTime;
	private String name;
	private int ultimateCooldown = 0;
	private int XP, maxXP, LEVEL;
	private int strength;
	private Skill[] skills = new Skill[4];

	public Player(Keyboard input) {
		this.sprite = Sprite.player_right;
		this.input = input;
	}

	public Player(Point p, Keyboard input, String name) {

		this.point = p;
		this.damage = 30;
		this.strength = 10;
		this.name = name;
		this.sprite = Sprite.players[0];
		this.movingAnimation = Sprite.playerMovings[0];
		this.XSIZE = this.sprite.XSIZE;
		this.YSIZE = this.sprite.YSIZE;
		this.input = input;
		this.hp = 100;
		this.maxHp = 100;
		this.attackSpeed = 60;
		this.speed = 18.;
		this.clickTime = 0;
		this.hostile = false;
		makeHitPixels();
		this.maxMana = 120;
		this.mana = this.maxMana;
		this.XP = 0;
		this.maxXP = 500;
		this.LEVEL = 1;
		this.hitbox = new Hitbox(this.point,this.XSIZE, this.YSIZE);
		this.skills[0] = new Firebolt(1);
		this.skills[1] = new Firebolt(2);
		this.skills[2] = new Firebolt(3);
		this.skills[3] = new Firebolt(4);

	}

	/*
	 * @Override public void renderHpBar(Screen screen) { double c = this.hp;
	 * double max = this.maxHp; for (int j = 0; j < 7; j++) {
	 * screen.drawLine(80, screen.height -(12 + j), 1, (c / max) * 100, 0,
	 * Color.RED, false); } screen.drawRectangle(80, screen.height - 18, 100, 6,
	 * Color.RED, 1 , false); screen.addText(80, screen.height -18, "HP " +
	 * this.hp + "/" + this.maxHp, false);
	 *
	 * }
	 *
	 * @Override public void renderManaBar(Screen screen) { for (int j = 0; j <
	 * 7; j++) { double c = this.mana; double max = this.maxMana;
	 * screen.drawLine(200, screen.height -(12 + j), 1, (c / max) * 100, 0,
	 * Color.CYAN, false); } screen.drawRectangle(200, screen.height -18, 100,
	 * 6, Color.CYAN, 1 , false); screen.addText(200, screen.height -18,
	 * "Mana: " + this.mana+"/"+this.maxMana, false);
	 *
	 *
	 * }
	 *
	 * public void renderXpBar(Screen screen) { for (int j = 0; j < 7; j++) {
	 * double c = this.XP; double max = this.maxXP; screen.drawLine(80,
	 * screen.height -(5 + j), 1, (c / max) * 200, 0, Color.GREEN, false); }
	 * screen.drawRectangle(80, screen.height -11, 200, 6, Color.GREEN, 1 ,
	 * false); screen.addText(280,screen.height, "XP: "+ this.XP
	 * +"/"+this.maxXP, false);
	 *
	 *
	 * }
	 */
	@Override
	public void render(Screen screen) {
		/*
		 * if (this.left) { screen.renderShadow((int) this.x, (int) (this.y),
		 * this.sprite, false, false, true); } if (this.right) {
		 * screen.renderShadow((int) this.x, (int) (this.y), this.sprite, true,
		 * false, true); }
		 */

		screen.addText(new Point(16,16,0), "Name: " + this.name + " Level:" + this.LEVEL + " HP:" + this.hp + " strength:" + this.strength + " DMG:" + this.damage, false);
		for (int i = 0; i < this.skills.length; i++) {
			if(this.skills[i].activated()){
				screen.drawCircle(this.point, this.skills[i].castRange(), Color.CYAN.getRGB());
			}				
			screen.addText(new Point(16,30 + 7*i,0), (i + 1) + ": " + this.skills[i].name + " level:" + this.skills[i].level + " " + this.skills[i].activated + " " + this.skills[i].coolDown(), false);
		}
		if (this.left) {
			if (this.moving) {
				screen.renderSprite(this.point(), this.movingAnimation[this.MovingAnimation], true, false, true);
			} else {
				screen.renderSprite(point(), this.sprite, true, false, true);
			}
		}
		if (this.right) {
			if (this.moving) {
				screen.renderSprite(point(), this.movingAnimation[this.MovingAnimation], false, false, true);
			} else {
				screen.renderSprite(point(), this.sprite, false, false, true);
			}
		}

		if (!this.right && !this.left) {
			screen.renderSprite(point(), this.sprite, true, false, true);
		}

	}

	@Override
	public void update() {
		if (this.hp <= 0) {
			explode();
		}
		this.point.update();
		updateBuffs();
		updateAttack();
		updateSkills();
		activateSkills();
		useSkills();
		updateLevels();
		regenHP();
		updateCooldowns();
		this.time++;
		if (this.time > 600) {
			this.time = 0;
		}
		this.hitbox.update(this);
		regenMana();
		// alustaSuunnat();
		double ax = 0;
		double ay = 0;

		if (this.input.down) {
			ay += 1;
		}
		if (this.input.up) {
			ay += -1;
		}
		if (this.input.right) {
			ax += 1;
			this.right = true;
			this.left = false;
		}
		if (this.input.left) {
			ax += -1;
			this.left = true;
			this.right = false;
		}

		pickDrops();
		this.point.setVector(ax,ay,0);
		if (ay == 0 && ax == 0) {

			stop();
		}


	}

	private void updateSkills() {
		for (int i = 0; i < this.skills.length; i++) {
			this.skills[i].update();
		}
	}

	private void useSkills() {
		for (int i = 0; i < this.skills.length; i++) {
			if (this.skills[i].activated()) {
				if (Mouse.getB() == 3) {
					this.skills[i].deactivate();
				}
				int success = 0;
				if (Mouse.getB() == 1) {
					if (this.skills[i].isActiveSkill()) {
						success = ((ActiveSkill)this.skills[i]).use(this, this.level.getMob(CursorLevelXpos(), CursorLevelYpos()));
						this.AttackCooldown = 120 - this.attackSpeed;
					}
					switch (success){
					case 1:{
						addFailMessage("No target!");
						break;
					}
					case 2:{
						addFailMessage("Cooldown!");
						break;
					}
					case 3:{
						addFailMessage("No mana!");
						break;
					}
					case 4:{
						addFailMessage("Not at range!");
						break;
					}
					default:{
						break;
					}
					}
				}
			}
		}
	}

	private void addFailMessage(String string) {
		this.level.addUnique(new FloatMessage(new Point(CursorLevelXpos(), CursorLevelYpos(),0), string, 30, Color.PINK));
	}
	private void addFailMessage(String string, Player player) {
		this.level.addUnique(new FloatMessage(point(), string, 30, Color.PINK));

	}

	private int CursorLevelXpos() {

		return Mouse.getXScaled() + this.level.xScroll();
	}

	private int CursorLevelYpos() {

		return Mouse.getYScaled() + this.level.yScroll();
	}

	private void activateSkills() {
		for (int i = 1; i < this.skills.length + 1; i++) {
			if (this.input.num[i]) {
				boolean activate = true;
				for (int k = 0; k < this.skills.length - 1; k++) {
					if (this.skills[(i + k) % this.skills.length].activated()) {
						activate = false;
						break;
					}
				}
				if (activate) {
					if(this.skills[i - 1].isActiveSkill()){
						if (this.mana < ((ActiveSkill)this.skills[i - 1]).manaCost()) {
							addFailMessage("No mana!");
							return;
						}
						if (!((ActiveSkill)this.skills[i - 1]).ready()) {
							addFailMessage("Cooldown!");
							return;
						}
					}
					this.skills[i - 1].activate();
				}
			}
		}

	}

	private void updateLevels() {
		if (this.XP >= this.maxXP) {
			levelUP();
		}

	}

	private void pickDrops() {
		for (int i = 0; i < this.level.drops.size(); i++) {
			if (point.distance(this.level.drops.get(i).point()) < 16) {
				this.level.drops.get(i).effect(this);
				this.level.drops.get(i).remove();
			}
		}

	}

	

	private void regenHP() {
		if (this.time % (120 - this.LEVEL) == 0 && this.hp < this.maxHp) {
			this.hp++;
		}
	}

	private void updateCooldowns() {
		if (this.time % 60 == 0 && this.ultimateCooldown != 0) {
			this.ultimateCooldown--;
		}
		if (this.AttackCooldown > 0) {
			this.AttackCooldown--;
		}

	}

	private void updateAttack() {
		if (skillIsActivated()) {
			return;
		}
		if (Mouse.getB() == 1 && this.AttackCooldown == 0) {
			this.AttackCooldown = 120 - this.attackSpeed;
			double dx = (Mouse.getXScaled() + this.level.xScroll()) - this.point.x() - 8;
			double dy = (Mouse.getYScaled() + this.level.yScroll()) - this.point.y() - 8;
			double dir = Math.atan2(dy, dx);
			this.shoot(dir, 0, 0);
		}
		if (Mouse.getB() == 3) {
			int xt = Mouse.getXScaled() + this.level.xScroll();
			int yt = Mouse.getYScaled() + this.level.yScroll();
			Mob target = this.level.getMob(xt, yt);
			if (target != null) {
				this.level.add(new rangedAttack(point(), this, target));
			}

		}
	}

	private boolean skillIsActivated() {
		for (int i = 0; i < this.skills.length; i++) {
			if (this.skills[i].activated()) {
				return true;
			}
		}
		return false;
	}

	private int shootTime() {
		return this.shootTime++;
	}

	protected void shoot(double dirr, double pvx, double pvy) {
		this.level.add(new WizardProjectile(point(), dirr, this, this.damage));
	}

	public String name() {
		return this.name;
	}

	@Override
	public boolean isPlayer() {
		return true;
	}

	@Override
	public void remove() {
		if (this.removed) {
			return;
		}
		this.deaths++;
		this.removed = true;
	}

	public int deaths() {
		return this.deaths;
	}

	public void respawn(int i, int j) {
		this.removed = false;
		this.point = new Point(i,j,0);

	}

	public void getDamage(double dmg) {
		this.hp -= (int) dmg;

	}

	@Override
	protected void regenMana() {
		if (this.mana < 0) {
			this.mana = 0;
		}
		if (this.mana <= this.maxMana && this.time % 20 == 0) {
			this.mana += 1 + this.LEVEL / 5;
		}

	}

	@Override
	public void gainKill(Mob kill) {
		Game.LOG("killed " + kill);
		getXP(kill.value());
		this.kills++;

	}

	private void getXP(int value) {
		this.XP += value / 3 + Entity.random.nextInt(50);
		Game.LOG("Gained "+value+"XP");
	}

	public void levelUP() {
		this.level.newMessageFrom("LEVEL UP!", this, new Color(220, 20, 20), 30);
		this.LEVEL++;
		this.strength += 2 + Entity.random.nextInt(2);
		this.XP -= this.maxXP;
		this.maxXP += this.maxXP / 2 + 10 * this.LEVEL * this.LEVEL;
		this.maxHp = this.strength * 10;
		this.maxMana += this.LEVEL;
		this.attackSpeed += 2;
		this.hp = this.maxHp;
		Game.LOG("");
		Game.LOG(this.name + " leveled to " + this.LEVEL);
		Game.LOG("STR:"+this.strength);
		Game.LOG("HP:"+this.maxHp);
		Game.LOG("MANA:"+this.maxMana);
		Game.LOG("ATTACK SPEED:"+this.attackSpeed);
		updateDmg();

	}

	private void updateDmg() {
		double olddamage = this.damage;
		this.damage = 20 + this.LEVEL + this.strength / 10;
		Game.LOG(this.name + " damage updated from " + olddamage + " to " + this.damage);
	}

	public int level() {
		return this.LEVEL;
	}

	/**
	 * Skill number i
	 * @param i
	 * @return
	 */
	public Skill getSkill(int i) {
		if(i<1 || 4 < i){
			System.err.println("getSKill reference number must be between 1 - 4");
			return null;
		}
		return this.skills[i - 1];
	}

	public int skillAmount() {
		return this.skills.length;
	}

	/**
	 * activates skill number i
	 * Indexing from 0...3
	 * @param i
	 */
	public void activateSkill(int i) {
		boolean activate = true;
		for (int k = 1; k < this.skills.length - 1; k++) {
			if (this.skills[(i + k) % this.skills.length].activated()) {
				activate = false;
				break;
			}

			if (activate) {
				if (this.skills[i].isActiveSkill()) {
					if (this.mana < ((ActiveSkill) this.skills[i]).manaCost()) {
						addFailMessage("No mana!",this);
						return;
					}
					if (!((ActiveSkill) this.skills[i]).ready()) {
						addFailMessage("Cooldown!",this);
						return;
					}
				}
				this.skills[i].activate();
			}
		}

	}
}

/*
 * private boolean ycollision(double ya) {
 *
 * boolean solid = false; for (int i = 0; i < this.hitPixels.size(); i++) { if
 * (level.getTile((((((int) this.x) + this.hitPixels.get(i).x())) / 16),
 * (((((int) this.y) + (int) ya + this.hitPixels.get(i).y())) / 16)).solid()) {
 * solid = true; } } return solid; }
 *
 * private boolean xcollision(double xa) {
 *
 * boolean solid = false; for (int i = 0; i < this.hitPixels.size(); i++) { if
 * (level.getTile((((((int) this.x) + (int) xa + this.hitPixels.get(i).x())) /
 * 16), (((((int) this.y) + this.hitPixels.get(i).y())) / 16)).solid()) { solid
 * = true; } } return solid; }
 */
// this.range < Math.sqrt((Math.abs(this.y - this.yOrigin) * Math.abs(this.y -
// this.yOrigin) + Math.abs(this.x - this.xOrigin) * Math.abs(this.x -
// this.xOrigin)))
/*
 * public void move() {
 *
 * anim++;
 *
 *
 * int xdir = 0; if(this.nx < 0){ xdir = -1; } if(this.nx > 0){ xdir = 1; } for
 * (int i = 0, j = (int) Math.abs(nx); i < j; i++) { if(!xcollision(xdir)){
 * this.x += xdir; } }
 *
 *
 * int ydir = 0; if(this.ny < 0){ ydir = -1; } if(this.ny > 0){ ydir = 1; } for
 * (int i = 0, j = (int) Math.abs(ny); i < j ; i++) { if(!ycollision(ydir)){
 * this.y += ydir; } }
 *
 *
 *
 * }
 */