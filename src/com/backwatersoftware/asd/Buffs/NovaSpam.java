package com.backwatersoftware.asd.Buffs;
import com.backwatersoftware.asd.Attacks.Attacks;
import com.backwatersoftware.asd.entity.mob.Mob;

public class NovaSpam extends Buff {

	public NovaSpam(int duration){
		super(duration, 1);
	}
	
	@Override
	public void effect(Mob host){
		if(duration() % 20 == 0){
			Attacks.NovaStrike(host.point(), 0, host);
		}
	}
}
