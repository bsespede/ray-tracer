package ar.edu.itba.grupo2.light;

import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.utils.Material;

public class Point implements Light {

	private float ls;
	private Material color;
	private Point3D p;
	
	public Point(final float ls, final Material color, final Point3D p) {
		this.ls = ls;
		this.color = color;
		this.p = p;
	}

	public Vector3D getDirection(final Collision collision) {
		return p.distanceVector(collision.p).normalize();
	}

	public Material shade(final Collision collision) {
		return color.scaleCopy(ls / p.distance(collision.p));
	}
	

}
