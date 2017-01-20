package ar.edu.itba.grupo2.geometry;

import ar.edu.itba.grupo2.material.Material;
import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.sampler.Sampler;
import ar.edu.itba.grupo2.utils.MathConst;

public class Triangle extends GeometricObject{

	private final Point3D v0, v1, v2;
	private final Vector3D n;
	
	public Triangle(Vector3D n, Point3D v0, Point3D v1, Point3D v2, Material material) {
		super(material);
		this.n = n;
		this.v0 = v0;
		this.v1 = v1;
		this.v2 = v2;
	}

	@Override
	public boolean hit(Ray ray) {
		
		// Solve cramer's determinant
		float a = v0.x - v1.x;
		float b = v0.x - v2.x;
		float c = ray.d.x;
		float d = v0.x - ray.p.x;
		
		float e = v0.y - v1.y;
		float f = v0.y - v2.y;
		float g = ray.d.y;
		float h = v0.y - ray.p.y;
		
		float i = v0.z - v1.z;
		float j = v0.z - v2.z;
		float k = ray.d.z;
		float l = v0.z - ray.p.z;
		
		float m = f * k - g * j;
		float n = h * k - g * l;
		float p = f * l - h * j;
		float q = g * i - e * k;
		float s = e * j - f * i;
		
		float denom = 1f / (a * m + b * q + c * s);
		
		float e1 = d * m - b * n - c * p;
		float beta = e1 * denom;
		
		if (beta < 0) {
			return false;
		}
		
		float r = e * l - h * i;
		float e2 = a * n + d * q + c * r;
		float gamma = e2 * denom;
		
		if (gamma < 0) {
			return false;
		}
		
		if (beta + gamma > 1) {
			return false;
		}
		
		float e3 = a * p - b * r + d * s;
		float t = e3 * denom;
		
		if (t < MathConst.EPSILON) {
			return false;
		}
		
		ray.t = t;
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
