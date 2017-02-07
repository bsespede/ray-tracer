package ar.edu.itba.grupo2.scene;

import java.util.LinkedList;
import java.util.List;

import ar.edu.itba.grupo2.geometry.GeometricObject;
import ar.edu.itba.grupo2.geometry.Plane;
import ar.edu.itba.grupo2.geometry.Sphere;
import ar.edu.itba.grupo2.light.Ambient;
import ar.edu.itba.grupo2.light.Light;
import ar.edu.itba.grupo2.light.Point;
import ar.edu.itba.grupo2.material.Material;
import ar.edu.itba.grupo2.material.Matte;
import ar.edu.itba.grupo2.material.Phong;
import ar.edu.itba.grupo2.material.BRDF.GlossySpecular;
import ar.edu.itba.grupo2.material.BRDF.Lambert;
import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.object.Object3D;
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

	private final static boolean SHADOWS_ON = false;
	private final ViewPlane vp;
	private final Sampler sampler;
	private final Tracer tracer;
	private final List<Object3D> objects;
	private final Light ambientLight;
	private final List<Light> lights;
	private final RGBColor background;
	
	public World(final int hRes, final int vRes) {
		this.sampler = new Regular(1);
		this.vp = new ViewPlane(hRes, vRes, 1);
		this.background = new RGBColor();
		this.objects = new LinkedList<Object3D>();
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
		this.objects = new LinkedList<Object3D>();
		this.tracer = new RayCast(this);
		this.lights = new LinkedList<Light>();
		this.ambientLight = ambientLight;
	}
	
	public Collision hitObjects(final Ray ray) {
		Object3D collisionObject = null;	
		float t = 0;
		float minT = Float.MAX_VALUE;
		
		for (Object3D object: objects) {
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
		
		for (Object3D object: objects) {
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
		final GeometricObject plane = new Plane(new Point3D(0, 0, 0), new Vector3D(0, 1, 0));
		objects.add(new Object3D(plane, lowerWallMaterial));
		
//		final Material boxMaterial = new Phong(new Lambert(sampler, 0.9f, new RGBColor(1, 0, 0)), new GlossySpecular(sampler, 0.1f, 1.25f));
//		final GeometricObject box = new AxisAlignedBox(new Point3D(0, 0, 0), new Point3D(3, 3, 5));
//		objects.add(new Object3D(box, boxMaterial));
		
//		final Material sphere2Material = new Emmisive(100, new RGBColor(1, 1, 1));
//		final GeometricObject sphere2 = new Sphere(3, new Point3D(6, 8, 1));
//		objects.add(new Object3D(sphere2, sphere2Material));
		
		final Material sphereMaterial = new Phong(new Lambert(sampler, 0.9f, new RGBColor(1, 0, 0)), new GlossySpecular(sampler, 0.1f, 1.25f));
		final GeometricObject sphere = new Sphere(3, new Point3D(0, 0, 0));
		final Object3D sphereInstance = new Object3D(sphere, sphereMaterial);
		sphereInstance.scale(3, 1, 1);
		//sphereInstance.zRotate(3.14f/2);
		sphereInstance.translate(10, 5, 0);
		objects.add(sphereInstance);
				
		final Light directional = new Point(10f, new RGBColor(1f, 1f, 1f), new Point3D(4, 4, 4));
		//final Light area = new AreaLight(sphere2, (Emmisive) sphere2Material, sampler);
		addLight(directional);
		//addLight(area);
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
