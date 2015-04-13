package resourcemanager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ResourceManager {

	private Map<String, Resource> resources;

	private String path;
	public ResourceManager(String path) {
		resources = new HashMap<String, Resource>();
		this.path = path;
	}

	public void loadResource(String name, String suffix) throws ResourceNotLoadedException {
		String namePath = path + name + suffix;
		Resource res = new Resource(namePath);
		
		File file = new File(namePath);
		
		if(!file.exists())
			throw new ResourceNotLoadedException("File does not exists.");
		
		try {
			res.setTextureData(ImageIO.read(file));
		} catch (IOException e) {
			throw new ResourceNotLoadedException("Error on reading file from disk.");
		}
		
		resources.put(name, res);
	}

	public BufferedImage getTexture(String texture) {
		return resources.get(texture).getTexture();
	}
}
