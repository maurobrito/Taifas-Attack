package gamelogic.scenary;

import gameviews.MinionViewsMediator;
import interfaces.IGameWindow;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import util.Vector2D;

//create the Scenary, putting the background
@SuppressWarnings("unused")
public class ScenaryFactory {
	
	//constructor
	public static Scenary createScenary(String fileName) {
		Scenary scenary = new Scenary();

		float tileSize = 64.0f;
		float pixelAdjust = 32.0f;
		float spriteAdjust = 0.0f;
		
		MapObject map = new MapObject();
		//reads the file with the map info and creates the map
		try{
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			
			map.height = Integer.parseInt(br.readLine());
			map.width = Integer.parseInt(br.readLine());
			map.matrix = new int[map.height][map.width];
			
			String delimiter = " ";
			for(int row = 0; row<map.height; row++){
				String line = br.readLine();
				String[] tokens = line.split(delimiter);
				for(int col=0; col<map.width; col++){
					map.matrix[row][col] = Integer.parseInt(tokens[col]);
				}
			}
		}
		catch(Exception e){
			System.err.println(e.getMessage());
		}
	
		scenary.setMap(map);

		//defines the path, the start point and the end point
		scenary.setStartPoint(new Vector2D(tileSize + pixelAdjust + spriteAdjust, pixelAdjust + spriteAdjust));
		scenary.setEndPoint(new Vector2D(scenary.getStartPoint().getX() + 9*tileSize, scenary.getStartPoint().getY()));
		
		ArrayList<Vector2D> path = new ArrayList<>();
		path.add(new Vector2D(scenary.getStartPoint().getX(), scenary.getStartPoint().getY() + 2*tileSize));
		path.add(new Vector2D(scenary.getStartPoint().getX() + 5*tileSize, scenary.getStartPoint().getY() + 2*tileSize));
		path.add(new Vector2D(scenary.getStartPoint().getX() + 5*tileSize, scenary.getStartPoint().getY() + 5*tileSize));
		path.add(new Vector2D(scenary.getStartPoint().getX(), scenary.getStartPoint().getY() + 5*tileSize));
		path.add(new Vector2D(scenary.getStartPoint().getX(), scenary.getStartPoint().getY() + 8*tileSize));
		path.add(new Vector2D(scenary.getStartPoint().getX() + 9*tileSize, scenary.getStartPoint().getY() + 8*tileSize));
		path.add(scenary.getEndPoint());
		
		scenary.setPath(path);
		
		return scenary;
	}
}
