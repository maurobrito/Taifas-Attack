package toweroffense.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HighScoresClientConn extends Thread {
	private Socket socket;
	private IServerCallback callback;
	private DataInputStream is = null;
	private DataOutputStream os = null;
	private Boolean connected = false;
	
	public HighScoresClientConn(Socket socket, IServerCallback callback) {
		this.socket = socket;
		this.callback = callback;
		try {
			is = new DataInputStream(socket.getInputStream());
			os = new DataOutputStream(socket.getOutputStream());
			connected = true;
		} catch (IOException e) {
			callback.showMessage("Não foi possível estabelecer uma comunicação com o cliente.");
		}
	}
	
	@Override
	public void run() {
		String hs = "";
		for(HighscoreEntry he : callback.getHighscores()) {
			hs += he.name + " " + he.score + " ";
		}
		sendData("HS:"+hs);
		
		while(connected) {
			String msg = "";
			try {
				msg = is.readUTF();
			} catch (IOException e) {
				callback.showMessage("A conexão foi encerrada.");
				break;
			}
			
			if(msg.startsWith("HS:")) {
				callback.highscoreReceived(this, msg.substring(3));
			} else if(msg.startsWith("END")) {
				connected = false;
			} else {
				System.err.println("MSG desconhecida: "+ msg);
			}
		}
		
		try {
			os.close();
			is.close();
			socket.close();
		} catch (IOException e) {
			callback.showMessage("Não foi possível finalizar a conexão corretamente.");
		}
	}

	public synchronized void sendData(String string) {
		try {
			os.writeUTF(string);
		} catch (IOException e) {
			callback.showMessage("Não foi possível enviar uma mensagem ao cliente.");
		}
	}
	
	public synchronized void closeConnection() {
		connected = false;
	}
}
