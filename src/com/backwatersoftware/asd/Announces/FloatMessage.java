package com.backwatersoftware.asd.Announces;

import java.awt.Color;

import com.backwatersoftware.asd.entity.Entity;
import com.backwatersoftware.asd.graphics.Screen;
import com.backwatersoftware.physics.Point;

public class FloatMessage extends Entity {
private String message;
private Color color;
private int life;
	public FloatMessage(Point p, String message, int life, Color col){
		this.point = p;
		this.message = message;
		this.life = life;
		this.color = col;
	}
	
	@Override
	public void update(){
		this.time++;
		if(this.time > this.life){
			remove();
		}
	}
	
	@Override
	public void render(Screen screen){
		screen.addText(this.point, this.message, true, this.color);
	}
	
	@Override
	public boolean isFloatMessage(){
		return true;
	}

	public String message() {
		return this.message;
	}
	@Override
	public boolean equals(Object o){
		FloatMessage mes = (FloatMessage)o;
		if (mes.message.equals(this.message)) {
		return true;
		}
		return false;
	}
}
