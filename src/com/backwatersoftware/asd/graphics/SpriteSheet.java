package com.backwatersoftware.asd.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	public final int xSIZE, ySIZE;
	public int[] pixels;

	public static SpriteSheet players = new SpriteSheet("/textures/Pelaaja.png", 128, 16);
	public static SpriteSheet tiles = new SpriteSheet("/textures/spritesheet.png", 256, 256);
	public static SpriteSheet errors = new SpriteSheet("/textures/errorSprite.png", 16, 16);
	public static SpriteSheet ABC = new SpriteSheet("/textures/Aakkoset.png", 130, 12);
	public static SpriteSheet Numbers = new SpriteSheet("/textures/numbers.png", 40, 6);
	public static SpriteSheet Symbols = new SpriteSheet("/textures/symbols.png", 40, 6);
	public SpriteSheet(String path, int xsize, int ysize) {
		this.ySIZE = ysize;
		this.path = path;
		this.xSIZE = xsize;
		this.pixels = new int[this.xSIZE * this.ySIZE];
		load();
	}

	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(this.path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, this.pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
