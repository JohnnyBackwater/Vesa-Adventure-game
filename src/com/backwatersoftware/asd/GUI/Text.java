package com.backwatersoftware.asd.GUI;

import com.backwatersoftware.asd.graphics.Screen;
import com.backwatersoftware.physics.Point;

public class Text {

	public String message;
	public int x,y;
	
	public Text(int x, int y, String s){
		this.message = s;
		this.x = x;
		this.y = y;
	}
	
	public void render(Screen screen){
		screen.addText(new Point(this.x, this.y, 0), this.message, false);
	}
}
