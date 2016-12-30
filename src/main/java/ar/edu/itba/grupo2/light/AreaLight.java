package ar.edu.itba.grupo2.light;

import ar.edu.itba.grupo2.geometry.GeometricObject;
import ar.edu.itba.grupo2.material.Emmisive;
import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.sampler.Sampler;
import ar.edu.itba.grupo2.utils.RGBColor;

public class AreaLight implements Light{

	private final Sampler sampler;
	private final GeometricObject object;
	private final Emmisive material;
	private Point3D samplePoint;
	private Vector3D samplePointNormal;
	private Vector3D wi;
	
	public AreaLight(GeometricObject object, Emmisive material, Sampler sampler) {
		this.object = object;
		this.material = material;
		this.sampler = sampler;
	}

	public Vector3D getDirection(Collision collision) {
		samplePoint = object.sample(sampler);
		samplePointNormal = object.getNormal(samplePoint);
		wi = samplePoint.distanceVector(collision.p);
		return wi.normalize();		
	}

	public RGBColor L(Collision collision) {
		float ndotd = samplePointNormal.dot(wi) * (-1);
		
		if (ndotd > 0) {
			return material.getLe(collision);
		} else {
			return new RGBColor();
		}
	}

	public float getLightDistance(Point3D p, Ray ray) {
		return samplePoint.distanceVector(ray.p).dot(ray.d);
	}

	public float G(Collision collision) {
		float ndotd = samplePointNormal.dot(wi) * (-1);
		float d = samplePoint.distance(collision.p);
		return ndotd / (d * d);
	}

	public float pdf(Collision collision) {
		return object.pdf(collision);
	}

}
