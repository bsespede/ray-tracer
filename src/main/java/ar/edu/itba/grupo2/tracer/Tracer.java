package ar.edu.itba.grupo2.tracer;

import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.utils.Material;

public interface Tracer {

	public Material traceRay(Ray ray);
	
}
