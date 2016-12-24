package ar.edu.itba.grupo2.geometry;

import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.utils.Constant;
import ar.edu.itba.grupo2.utils.RGBColor;

public class Sphere extends GeometricObject {
	
	private final float radius;
	private final Point3D p;
	
	public Sphere(final float radius, final Point3D p, final RGBColor color) {
		super(color);
		this.radius = radius;
		this.p = p;
	}

	public Collision hit(final Ray ray) {
		float t;
		Vector3D aux = new Vector3D(ray.p.x - p.x, ray.p.y - p.y, ray.p.z - p.z);
		float a = ray.d.dot(ray.d);
		float b = 2 * aux.dot(ray.d);
		float c = aux.dot(aux) - radius * radius;
		float disc = b * b - 4 * a * c;
		
		if (disc < 0) {
			return null;
		} else {
			float e = (float) Math.sqrt(disc);
			float denom = 2 * a;
			t = ((-1) * b - e) / denom;
			
			if (t > Constant.EPSILON) {
				return new Collision(ray.p.translate(ray.d, t), aux.add(ray.d.scale(t)).scale(1/radius), t, this);
			}

			t = ((-1) * b + e) / denom;
			
			if (t > Constant.EPSILON) {
				return new Collision(ray.p.translate(ray.d, t), aux.add(ray.d.scale(t)).scale(1/radius), t, this);
			}
		}
		return null;
	}

}
