package com.backwatersoftware.asd.GUI;

import com.backwatersoftware.asd.Game;
import com.backwatersoftware.asd.GUI.Buttons.Button;
import com.backwatersoftware.physics.Point;

public class MainMenu extends Gui {

	/**
	 * Main menu screen with buttons key 1: NEW GAME key 2: EXIT
	 */
	public MainMenu() {
		// this.addButton(new Button(x, y, width, height, text)));
		this.x = 0;
		this.y = 0;
		this.width = Game.width;
		this.height = Game.height;
		this.addButton(new Button(new Point(Game.width / 2, 50, 0), 80, 35, "battletest", 1, true, null));
		this.addButton(new Button(new Point(Game.width / 2, 100, 0), 80, 35, "Consume", 2, true, null));
		this.addButton(new Button(new Point(Game.width / 2, 150, 0), 80, 35, "Levelup test", 3, true, null));
		this.addButton(new Button(new Point(Game.width / 2 + 80, 50, 0), 80, 35, "Snowing", 4, true, null));
		this.addButton(new Button(new Point(Game.width / 2 - 80, 50, 0), 80, 35, "Edge pan test", 5, true, null));
		this.addText(100, 200, "Testing if automatic lining will work if the text goes beyond the drawable area. I was so lazy to modify the code to start this from closer the edge so i must write a lot. Check out the new cool buttons");
	}

}
