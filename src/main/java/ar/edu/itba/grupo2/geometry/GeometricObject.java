package ar.edu.itba.grupo2.geometry;

import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.utils.Material;

public abstract class GeometricObject {

	protected final Material material;
		
	public GeometricObject(Material color) {
		this.material = color;
	}

	public Material getColor() {
		return material;
	}
	
	public abstract boolean hit(final Ray ray);

	public abstract Collision calculateCollision(final Ray ray, final float t);
	
}
