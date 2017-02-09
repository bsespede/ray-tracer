package ar.edu.itba.grupo2.camera;

import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.scene.World;

public abstract class Camera {

	protected final Point3D eye;
	protected final Point3D lookAt;
	protected final Vector3D up;
	protected final Vector3D u, v, w;
	
	public Camera(final Point3D eye, final Point3D lookAt, Vector3D up) {
		this.eye = eye;
		this.lookAt = lookAt;
		this.up = up;
		final Vector3D distanceVector = eye.distanceVector(lookAt);
		if (eye.x == lookAt.x && eye.z == lookAt.z && lookAt.y < eye.y) { // test for gymbal locking
			System.out.println("[INFO] Gymbal-lock");
			this.w = new Vector3D(0, 1, 0);
			this.u = new Vector3D(0, 0, 1);
			this.v = new Vector3D(1, 0, 0);
		} else { // otherwise calculate the ONB
			this.w = distanceVector.normalize();
			this.u = up.cross(w).normalize();
			this.v = w.cross(u);
		}		
	}
	
	public abstract void render(World world);
	
}
