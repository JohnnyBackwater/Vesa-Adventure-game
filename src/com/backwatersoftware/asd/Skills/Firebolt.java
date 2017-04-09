package com.backwatersoftware.asd.Skills;

import com.backwatersoftware.asd.Game;
import com.backwatersoftware.asd.entity.mob.Mob;
import com.backwatersoftware.asd.entity.projectile.boltFire;

public class Firebolt extends ActiveSkill {

	public double damage;
	public Firebolt(int level){
		super("Firebolt");
		this.level = level;
		this.damage = 150 + (level - 1) * 75;
		this.cooldown = 5 - (level - 1) * 3;
		this.ready = this.cooldown;
		this.manacost = 100 + this.level*10;
		this.castRange = 200;
	}
	
	@Override
	public void update(){
		if(this.ready < this.cooldown && (Game.TIME % Game.TICKRATE) == 0){
			this.ready++;
		}
	}
	
	@Override
	/**
	 * returns 
	 * 0: successful cast
	 * 1: no target
	 * 2: on cooldown
	 * 3: no mana
	 */
	public int use(Mob user, Mob target){
		if(target == null){		
			return 1;
		}
		if(this.cooldown != this.ready){
			return 2;
		}
		if(user.mana < this.manacost){
			return 3;
		}
		if(user.point().distance(target.point()) > this.castRange){
			return 4;
		}
		user.fireMissile(new boltFire(user, target, this.damage));
		Game.LOG("Firing bolt of fire at " + target);
		this.ready = 0;
		deactivate();
		return 0;
	}
}
