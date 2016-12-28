package ar.edu.itba.grupo2.BRDF;

import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.sampler.Sampler;
import ar.edu.itba.grupo2.utils.RGBColor;

public class GlossySpecular extends BRDF{

	private final float ks;
	private final float e;
	
	public GlossySpecular(final Sampler sampler, final float ks, final float e) {
		super(sampler);
		this.ks = ks;
		this.e = e;
	}

	@Override
	public RGBColor f(Collision c, Vector3D wi, Vector3D wo) {
		RGBColor L = new RGBColor();
		float ndotwi = c.n.dot(wi);
		Vector3D r = c.n.scaleCopy(2 * ndotwi).add(wi.scaleCopy(-1));
		float rdotwo = r.dot(wo);
		
		if (rdotwo > 0) {
			float sc = ks * (float) Math.pow(rdotwo, e);
			L = new RGBColor(sc, sc, sc);			
		}
		
		return L;
	}

	@Override
	public RGBColor rho(Collision c, Vector3D wo) {
		return new RGBColor();
	}

	
}
