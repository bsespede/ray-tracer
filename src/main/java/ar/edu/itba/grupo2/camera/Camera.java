package ar.edu.itba.grupo2.camera;

import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.scene.World;

public abstract class Camera {

	protected final Point3D eye;
	protected final Point3D lookAt;
	protected final Vector3D up;
	protected final Vector3D u, v, w;
	
	protected Camera(final Point3D eye, final Point3D lookAt, Vector3D up) {
		this.eye = eye;
		this.lookAt = lookAt;
		this.up = up;
		this.w = eye.distanceVector(lookAt);
		w.normalize();
		this.u = up.cross(w);
		u.normalize();
		this.v = w.cross(u);
	}
	
	public abstract void render(World world);
	
}
