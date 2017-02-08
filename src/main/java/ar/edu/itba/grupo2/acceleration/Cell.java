package ar.edu.itba.grupo2.acceleration;

import java.util.LinkedList;
import java.util.List;

import ar.edu.itba.grupo2.geometry.GeometricObject;

public class Cell {

	private List<GeometricObject> objects = new LinkedList<GeometricObject>();
	
	public void addObject(GeometricObject obj) {
		objects.add(obj);
	}

	public boolean isEmpty() {
		return objects.isEmpty();
	}
}
