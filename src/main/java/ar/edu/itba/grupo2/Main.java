package ar.edu.itba.grupo2;

import ar.edu.itba.grupo2.light.Ambient;
import ar.edu.itba.grupo2.light.Light;
import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.scene.World;
import ar.edu.itba.grupo2.utils.RGBColor;

public class Main {

	public static void main(String[] args) {
		
		final int width = 500;
		final int height = 500;
		final float pixelSize = 1;
		final int samples = 1;
		final Light ambientLight = new Ambient(new RGBColor(1, 1, 1), 0.05f);
		final RGBColor background = new RGBColor(0.2f, 0.2f, 0.2f);
		final float fov = 100;
		
    	World world = new World(width, height, pixelSize, samples, ambientLight, background);
    	world.build();
    	
		Point3D eye = new Point3D(5, 5, 10);
		Point3D lookAt = new Point3D(3, 5, 0);
		Vector3D up = new Vector3D(1, 0, 0);
    	
		System.out.println("Starting render");
//    	Camera camera = new Pinhole(eye, lookAt, up, fov);
//    	camera.render(world);
//    	Image.draw(world, new File("test/test2.png"), "png");
    	System.out.println("Finished render");
		
		RealtimeRender renderer = new RealtimeRender(world, eye, lookAt, up, fov);
		renderer.run();
	}

}
