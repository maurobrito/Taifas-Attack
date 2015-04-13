package graphics2D;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class J2DSpriteTest {
	public static void main(String[] args) {		
		J2DGameWindow window = new J2DGameWindow();
		
		window.setResolution(1024, 768);
		
		window.setTitle("Teste Window");
		
		try {
			J2DSprite sprite = new J2DSprite(window, ImageIO.read(new File("assets/glass.png")));
			System.out.println(sprite.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		window.startRendering();
		
		
	}
	
}
