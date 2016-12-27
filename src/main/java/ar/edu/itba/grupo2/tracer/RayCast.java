package ar.edu.itba.grupo2.tracer;

import ar.edu.itba.grupo2.material.Material;
import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.scene.World;
import ar.edu.itba.grupo2.utils.RGBColor;

public class RayCast implements Tracer {

	private final World world;
	
	public RayCast(final World world) {
		this.world = world;
	}
	
	public RGBColor traceRay(final Ray ray) {
		Collision collision = world.hitObjects(ray);
		
		if (collision != null) {
			final Material material = collision.object.getMaterial();
			return material.shade(world, collision);
			//return new RGBColor(collision.object.getColor().r / collision.t, collision.object.getColor().g / collision.t, collision.object.getColor().b / collision.t);
			//return collision.object.getColor();
		} else {
			return world.getBackground();
		}
	}
	
}
