package ar.edu.itba.grupo2.geometry;

import ar.edu.itba.grupo2.material.Material;
import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.sampler.Sampler;
import ar.edu.itba.grupo2.utils.MathConst;

public class Sphere extends GeometricObject {
	
	private final float radius;
	private final Point3D p;
	private final float invArea;
	
	public Sphere(final float radius, final Point3D p) {
		this.radius = radius;
		this.p = p;
		this.invArea = 1 / (4 * MathConst.PI);
	}

	@Override
	public boolean hit(final Ray ray) {
		final Vector3D aux = new Vector3D(ray.p.x - p.x, ray.p.y - p.y, ray.p.z - p.z);
		final float a = ray.d.dot(ray.d);
		final float b = 2 * aux.dot(ray.d);
		final float c = aux.dot(aux) - radius * radius;
		final float disc = b * b - 4 * a * c;
		float t;
		
		if (disc < 0) {
			return false;
		} else {
			float e = (float) Math.sqrt(disc);
			float denom = 2 * a;
			t = ((-1) * b - e) / denom;
			
			if (t > MathConst.EPSILON) {
				ray.t = t;
				return true;
			}

			t = ((-1) * b + e) / denom;
			
			if (t > MathConst.EPSILON) {
				ray.t = t;
				return true;
			}
		}
		
		return false;
	}

	@Override
	public Collision calculateCollision(final Ray ray, final float t, final Material material) {
		final Vector3D aux = new Vector3D(ray.p.x - p.x, ray.p.y - p.y, ray.p.z - p.z);
		return new Collision(ray, ray.p.translate(ray.d, t), aux.add(ray.d.scale(t)).scale(1 / radius), t, this, material);
	}

	@Override
	public Point3D sample(Sampler sampler) {
		return sampler.sampleUnitSphere().scaleCopy(radius).add(p);
	}

	@Override
	public Vector3D getNormal(Point3D point) {
		return point.distanceVector(p).normalize();
	}

	@Override
	public float pdf(Collision collision) {
		return invArea;
	}

}
