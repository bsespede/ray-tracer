package ar.edu.itba.grupo2.light;

import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.utils.RGBColor;

public class Directional implements Light {

	private float ls;
	private RGBColor color;
	private Vector3D d;
	
	public Directional(final float ls, final RGBColor color, final Vector3D d) {
		this.ls = ls;
		this.color = color;
		this.d = d;
	}

	public Vector3D getDirection(final Collision collision) {
		return d;
	}

	public RGBColor L(final Collision collision) {
		return color.scaleCopy(ls);
	}

	public float getLightDistance(Point3D p, Ray ray) {
		return Float.MAX_VALUE;
	}

	public float G(Collision collision) {
		return 1;
	}

	public float pdf(Collision collision) {
		return 1;
	}	

}
