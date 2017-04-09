package com.backwatersoftware.asd.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.backwatersoftware.asd.Game;
import com.backwatersoftware.physics.Point;

public class Mouse implements MouseListener, MouseMotionListener {

	private static int mouseX = -1;
	private static int mouseY = -1;
	private static int mouseB = -1;
	
	public static int getX(){
		return mouseX;
	}

	public static int getY(){
		return mouseY;
	}

	public static int getB(){
		return mouseB;
	}
	/**
	 * 
	 * @return y position scaled with game's window scale
	 */
	public static int getYScaled(){
		return mouseY / Game.scale;
	}
	/**
	 * 
	 * @return x position scaled with game's window scale
	 */
	public static int getXScaled(){
		return mouseX / Game.scale;
	}
	
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mouseClicked(MouseEvent e) {
		//mouseB = e.getButton();
	}

	public void mousePressed(MouseEvent e) {
		mouseB = e.getButton();
	}

	public void mouseReleased(MouseEvent e) {
		mouseB = -1;
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	

}
