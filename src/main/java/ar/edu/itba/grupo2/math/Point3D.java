package ar.edu.itba.grupo2.math;

public class Point3D {

	public float x, y, z;
	
	public Point3D(final float x, final float y, final float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point3D other = (Point3D) obj;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
			return false;
		if (Float.floatToIntBits(z) != Float.floatToIntBits(other.z))
			return false;
		return true;
	}

	public float squaredDistance(final Point3D p) {
		float diffX = x - p.x;
		float diffY = y - p.y;
		float diffZ = z - p.z;
		return diffX * diffX + diffY * diffY + diffZ * diffZ;
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

	public Vector3D distanceVector(Point3D p) {
		return new Vector3D(x - p.x, y - p.y, z - p.z);
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
	
	public Point3D subCopy(Point3D p) {
		return new Point3D(x - p.x, y - p.y, z - p.z);
	}

	public Point3D scale(float s) {
		this.x *= s;
		this.y *= s;
		this.z *= s;
		return this;
	}

	public Point3D scaleCopy(float s) {
		return new Point3D(x * s, y * s, z * s);
	}
	
	public Point3D transform(Matrix4D mat) {
		float transX = mat.m[0][0] * x + mat.m[0][1] * y + mat.m[0][2] * z + mat.m[0][3];
		float transY = mat.m[1][0] * x + mat.m[1][1] * y + mat.m[1][2] * z + mat.m[1][3];
		float transZ = mat.m[2][0] * x + mat.m[2][1] * y + mat.m[2][2] * z + mat.m[2][3];		
		return new Point3D(transX, transY, transZ);
	}
	
}
