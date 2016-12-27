package ar.edu.itba.grupo2.geometry;

import ar.edu.itba.grupo2.material.Material;
import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.utils.RGBColor;

public abstract class GeometricObject {

	protected final Material material;
		
	public GeometricObject(Material material) {
		this.material = material;
	}
	
	public abstract boolean hit(final Ray ray);

	public abstract Collision calculateCollision(final Ray ray, final float t);

	public Material getMaterial() {
		return material;
	}
	
}
