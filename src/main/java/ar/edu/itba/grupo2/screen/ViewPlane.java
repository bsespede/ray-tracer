package ar.edu.itba.grupo2.screen;

import ar.edu.itba.grupo2.utils.RGBColor;

public class ViewPlane {

	private final RGBColor[][] pixels;
	private final float invGamma;
	public final int hRes;
	public final int vRes;
	public final float s;
	
	public ViewPlane(final int hRes, final int vRes) {
		this.pixels = new RGBColor[hRes][vRes];
		this.hRes = hRes;
		this.vRes = vRes;
		this.s = 1;
		this.invGamma = 1;
	}
	
	public ViewPlane(final int hRes, final int vRes, final float s, final float gamma) {
		this.pixels = new RGBColor[hRes][vRes];
		this.hRes = hRes;
		this.vRes = vRes;
		this.s = s;
		this.invGamma = 1/gamma;
	}
	
	public void drawPixel(final int row, final int col, final RGBColor color) {
		if (invGamma != 1) {
			pixels[row][col] = new RGBColor((float) Math.pow(color.r, invGamma), (float) Math.pow(color.g, invGamma), (float) Math.pow(color.b, invGamma));
		} else {
			pixels[row][col] = color;			
		}
	}

	public int getWidth() {
		return (int) (hRes * s);
	}
	
	public int getHeight() {
		return (int) (vRes * s);
	}
	
	public RGBColor getPixel(final int row, final int col) {
		return pixels[row][col];
	}
	
}
