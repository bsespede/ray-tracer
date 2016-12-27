package ar.edu.itba.grupo2.tracer;

import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.scene.World;
import ar.edu.itba.grupo2.utils.Material;

public class RayTracer implements Tracer {

	private final World world;
	
	public RayTracer(final World world) {
		this.world = world;
	}
	
	public Material traceRay(final Ray ray) {
		Collision collision = world.hitObjects(ray);
		
		if (collision != null) {
			return new Material(collision.object.getColor().r / collision.t, collision.object.getColor().g / collision.t, collision.object.getColor().b / collision.t);
			//return collision.object.getColor();
		} else {
			return world.getBackground();
		}
	}
	
}
