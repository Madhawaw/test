package com.eyepax.assignment;

import java.util.Random;

/**
 * Created by madhawa.w on 7/24/2022.
 */
public class ColorGenerator {

	public static Random random = new Random();

	public  int getRandomIntColor() {
		int[] rgb = getRandomRgb();
		int color = 0;
		for (int c : rgb) {
			color = color << 8;
			color = color | c;
		}
		return color;
	}

	public int[] getRandomRgb() {
		int[] rgb = new int[3];
		for (int i = 0; i < 3; i++) {
			rgb[i] = random.nextInt(255);
		}
		return rgb;
	}

}
