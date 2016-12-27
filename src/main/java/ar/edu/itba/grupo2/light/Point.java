package ar.edu.itba.grupo2.light;

import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.utils.RGBColor;

public class Point implements Light {

	private float ls;
	private RGBColor color;
	private Point3D p;
	
	public Point(final float ls, final RGBColor color, final Point3D p) {
		this.ls = ls;
		this.color = color;
		this.p = p;
	}

	public Vector3D getDirection(final Collision collision) {
		return p.distanceVector(collision.p).normalize();
	}

	public RGBColor L(final Collision collision) {
		float r = p.distance(collision.p);
		return color.scaleCopy(ls / (r * r));
	}
	

}
