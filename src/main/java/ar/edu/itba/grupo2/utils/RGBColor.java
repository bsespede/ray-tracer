package ar.edu.itba.grupo2.utils;

import java.awt.Color;

public class RGBColor {

	public final float r, g, b;

	public RGBColor(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public Color toColor() {
		return new Color ((int) (r * 255), (int) (g * 255), (int) (b * 255));
	}

	public String toString() {
		return "RGBColor [r=" + r + ", g=" + g + ", b=" + b + "]";
	}
	
}
