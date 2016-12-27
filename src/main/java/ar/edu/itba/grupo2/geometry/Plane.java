package ar.edu.itba.grupo2.geometry;

import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.utils.RGBColor;
import ar.edu.itba.grupo2.utils.MathConst;

public class Plane extends GeometricObject{

	private final Vector3D n;
	private final Point3D p;
	
	public Plane(final Point3D p, final Vector3D n, final RGBColor color) {
		super(color);
		this.p = p;
		this.n = n.normalize();
	}
	
	@Override
	public boolean hit(final Ray ray) {
		float t = p.distanceVector(ray.p).dot(n) / ray.d.dot(n);
		
		if (t > MathConst.EPSILON) {
			ray.t = t;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Collision calculateCollision(final Ray ray, float t) {
		return new Collision(ray.p.translate(ray.d, t), n, t , this);
	}

}
