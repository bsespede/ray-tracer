package ar.edu.itba.grupo2.math;

public class Point2D {

	public float x, y;

	public Point2D(float x, float y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Point2D [x=" + x + ", y=" + y + "]";
	}
	
	public Point2D scaleCopy(float s) {
		return new Point2D(x * s, y * s);
	}
	
}
