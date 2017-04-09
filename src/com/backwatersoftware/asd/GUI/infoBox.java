package com.backwatersoftware.asd.GUI;
import java.awt.Color;
import java.util.ArrayList;

import com.backwatersoftware.asd.graphics.Screen;
import com.backwatersoftware.asd.input.Mouse;
import com.backwatersoftware.physics.Point;

public class infoBox {

	private boolean visible;
	private Point p;
	private String text;
	
	public infoBox(Point p, String text){
		this.visible = false;
		this.p = p;
		this.text = text;
	}
	public void render(Screen screen){
		double dx = Math.abs((Mouse.getXScaled() + screen.xOffset) - (this.p.x() + 8));
		double dy = Math.abs((Mouse.getYScaled() + screen.yOffset) - (this.p.y() + 8));
		if(Math.pow(dx + dy, 0.5) > 3){
			return;
		}
		
		
		int width = 80;
		int height = 0;
		String[] words = this.text.split(" ");
		ArrayList<String> lines = new ArrayList<String>();
		
		int newline = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < words.length; i++) {	
			if(newline > 80){
				newline = 0;
				lines.add(sb.toString());
				sb = new StringBuilder();
				continue;
			}
			sb.append(words[i] + " ");
			newline += words[i].length() * 5 + 5;
		}
		lines.add(sb.toString());
		screen.fillRect(new Point(this.p,-2,-12), width, 6 + 6*lines.size(), Color.black, true);
		for (int i = 0; i < lines.size(); i++) {
			screen.addText(new Point(this.p,0,0+6*i), lines.get(i), true,Color.white);
		}
		
	}
	public void show() {
		this.visible= true;	
	}
	public void hide() {
		this.visible = false;
		
	}
	
}
