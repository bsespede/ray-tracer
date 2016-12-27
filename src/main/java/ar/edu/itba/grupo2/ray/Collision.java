package ar.edu.itba.grupo2.ray;

import ar.edu.itba.grupo2.geometry.GeometricObject;
import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;

public class Collision {

	public final Point3D p;
	public final Vector3D n;
	public final float t;
	public final Ray ray;
	public final GeometricObject object;
	
	public Collision(final Ray ray, final Point3D p, final Vector3D n, final float t, final GeometricObject object) {
		this.ray = ray;
		this.p = p;
		this.n = n;
		this.t = t;
		this.object = object;
	}

}
