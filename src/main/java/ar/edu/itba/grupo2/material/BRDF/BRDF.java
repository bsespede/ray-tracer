package ar.edu.itba.grupo2.material.BRDF;

import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.sampler.Sampler;
import ar.edu.itba.grupo2.utils.RGBColor;

public abstract class BRDF {

	protected final Sampler sampler;
	
	public BRDF(final Sampler sampler) {
		this.sampler = sampler;
	}
	
	public abstract RGBColor f(final Collision collision, final Vector3D wi, final Vector3D wo);
	
	public abstract RGBColor rho(final Collision collision, final Vector3D wo);
	
}
