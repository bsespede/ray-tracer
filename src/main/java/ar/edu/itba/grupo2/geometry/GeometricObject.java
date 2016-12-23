package ar.edu.itba.grupo2.geometry;

import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.utils.RGBColor;

public interface GeometricObject {

	public Collision hit(final Ray ray);
	public RGBColor getColor();
	
}
