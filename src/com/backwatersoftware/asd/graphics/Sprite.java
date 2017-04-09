package com.backwatersoftware.asd.graphics;

import java.util.Random;

public class Sprite {

	public final int XSIZE, YSIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;

	public boolean xFlip;
	public boolean yFlip;
	public int key;
	public int xSpace;
	public int yOffset;

	public static Sprite[] players = {new Sprite(16,0,0,SpriteSheet.players)};
	public static Sprite[][] playerMovings = {{	new Sprite(16,0,0,SpriteSheet.players),
												new Sprite(16,1,0,SpriteSheet.players),
												new Sprite(16,0,0,SpriteSheet.players),
												new Sprite(16,2,0,SpriteSheet.players)}};
	
	public static Sprite Hayman = new Sprite(16, 3, 3, SpriteSheet.tiles);
	public static Sprite fireBall = new Sprite(16,2,3,SpriteSheet.tiles);
	public static Sprite xpBook = new Sprite(16,1,3,SpriteSheet.tiles);
	public static Sprite errorSprite = new Sprite(16, 0, 0, SpriteSheet.errors);
	public static Sprite Ganja = new Sprite(16, 3, 2, SpriteSheet.tiles);
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite grass2 = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite grass3 = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite rock1 = new Sprite(16, 0, 1, SpriteSheet.tiles);
	public static Sprite water0 = new Sprite(16, 1, 1, SpriteSheet.tiles);
	public static Sprite flower1 = new Sprite(16, 2, 1, SpriteSheet.tiles);
	public static Sprite BrickWall = new Sprite(16, 4, 2, SpriteSheet.tiles);
	public static Sprite tree = new Sprite(16,32,6,1,SpriteSheet.tiles);

	public static Sprite player_left = new Sprite(32, 2, 0, SpriteSheet.tiles);
	public static Sprite player_right = new Sprite(32, 3, 0, SpriteSheet.tiles);
	public static Sprite morottaja = new Sprite(64, 0, 1, SpriteSheet.tiles);
	public static Sprite morottajaAttack = new Sprite(64, 0, 2, SpriteSheet.tiles);

	public static Sprite player_movingleft = new Sprite(32, 5, 0, SpriteSheet.tiles);
	public static Sprite player_movingright = new Sprite(32, 4, 0, SpriteSheet.tiles);

	public static Sprite VoidSprite = new Sprite(16, 0);
	public static Sprite BluePlasmaBall = new Sprite(16, 0, 2, SpriteSheet.tiles);
	public static Sprite RedPlasmaBall = new Sprite(16, 0, 3, SpriteSheet.tiles);
	public static Sprite LaserAnimLeft = new Sprite(16, 1, 2, SpriteSheet.tiles);
	public static Sprite LaserAnimRight = new Sprite(16, 2, 2, SpriteSheet.tiles);

	public static Sprite Space = new Sprite(16, 5, 2, SpriteSheet.tiles);
	/**
	 * Constructor for rectangle sprites with different x and y sizes
	 * 
	 * @param xsize
	 * @param ysize
	 * @param x
	 * @param y
	 * @param sheet
	 */
	public Sprite(int xsize, int ysize, int x, int y, SpriteSheet sheet) {
		this.XSIZE = xsize;
		this.YSIZE = ysize;
		this.pixels = new int[this.XSIZE * this.YSIZE];
		this.x = x * xsize;
		this.y = y * ysize;
		this.sheet = sheet;
		this.xFlip = false;
		this.yFlip = false;
		load();
	}

	/**
	 * Constructor for square sprites
	 * 
	 * @param size
	 * @param x
	 * @param y
	 * @param sheet
	 */
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this.XSIZE = size;
		this.YSIZE = size;
		this.pixels = new int[this.XSIZE * this.YSIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		this.xFlip = false;
		this.yFlip = false;
		load();
	}

	/**
	 * Mainly used for Character Sprites with key = char
	 * 
	 * @param xsize
	 * @param ysize 
	 * @param x
	 * @param y
	 * @param sheet
	 * @param key
	 */
	public Sprite(int xsize, int ysize, int x, int y, SpriteSheet sheet, int key, int xspace, int yoffset) {
		this.key = key;
		this.XSIZE = xsize;
		this.YSIZE = ysize;
		this.pixels = new int[this.XSIZE * this.YSIZE];
		this.x = x * xsize;
		this.y = y * ysize;
		this.sheet = sheet;
		this.xFlip = false;
		this.yFlip = false;
		this.xSpace = xspace;
		this.yOffset = yoffset;
		load();
	}
/**
 * Square Sprite
 * @param size
 * @param colour
 */
	public Sprite(int size, int colour) {
		this.XSIZE = size;
		this.YSIZE = size;
		this.pixels = new int[this.XSIZE * this.YSIZE];
		setColor(colour);
	}

	public Sprite(int size) {
		this.XSIZE = size;
		this.YSIZE = size;
		this.pixels = new int[this.XSIZE * this.YSIZE];
	}

	private void setColor(int color) {
		for (int i = 0; i < this.XSIZE * this.YSIZE; i++) {
			this.pixels[i] = color;
		}

	}

	private void load() {
		for (int y = 0; y < this.YSIZE; y++) {
			for (int x = 0; x < this.XSIZE; x++) {
				this.pixels[x + y * this.XSIZE] = this.sheet.pixels[(x + this.x) + (y + this.y) * this.sheet.xSIZE];
			}
		}
	}

	public static Sprite randomMinionSprite() {
		Random rand = new Random();
		return new Sprite(16, rand.nextInt(3), 15, SpriteSheet.tiles);
	}
/**
 * Produces randomly a Square sprites for sprite by scrambling the colors
 * @param sprite
 * @return
 */
	private static Sprite makeRandom(Sprite sprite) {
		Random rand = new Random();
		Sprite s = new Sprite(sprite.XSIZE);
		for (int ya = 0; ya < s.YSIZE; ya++) {
			for (int xa = 0; xa < s.XSIZE; xa++) {
				s.pixels[xa + ya * s.XSIZE] = sprite.pixels[rand.nextInt(sprite.XSIZE) + rand.nextInt(sprite.YSIZE) * s.XSIZE];
			}
		}
		return s;
	}

	public void setXflip(boolean flip) {
		this.xFlip = flip;
	}

	public void setYflip(boolean flip) {
		this.yFlip = flip;
	}

	public static Sprite makeGrassSprite() {
		return makeRandom(grass);
	}

	public static Sprite makeSpaceSprite() {
		return makeRandom(Space);
	}

}
