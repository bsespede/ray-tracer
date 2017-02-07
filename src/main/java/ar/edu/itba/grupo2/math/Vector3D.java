package ar.edu.itba.grupo2.math;

public class Vector3D {

	public float x, y, z;
	
	public Vector3D(final float x, final float y, final float z) {
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
		Vector3D other = (Vector3D) obj;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
			return false;
		if (Float.floatToIntBits(z) != Float.floatToIntBits(other.z))
			return false;
		return true;
	}

	public float dot(final Vector3D v) {
		return x * v.x + y * v.y + z * v.z;
	}
	
	public Vector3D cross(final Vector3D v) {
		return new Vector3D(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
	}
	
	public Vector3D add(final Vector3D v) {
		x += v.x;
		y += v.y;
		z += v.z;
		return this;
	}
	
	public Vector3D addScaled(final Vector3D v, final float s) {
		x += v.x * s;
		y += v.y * s;
		z += v.z * s;		
		return this;
	}
	
	public Vector3D sub(final Vector3D v) {
		x -= v.x;
		y -= v.y;
		z -= v.z;		
		return this;
	}
	
	public Vector3D normalize() {
		float m = module();
		x /= m;
		y /= m;
		z /= m;
		return this;
	}
	
	public Vector3D scale(final float s) {
		x *= s;
		y *= s;
		z *= s;		
		return this;
	}
	
	public Vector3D scaleCopy(final float s) {
		return new Vector3D(x * s, y * s, z * s);
	}
	
	public float module() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}
	
	public float angle(final Vector3D vector) {
		return (float) Math.acos(dot(vector));
	}

	public String toString() {
		return "Vector3D [x=" + x + ", y=" + y + ", z=" + z + "]";
	}

	public Vector3D add(Point3D p) {
		this.x += p.x;
		this.y += p.y;
		this.z += p.z;
		return this;
	}
	
	public Vector3D transform(Matrix4D mat) {
		float transX = mat.m[0][0] * x + mat.m[0][1] * y + mat.m[0][2] * z;
		float transY = mat.m[1][0] * x + mat.m[1][1] * y + mat.m[1][2] * z;
		float transZ = mat.m[2][0] * x + mat.m[2][1] * y + mat.m[2][2] * z;		
		return new Vector3D(transX, transY, transZ);
	}
	
	public Vector3D transposeTransform(Matrix4D mat) {
		float transX = mat.m[0][0] * x + mat.m[1][0] * y + mat.m[2][0] * z;
		float transY = mat.m[0][1] * x + mat.m[1][1] * y + mat.m[2][1] * z;
		float transZ = mat.m[0][2] * x + mat.m[1][2] * y + mat.m[2][2] * z;		
		return new Vector3D(transX, transY, transZ);
	}
}
