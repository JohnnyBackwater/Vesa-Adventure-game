package com.backwatersoftware.asd.Buffs;
import com.backwatersoftware.asd.entity.mob.Mob;

public class SpeedBoost extends Buff {

	
	public SpeedBoost(int dur){
		super(dur, 2);
		this.stackable = false;
	}
	
	


	@Override
	public void effect(Mob host){
		if(this.duration() == 0){
			host.setSpeed(host.getSpeed()*2);
		}
		if(this.duration() == this.lifetime()-1){
			host.setSpeed(host.getSpeed()/2);
		}
	}

	
}