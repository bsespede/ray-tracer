package ar.edu.itba.grupo2.scene;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import ar.edu.itba.grupo2.geometry.GeometricObject;
import ar.edu.itba.grupo2.geometry.Sphere;
import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.screen.ViewPlane;

public class World {

	private final ViewPlane vp;
	private final Color background = Color.WHITE;
	private final List<GeometricObject> objects;
	private final Tracer tracer;
	
	public World(final int hRes, final int vRes, final Color background) {
		this.vp = new ViewPlane(hRes, vRes);
		this.background = background;
		this.objects = new LinkedList<GeometricObject>();
	}
	
	public void render() {
		Color pixelColor;
		Ray ray;
		float z = 100;
		float x, y;
		
		for (int row = 0; row < vp.hRes; row++) {
			for (int col = 0; col < vp.vRes; col++) {
				x = vp.s * (col - 0.5f * (vp.hRes - 1));
				x = vp.s * (row - 0.5f * (vp.vRes - 1));
				pixelColor = tracer.traceRay(new Ray(new Point3D(x, y, z), new Vector3D(0, 0, -1)));
			}
		}
	}
	
	public void hitObjects(final Ray ray) {
		final Collision collision;
		collision.t = Float.MAX_VALUE;
		
		for (GeometricObject object: objects) {
			if (object.hit(ray, collision) && t)
		}
		
	}
	
	public void build() {
		objects.add(new Sphere(1, new Point3D(0, 0, 0)));
	}
	
}
