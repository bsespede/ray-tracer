package ar.edu.itba.grupo2.scene;

import java.util.LinkedList;
import java.util.List;

import ar.edu.itba.grupo2.BRDF.GlossySpecular;
import ar.edu.itba.grupo2.BRDF.Lambert;
import ar.edu.itba.grupo2.geometry.GeometricObject;
import ar.edu.itba.grupo2.geometry.Plane;
import ar.edu.itba.grupo2.geometry.Sphere;
import ar.edu.itba.grupo2.light.Ambient;
import ar.edu.itba.grupo2.light.AreaLight;
import ar.edu.itba.grupo2.light.Directional;
import ar.edu.itba.grupo2.light.Light;
import ar.edu.itba.grupo2.material.Emmisive;
import ar.edu.itba.grupo2.material.Material;
import ar.edu.itba.grupo2.material.Matte;
import ar.edu.itba.grupo2.material.Phong;
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

	private final static boolean SHADOWS_ON = true;
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
	
	public boolean hitObjectsForShadow(final Light light, final Collision collision, final Ray ray) {
		float distance = light.getLightDistance(collision.p, ray);
		
		for (GeometricObject object: objects) {
			if (!object.hasEmmisiveMaterial() && object.hit(ray) && ray.t < distance) {
				return true;
			}
		}
		
		return false;
	}
	
	public void addLight(final Light light) {
		lights.add(light);
	}
	
	public void build() {
		
		final Material lowerWallMaterial = new Matte(new Lambert(sampler, 1f, new RGBColor(1, 1, 1)));
		final GeometricObject plane = new Plane(new Point3D(0, 0, 0), new Vector3D(0, 1, 0), lowerWallMaterial);
		objects.add(plane);
		
		final Material sphereMaterial = new Phong(new Lambert(sampler, 0.9f, new RGBColor(1, 1, 1)), new GlossySpecular(sampler, 0.1f, 1.25f));
		final GeometricObject sphere = new Sphere(3, new Point3D(0, 3, 0), sphereMaterial);
		objects.add(sphere);
		
		final Material sphere2Material = new Emmisive(100, new RGBColor(1, 1, 1));
		final GeometricObject sphere2 = new Sphere(3, new Point3D(6, 8, 1), sphere2Material);
		objects.add(sphere2);
				
		final Light directional = new Directional(1f, new RGBColor(1f, 0f, 0f), new Vector3D(1, 1, 1));
		final Light area = new AreaLight(sphere2, (Emmisive) sphere2Material, sampler);
		//addLight(directional);
		addLight(area);
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
	
	public boolean shadowsOn() {
		return SHADOWS_ON;
	}

}
