package ar.edu.itba.grupo2.utils;

import java.awt.Color;

public class Material {

	public float r, g, b;

	public Material() {
		this.r = 0;
		this.g = 0;
		this.b = 0;
	}
	
	public Material(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public Color toColor() {
		int red = (int) (r * 255);
		int green = (int) (g * 255);
		int blue = (int) (b * 255);
		return new Color (Math.min(red, 255), Math.min(green, 255), Math.min(blue, 255));
	}

	public String toString() {
		return "RGBColor [r=" + r + ", g=" + g + ", b=" + b + "]";
	}

	public Material add(Material color) {
		this.r = r + color.r;
		this.g = g + color.g;
		this.b = b + color.b;
		return this;
	}

	public Material div(int d) {
		this.r = r / d;
		this.g = g / d;
		this.b = b / d;
		return this;
	}

	public Material scaleCopy(float s) {
		return new Material(r * s, g * s, b * s);
	}
	
}
