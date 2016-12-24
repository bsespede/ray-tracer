package ar.edu.itba.grupo2;

import java.io.File;

import ar.edu.itba.grupo2.camera.Camera;
import ar.edu.itba.grupo2.camera.Pinhole;
import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.scene.World;
import ar.edu.itba.grupo2.screen.Image;
import ar.edu.itba.grupo2.utils.RGBColor;

public class Main {
	
    public static void main(String[] args) {
    	World world = new World(300, 300, 1.0f, 16, new RGBColor());
    	world.build();
    	Camera camera = new Pinhole(new Point3D(3, 3, 0), new Point3D(0, 0, 0), new Vector3D(0, 0, -1), 10);
    	camera.render(world);
    	Image.draw(world, new File("test/test2.png"), "png");
    }
    
}
