package com.yoti.hoover.model;

import java.util.Arrays;
/*
 * Output after operation done by hoover according to instructions.
 */

public class HooverOutput {

	/*
	 * final position of hoover in Room
	 */
	private int[] coords;
	
	/*
	 * Number of patches cleaned.
	 */
	private int patches;

	public int[] getCoords() {
		return coords;
	}

	public void setCoords(int[] coords) {
		this.coords = coords;
	}

	public int getPatches() {
		return patches;
	}

	public void setPatches(int patches) {
		this.patches = patches;
	}

	@Override
	public String toString() {
		return "HooverOutput [coords=" + Arrays.toString(coords) + ", patches=" + patches + "]";
	}
	
}
