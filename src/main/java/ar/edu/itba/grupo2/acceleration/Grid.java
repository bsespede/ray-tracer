package ar.edu.itba.grupo2.acceleration;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.grupo2.acceleration.boundingvolume.BBox;
import ar.edu.itba.grupo2.math.MathUtil;
import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.utils.MathConst;
import sun.security.jca.GetInstance.Instance;

public class Grid {

	private List<Cell> cells;
	private BBox bbox;
	private int nx, ny, nz;

	public BBox bbox();
	
	public void setup() {
		// max and min coords of the grid
		Point3D minCoord = minCoords();
		Point3D maxCoord = maxCoords();
		
		//setup the grid bbox
		bbox = new BBox(minCoord, maxCoord);
		
		// compute numbers of cells
		int numObjects = objects.size();
		float wX = maxCoord.x - minCoord.x;
		float wY = maxCoord.y - minCoord.y;
		float wZ = maxCoord.z - minCoord.z;
		float multiplier = 2;
		float s = (float) Math.pow(wX * wY * wZ / numObjects, 1 / 3);
		nx = (int) (multiplier * wX / s) + 1;
		ny = (int) (multiplier * wY / s) + 1;
		nz = (int) (multiplier * wZ / s) + 1;
		
		// set up the cell lists
		int numCells = nx * ny * nz;
		cells = new ArrayList<Cell>(numCells); 
		
		// set up temp array with the number of objects per cell????
		
		// put stuff in cells
		BBox objBBox;
		int index; // cell index
		
		for (int i = 0; i < numObjects; i++) {
			objBBox = objects.get(i).bbox();
			
			//compute cell indices for the corners of the bbox of the object
			int ixmin = MathUtil.clamp((objBBox.p0.x - minCoord.x) * nx / (maxCoord.x - minCoord.x), 0, nx - 1);
			int iymin = MathUtil.clamp((objBBox.p0.y - minCoord.y) * ny / (maxCoord.y - minCoord.y), 0, ny - 1);
			int izmin = MathUtil.clamp((objBBox.p0.z - minCoord.z) * nz / (maxCoord.z - minCoord.z), 0, nz - 1);
			int ixmax = MathUtil.clamp((objBBox.p1.x - minCoord.x) * nx / (maxCoord.x - minCoord.x), 0, nx - 1);
			int iymax = MathUtil.clamp((objBBox.p1.y - minCoord.y) * ny / (maxCoord.y - minCoord.y), 0, ny - 1);
			int izmax = MathUtil.clamp((objBBox.p1.z - minCoord.z) * nz / (maxCoord.z - minCoord.z), 0, nz - 1);;
		
			// add objects to cells
			for (int iz = izmin; iz <= izmax; iz++) {
				for (int iy = iymin; iy <= iymax; iy++) {
					for (int ix = ixmin; ix <= ixmax; ix++) {
						index = ix + nx * iy + nx * ny * iz;
						
						if (cells.get(index) == null) {
							Cell cell = new Cell();
							cell.addObject(objects.get(i));
							cells.add(index, cell);
						} else {
							cells.get(index).addObject(objects.get(i));
						}
					}
				}
			}
		}		
	}

	private Point3D minCoords() {
		BBox bbox;
		Point3D p0 = new Point3D(Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE);

		int numObjects = objects.size();

		for (int i = 0; i < numObjects; i++) {
			bbox = objects.get(i).bbox();

			if (bbox.p0.x < p0.x) {
				p0.x = bbox.p0.x;
			}

			if (bbox.p0.y < p0.y) {
				p0.y = bbox.p0.y;
			}

			if (bbox.p0.z < p0.z) {
				p0.z = bbox.p0.z;
			}

		}

		p0.x -= MathConst.EPSILON;
		p0.y -= MathConst.EPSILON;
		p0.z -= MathConst.EPSILON;

		return p0;
	}

	private Point3D maxCoords() {
		BBox bbox;
		Point3D p0 = new Point3D(Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE);

		int numObjects = objects.size();

		for (int i = 0; i < numObjects; i++) {
			bbox = objects.get(i).bbox();

			if (bbox.p0.x > p0.x) {
				p0.x = bbox.p0.x;
			}

			if (bbox.p0.y > p0.y) {
				p0.y = bbox.p0.y;
			}

			if (bbox.p0.z > p0.z) {
				p0.z = bbox.p0.z;
			}

		}

		p0.x += MathConst.EPSILON;
		p0.y += MathConst.EPSILON;
		p0.z += MathConst.EPSILON;

		return p0;
	}

}
