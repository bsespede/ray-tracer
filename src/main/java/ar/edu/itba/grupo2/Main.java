package ar.edu.itba.grupo2;

import java.awt.Color;

import ar.edu.itba.grupo2.scene.World;

public class Main {
	
    public static void main( String[] args ) {
    	World world = new World(500, 500, Color.WHITE);
    	world.build();
    	world.render();
    }
    
}
