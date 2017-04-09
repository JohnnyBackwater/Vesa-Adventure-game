package com.backwatersoftware.asd.level.tile;

import com.backwatersoftware.asd.graphics.Screen;
import com.backwatersoftware.asd.graphics.Sprite;

public class RockTile extends Tile {
	public RockTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen){
		screen.renderTile(x << 4, y << 4, this,false, false);
	}
	
	public boolean solid(){
		return true;
	}
	
	public boolean breakable(){
		return true;
	}
}
