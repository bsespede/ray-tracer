package ar.edu.itba.grupo2.ray;

import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;

public class Ray {

	final public Point3D p;
	final public Vector3D d;
	public float t;
	
	public Ray(Point3D o, Vector3D d) {
		this.p = o;
		this.d = d.normalize();
		this.t = Float.MAX_VALUE;
	}
	
}
