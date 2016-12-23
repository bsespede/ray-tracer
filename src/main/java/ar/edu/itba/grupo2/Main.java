package ar.edu.itba.grupo2;

import java.io.File;

import ar.edu.itba.grupo2.scene.World;
import ar.edu.itba.grupo2.screen.Image;
import ar.edu.itba.grupo2.utils.RGBColor;

public class Main {
	
    public static void main( String[] args ) {
    	World world = new World(500, 500, new RGBColor(1, 1, 1));
    	world.build();
    	world.render();
    	Image.draw(world, new File("test.png"), "png");
    }
    
}
