package ar.edu.itba.grupo2;

import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.scene.World;
import ar.edu.itba.grupo2.utils.RGBColor;

public class Main {

	public static void main(String[] args) {
    	World world = new World(500, 500, 1.0f, 1, new RGBColor());
    	world.build();
		Point3D eye = new Point3D(0, 0, 5);
		Point3D lookAt = new Point3D(0, 0, 0);
		Vector3D up = new Vector3D(0, 1, 0);
    	
//    	Camera camera = new Pinhole(eye, lookAt, up, 2);
//    	camera.render(world);
//    	Image.draw(world, new File("test/test2.png"), "png");
		
		RealtimeRender renderer = new RealtimeRender(world, eye, lookAt, up);
		renderer.run();
	}

}
