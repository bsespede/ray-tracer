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
		final RGBColor background = new RGBColor(0, 0, 0);
		
    	World world = new World(width, height, pixelSize, samples, ambientLight, background);
    	world.build();
    	
		Point3D eye = new Point3D(0, 0, 3);
		Point3D lookAt = new Point3D(0, 0, 0);
		Vector3D up = new Vector3D(1, 0, 0);
    	
//    	Camera camera = new Pinhole(eye, lookAt, up, 2);
//    	camera.render(world);
//    	Image.draw(world, new File("test/test2.png"), "png");
		
		RealtimeRender renderer = new RealtimeRender(world, eye, lookAt, up);
		renderer.run();
	}

}
