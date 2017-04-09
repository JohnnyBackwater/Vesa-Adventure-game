package com.backwatersoftware.asd.GUI.Buttons;

import java.awt.Color;

import com.backwatersoftware.asd.Game;
import com.backwatersoftware.asd.Skills.Skill;
import com.backwatersoftware.asd.graphics.Effects;
import com.backwatersoftware.asd.graphics.Screen;
import com.backwatersoftware.asd.input.Mouse;
import com.backwatersoftware.physics.Point;

public class Button {

	private Point point;
	private int width;
	private int height;
	private String text;
	private String type;
	protected int time;
	private boolean pressed;
	private int key;
	private boolean active;
	protected Color defaultCol = Color.BLACK;
	protected Color col;
	
	public Button(Point p, int width, int height, String text, int key, boolean active, Color col) {
		this.active = active;
		this.point = p;
		this.width = width;
		this.height = height;
		this.text = text;
		this.key = key;
		this.col = this.defaultCol;
		if (col != null) {
			this.col = col;
			this.defaultCol = col;
		}

	}

	

	protected void setText(String newText){
		this.text = newText;
	}

	public boolean pressed(){
		return this.pressed;
	}

	public boolean isActive(){
		return this.active;
	}

	public void activate(){
		this.active = true;
	}

	public void deactivate(){
		this.active = false;
	}

	public void update(){
		this.time++;
			
		if(clicked()){
			setColor(Effects.darkenColor(col, 1));
		}
		else{
			setColor(this.defaultCol);
		}
				
	}

	protected boolean clicked() {
		if(Mouse.getB() == 1){
			if(this.point.x() < Mouse.getXScaled() && Mouse.getXScaled() < this.point.x() + this.width && this.point.y() < Mouse.getYScaled() && Mouse.getYScaled() < this.point.y() + this.height){
				this.pressed = true;
					
			}
		}
		if (Mouse.getB() == -1) {
			this.pressed = false;
		}
		return this.pressed();
	}



	protected void setColor(Color col) {
		this.col = col;
	}

	public void render(Screen screen) {
		screen.fillRect(this.point, this.width, this.height, this.col);
		screen.addText(this.point(2,this.height/2), this.text, false);
		screen.drawRectangle(this.point, this.width, this.height, Color.WHITE, 1, false);

	}

	private Point point(int i, int j) {
		Point p = new Point(this.point.x() + i, this.point.y() + j,0);
		return p;	
	}



	public void release() {
		this.pressed = false;
	}

	public int key() {
		return this.key;
	}
}
