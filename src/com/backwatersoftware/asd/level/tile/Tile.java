package com.backwatersoftware.asd.level.tile;

import java.util.Random;

import com.backwatersoftware.asd.TileCoordinate;
import com.backwatersoftware.asd.graphics.Screen;
import com.backwatersoftware.asd.graphics.Sprite;

public class Tile {

	
	public int x, y;
	public Sprite sprite;
	

	private static Random random = new Random();
	public static Tile grass0 = new GrassTile(Sprite.grass);
	public static Tile grass1 = new GrassTile(Sprite.grass2);
	public static Tile grass2 = new GrassTile(Sprite.grass3);
	public static Tile rock1 = new RockTile(Sprite.rock1);
	public static Tile voidTile = new VoidTile(Sprite.VoidSprite);
	public static Tile water0 = new WaterTile(Sprite.water0);
	public static Tile grassFlower = new grassFlowerTile(Sprite.flower1);
	public static Tile BrickWall = new BrickWallTile(Sprite.BrickWall);
	public static Tile SpaceTile = new SpaceTile(Sprite.Space);
	public static Tile[] TileList = {grass0, grass1, grass2, rock1, voidTile};
	public static Tile[] grass = makeGrassTiles();
	public static Tile[] space = makeSpaceTiles();
	public static TileCoordinate position;
	
	public Tile(Sprite sprite){
		this.sprite = sprite;
	}
	
	private static Tile[] makeGrassTiles() {
		Tile[] tiles = new Tile[64];
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = new GrassTile(Sprite.makeGrassSprite());
		}
		return tiles;
	}
	private static Tile[] makeSpaceTiles() {
		Tile[] tiles = new Tile[64];
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = new GrassTile(Sprite.makeSpaceSprite());
		}
		return tiles;
	}

	public void render(int x, int y, Screen screen){
	}
	
	public static Tile getGrassTile(){
		if(random.nextInt(100) < 1){
			return Tile.grassFlower;
		}
		return Tile.grass[random.nextInt(Tile.grass.length)];
	}
	
	public static Tile getSpaceTile(){
		return Tile.space[random.nextInt(Tile.space.length)];
	}
	
	public boolean solid(){
		return false;
	}
	public boolean breakable(){
		return false;
	}
	public void setCoordinate(int x, int y){
		
	}
}
