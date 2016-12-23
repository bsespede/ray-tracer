package ar.edu.itba.grupo2.geometry;

import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.utils.Constant;

public class Sphere implements GeometricObject {
	
	private final float radius;
	private final Point3D p;
	
	public Sphere(final float r, final Point3D p) {
		this.radius = r;
		this.p = p;
	}

	public boolean hit(final Ray ray, final Collision collision) {
		float t;
		Vector3D aux = new Vector3D(ray.p.x - p.x, ray.p.y - p.y, ray.p.z - p.z);
		float a = ray.d.dot(ray.d);
		float b = 2 * aux.dot(ray.d);
		float c = aux.dot(aux) - radius * radius;
		float disc = b * b - 4 * a * c;
		
		if (disc < 0) {
			return false;
		} else {
			float e = (float) Math.sqrt(disc);
			float denom = 2 * a;
			t = ((-1) * b - e) / denom;
			
			if (t > Constant.EPSILON) {
				collision.t = t;
				collision.n = aux.add(ray.d.scale(t)).scale(1/radius);
				collision.p = ray.p.translate(ray.d, t);
				return true;
			}

			t = ((-1) * b + e) / denom;
			
			if (t > Constant.EPSILON) {
				collision.t = t;
				collision.n = aux.add(ray.d.scale(t)).scale(1/radius);
				collision.p = ray.p.translate(ray.d, t);
				return true;
			}
		}
		return false;
	}

}
