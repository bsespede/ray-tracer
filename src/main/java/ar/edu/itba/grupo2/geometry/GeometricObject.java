package ar.edu.itba.grupo2.geometry;

import ar.edu.itba.grupo2.material.Material;
import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.sampler.Sampler;

public abstract class GeometricObject {
	
	public abstract boolean hit(final Ray ray);

	public abstract Collision calculateCollision(final Ray ray, final float t, final Material material);
	
	// Area light stuff
	public abstract Point3D sample(Sampler sampler);

	public abstract Vector3D getNormal(Point3D samplePoint);

	public abstract float pdf(Collision collision);

}
