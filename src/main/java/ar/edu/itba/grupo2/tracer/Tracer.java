package ar.edu.itba.grupo2.tracer;

import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.utils.RGBColor;

public interface Tracer {

	public RGBColor traceRay(Ray ray);
	
}
