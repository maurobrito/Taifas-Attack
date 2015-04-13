package JOGL;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.jogamp.opengl.util.FPSAnimator;

public class JOGLGameWindow {
	private final JFrame frame;
	JOGLRenderer glRenderer;
	
	public JOGLGameWindow(String title) {
        // Create the top-level container
        frame = new JFrame(); // Swing's JFrame or AWT's Frame
        frame.setTitle(title);
	}

	public void initRenderer(int windowWidth, int windowHeight, int fps) {
		// Create the OpenGL rendering canvas
		glRenderer = new JOGLRenderer();
		glRenderer.setPreferredSize(new Dimension(windowWidth, windowHeight));

		final FPSAnimator animator = new FPSAnimator(glRenderer, fps, true);
		
		frame.getContentPane().add(glRenderer);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// Use a dedicate thread to run the stop() to ensure that the
				// animator stops before program exits.
				new Thread() {
					@Override
					public void run() {
						if (animator.isStarted())
							animator.stop();
						System.exit(0);
					}
				}.start();
			}
		});

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				animator.start(); // start the animation loop
			}
		});

		frame.pack();
		frame.setVisible(true);
	}
	
	
	public JOGLSprite getSprite() {
		return new JOGLSprite(glRenderer);
	}
}
