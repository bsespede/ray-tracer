package ar.edu.itba.grupo2.geometry;

import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.ray.Ray;

public interface GeometricObject {

	public boolean hit(final Ray ray, final Collision collision);
	
}
