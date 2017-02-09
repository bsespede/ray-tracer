package ar.edu.itba.grupo2;

import java.io.File;

import ar.edu.itba.grupo2.camera.Camera;
import ar.edu.itba.grupo2.camera.ThinLens;
import ar.edu.itba.grupo2.light.Ambient;
import ar.edu.itba.grupo2.light.Light;
import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.scene.World;
import ar.edu.itba.grupo2.screen.Image;
import ar.edu.itba.grupo2.utils.RGBColor;

public class Main {

	public static void main(String[] args) {
		
		final int width = 500;
		final int height = 500;
		final float pixelSize = 1;
		final int samples = 200;
		final Light ambientLight = new Ambient(new RGBColor(1, 1, 1), 0.05f);
		final RGBColor background = new RGBColor(0.2f, 0.2f, 0.2f);
		final float fov = 20f;
		
    	World world = new World(width, height, pixelSize, samples, ambientLight, background);
    	world.build();
    	
		Point3D eye = new Point3D(0, 11, 18);
		Point3D lookAt = new Point3D(0, 10, 0);
		Vector3D up = new Vector3D(1, 0, 0);
    	
		for (int i = 0; i < 10; i++) {
			System.out.println("Starting render "+ i);
	    	Camera camera = new ThinLens(eye, lookAt, up, fov, i, 3f);
	    	camera.render(world);
	    	Image.draw(world, new File(i+".png"), "png");
	    	System.out.println("Finished render");
		}
//		
//		RealtimeRender renderer = new RealtimeRender(world, eye, lookAt, up, fov);
//		renderer.run();
	}

}
