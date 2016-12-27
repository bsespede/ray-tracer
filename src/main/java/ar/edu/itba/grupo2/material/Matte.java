package ar.edu.itba.grupo2.material;

import ar.edu.itba.grupo2.BRDF.Lambert;
import ar.edu.itba.grupo2.light.Light;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.ray.Collision;
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
			float aux = collision.n.dot(wi);
			if (aux > 0) {
				L.add(diffuseBRDF.f(collision, wo, wi).multCopy(light.L(collision)).scaleCopy(aux));			
			}
		}
		
		return L;
	}	
	
}
