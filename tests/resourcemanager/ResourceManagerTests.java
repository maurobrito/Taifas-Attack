package resourcemanager;

public class ResourceManagerTests {
	public static void main(String[] args) {
		ResourceManager resTest = new ResourceManager("assets/");
		
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		
		try {
			resTest.loadResource("star", ".bmp");
			resTest.getTexture("star");
		} catch (ResourceNotLoadedException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("Falha ao carregar o arquivo");
		}
	}
}