package com.backwatersoftware.asd.Skills;

import com.backwatersoftware.asd.Game;
import com.backwatersoftware.asd.entity.mob.Mob;

public class ActiveSkill extends Skill {

	protected int cooldown;
	protected String description;
	public double castRange;
	public int ready;
	public int manacost;
		
	public ActiveSkill(String name){
		this.name = name;
		this.active = true;
	}
	
	@Override
	public void update(){
		if(this.ready < this.cooldown && (Game.TIME % Game.TICKRATE) == 0){
			this.ready++;
		}
	}
	
	public int manaCost() {
		return this.manacost;
	}

	/**
	 * returns 
	 * 0: successful cast
	 * 1: no target
	 * 2: on cooldown
	 * 3: no mana
	 */
	public int use(Mob user, Mob target){
		return 0;
	}
	
	@Override
	public boolean isActiveSkill() {
		return true;
	}
	@Override
	public int coolDown() {
		return this.cooldown - this.ready;
	}
	public boolean ready() {
		if ((this.cooldown - this.ready) == 0) {
			return true;
		}
		return false;
	}
	public double castRange() {
		return this.castRange;
	}
}
