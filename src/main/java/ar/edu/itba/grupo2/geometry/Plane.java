package ar.edu.itba.grupo2.geometry;

import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.ray.Ray;
import ar.edu.itba.grupo2.utils.Constant;
import ar.edu.itba.grupo2.utils.RGBColor;

public class Plane extends GeometricObject{

	private final Vector3D n;
	private final Point3D p;
	
	public Plane(final Point3D p, final Vector3D n, final RGBColor color) {
		super(color);
		this.p = p;
		this.n = n;
	}
	
	public Collision hit(final Ray ray) {
		float aux = n.dot(ray.d);
		
		if (aux == 0) {
			return null;
		}
		
		float t = ((p.x - ray.p.x) * n.x + (p.y - ray.p.y) * n.y + (p.z - ray.p.z) * n.z) / aux;
		
		if (t > Constant.EPSILON) {
			return new Collision(ray.p.translate(ray.d, t), n, t, this);
		}
		
		return null;
	}

}
