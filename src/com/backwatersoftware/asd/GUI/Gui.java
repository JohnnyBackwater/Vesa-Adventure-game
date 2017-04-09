package com.backwatersoftware.asd.GUI;

import java.awt.Color;
import java.util.ArrayList;

import com.backwatersoftware.asd.GUI.Buttons.Button;
import com.backwatersoftware.asd.graphics.Screen;
import com.backwatersoftware.physics.Point;

public class Gui {

	protected ArrayList<Button> buttons = new ArrayList<Button>();
	protected int x, y, width, height;
	protected Color backgroundCol = Color.BLACK;
	protected boolean show;
	private ArrayList<Text> texts = new ArrayList<Text>();

	public Gui() {

	}

	public void addButton(Button b) {
		this.buttons.add(b);
	}

	public void addText(int x, int y, String message) {
		this.texts.add(new Text(x, y, message));
	}

	public void update() {
		if (this.show) {
			for (int i = 0; i < this.buttons.size(); i++) {
				if (this.buttons.get(i).isActive()) {
					this.buttons.get(i).update();
				}
			}
		}
	}

	public void render(Screen screen) {

		if (this.show) {
			screen.fillRect(new Point(this.x, this.y,0), this.width, this.height, this.backgroundCol);
			for (int i = 0; i < this.buttons.size(); i++) {
				if (this.buttons.get(i).isActive()) {
					this.buttons.get(i).render(screen);
				}

			}
			for (int i = 0; i < this.texts.size(); i++) {
				this.texts.get(i).render(screen);
			}
		}


	}

	public boolean buttonPressed(int key) {
		for (int i = 0; i < this.buttons.size(); i++) {
			if (this.buttons.get(i).pressed() && this.buttons.get(i).key() == key) {
				return true;
			}
		}
		return false;
	}

	public void show() {
		this.show = true;
	}

	public void hide() {
		realeaseButtons();
		this.show = false;
	}

	private void realeaseButtons() {
		for (int i = 0; i < this.buttons.size(); i++) {
			this.buttons.get(i).release();
		}

	}

	public void activateButton(int key) {
		for (int i = 0; i < this.buttons.size(); i++) {
			if (this.buttons.get(i).key() == key) {
				this.buttons.get(i).activate();
			}
		}
		realeaseButtons();
	}

	public void deactivateButton(int key) {
		for (int i = 0; i < this.buttons.size(); i++) {
			if (this.buttons.get(i).key() == key) {
				this.buttons.get(i).deactivate();
			}
		}
		realeaseButtons();
	}

	public boolean isVisible() {
		return this.show;
	}

}
