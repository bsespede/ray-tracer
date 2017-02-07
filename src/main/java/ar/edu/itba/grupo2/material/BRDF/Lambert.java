package ar.edu.itba.grupo2.material.BRDF;

import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.sampler.Sampler;
import ar.edu.itba.grupo2.utils.MathConst;
import ar.edu.itba.grupo2.utils.RGBColor;

public class Lambert extends BRDF{

	private final float kd;
	private final RGBColor color;

	public Lambert(final Sampler sampler, final float kd, final RGBColor color) {
		super(sampler);
		this.kd = kd;
		this.color = color;
	}

	@Override
	public RGBColor f(final Collision collision, final Vector3D wi, final Vector3D wo) {
		return color.scaleCopy(kd * MathConst.INV_PI);
	}

	@Override
	public RGBColor rho(final Collision collision, final Vector3D wo) {
		return color.scaleCopy(kd);
	}

}
