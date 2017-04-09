package com.backwatersoftware.asd.graphics;

import java.awt.Color;

public class Effects {


	/**
	 * Not sure if this works properly. Still under development
	 * Should reduce the brightness of the given color by the given amount
	 *
	 * The darkening is linear and it reduces each color value by 10 * amount
	 * @param col
	 * @param amount
	 * @return
	 */
	public static Color darkenColor(Color col, int amount){
		int r = col.getRed() - 10*amount;
		int g = col.getGreen() - 10*amount;
		int b = col.getBlue() - 10*amount;
		if (r < 0) {
			r = 0;
		}
		if (g < 0) {
			g = 0;
		}

		if (b < 0) {
			b=0;
		}
		return new Color(r, g, b);
	}

	/**
	 * Changes the brightness of the given color by the given percentage (amount)
	 *
	 *
	 * @param col the Color to be modified
	 * @param amount*100%
	 * @return
	 */
	public static Color setBrightness(Color col, double amount){

		//TODO Not workin??? TEST
		float a = (float) amount;
		int newcol = col.HSBtoRGB(1, 1, a);
		return new Color(newcol);

	}
}
