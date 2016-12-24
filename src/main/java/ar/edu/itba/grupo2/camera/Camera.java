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
		final Vector3D distanceVector = eye.distanceVector(lookAt);
		final Vector3D parallelTest = distanceVector.cross(up);
		if (parallelTest.x == 0 && parallelTest.y == 0 && parallelTest.z == 0) { // test for gymbal locking
			System.out.println("Gymbal lock");
			this.u = new Vector3D(0, 0, 1);
			this.v = new Vector3D(1, 0, 0);
			this.w = new Vector3D(0, 1, 0);
		} else { // otherwise calculate the ONB
			this.w = distanceVector;
			this.u = up.cross(w).normalize();
			this.v = w.cross(u).normalize();
		}		
	}
	
	public abstract void render(World world);
	
}
