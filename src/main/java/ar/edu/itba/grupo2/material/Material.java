package ar.edu.itba.grupo2.material;

import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.scene.World;
import ar.edu.itba.grupo2.utils.RGBColor;

public interface Material {

	public RGBColor shade(World world, Collision collision);

}
