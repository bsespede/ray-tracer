package ar.edu.itba.grupo2.camera;

import ar.edu.itba.grupo2.math.Point2D;
import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.sampler.Sampler;
import ar.edu.itba.grupo2.scene.World;
import ar.edu.itba.grupo2.screen.ViewPlane;
import ar.edu.itba.grupo2.tracer.Tracer;
import ar.edu.itba.grupo2.utils.MathConst;
import ar.edu.itba.grupo2.utils.RGBColor;

public class FishEye extends Camera {

	private float psiMax;
	
	public FishEye(final Point3D eye, final Point3D lookAt, final Vector3D up, final float psiMax) {
		super(eye, lookAt, up);
		this.psiMax = psiMax;
	}

	@Override
	public void render(World world) {
		ViewPlane vp = world.getViewPlane();
		Sampler sampler = world.getSampler();
		Tracer tracer = world.getTracer();
		RGBColor pixelColor;
		Ray ray;
		Point2D sp, pp, pn;
		float rSquared;
		
		for (int row = 0; row < vp.hRes; row++) {
			for (int col = 0; col < vp.vRes; col++) {
				pixelColor = new RGBColor();
				for (int i = 0; i < sampler.numSamples(); i++) {
					sp = sampler.sampleUnitSquare();
					pp = new Point2D(vp.s * (col - 0.5f * vp.hRes + sp.x), vp.s * (row - 0.5f * vp.hRes + sp.y));
					
					pn = new Point2D(2.0f / (vp.s * vp.hRes) * pp.x, 2.0f / (vp.s * vp.vRes) * pp.y);
					rSquared = pn.x * pn.x + pn.y * pn.y;
					
					ray = new Ray(eye, 	rayDirection(pn, rSquared));
					if (rSquared <= 1) {
						pixelColor.add(tracer.traceRay(ray));
					}
				}
				pixelColor.div(sampler.numSamples());
				vp.drawPixel(row, col, pixelColor);
				
			}
		}
	}
	
	// Based on "Ray tracing from the ground up"'s implementation
	private Vector3D rayDirection(final Point2D pn, final float rSquared) {
		if (rSquared <= 1) {
			final float r = (float) Math.sqrt(rSquared);
			final float psi = r * psiMax * (float) Math.PI;
			final float sinPsi = (float) Math.sin(psi);
			final float cosPsi = (float) Math.cos(psi);
			final float sinAlpha = pn.y / r;
			final float cosAlpha = pn.x / r;
			final Vector3D dir = u.scaleCopy(sinPsi * cosAlpha).add(v.scaleCopy(sinPsi * sinAlpha)).sub(w.scaleCopy(cosPsi));
			return dir;
		} else {
			return new Vector3D(0, 0, 0);
		}
	}

}
