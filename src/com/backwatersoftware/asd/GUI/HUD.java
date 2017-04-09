package com.backwatersoftware.asd.GUI;

import java.awt.Color;

import com.backwatersoftware.asd.GUI.Buttons.Button;
import com.backwatersoftware.asd.GUI.Buttons.skillButton;
import com.backwatersoftware.asd.entity.mob.Player;
import com.backwatersoftware.physics.Point;

public class HUD extends Gui {

	private Player player;
	public HUD(int x, int y, int width, int height, Player p, Color col){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.player = p;
		//(int x, int y, int width, int height, String text, int key, boolean active)
		for (int i = 0; i < this.player.skillAmount(); i++) {
			Point pp = new Point(x + 2 + 38 * i, y + 10,0);
			addButton(new skillButton(pp, 36, 36, this.player.getSkill(1 + i), 1 + i, true, new Color(150,100,100)));
		}
		this.show = true;
		this.backgroundCol = col;
	}

	/**
	 * Interface between user and the player
	 * Buttons by key (int):
	 * 1 = Skillslot 1
	 * 2 = Skillslot 2
	 * 3 = skillslot 3
	 * 4 = skillslot 4
	 */
	@Override
	public void update(){
		if(this.player.level.paused()){
			this.hide();
		}
		else{
			this.show();
		}
		if (this.show) {
			for (int i = 0; i < this.buttons.size(); i++) {
				if (this.buttons.get(i).isActive()) {
					this.buttons.get(i).update();
				}
			}
		}

		// Skill activation buttons (0, 1, 2, 3)
		// keys 1 - 4
		for (int i = 1; i < 5; i++) {
			if(this.buttonPressed(i)){
				this.player.activateSkill(i-1);
			}
		}
	}


}
