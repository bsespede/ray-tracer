package ar.edu.itba.grupo2.object;

import ar.edu.itba.grupo2.geometry.GeometricObject;
import ar.edu.itba.grupo2.material.Material;
import ar.edu.itba.grupo2.math.Matrix4D;
import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.ray.Ray;

public class Object3D {

	private final Material material;
	private final GeometricObject object;
	private Matrix4D invMatrix;
	
	public Object3D(final GeometricObject object, final Material material) {
		this.material = material;
		this.object = object;
		this.invMatrix = new Matrix4D();
	}
	
	public Material getMaterial() {
		return material;
	}
	
	public boolean hasEmmisiveMaterial() {
		return material.isEmmisive();
	}
	
	public boolean hit(final Ray ray) {
		Ray invRay = new Ray(ray.p.transform(invMatrix), ray.d.transform(invMatrix));

		if (object.hit(invRay)) {
			ray.t = invRay.t;
			return true;
		}
		
		return false;
	}

	public Collision calculateCollision(Ray ray, float t) {
		Collision collision = object.calculateCollision(ray, t, material);
		collision.n = collision.n.transposeTransform(invMatrix);
		return collision;
	}
	
	public void translate(float x, float y, float z) {
		Matrix4D mat = new Matrix4D();		
		mat.m[0][3] = -x;
		mat.m[1][3] = -y;
		mat.m[2][3] = -z;		
		invMatrix = invMatrix.multiply(mat);
	}
	
	public void scale(float x, float y, float z) {
		Matrix4D mat = new Matrix4D();
		mat.m[0][0] /= x;
		mat.m[1][1] /= y;
		mat.m[2][2] /= z;		
		invMatrix = invMatrix.multiply(mat);
	}
	
	public void xRotate(float rad) {
		Matrix4D mat = new Matrix4D();
		mat.m[1][1] = (float) Math.cos(rad);
		mat.m[1][2] = (float) Math.sin(rad);
		mat.m[2][1] = (-1) * mat.m[1][2];
		mat.m[2][2] = mat.m[1][1];		
		invMatrix = invMatrix.multiply(mat);
	}
	
	public void yRotate(float rad) {
		Matrix4D mat = new Matrix4D();
		mat.m[0][0] = (float) Math.cos(rad);
		mat.m[2][0] = (float) Math.sin(rad);
		mat.m[0][2] = (-1) * mat.m[2][0];
		mat.m[2][2] = mat.m[0][0];		
		invMatrix = invMatrix.multiply(mat);
	}
	
	public void zRotate(float rad) {
		Matrix4D mat = new Matrix4D();
		mat.m[0][0] = (float) Math.cos(rad);
		mat.m[0][1] = (float) Math.sin(rad);
		mat.m[1][0] = (-1) * mat.m[0][1];
		mat.m[1][1] = mat.m[0][0];		
		invMatrix = invMatrix.multiply(mat);
	}	
	
}
