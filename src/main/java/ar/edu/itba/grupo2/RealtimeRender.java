package ar.edu.itba.grupo2;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import ar.edu.itba.grupo2.camera.Camera;
import ar.edu.itba.grupo2.camera.Pinhole;
import ar.edu.itba.grupo2.math.Point3D;
import ar.edu.itba.grupo2.math.Vector3D;
import ar.edu.itba.grupo2.scene.World;
import ar.edu.itba.grupo2.screen.ViewPlane;

public class RealtimeRender {

	private Camera camera;
	private World world;
	private Point3D eye;
	private Point3D lookAt;
	private Vector3D up;
	
	private static final float MOVE_DELTA = 0.1f;
	
	public RealtimeRender(final World world, final Point3D eye, final Point3D lookAt, final Vector3D up) {
		this.world = world;
		this.eye = eye;
		this.lookAt = lookAt;
		this.up = up;
	}
	
	public void run() {
		JFrame frame = new JFrame();
		frame.setSize(world.getViewPlane().getWidth(), world.getViewPlane().getHeight());
		frame.setVisible(true);
		frame.getContentPane().add(new FrameDrawer());
		frame.addKeyListener(new CameraKeyListener());
		while (true) {
				long startTime = System.currentTimeMillis();
				camera = new Pinhole(eye, lookAt, up, 10);
				camera.render(world);
				float elapsedtime = (System.currentTimeMillis() - startTime) / 1000f;
				//System.out.println("[RayTracer] ended. Time: " + elapsedtime + " [s]");
				frame.repaint();
		}
	}

	private class FrameDrawer extends Component {

		@Override
		public void paint(Graphics graphics) {
			super.paint(graphics);

			final ViewPlane vp = world.getViewPlane();
			for (int i = 0; i < vp.getWidth(); i++) {
				for (int j = 0; j < vp.getHeight(); j++) {
					graphics.setColor(vp.getPixel((int) (i / vp.s), (int) (j / vp.s)).toColor());
					graphics.drawRect(i, j, 1, 1);
				}
			}

			graphics.setColor(Color.WHITE);
			graphics.drawString(String.format("eye: [%.2f, %.2f, %.2f]", eye.x, eye.y, eye.z), 10, vp.getHeight() - 30);
			graphics.drawString(String.format("lookAt: [%.2f, %.2f, %.2f]", lookAt.x, lookAt.y, lookAt.z), 10, vp.getHeight() - 50);
		}
	}

	private class CameraKeyListener implements KeyListener {

		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			switch (keyCode) {
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
				break;
			case KeyEvent.VK_W:
				eye.add(new Point3D(0, MOVE_DELTA, 0));
				break;
			case KeyEvent.VK_S:
				eye.sub(new Point3D(0, MOVE_DELTA, 0));
				break;
			case KeyEvent.VK_A:
				eye.sub(new Point3D(MOVE_DELTA, 0, 0));
				break;
			case KeyEvent.VK_D:
				eye.add(new Point3D(MOVE_DELTA, 0, 0));
				break;
			case KeyEvent.VK_U:
				eye.add(new Point3D(0, 0, MOVE_DELTA));
				break;
			case KeyEvent.VK_J:
				eye.sub(new Point3D(0, 0, MOVE_DELTA));
				break;
			case KeyEvent.VK_DOWN:
				lookAt.sub(new Point3D(0, MOVE_DELTA, 0));
				break;
			case KeyEvent.VK_UP:
				lookAt.add(new Point3D(0, MOVE_DELTA, 0));
				break;
			case KeyEvent.VK_LEFT:
				lookAt.sub(new Point3D(MOVE_DELTA, 0, 0));
				break;
			case KeyEvent.VK_RIGHT:
				lookAt.add(new Point3D(MOVE_DELTA, 0, 0));
				break;
			case KeyEvent.VK_I:
				lookAt.add(new Point3D(0, 0, MOVE_DELTA));
				break;
			case KeyEvent.VK_K:
				lookAt.sub(new Point3D(0, 0, MOVE_DELTA));
				break;
			}
		}

		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
		}

		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub			
		}

	}
}
