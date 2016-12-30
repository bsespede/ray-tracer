package ar.edu.itba.grupo2.material;

import ar.edu.itba.grupo2.BRDF.Lambert;
import ar.edu.itba.grupo2.light.Light;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.scene.World;
import ar.edu.itba.grupo2.utils.RGBColor;

public class Matte implements Material{

	private final Lambert diffuseBRDF;

	public Matte(Lambert diffuseBRDF) {
		this.diffuseBRDF = diffuseBRDF;
	}

	public RGBColor shade(World world, Collision collision) {
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
					L.add(diffuseBRDF.f(collision, wi, wo).multCopy(light.L(collision)).scaleCopy(ndotwi));					
				}
			}
		}

		return L;
	}

	public RGBColor areaLightShade(World world, Collision collision) {
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
					L.add(diffuseBRDF.f(collision, wi, wo).multCopy(light.L(collision)).scaleCopy(ndotwi * light.G(collision) / light.pdf(collision)));					
				}
			}
		}

		return L;
	}

}
