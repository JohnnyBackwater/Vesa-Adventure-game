package com.backwatersoftware.asd.Buffs;

import com.backwatersoftware.asd.Game;
import com.backwatersoftware.asd.Attacks.Attacks;
import com.backwatersoftware.asd.entity.mob.Mob;

public abstract class Buff {

	private int duration;
	private int lifetime;
	private int buffID;
	private boolean removed;
	protected boolean stackable;
	
	
	public Buff(int duration, int ID){
		this.buffID = ID;
		this.duration = 0;
		this.lifetime = duration*60;
	}
	
	public void update(Mob host){
		effect(host);
		this.duration++;
		if(this.duration == this.lifetime){
			remove();
		}
	}
	
	public int buffID(){
		return this.buffID;
	}
	public boolean stackable(){
		return this.stackable;
	}
	
	/**
	 * Has the access all to the Mob's attributes. All changes done in 
	 * Mob are permanent and must be reversed when the lifetime is at it's end
	 * @param m
	 */
	public void effect(Mob m){
		//TODO For each buff this must be defined
	}
	private void remove(){
		this.removed = true;
	}
	public boolean removed(){
		return this.removed;
	}
	public int duration(){
		return this.duration;
	}
	protected int lifetime() {
		// TODO Auto-generated method stub
		return this.lifetime;
	}
	public void refresh() {
		this.duration = 1;		
	}
	
}



