package graphics2D;

import static javax.media.opengl.GL.*;
import static javax.media.opengl.GL2.*;

import java.io.IOException;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLException;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class Sprite {
	int x;
	int y;
	int width;
	int height;
	
	Texture texture;
	private String textureFileName = "Doente1.png";
	   private String textureFileType = ".png";
	public Sprite(){
		x = 0;
		y = 0;
		width = 0;
		height = 0;
	}
	
	public void draw(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glLoadIdentity();
		gl.glTranslatef(0.0f, 0.0f, -3.0f);
	    
		gl.glBegin(GL_QUADS);
		gl.glVertex3f(0.0f, 	0.0f, 	0.0f);
		gl.glVertex3f(-1.0f, 	0.0f, 	0.0f);
		gl.glVertex3f(-1.0f, 	-1.0f, 	0.0f);
		gl.glVertex3f(0.0f, 	-1.0f, 	0.0f);

		gl.glEnd();
	}
	
	public void loadTexture(GLAutoDrawable drawable)
	{
	}
}
