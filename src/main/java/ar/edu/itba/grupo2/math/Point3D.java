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
	
}
