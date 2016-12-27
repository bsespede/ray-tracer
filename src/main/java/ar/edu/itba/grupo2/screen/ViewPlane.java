package ar.edu.itba.grupo2.screen;

import ar.edu.itba.grupo2.utils.Material;

public class ViewPlane {

	private final Material[][] pixels;
	public final int hRes;
	public final int vRes;
	public final float s;
	
	
	public ViewPlane(final int hRes, final int vRes) {
		this.pixels = new Material[hRes][vRes];
		this.hRes = hRes;
		this.vRes = vRes;
		this.s = 1;
	}
	
	public ViewPlane(final int hRes, final int vRes, final float s) {
		this.pixels = new Material[hRes][vRes];
		this.hRes = hRes;
		this.vRes = vRes;
		this.s = s;
	}
	
	public void drawPixel(final int row, final int col, final Material color) {
			pixels[row][col] = color;
	}

	public int getWidth() {
		return (int) (hRes * s);
	}
	
	public int getHeight() {
		return (int) (vRes * s);
	}
	
	public Material getPixel(final int row, final int col) {
		return pixels[row][col];
	}
	
}
