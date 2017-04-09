package com.backwatersoftware.asd.level;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.backwatersoftware.asd.graphics.Screen;
import com.backwatersoftware.asd.level.tile.Tile;

public class SpawnLevel extends Level {

	private Tile[] tiles;
	private int[] levelPixels;

	public SpawnLevel(String path) {	
		super(path);
		this.random = new Random();
	}

	@Override
	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read((SpawnLevel.class.getResource(path)));

			int w = image.getWidth();
			int h = image.getHeight();
			this.width = w;
			this.height = h;
			this.tiles = new Tile[w * h];
			this.levelPixels = new int[w * h];
			
			this.levelPixels = image.getRGB(0, 0, w, h, this.levelPixels, 0, w);
			generateLevel();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Converts the integer pixel array "pixels" to array of Tiles
	 * RedBrickWall = 0xFF5555
	 * Grass = 0xFF00
	 * Rock = 0x808080
	 * water = 00FFFF
	 */
	@Override
	protected void generateLevel() {
		byte col;
		for (int w = 0; w < this.levelPixels.length; w++){
			if(w % this.width == 0 && w != 0){
			}
			
			col = (byte)this.levelPixels[w];

			
			switch (col) {
			case (byte)0xFF00: {
				this.tiles[w] = Tile.getGrassTile();			
				break;
			}
			case (byte)0x808080: {
				this.tiles[w] = Tile.rock1;
				
				break;
			}
			case (byte)0x00FFF: {
				this.tiles[w] = Tile.water0;
				
				break;
			}
			case (byte)0xFF5555: {
				this.tiles[w] = Tile.BrickWall;
		
				break;
			}
			case (byte)0x404040:{
				this.tiles[w] = Tile.getSpaceTile();
				break;
			}
			}
		
		}
	}

		
	@Override
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= this.width || y >= this.height) {
			return Tile.voidTile;
		}
		return this.tiles[x + y * this.width];
	}
}
