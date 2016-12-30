package ar.edu.itba.grupo2.material;

import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.scene.World;
import ar.edu.itba.grupo2.utils.RGBColor;

public class Emmisive implements Material{
	
	private final float ls;
	private final RGBColor color;
	
	public Emmisive(final float ls, final RGBColor color) {
		this.ls = ls;
		this.color = color;
	}

	public RGBColor shade(final World world, final Collision collision) {
		return new RGBColor(0, 0, 0);
	}

	public RGBColor areaLightShade(final World world, final Collision collision) {
		if (collision.n.dot(collision.ray.d) * (-1) > 0) {
			return color.scaleCopy(ls);
		} else {
			return new RGBColor(0, 0, 0);
		}
	}

}
