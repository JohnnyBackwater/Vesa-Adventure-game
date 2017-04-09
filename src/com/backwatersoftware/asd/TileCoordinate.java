package com.backwatersoftware.asd;

public class TileCoordinate {
	
	//TODO maybe improve the tile size thingy
	public int x, y;
	private final int TILE_SIZE = 16;

	public TileCoordinate(int x, int y) {
		this.x = x * this.TILE_SIZE;
		this.y = y * this.TILE_SIZE;
	}

	public int x() {
		return this.x;
	}

	public int y() {
		return this.y;
	}

	public int[] xy() {
		int[] r = new int[2];
		r[0] = this.x;
		r[1] = this.y;
		return r;
	}
}
