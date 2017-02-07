package ar.edu.itba.grupo2.material;

import ar.edu.itba.grupo2.light.Light;
import ar.edu.itba.grupo2.material.BRDF.GlossySpecular;
import ar.edu.itba.grupo2.material.BRDF.Lambert;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.scene.World;
import ar.edu.itba.grupo2.utils.RGBColor;

public class Phong implements Material{

	private final Lambert diffuseBRDF;
	private final GlossySpecular specularBRDF;	
	
	public Phong(final Lambert diffuseBRDF, final GlossySpecular specularBRDF) {
		this.diffuseBRDF = diffuseBRDF;
		this.specularBRDF = specularBRDF;
	}

	public RGBColor shade(final World world, final Collision collision) {
		Vector3D wo = collision.ray.d.scaleCopy(-1);
		RGBColor L = diffuseBRDF.rho(collision, wo).multCopy(world.getAmbientLight().L(collision));
		
		for (Light light: world.getLights()) {
			Vector3D wi = light.getDirection(collision);
			float ndotwi = collision.n.dot(wi);
			if (ndotwi > 0) {
				boolean inShadow = false;
				
				if (world.shadowsOn()) {
					Ray shadowRay = new Ray(collision.p, wi);
					inShadow = world.hitObjectsForShadow(light, collision, shadowRay);
				}
				
				if (!inShadow) {
					RGBColor combined = diffuseBRDF.f(collision, wo, wi).add(specularBRDF.f(collision, wi, wo));
					L.add(combined.multCopy(light.L(collision)).scaleCopy(ndotwi * light.G(collision) / light.pdf(collision)));						
				}				
			}
		}
		
		return L;
	}

	public boolean isEmmisive() {
		return false;
	}

}
