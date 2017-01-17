package ar.edu.itba.grupo2.geometry;

import ar.edu.itba.grupo2.material.Material;
import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.sampler.Sampler;
import ar.edu.itba.grupo2.utils.MathConst;

public class AxisAlignedBox extends GeometricObject{
	
	private final Point3D p0, p1;

	public AxisAlignedBox(Point3D p0, Point3D p1, Material material) {
		super(material);
		this.p0 = p0;
		this.p1 = p1;
	}

	// Basado en el algoritmo de Shirley & Morley (2003)
	@Override
	public boolean hit(Ray ray) {
		float txMin, tyMin, tzMin;
		float txMax, tyMax, tzMax;
		
		// Encontrar los "slabs" de interseccion		
		float a = 1f / ray.d.x;		
		if (a >= 0) {
			txMin = (p0.x - ray.p.x) * a;
			txMax = (p1.x - ray.p.x) * a;
		} else {
			txMin = (p1.x - ray.p.x) * a;
			txMax = (p0.x - ray.p.x) * a;
		}
		
		float b = 1f / ray.d.y;		
		if (b >= 0) {
			tyMin = (p0.y - ray.p.y) * b;
			tyMax = (p1.y - ray.p.y) * b;
		} else {
			tyMin = (p1.y - ray.p.y) * b;
			tyMax = (p0.y - ray.p.y) * b;
		}
		
		float c = 1f / ray.d.z;		
		if (c >= 0) {
			tzMin = (p0.z - ray.p.z) * c;
			tzMax = (p1.z - ray.p.z) * c;
		} else {
			tzMin = (p1.z - ray.p.z) * c;
			tzMax = (p0.z - ray.p.z) * c;
		}
		
		float t0, t1;
		Facing faceOut, faceIn;
		
		// Econtrar el t que entra más grande
		if (txMin > tyMin) {
			t0 = txMin;
			faceIn = (c >= 0) ? Facing.NEGATIVE_X : Facing.POSITIVE_X;
		} else {
			t0 = tyMin;
			faceIn = (b >= 0) ? Facing.NEGATIVE_Y : Facing.POSITIVE_Y;
		}
		
		if (tzMin > t0) {
			t0 = tzMin;
			faceIn = (b >= 0) ? Facing.NEGATIVE_Z : Facing.POSTIVE_Z;
		}
		
		// Ahora el t más chico que sale		
		if (txMax < tyMax) {
			t1 = txMax;
			faceOut = (a >= 0) ? Facing.POSITIVE_X : Facing.NEGATIVE_X;
		} else {
			t1 = tyMax;
			faceOut = (b >= 0) ? Facing.POSITIVE_Y : Facing.NEGATIVE_Y;
		}
		
		if (tzMax < t1) {
			t1 = tzMax;
			faceOut = (c >= 0) ? Facing.POSTIVE_Z : Facing.NEGATIVE_Z;
		}
		
		if (t0 < t1 && t1 > MathConst.EPSILON) {
			if (t0 > MathConst.EPSILON) {
				ray.t = t0; 
				return true; // Pega afuera
			} else {
				ray.t = t1;
				return true; // Pega adentro
			}
		}
		
		return false;
	}

	@Override
	public Collision calculateCollision(Ray ray, float t) {
		float txMin, tyMin, tzMin;
		float txMax, tyMax, tzMax;
		
		// Encontrar los "slabs" de interseccion		
		float a = 1f / ray.d.x;		
		if (a >= 0) {
			txMin = (p0.x - ray.p.x) * a;
			txMax = (p1.x - ray.p.x) * a;
		} else {
			txMin = (p1.x - ray.p.x) * a;
			txMax = (p0.x - ray.p.x) * a;
		}
		
		float b = 1f / ray.d.y;		
		if (b >= 0) {
			tyMin = (p0.y - ray.p.y) * b;
			tyMax = (p1.y - ray.p.y) * b;
		} else {
			tyMin = (p1.y - ray.p.y) * b;
			tyMax = (p0.y - ray.p.y) * b;
		}
		
		float c = 1f / ray.d.z;		
		if (c >= 0) {
			tzMin = (p0.z - ray.p.z) * c;
			tzMax = (p1.z - ray.p.z) * c;
		} else {
			tzMin = (p1.z - ray.p.z) * c;
			tzMax = (p0.z - ray.p.z) * c;
		}
		
		float t0, t1;
		Facing faceOut, faceIn;
		
		// Econtrar el t que entra más grande
		if (txMin > tyMin) {
			t0 = txMin;
			faceIn = (c >= 0) ? Facing.NEGATIVE_X : Facing.POSITIVE_X;
		} else {
			t0 = tyMin;
			faceIn = (b >= 0) ? Facing.NEGATIVE_Y : Facing.POSITIVE_Y;
		}
		
		if (tzMin > t0) {
			t0 = tzMin;
			faceIn = (b >= 0) ? Facing.NEGATIVE_Z : Facing.POSTIVE_Z;
		}
		
		// Ahora el t más chico que sale		
		if (txMax < tyMax) {
			t1 = txMax;
			faceOut = (a >= 0) ? Facing.POSITIVE_X : Facing.NEGATIVE_X;
		} else {
			t1 = tyMax;
			faceOut = (b >= 0) ? Facing.POSITIVE_Y : Facing.NEGATIVE_Y;
		}
		
		if (tzMax < t1) {
			t1 = tzMax;
			faceOut = (c >= 0) ? Facing.POSTIVE_Z : Facing.NEGATIVE_Z;
		}
		
		if (t0 < t1 && t1 > MathConst.EPSILON) {
			if (t0 > MathConst.EPSILON) {
				return new Collision(ray, ray.p.translate(ray.d, t1), faceIn.getNormal(), t1, this); // Pega afuera
			} else {
				return new Collision(ray, ray.p.translate(ray.d, t1), new Vector3D(1, 0, 0), t1, this); // Pega adentro
			}
		}
		return null;
	}
	
	@Override
	public Point3D sample(Sampler sampler) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Vector3D getNormal(Point3D samplePoint) {
		throw new UnsupportedOperationException();
	}

	@Override
	public float pdf(Collision collision) {
		throw new UnsupportedOperationException();
	}
	
	private enum Facing {
		
		NEGATIVE_X {
			@Override
			public Vector3D getNormal() {
				return new Vector3D(-1, 0, 0);
			}
		},
		NEGATIVE_Y {
			@Override
			public Vector3D getNormal() {
				return new Vector3D(0, -1, 0);
			}
		},
		NEGATIVE_Z {
			@Override
			public Vector3D getNormal() {
				return new Vector3D(0, 0, -1);
			}
		},
		POSITIVE_X {
			@Override
			public Vector3D getNormal() {
				return new Vector3D(1, 0, 0);
			}
		},
		POSITIVE_Y {
			@Override
			public Vector3D getNormal() {
				return new Vector3D(0, 1, 0);
			}
		},
		POSTIVE_Z {
			@Override
			public Vector3D getNormal() {
				return new Vector3D(0, 0, 1);
			}
		};

		public abstract Vector3D getNormal();
		
	}

}
