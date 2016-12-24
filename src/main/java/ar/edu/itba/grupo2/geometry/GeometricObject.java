package ar.edu.itba.grupo2.geometry;

import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.utils.RGBColor;

public abstract class GeometricObject {

	protected final RGBColor color;
		
	public GeometricObject(RGBColor color) {
		this.color = color;
	}

	public RGBColor getColor() {
		return color;
	}
	
	public abstract Collision hit(final Ray ray);
	
}
