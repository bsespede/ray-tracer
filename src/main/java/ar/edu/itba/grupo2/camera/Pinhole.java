package ar.edu.itba.grupo2.camera;

import ar.edu.itba.grupo2.math.Point2D;
import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.sampler.Sampler;
import ar.edu.itba.grupo2.scene.World;
import ar.edu.itba.grupo2.screen.ViewPlane;
import ar.edu.itba.grupo2.tracer.Tracer;
import ar.edu.itba.grupo2.utils.Material;

public class Pinhole extends Camera{

	private final float d;
	
	public Pinhole(final Point3D eye, final Point3D lookAt, final Vector3D up, final float d) {
		super(eye, lookAt, up);
		this.d = d;
	}

	public void render(World world) {
		ViewPlane vp = world.getViewPlane();
		Sampler sampler = world.getSampler();
		Tracer tracer = world.getTracer();
		Material pixelColor;
		Ray ray;
		Point2D sp, pp;
		
		for (int row = 0; row < vp.hRes; row++) {
			for (int col = 0; col < vp.vRes; col++) {
				pixelColor = new Material();
				for (int i = 0; i < sampler.numSamples(); i++) {
					sp = sampler.sampleUnitSquare();
					pp = new Point2D(vp.s * (col - 0.5f * vp.hRes + sp.x), vp.s * (row - 0.5f * vp.hRes + sp.y));
					ray = new Ray(eye, rayDirection(pp));
					pixelColor.add(tracer.traceRay(ray));
				}
				pixelColor.div(sampler.numSamples());
				vp.drawPixel(row, col, pixelColor);
				
			}
		}
	}
	
	private Vector3D rayDirection(final Point2D p) {
		Vector3D dir = u.scaleCopy(p.x).add(v.scaleCopy(p.y)).sub(w.scaleCopy(d));
		return dir.normalize();
	}

}
