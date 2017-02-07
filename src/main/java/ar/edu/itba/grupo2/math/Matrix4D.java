package ar.edu.itba.grupo2.math;

public class Matrix4D {

	public float m[][];
	
	public Matrix4D() {
		m = new float[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (i == j) {
					m[i][j] = 1;
				} else {
					m[i][j] = 0;
				}
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
	
}
