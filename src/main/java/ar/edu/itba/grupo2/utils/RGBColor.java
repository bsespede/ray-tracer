package ar.edu.itba.grupo2.utils;

import java.awt.Color;

public class RGBColor {

	public float r, g, b;

	public RGBColor() {
		this.r = 0;
		this.g = 0;
		this.b = 0;
	}
	
	public RGBColor(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public Color toColor() {
		int red = (int) (r * 255);
		int green = (int) (g * 255);
		int blue = (int) (b * 255);
		return new Color(Math.min(red, 255), Math.min(green, 255), Math.min(blue, 255));
	}

	public String toString() {
		return "RGBColor [r=" + r + ", g=" + g + ", b=" + b + "]";
	}

	public RGBColor add(RGBColor color) {
		this.r = r + color.r;
		this.g = g + color.g;
		this.b = b + color.b;
		return this;
	}

	public RGBColor div(int d) {
		this.r = r / d;
		this.g = g / d;
		this.b = b / d;
		return this;
	}

	public RGBColor scaleCopy(final float s) {
		return new RGBColor(r * s, g * s, b * s);
	}

	public RGBColor multCopy(final RGBColor c) {
		return new RGBColor(r * c.r, g * c.g, b * c.b);
	}
	
}
