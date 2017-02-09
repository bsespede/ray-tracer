package ar.edu.itba.grupo2.camera;

import ar.edu.itba.grupo2.math.Point2D;
import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.sampler.Sampler;
import ar.edu.itba.grupo2.scene.World;
import ar.edu.itba.grupo2.screen.ViewPlane;
import ar.edu.itba.grupo2.tracer.Tracer;
import ar.edu.itba.grupo2.utils.RGBColor;

public class ThinLens extends Camera{
	
	private final float d;
	private final float f;
	private final float lensRadius;

	public ThinLens(final Point3D eye, final Point3D lookAt, final Vector3D up, final float d, final float f, final float lensRadius) {
		super(eye, lookAt, up);
		this.d = d;
		this.f = f;
		this.lensRadius = lensRadius;
	}

	public void render(World world) {
		ViewPlane vp = world.getViewPlane();
		Sampler sampler = world.getSampler();
		Tracer tracer = world.getTracer();
		RGBColor pixelColor;
		Ray ray;
		Point2D sp, pp, dp, lp;
		
		for (int row = 0; row < vp.hRes; row++) {
			for (int col = 0; col < vp.vRes; col++) {
				pixelColor = new RGBColor();
				for (int i = 0; i < sampler.numSamples(); i++) {
					sp = sampler.sampleUnitSquare();
					pp = new Point2D(vp.s * (col - 0.5f * vp.hRes + sp.x), vp.s * (row - 0.5f * vp.hRes + sp.y));
					
					dp = sampler.sampleUnitDisk();
					lp = dp.scaleCopy(lensRadius);
					
					Vector3D origin = u.scaleCopy(lp.x).add(v.scaleCopy(lp.y)).add(eye);					
					ray = new Ray(new Point3D(origin.x, origin.y, origin.z), rayDirection(pp, lp));
					
					pixelColor.add(tracer.traceRay(ray));
				}
				pixelColor.div(sampler.numSamples());
				vp.drawPixel(row, col, pixelColor);
				
			}
		}
	}

	private Vector3D rayDirection(final Point2D pixel, final Point2D lens) {
		Point2D p = new Point2D(pixel.x * f / d, pixel.y * f / d);
		
		return u.scaleCopy(p.x - lens.x).add(v.scaleCopy(p.y - lens.y)).sub(w.scaleCopy(f)).normalize();
	}
}
