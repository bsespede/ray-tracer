package ar.edu.itba.grupo2.math;

public class Matrix4D {

	public float m[][];
	
	public Matrix4D() {
		m = new float[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				m[i][j] = 0;
			}
		}
	}
	
	public Matrix4D multiply(Matrix4D mat) {
		final Matrix4D product = new Matrix4D();
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				float sum = 0;
				
				for (int k = 0; k < 4; k++) {
					sum += m[j][k] * mat.m[k][i];
				}
				
				product.m[j][i] = sum;
			}
		}
		
		return product;		
	}
	
	public Matrix4D divide(float n) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				m[i][j] /= n;
			}
		}
		
		return this;
	}
	
	public static Matrix4D translationMatrix(float x, float y, float z) {
		Matrix4D mat = new Matrix4D();
		mat.m[0][0] = 1;
		mat.m[1][1] = 1;
		mat.m[2][2] = 1;
		mat.m[3][3] = 1;		
		mat.m[0][3] = x;
		mat.m[1][3] = y;
		mat.m[2][3] = z;		
		return mat;
	}
	
	public static Matrix4D scalingMatrix(float x, float y, float z) {
		Matrix4D mat = new Matrix4D();
		mat.m[0][0] = x;
		mat.m[1][1] = y;
		mat.m[2][2] = z;
		mat.m[3][3] = 1;		
		return mat;
	}
	
	public static Matrix4D xRotationMatrix(float rad) {
		Matrix4D mat = new Matrix4D();
		mat.m[1][1] = (float) Math.cos(rad);
		mat.m[2][1] = (float) Math.sin(rad);
		mat.m[1][2] = (-1) * mat.m[1][2];
		mat.m[2][2] = mat.m[1][1];
		mat.m[0][0] = 1;
		mat.m[3][3] = 1;		
		return mat;
	}
	
	public static Matrix4D yRotationMatrix(float rad) {
		Matrix4D mat = new Matrix4D();
		mat.m[0][0] = (float) Math.cos(rad);
		mat.m[0][2] = (float) Math.sin(rad);
		mat.m[2][0] = (-1) * mat.m[0][2];
		mat.m[2][2] = mat.m[0][0];
		mat.m[3][3] = 1;
		mat.m[1][1] = 1;
		return mat;
	}
	
	public static Matrix4D zRotationMatrix(float rad) {
		Matrix4D mat = new Matrix4D();
		mat.m[0][0] = (float) Math.cos(rad);
		mat.m[1][0] = (float) Math.sin(rad);
		mat.m[0][1] = (-1) * mat.m[1][0];
		mat.m[1][1] = mat.m[0][0];
		mat.m[3][3] = 1;
		mat.m[2][2] = 1;
		return mat;
	}
	
	public static Matrix4D inverseTranslationMatrix(float x, float y, float z) {
		Matrix4D mat = new Matrix4D();
		mat.m[0][0] = 1;
		mat.m[1][1] = 1;
		mat.m[2][2] = 1;
		mat.m[3][3] = 1;		
		mat.m[0][3] = -x;
		mat.m[1][3] = -y;
		mat.m[2][3] = -z;		
		return mat;
	}
	
	public static Matrix4D inverseScalingMatrix(float x, float y, float z) {
		Matrix4D mat = new Matrix4D();
		mat.m[0][0] = 1/x;
		mat.m[1][1] = 1/y;
		mat.m[2][2] = 1/z;
		mat.m[3][3] = 1;		
		return mat;
	}
	
	public static Matrix4D inverseXRotationMatrix(float rad) {
		Matrix4D mat = new Matrix4D();
		mat.m[1][1] = (float) Math.cos(rad);
		mat.m[1][2] = (float) Math.sin(rad);
		mat.m[2][1] = (-1) * mat.m[1][2];
		mat.m[2][2] = mat.m[1][1];
		mat.m[0][0] = 1;
		mat.m[3][3] = 1;		
		return mat;
	}
	
	public static Matrix4D inverseYRotationMatrix(float rad) {
		Matrix4D mat = new Matrix4D();
		mat.m[0][0] = (float) Math.cos(rad);
		mat.m[2][0] = (float) Math.sin(rad);
		mat.m[0][2] = (-1) * mat.m[2][0];
		mat.m[2][2] = mat.m[0][0];
		mat.m[1][1] = 1;
		mat.m[3][3] = 1;
		return mat;
	}
	
	public static Matrix4D inverseZRotationMatrix(float rad) {
		Matrix4D mat = new Matrix4D();
		mat.m[0][0] = (float) Math.cos(rad);
		mat.m[0][1] = (float) Math.sin(rad);
		mat.m[1][0] = (-1) * mat.m[0][1];
		mat.m[1][1] = mat.m[0][0];
		mat.m[2][2] = 1;
		mat.m[3][3] = 1;
		return mat;
	}	
	
}
