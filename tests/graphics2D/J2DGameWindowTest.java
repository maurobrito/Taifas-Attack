package graphics2D;

import interfaces.IGameWindow;

public class J2DGameWindowTest {

	public static void main(String[] args) {
		IGameWindow window = new J2DGameWindow();
		
		window.setResolution(1024, 768);
		
		window.setTitle("Teste Window");
		
		window.startRendering();
	}

}
