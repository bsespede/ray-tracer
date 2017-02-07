package ar.edu.itba.grupo2.ray;

import ar.edu.itba.grupo2.geometry.GeometricObject;
import ar.edu.itba.grupo2.material.Material;
import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;

public class Collision {

	public Point3D p;
	public Vector3D n;
	public float t;
	public Ray ray;
	public GeometricObject object;
	public Material material;
	
	public Collision(final Ray ray, final Point3D p, final Vector3D n, final float t, final GeometricObject object, final Material material) {
		this.ray = ray;
		this.p = p;
		this.n = n;
		this.t = t;
		this.object = object;
		this.material = material;
	}
	
}
