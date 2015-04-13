package resourcemanager;

import java.awt.image.BufferedImage;


public class Resource {

	private BufferedImage image;
	private String name;
	
	public Resource(String name) {
		this.name = name;
	}

	public BufferedImage getTexture() {

		return image;
	}

	public void setTextureData(BufferedImage bufferedImage) {
		this.image = bufferedImage;
	}
	
	@Override
	public String toString() {
		return name;
	}

}