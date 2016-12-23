package ar.edu.itba.grupo2.screen;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ar.edu.itba.grupo2.scene.World;

public class Image {

	public static void draw(final World world, final File output, final String format) {
		final ViewPlane vp = world.getViewPlane();
		try {
			BufferedImage image = new BufferedImage(vp.getWidth(), vp.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D graphics = image.createGraphics();
			for (int i = 0; i < vp.getWidth(); i++) {
				for (int j = 0; j < vp.getHeight(); j++) {
					graphics.setColor(vp.getPixel(i/(int)vp.s, j/(int)vp.s).toColor());
					graphics.drawRect(i, j, 1, 1);
				}
			}
			ImageIO.write(image, format, output);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
}
