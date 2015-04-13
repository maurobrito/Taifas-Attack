package toweroffense.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import toweroffense.highscoresserver.ServerFrame;

public class HighScoresServer implements IServerCallback{
	private HighScoresListener listener;
	
	private static ServerFrame serverFrame;
	private static HighScoresServer server;
	
	private List<HighscoreEntry> highscores;
	
	public HighScoresServer() {
		highscores = new ArrayList<>();
	}
	
	public static void main(String[] args) {
		serverFrame = new ServerFrame();
		server = new HighScoresServer();
		serverFrame.setTitle("Taifas Attack Highscores Server v0.1");
		serverFrame.buildFrame();
		serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		serverFrame.setCallback(server);
	}

	@Override
	public String[] getInfo(int selectedIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void startServer() {
		@SuppressWarnings("resource")
		String hsLine = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader("assets/Highscores.txt"));
			hsLine = br.readLine().trim();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.err.println(hsLine);
		
		String delimiter = " ";
		String[] tokens = hsLine.split(delimiter);
		
		for(int i = 0; i < 5; i++){
			HighscoreEntry he = new HighscoreEntry();
			he.name = tokens[2*i];
			he.score = Integer.valueOf(tokens[2*i+1]);
			highscores.add(he);
		}

		try {
			listener = new HighScoresListener(8000, this);
			listener.setDaemon(true);
			serverFrame.println("O Servidor está funcionando na porta " + 8000 + ".");
			listener.start();
			serverFrame.setOnline();
		} catch (IOException e) {
			serverFrame.setOffline();
			serverFrame.println("Não foi possível iniciar o servidor.");
		}
	}

	@Override
	public void closeServer() {
		listener.stopListener();
		serverFrame.setOffline();
	}

	@Override
	public void showMessage(String string) {
		serverFrame.println(string);
	}

	@Override
	public void highscoreReceived(HighScoresClientConn conn, String highscore) {
		
		String[] tokens = highscore.trim().split(" ");
		String nome = "";
		int c = 0;
		for(c=0; c < tokens.length - 1; c++) {
			nome += tokens[c];
		}
		int score = Integer.valueOf(tokens[c]);
		String lastNome = nome;
		int lastScore = score;
		for(int i = highscores.size() - 1; i >= 0; i--) {
			if(score > highscores.get(i).score) {
				if(i == 4) {
					highscores.get(i).name = nome;
					highscores.get(i).score = score;
				} else {
					lastNome = highscores.get(i).name;
					lastScore = highscores.get(i).score;
					highscores.get(i).name = nome;
					highscores.get(i).score = score;
					highscores.get(i+1).name = lastNome;
					highscores.get(i+1).score = lastScore;
				}
			}
		}
		saveHighscores();
	}
	
	@Override
	public List<HighscoreEntry> getHighscores() {
		return highscores;
	}
	
	public void saveHighscores() {
		PrintWriter writer;
		try {
			writer = new PrintWriter("assets/Highscores.txt", "UTF-8");
			for(HighscoreEntry he : highscores) {
				writer.print(he.name + " " + he.score + " ");
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
}
