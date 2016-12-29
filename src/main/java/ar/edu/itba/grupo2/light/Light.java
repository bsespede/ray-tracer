package ar.edu.itba.grupo2.light;

import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.ray.Collision;
import ar.edu.itba.grupo2.utils.RGBColor;

public interface Light {

	public Vector3D getDirection(Collision collision);
	
	public RGBColor L(Collision collision);

	public float getLightDistance(Point3D p);
	
}
