package ar.edu.itba.grupo2.acceleration.boundingvolume;

import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.utils.MathConst;

public class BBox {

	public final Point3D p0;
	public final Point3D p1;
	
	public BBox(Point3D p0, Point3D p1) {
		this.p0 = p0;
		this.p1 = p1;
	}

	public static BBox surround (BBox b1, BBox b2) {
		Point3D min = new Point3D(Math.min(b1.p0.x, b2.p0.x), Math.min(b1.p0.y, b2.p0.y), Math.min(b1.p0.z, b2.p0.z));
		Point3D max = new Point3D(Math.max(b1.p1.x, b2.p1.x), Math.max(b1.p1.y, b2.p1.y), Math.max(b1.p1.z, b2.p1.z));
		return new BBox(min, max);
	}
	
	public boolean hit(Ray ray) {
		float txMin, tyMin, tzMin;
		float txMax, tyMax, tzMax;
		
		// Encontrar los "slabs" de interseccion		
		float a = 1f / ray.d.x;		
		if (a >= 0) {
			txMin = (p0.x - ray.p.x) * a;
			txMax = (p1.x - ray.p.x) * a;
		} else {
			txMin = (p1.x - ray.p.x) * a;
			txMax = (p0.x - ray.p.x) * a;
		}
		
		float b = 1f / ray.d.y;		
		if (b >= 0) {
			tyMin = (p0.y - ray.p.y) * b;
			tyMax = (p1.y - ray.p.y) * b;
		} else {
			tyMin = (p1.y - ray.p.y) * b;
			tyMax = (p0.y - ray.p.y) * b;
		}
		
		float c = 1f / ray.d.z;		
		if (c >= 0) {
			tzMin = (p0.z - ray.p.z) * c;
			tzMax = (p1.z - ray.p.z) * c;
		} else {
			tzMin = (p1.z - ray.p.z) * c;
			tzMax = (p0.z - ray.p.z) * c;
		}
		
		float t0, t1;
		
		if (txMin > tyMin) {
			t0 = txMin;
		} else {
			t0 = tyMin;
		}
		
		if (tzMin > t0) {
			t0 = tzMin;
		}
		
		// Ahora el t más chico que sale		
		if (txMax < tyMax) {
			t1 = txMax;
		} else {
			t1 = tyMax;
		}
		
		if (tzMax < t1) {
			t1 = tzMax;
		}
		
		return (t0 < t1 && t1 > MathConst.EPSILON);		
		
	}
}
