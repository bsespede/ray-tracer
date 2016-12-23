package ar.edu.itba.grupo2.geometry;

import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.utils.Constant;

public class Plane implements GeometricObject{

	private final Vector3D n;
	private final Point3D p;
	
	public Plane(Point3D p, Vector3D n) {
		this.p = p;
		this.n = n;
	}
	
	public boolean hit(final Ray ray, final Collision collision) {
		float aux = n.dot(ray.d);
		
		if (aux == 0) {
			return false;
		}
		
		float t = ((p.x - ray.p.x) * n.x + (p.y - ray.p.y) * n.y + (p.z - ray.p.z) * n.z) / aux;
		
		if (t > Constant.EPSILON) {
			collision.n = n;
			collision.p = ray.p.translate(ray.d, t);
			collision.t = t;
			return true;
		}
		
		return false;
	}

}
