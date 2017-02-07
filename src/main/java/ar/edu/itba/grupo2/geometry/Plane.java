package ar.edu.itba.grupo2.geometry;

import ar.edu.itba.grupo2.material.Material;
import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.sampler.Sampler;
import ar.edu.itba.grupo2.utils.MathConst;

public class Plane extends GeometricObject{

	private final Vector3D n;
	private final Point3D p;
	
	public Plane(final Point3D p, final Vector3D n) {
		this.p = p;
		this.n = n.normalize();
	}
	
	@Override
	public boolean hit(final Ray ray) {
		float t = p.distanceVector(ray.p).dot(n) / ray.d.dot(n);
		
		if (t > MathConst.EPSILON) {
			ray.t = t;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Collision calculateCollision(final Ray ray, float t, Material material) {
		return new Collision(ray, ray.p.translate(ray.d, t), n, t , this, material);
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
