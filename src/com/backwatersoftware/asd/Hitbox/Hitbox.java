package com.backwatersoftware.asd.Hitbox;

import java.util.ArrayList;

import com.backwatersoftware.asd.entity.Entity;
import com.backwatersoftware.physics.Point;

public class Hitbox {
	
	public Point point;
	public ArrayList<Point> points = new ArrayList<Point>();
	public double x,y,x2,y2;
	public Hitbox(Point p, double x2, double y2) {
		this.point = p;
		this.x = p.x();
		this.y = p.y();
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public void update(Entity e){
		this.x = (int)e.point().x();
		this.x2 = (int)e.point().x() + e.getXSize();
		this.y = (int)e.point().y();
		this.y2 = (int)e.point().y() + e.getYSize();
		this.point.update();
	}

	/**
	 * 
	 * @param h1
	 * @param h2
	 * @param h1xoffset
	 * @return
	 */
	public static boolean XhitboxCollision(Hitbox h1, Hitbox h2, double h1xoffset) {
		if (h2.x < h1.x + h1xoffset && h1.x + h1xoffset < h2.x2 && h2.y < h1.y && h1.y < h2.y2) {
			return true;
		}
		if (h2.x < h1.x2 + h1xoffset && h1.x2 + h1xoffset < h2.x2 && h2.y < h1.y && h1.y < h2.y2) {
			return true;
		}
		if (h2.x < h1.x + h1xoffset && h1.x + h1xoffset < h2.x2 && h2.y < h1.y2 && h1.y2 < h2.y2) {
			return true;
		}
		if (h2.x < h1.x2 + h1xoffset && h1.x2 + h1xoffset < h2.x2 && h2.y < h1.y2 && h1.y2 < h2.y2) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param h1
	 * @param h2
	 * @param h1yoffset
	 * @return
	 */
	public static boolean YhitboxCollision(Hitbox h1, Hitbox h2, int h1yoffset) {
		if (h2.x < h1.x && h1.x < h2.x2 && h2.y < h1.y + h1yoffset && h1.y + h1yoffset < h2.y2) {
			return true;
		}
		if (h2.x < h1.x2 && h1.x2 < h2.x2 && h2.y < h1.y + h1yoffset && h1.y + h1yoffset < h2.y2) {
			return true;
		}
		if (h2.x < h1.x && h1.x < h2.x2 && h2.y < h1.y2 + h1yoffset && h1.y2 + h1yoffset < h2.y2) {
			return true;
		}
		if (h2.x < h1.x2 && h1.x2 < h2.x2 && h2.y < h1.y2 + h1yoffset && h1.y2 + h1yoffset < h2.y2) {
			return true;
		}
		return false;
	}

}
