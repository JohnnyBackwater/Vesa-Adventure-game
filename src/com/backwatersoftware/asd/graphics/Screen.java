package com.backwatersoftware.asd.graphics;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.backwatersoftware.asd.level.tile.Tile;
import com.backwatersoftware.physics.Point;

public class Screen {
	public int width;
	public int height;
	private final int MAP_SIZE = 32;
	
	public int xOffset;
	public int yOffset;
	public int[] pixels;
	//public int[] tiles = new int[this.MAP_SIZE * this.MAP_SIZE];
	public CharTaulu chars = new CharTaulu(SpriteSheet.ABC, SpriteSheet.Numbers);

	private Random random = new Random();

	public int time;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
		//	for (int i = 0; i < this.tiles.length; i++) {
		//	this.tiles[i] = this.random.nextInt(0xffffff);
		//	}
	}

	public void clear() {
		for (int i = 0; i < this.pixels.length; i++) {
			this.pixels[i] = 0;
		}
	}

	public void renderTile(int xp, int yp, Tile tile, boolean xFlip, boolean yFlip) {
		xp -= this.xOffset;
		yp -= this.yOffset;
		for (int y = 0; y < tile.sprite.YSIZE; y++) {
			int ya = y + yp;
			int ys = y;
			if (yFlip) {
				ys = (tile.sprite.YSIZE - 1) - y;
			}
			for (int x = 0; x < tile.sprite.XSIZE; x++) {
				int xa = x + xp;
				int xs = x;
				if (xFlip) {
					xs = (tile.sprite.XSIZE - 1) - x;
				}
				if (xa < -tile.sprite.XSIZE || xa >= this.width || ya < 0 || ya >= this.height)
					break;
				if (xa < 0)
					xa = 0;
				this.pixels[xa + ya * this.width] = tile.sprite.pixels[xs + ys * tile.sprite.XSIZE];
			}
		}
	}

	public void renderPlayer(int xp, int yp, Sprite sprite, boolean xFlip, boolean yFlip) {
		xp -= this.xOffset;
		yp -= this.yOffset;
		for (int y = 0; y < sprite.YSIZE; y++) {
			int ya = y + yp;
			int ys = y;
			if (yFlip) {
				ys = (sprite.YSIZE - 1) - y;
			}
			for (int x = 0; x < sprite.XSIZE; x++) {
				int xa = x + xp;
				int xs = x;
				if (xFlip) {
					xs = (sprite.XSIZE - 1) - x;
				}
				if (xa < -sprite.XSIZE || xa >= this.width || ya < 0 || ya >= this.height)
					break;
				if (xa < 0)
					xa = 0;
				int col = sprite.pixels[xs + ys * sprite.XSIZE];
				if (col != 0xffFF00FF)
					this.pixels[xa + ya * this.width] = col;
			}
		}
	}

	public void drawLine(Point point, int width, double length, double dir, Color col, boolean offset) {
		int x = (int)point.x();
		int y = (int)point.y();
		if (offset) {
			x -= this.xOffset;
			y -= this.yOffset;
		}

		for (double i = 0; i < length; i++) {
			if (x + (int) (i * Math.cos(dir)) + y * this.width + this.width * (int) (i * Math.sin(dir)) + 1 > this.pixels.length || 0 > x + (int) (i * Math.cos(dir)) + y * this.width + this.width * (int) (i * Math.sin(dir))) {
				continue;
			}
			if (x + (int) (i * Math.cos(dir)) >= this.width) {
				continue;
			}
			if (x + (int) (i * Math.cos(dir)) >= this.width) {
				continue;
			}
			if (x + (int) (i * Math.cos(dir)) < 0) {
				continue;
			}
			this.pixels[x + (int) (i * Math.cos(dir)) + y * this.width + this.width * (int) (i * Math.sin(dir))] = col.getRGB();
		}

	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void drawGreenLine(Point p, int width, double length, double dir, boolean offset) {
		// x Math.cos(dir);
		// y Math.sin(dir);
		int x = (int)p.x();
		int y = (int)p.y();
		if (offset) {
			x -= this.xOffset;
			y -= this.yOffset;
		}

		for (double i = 0; i < length; i++) {
			if (x + (int) (i * Math.cos(dir)) + y * this.width + this.width * (int) (i * Math.sin(dir)) + 1 > this.pixels.length || 0 > x + (int) (i * Math.cos(dir)) + y * this.width + this.width * (int) (i * Math.sin(dir))) {
				continue;
			}
			if (x + (int) (i * Math.cos(dir)) >= this.width) {
				continue;
			}
			if (x + (int) (i * Math.cos(dir)) < 0) {
				continue;
			}

			this.pixels[x + (int) (i * Math.cos(dir)) + y * this.width + this.width * (int) (i * Math.sin(dir))] = Color.GREEN.getRGB();
		}

	}

	public void drawSquare(int x, int y, int sides, int color, int lineWidth, boolean offset) {

		if (offset) {
			x -= this.xOffset;
			y -= this.yOffset;
		}

		for (int i = 0; i < sides; i++) {
			if ((x + i + y * this.width) > this.pixels.length || 0 > x + i + y * this.width && !(sides > Integer.MAX_VALUE - 10000)) {
				break;
			}
			this.pixels[x + i + y * this.width] = color;
		}

		for (int i = 0; i < sides; i++) {
			if ((x + i + y * this.width + this.width * sides) > this.pixels.length || 0 > x + i + y * this.width + this.width * sides && !(sides > Integer.MAX_VALUE - 10000)) {
				break;
			}
			this.pixels[x + i + y * this.width + this.width * sides] = color;
		}
		for (int i = 0; i < sides; i++) {
			if ((x + y * this.width + this.width * i) > this.pixels.length || 0 > x + y * this.width + this.width * i && !(sides > Integer.MAX_VALUE - 10000)) {
				break;
			}
			this.pixels[x + y * this.width + this.width * i] = color;
		}
		for (int i = 0; i < sides; i++) {
			if ((x + sides + y * this.width + this.width * i) > this.pixels.length || 0 > x + sides + y * this.width + this.width * i && !(sides > Integer.MAX_VALUE - 10000)) {

				break;
			}
			this.pixels[x + sides + y * this.width + this.width * i] = color;
		}

		if (((x + sides + y * this.width + sides * this.width) > this.pixels.length) || 0 > x + sides + y * this.width + sides * this.width && !(sides > Integer.MAX_VALUE - 10000)) {
			return;
		}
		this.pixels[x + sides + y * this.width + sides * this.width] = color;
	}

	public void drawRectangle(Point p, int xsize, int ysize, Color col, int lineWidth, boolean offset) {
		int color = col.getRGB();
		if (offset) {
			p.setOffset(this.xOffset, this.yOffset);
		}
		drawLine(p, 1, xsize, 0, new Color(color), false);
		drawLine(new Point(p.x(), p.y() + ysize,0), 1, xsize, 0, new Color(color), false);
		drawLine(p, 1, ysize, Math.PI / 2, new Color(color), false);
		drawLine(new Point(p.x() + xsize, p.y(),0), 1, ysize, Math.PI / 2, new Color(color), false);

	}

	/**
	 * Renders sprites. Like player
	 *
	 * @param xp
	 * @param yp
	 * @param sprite
	 * @param xFlip
	 * @param yFlip
	 */
	public void renderSprite(Point p, Sprite sprite, boolean xFlip, boolean yFlip, boolean offset) {
		int xp = (int)p.x();
		int yp = (int)p.y();
		if (offset) {
			xp -= this.xOffset;
			yp -= this.yOffset;
		}

		for (int y = 0; y < sprite.YSIZE; y++) {
			int ya = y + yp;
			int ys = y;
			if (yFlip) {
				ys = (sprite.YSIZE - 1) - y;
			}
			for (int x = 0; x < sprite.XSIZE; x++) {
				int xa = x + xp;
				int xs = x;
				if (xFlip) {
					xs = (sprite.XSIZE - 1) - x;
				}
				if (xa < -sprite.XSIZE || xa >= this.width || ya < 0 || ya >= this.height)
					break;
				if (xa < 0)
					xa = 0;
				int col = sprite.pixels[xs + ys * sprite.XSIZE];
				if (col != 0xffFF00FF)
					this.pixels[xa + ya * this.width] = col;
			}
		}

	}

	/**
	 * Renders the given sprite completely black.
	 * projection to the xy plane
	 * @param x
	 * @param y
	 * @param sprite
	 * @param b
	 * @param c
	 * @param d
	 */
	public void renderShadow(Point p, Sprite sprite, boolean xFlip, boolean yFlip, boolean offset) {
		int xp = (int)p.x();
		int yp = (int)p.y();
		if (offset) {
			xp -= this.xOffset;
			yp -= this.yOffset;
		}

		for (int y = 0; y < sprite.YSIZE; y++) {

			int ya = y + yp;
			int ys = y;
			if (yFlip) {
				ys = (sprite.YSIZE - 1) - y;
			}
			for (int x = 0; x < sprite.XSIZE; x++) {
				if(y % 2 == 0){
					if(x % 2 == 0) continue;
				}
				if(y % 2 != 0){
					if(x % 2 != 0) continue;
				}
				int xa = x + xp;
				int xs = x;
				if (xFlip) {
					xs = (sprite.XSIZE - 1) - x;
				}
				if (xa < -sprite.XSIZE || xa >= this.width || ya < 0 || ya >= this.height)
					break;
				if (xa < 0)
					xa = 0;
				int col = sprite.pixels[xs + ys * sprite.XSIZE];
				if (col != 0xffFF00FF)
					this.pixels[xa + ya * this.width] = 0x000000;
			}
		}
	}

	/**
	 * Renders sprites and fills white spaces with specified color
	 *
	 * @param xp
	 * @param yp
	 * @param sprite
	 * @param xFlip
	 * @param yFlip
	 * @param offset
	 * @param col Color to be set in white pixels
	 */
	public void renderSprite(Point p, Sprite sprite, boolean xFlip, boolean yFlip, boolean offset, Color coll) {
		int xp = (int)p.x();
		int yp = (int)p.y();
		if (offset) {
			xp -= this.xOffset;
			yp -= this.yOffset;
		}

		for (int y = 0; y < sprite.YSIZE; y++) {
			int ya = y + yp;
			int ys = y;
			if (yFlip) {
				ys = (sprite.YSIZE - 1) - y;
			}
			for (int x = 0; x < sprite.XSIZE; x++) {
				int xa = x + xp;
				int xs = x;
				if (xFlip) {
					xs = (sprite.XSIZE - 1) - x;
				}
				if (xa < -sprite.XSIZE || xa >= this.width || ya < 0 || ya >= this.height)
					break;
				if (xa < 0)
					xa = 0;
				int col = sprite.pixels[xs + ys * sprite.XSIZE];

				if (col != 0xffFF00FF){
					this.pixels[xa + ya * this.width] = col;
				}
				if (col == 0xffFFFFFF){
					this.pixels[xa + ya * this.width] = coll.getRGB();
				}
			}
		}

	}

	public void renderImage(BufferedImage im) {
		for (int i = 0; i < im.getWidth(); i++) {
			for (int j = 0; j < im.getHeight(); j++) {
				if (i + j * this.width >= this.pixels.length) {
					break;
				}
				this.pixels[i + j * this.width] = im.getRGB(i, j);
			}
		}

	}

	public void drawCircle(Point p, double radius, int col) {
		int x = (int)p.x();
		int y = (int)p.y();
		x -= this.xOffset;
		y -= this.yOffset;
		for (double i = 0, j = Math.PI * 2; i < j; i += 0.02) {
			if (x + (int) (radius * Math.cos(i)) + y * this.width + this.width * (int) (radius * Math.sin(i)) + 1 > this.pixels.length || 0 > x + (int) (radius * Math.cos(i)) + y * this.width + this.width * (int) (radius * Math.sin(i))) {
				continue;
			}
			if (x + (int) (radius * Math.cos(i)) >= this.width) {
				continue;
			}
			if (x + (int) (radius * Math.cos(i)) < 0) {
				continue;
			}

			this.pixels[x + (int) (radius * Math.cos(i)) + y * this.width + this.width * (int) (radius * Math.sin(i))] = col;

		}
	}

	public void drawPixel(Point p, Color color) {
		int x = (int) p.x();
		int y = (int) p.y();
		x -= this.xOffset;
		y -= this.yOffset;
		if (x < 0 || x > this.width || y < 0 || y > this.height || x + y * this.width >= this.pixels.length) {
			return;
		}
		this.pixels[x + y * this.width] = color.getRGB();

	}

	public void addText(Point p, String s, boolean offset, Color col) {
		int space = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ') {
				space += 5;
				continue;

			}
			Sprite sprite = this.chars.getSprite(s.charAt(i));
			renderSprite(new Point(p.x() + space, p.y() - 10 + sprite.yOffset,0), sprite, false, false, offset, col);
			if(sprite.key == 'j'){
				new Point(p.x()+1+space, p.y() - 10 + sprite.yOffset - 1,0);
				drawPixel(new Point(p.x()+1+space, p.y() - 10 + sprite.yOffset - 1,0), col);
			}
			space += sprite.xSpace;
		}
	}

	public void addText(Point p, String s, boolean offset) {
		Color col = Color.WHITE;
		int space = 0;
		int line = 0;
		String[] words = s.split(" ");
		for (int j = 0; j < words.length; j++) {
			String ss = words[j] + " ";
			for (int i = 0; i < ss.length(); i++) {
			if (ss.charAt(i) == ' ') {
				space += 4;
				continue;

			}
			if(ss.charAt(i) == ('\n')){
				space = 0;
				line++;
				continue;
			}
			if(p.x() + ss.length() * 5 + space > this.width){
				space = 0;
				line++;
				i--;
				continue;
			}
			Sprite sprite = this.chars.getSprite(ss.charAt(i));
			renderSprite(new Point(p.x() + space, p.y() - 10 + line * 7 + sprite.yOffset,0), sprite, false, false, offset);
			if(sprite.key == 'j'){
				drawPixel(new Point(p.x()+1+space, p.y() - 10 + line * 7 + sprite.yOffset - 1,0), col);
			}
			space += sprite.xSpace;
		}
		}
	}

	public void fillRect(Point p, int width, int height, Color col) {
		for (int i = 0; i < height; i++) {
			drawLine(new Point(p.x(), p.y() + i, 0) , 1, width, 0, col, false);
		}
	}

	public void fillRect(Point p, int width, int height, Color col, boolean b) {
		for (int i = 0; i < height; i++) {
			drawLine(new Point(p.x(), p.y() + i, 0) , 1, width, 0, col, b);
		}
		
	}
}