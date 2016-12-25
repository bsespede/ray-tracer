package ar.edu.itba.grupo2.math;

public class Point3D {

	public float x, y, z;
	
	public Point3D(final float x, final float y, final float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float distance(final Point3D p) {
		float diffX = x - p.x;
		float diffY = y - p.y;
		float diffZ = z - p.z;
		return (float) Math.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);
	}
	
	public Point3D translate(final Vector3D v, final float t) {
		return new Point3D(x + v.x * t, y + v.y * t, z + v.z * t);
	}

	public String toString() {
		return "Point3D [x=" + x + ", y=" + y + ", z=" + z + "]";
	}

	public Vector3D distanceVector(Point3D lookAt) {
		return new Vector3D(x - lookAt.x, y - lookAt.y, z - lookAt.z);
	}

	public Point3D add(Point3D p) {
		this.x += p.x;
		this.y += p.y;
		this.z += p.z;
		return this;
	}
	
	public Point3D sub(Point3D p) {
		this.x -= p.x;
		this.y -= p.y;
		this.z -= p.z;
		return this;
	}
	
}
