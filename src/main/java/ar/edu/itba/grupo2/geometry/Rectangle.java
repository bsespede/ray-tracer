package ar.edu.itba.grupo2.geometry;

import ar.edu.itba.grupo2.material.Material;
import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.sampler.Sampler;
import ar.edu.itba.grupo2.utils.MathConst;

public class Rectangle extends GeometricObject{

	private final Point3D p0;
	private final Vector3D a;
	private final Vector3D b;
	private final Vector3D n;
	
	private final float aModule;
	private final float bModule;
	
	public Rectangle(final Point3D p0, final Vector3D a, final Vector3D b, final Vector3D n, final Material material) {
		super(material);
		this.p0 = p0;
		this.a = a;
		this.b = b;
		this.n = n;
		this.aModule = a.module();
		this.bModule = b.module();
	}

	@Override
	public boolean hit(Ray ray) {
		float t = p0.distanceVector(ray.p).dot(n) / ray.d.dot(n);
		
		if (t < MathConst.EPSILON) {
			return false;
		}
		
		Point3D p = ray.p.translate(ray.d, t);
		Vector3D d = p.distanceVector(p0);
		
		float dDotA = d.dot(a);
		
		if (dDotA < 0 || dDotA > aModule) {
			return false;
		}
		
		float dDotB = d.dot(b);
		
		if (dDotB < 0 || dDotB > bModule) {
			return false;
		}
		
		return true;
	}

	@Override
	public Collision calculateCollision(Ray ray, float t) {
		return new Collision(ray, ray.p.translate(ray.d, t), n, t, this);
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
