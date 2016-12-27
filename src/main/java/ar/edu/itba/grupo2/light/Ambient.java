package ar.edu.itba.grupo2.light;

import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.utils.RGBColor;

public class Ambient implements Light{

	private float ls;
	private RGBColor color;
	
	public Ambient(final RGBColor color, final float ls) {
		this.color = color;
		this.ls = ls;
	}
	
	public Vector3D getDirection(final Collision collision) {
		return new Vector3D(0, 0, 0);
	}

	public RGBColor shade(final Collision collision) {
		return color.scaleCopy(ls);
	}

}
