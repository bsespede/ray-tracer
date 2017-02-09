package ar.edu.itba.grupo2.scene;

import java.util.LinkedList;
import java.util.List;

import ar.edu.itba.grupo2.geometry.AxisAlignedBox;
import ar.edu.itba.grupo2.geometry.GeometricObject;
import ar.edu.itba.grupo2.geometry.Plane;
import ar.edu.itba.grupo2.geometry.Sphere;
import ar.edu.itba.grupo2.light.Ambient;
import ar.edu.itba.grupo2.light.AreaLight;
import ar.edu.itba.grupo2.light.Directional;
import ar.edu.itba.grupo2.light.Light;
import ar.edu.itba.grupo2.light.Point;
import ar.edu.itba.grupo2.material.Emmisive;
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
		final GeometricObject planeLow = new Plane(new Point3D(0, 0, 0), new Vector3D(0, 1, 0));
		objects.add(new Object3D(planeLow, lowerWallMaterial));
		
		final Material upperWallMaterial = new Matte(new Lambert(sampler, 1f, new RGBColor(0, 1, 0)));
		final GeometricObject planeHigh = new Plane(new Point3D(0, 20, 0), new Vector3D(0, 1, 0));
		objects.add(new Object3D(planeHigh, upperWallMaterial));
		
		final Material rightWallMaterial = new Matte(new Lambert(sampler, 1f, new RGBColor(0, 0, 1)));
		final GeometricObject planeRight= new Plane(new Point3D(-10, 0, 0), new Vector3D(1, 0, 0));
		objects.add(new Object3D(planeRight, rightWallMaterial));
		
		final Material leftWallMaterial = new Matte(new Lambert(sampler, 1f, new RGBColor(1, 0, 0)));
		final GeometricObject planeLeft = new Plane(new Point3D(10, 0, 0), new Vector3D(1, 0, 0));
		objects.add(new Object3D(planeLeft, leftWallMaterial));
		
		final Material backWallMaterial = new Matte(new Lambert(sampler, 1f, new RGBColor(1, 0, 1)));
		final GeometricObject backPlane = new Plane(new Point3D(0, 0, -10), new Vector3D(0, 0, 1));
		objects.add(new Object3D(backPlane, backWallMaterial));
		
//		final Material boxMaterial = new Phong(new Lambert(sampler, 0.9f, new RGBColor(1, 0, 0)), new GlossySpecular(sampler, 0.1f, 1.25f));
//		final GeometricObject box = new AxisAlignedBox(new Point3D(0, 0, 0), new Point3D(3, 3, 5));
//		objects.add(new Object3D(box, boxMaterial));
		
//		final Material alMat = new Emmisive(20, new RGBColor(1, 1, 1));
//		final GeometricObject sphereAL = new Sphere(2, new Point3D(0, 20, 20));
//		objects.add(new Object3D(sphereAL, alMat));
		
		final Material sphereMaterial = new Matte(new Lambert(sampler, 0.9f, new RGBColor(1, 0, 0)));
		final GeometricObject sphere = new AxisAlignedBox(new Point3D(-5, 0, 10), new Point3D(0, 10, 15));
		final Object3D sphereInstance = new Object3D(sphere, sphereMaterial);
		objects.add(sphereInstance);
		
		final Material sphereMaterial3 = new Phong(new Lambert(sampler, 0.9f, new RGBColor(0, 1, 1)), new GlossySpecular(sampler, 0.1f, 1.25f));
		final GeometricObject sphere3 = new Sphere(2.5f, new Point3D(5, 5, 12.5f));
		final Object3D sphereInstance3 = new Object3D(sphere3, sphereMaterial3);
		objects.add(sphereInstance3);
				
		final Light directional = new Directional(10f, new RGBColor(1f, 1f, 1f), new Vector3D(1, -1, 0));
		final Light point = new Point(20f, new RGBColor(1f, 1f, 1f), new Point3D(0, 20, 20));
		//final Light area = new AreaLight(sphereAL, (Emmisive) alMat, sampler);
		//addLight(directional);
		addLight(point);
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
