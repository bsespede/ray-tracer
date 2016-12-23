package ar.edu.itba.grupo2.screen;

import java.awt.Color;

public class ViewPlane {

	public final int hRes;
	public final int vRes;
	public final float s;
	public final float gamma;
	
	public ViewPlane(int hRes, int vRes) {
		this.hRes = hRes;
		this.vRes = vRes;
		this.s = 1;
		this.gamma = 1;
	}
	
	public ViewPlane(int hRes, int vRes, float s, float gamma) {
		this.hRes = hRes;
		this.vRes = vRes;
		this.s = s;
		this.gamma = gamma;
	}
	
	public void drawPixel(final int row, final int col, final Color color) {
		
	}	
	
}
