package ar.edu.itba.grupo2.geometry;

import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.utils.RGBColor;

public abstract class GeometricObject {

	protected final RGBColor material;
		
	public GeometricObject(RGBColor color) {
		this.material = color;
	}

	public RGBColor getColor() {
		return material;
	}
	
	public abstract boolean hit(final Ray ray);

	public abstract Collision calculateCollision(final Ray ray, final float t);
	
}
