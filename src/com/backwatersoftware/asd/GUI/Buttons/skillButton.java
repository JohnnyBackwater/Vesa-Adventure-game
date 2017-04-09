package com.backwatersoftware.asd.GUI.Buttons;

import java.awt.Color;

import com.backwatersoftware.asd.Skills.Skill;
import com.backwatersoftware.asd.graphics.Effects;
import com.backwatersoftware.asd.input.Mouse;
import com.backwatersoftware.physics.Point;

public class skillButton extends Button {
	
	private Skill skill;
	public skillButton(Point p, int width, int height, Skill skill, int key, boolean active, Color col) {
		super(p, width,height, skill.name,key, active, col);
		this.skill = skill;
		this.col = this.defaultCol;
		if (col != null) {
			this.col = col;
			this.defaultCol = col;
		}
	}
	
	
	public void update(){
		this.time++;
		if(!this.skill.ready()){
			this.setText(this.skill.name+"\n"+this.skill.coolDown());
		}
		else{
			this.setText(this.skill.name);
		}
		if(clicked()){
			this.skill.activate();
		}	
		if(this.skill.activated()){
			this.setColor(Effects.darkenColor(this.defaultCol, 4));
		}
		else{
			this.setColor(this.defaultCol);
		}
	}
}
