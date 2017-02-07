package ar.edu.itba.grupo2.geometry;

import ar.edu.itba.grupo2.material.Material;
import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.sampler.Sampler;
import ar.edu.itba.grupo2.utils.MathConst;

public class Disk extends GeometricObject{

	private final Point3D c;
	private final Vector3D n;
	private final float radius;
	
	public Disk(final Point3D c, final Vector3D n, final float radius) {
		this.c = c;
		this.n = n;
		this.radius = radius;
	}

	@Override
	public boolean hit(Ray ray) {
		float t = c.distanceVector(ray.p).dot(n) / ray.d.dot(n);
		
		if (t < MathConst.EPSILON) {
			return false;
		}
		
		Point3D p = ray.p.translate(ray.d, t);
	
		if (c.squaredDistance(p) < radius * radius) {
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public Collision calculateCollision(Ray ray, float t, Material material) {
		return new Collision(ray, ray.p.translate(ray.d, t), n, t, this, material);
	}

	@Override
	public Point3D sample(Sampler sampler) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Vector3D getNormal(Point3D samplePoint) {
		throw new UnsupportedOperationException();
	}

	@Override
	public float pdf(Collision collision) {
		throw new UnsupportedOperationException();
	}	

}
