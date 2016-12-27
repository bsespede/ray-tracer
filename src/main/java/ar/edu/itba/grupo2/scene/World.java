package ar.edu.itba.grupo2.scene;

import java.util.LinkedList;
import java.util.List;

import ar.edu.itba.grupo2.BRDF.Lambert;
import ar.edu.itba.grupo2.geometry.GeometricObject;
import ar.edu.itba.grupo2.geometry.Plane;
import ar.edu.itba.grupo2.geometry.Sphere;
import ar.edu.itba.grupo2.light.Ambient;
import ar.edu.itba.grupo2.light.Directional;
import ar.edu.itba.grupo2.light.Light;
import ar.edu.itba.grupo2.light.Point;
import ar.edu.itba.grupo2.material.Material;
import ar.edu.itba.grupo2.material.Matte;
import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.sampler.MultiJittered;
import ar.edu.itba.grupo2.sampler.Regular;
import ar.edu.itba.grupo2.sampler.Sampler;
import ar.edu.itba.grupo2.screen.ViewPlane;
import ar.edu.itba.grupo2.tracer.RayCast;
import ar.edu.itba.grupo2.tracer.Tracer;
import ar.edu.itba.grupo2.utils.RGBColor;

public class World {

	private final ViewPlane vp;
	private final Sampler sampler;
	private final Tracer tracer;
	private final List<GeometricObject> objects;
	private final Light ambientLight;
	private final List<Light> lights;
	private final RGBColor background;
	
	public World(final int hRes, final int vRes) {
		this.sampler = new Regular(1);
		this.vp = new ViewPlane(hRes, vRes, 1);
		this.background = new RGBColor();
		this.objects = new LinkedList<GeometricObject>();
		this.tracer = new RayCast(this);
		this.lights = new LinkedList<Light>();
		this.ambientLight = new Ambient(new RGBColor(1, 1, 1), 1f);
	}
	
	public World(final int hRes, final int vRes, final float s, final int samples, final Light ambientLight, final RGBColor background) {
		if (samples > 1) {
			this.sampler = new MultiJittered(samples);
		} else {
			this.sampler = new Regular(1);			
		}
		this.vp = new ViewPlane(hRes, vRes, s);
		this.background = background;
		this.objects = new LinkedList<GeometricObject>();
		this.tracer = new RayCast(this);
		this.lights = new LinkedList<Light>();
		this.ambientLight = ambientLight;
	}
	
	public Collision hitObjects(final Ray ray) {
		GeometricObject collisionObject = null;	
		float t = 0;
		float minT = Float.MAX_VALUE;
		
		for (GeometricObject object: objects) {
			if (object.hit(ray) && ray.t < minT) {
				collisionObject = object;
				t = ray.t;
				minT = ray.t;
			}
		}
		
		if (collisionObject == null) {
			return null;
		} else {
			return collisionObject.calculateCollision(ray, t);			
		}
	}
	
	public void addLight(final Light light) {
		lights.add(light);
	}
	
	public void build() {
		
		// Add geometries with materials 
		//final Lambert ambientBRDF = new Lambert(sampler, 1f, new RGBColor(0.1f, 0.1f, 0.1f));
		//final Material upperWallMaterial = new Matte(new Lambert(sampler, 1f, new RGBColor(1, 0, 1)));
		final Material lowerWallMaterial = new Matte(new Lambert(sampler, 1f, new RGBColor(0, 1, 1)));
		//final Material leftmostWallMaterial = new Matte(new Lambert(sampler, 1f, new RGBColor(1, 0, 0)));
		//final Material rightmostWallMaterial = new Matte(new Lambert(sampler, 1f, new RGBColor(0, 1, 0)));
		//final Material backgroundWallMaterial = new Matte(new Lambert(sampler, 1f, new RGBColor(1, 1, 0)));
		final Material sphereMaterial = new Matte(new Lambert(sampler, 1f, new RGBColor(0, 0, 1)));
		//objects.add(new Plane(new Point3D(2, 0, 0), new Vector3D(1, 0, 0), rightmostWallMaterial));
		//objects.add(new Plane(new Point3D(-2, 0, 0), new Vector3D(1, 0, 0), leftmostWallMaterial));
		//objects.add(new Plane(new Point3D(0, 2, 0), new Vector3D(0, 1, 0), upperWallMaterial));
		objects.add(new Plane(new Point3D(0, -2, 0), new Vector3D(0, 1, 0), lowerWallMaterial));
		//objects.add(new Plane(new Point3D(0, 0, -2), new Vector3D(0, 0, 1), backgroundWallMaterial));
		objects.add(new Sphere(7, new Point3D(0, 0, 0), sphereMaterial));
		
		// Add lights
		final Light directional = new Directional(2f, new RGBColor(1f, 1f, 1f), new Vector3D(1, 1, 1));
		//final Light point = new Point(10f, new RGBColor(1, 1, 1), new Point3D(7, 7, 10));
		addLight(directional);
		//addLight(point);
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

	public List<Light> getLights() {
		return lights;
	}

	public Light getAmbientLight() {
		return ambientLight;
	}
	
}
