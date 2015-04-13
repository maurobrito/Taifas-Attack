package toweroffense.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HighScoresListener extends Thread {
	private ServerSocket serverSocket;
	private Boolean isActive;
	private IServerCallback callback;
	
	public HighScoresListener(int port, IServerCallback callback) throws IOException {
		super("WaitForConnections");
		serverSocket = new ServerSocket(port);
		this.callback = callback;
		isActive = false;
	}
	
	@Override
	public void run() {
		isActive = true;
		waitForConnections();
	}
	
	public void waitForConnections() {
		callback.showMessage("Aguardando novas conexões...");
		while(isActive) {
			try {
				Socket socket = serverSocket.accept();
				callback.showMessage("Conexão requisitada, aguardando confirmação.");
				HighScoresClientConn conn = new HighScoresClientConn(socket, callback);
				conn.start();
			} catch (IOException e) {
				callback.showMessage("Finalizando o servidor.");
			}
		}
	}
	
	public void stopListener() {
		try {
			serverSocket.close();
			isActive = false;
		} catch (IOException e) {
			callback.showMessage("Não foi possível terminar o serviço de socket.");
		}
	}
}


