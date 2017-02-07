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

	public Ray(Ray ray) {
		this.p = ray.p;
		this.d = ray.d;
		this.t = ray.t;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ray other = (Ray) obj;
		if (d == null) {
			if (other.d != null)
				return false;
		} else if (!d.equals(other.d))
			return false;
		if (p == null) {
			if (other.p != null)
				return false;
		} else if (!p.equals(other.p))
			return false;
		if (Float.floatToIntBits(t) != Float.floatToIntBits(other.t))
			return false;
		return true;
	}
	
	
}
