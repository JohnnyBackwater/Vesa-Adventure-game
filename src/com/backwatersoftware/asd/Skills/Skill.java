package com.backwatersoftware.asd.Skills;

public abstract class Skill {

	public String name;
	public boolean active, passive;
	public int level;
	public boolean activated;
	
	public Skill(){
		
	}
	
	public void update(){
		
	}
	public boolean activeSkill(){
		return true;
	}
	public boolean passiveSkill(){
		return true;
	}
	public boolean activated(){
		return this.activated;
	}
	public void activate(){
		this.activated = true;
	}
	public void deactivate(){
		this.activated = false;
	}

	public boolean isActiveSkill() {
		return false;
	}

	public int coolDown() {
		return 0;
	}

	public boolean ready() {
		return false;
	}

	public double castRange() {
		return 0;
	}

	
}
