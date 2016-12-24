package ar.edu.itba.grupo2.scene;

import java.util.LinkedList;
import java.util.List;

import ar.edu.itba.grupo2.geometry.GeometricObject;
import ar.edu.itba.grupo2.geometry.Plane;
import ar.edu.itba.grupo2.geometry.Sphere;
import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.sampler.MultiJittered;
import ar.edu.itba.grupo2.sampler.Regular;
import ar.edu.itba.grupo2.sampler.Sampler;
import ar.edu.itba.grupo2.screen.ViewPlane;
import ar.edu.itba.grupo2.tracer.RayTracer;
import ar.edu.itba.grupo2.tracer.Tracer;
import ar.edu.itba.grupo2.utils.RGBColor;

public class World {

	private final ViewPlane vp;
	private final Sampler sampler;
	private final Tracer tracer;
	private final List<GeometricObject> objects;
	private final RGBColor background;
	
	public World(final int hRes, final int vRes) {
		this.sampler = new Regular(1);
		this.vp = new ViewPlane(hRes, vRes, 1);
		this.background = new RGBColor();
		this.objects = new LinkedList<GeometricObject>();
		this.tracer = new RayTracer(this);
	}
	
	public World(final int hRes, final int vRes, final float s, final int samples, final RGBColor background) {
		if (samples > 1) {
			this.sampler = new MultiJittered(samples);
		} else {
			this.sampler = new Regular(1);			
		}
		this.vp = new ViewPlane(hRes, vRes, s);
		this.background = background;
		this.objects = new LinkedList<GeometricObject>();
		this.tracer = new RayTracer(this);
	}
	
	public Collision hitObjects(final Ray ray) {
		Collision auxCollision, closestCollision = null;
		float t = Float.MAX_VALUE;
		
		for (GeometricObject object: objects) {
			if ((auxCollision = object.hit(ray)) != null && auxCollision.t < t) {
				closestCollision = auxCollision;
			}
		}
		
		return closestCollision;
	}
	
	public void build() {
		objects.add(new Plane(new Point3D(0, 0, 0), new Vector3D(0,0, 1), new RGBColor(1, 1, 0)));
		objects.add(new Sphere(100, new Point3D(0, 0, 0), new RGBColor(1, 0, 0)));
	}
	
	public RGBColor getBackground() {
		return background;
	}
	
	public ViewPlane getViewPlane() {
		return vp;
	}

	public Sampler getSampler() {
		return sampler;
	}
	
	public Tracer getTracer() {
		return tracer;
	}
	
}
