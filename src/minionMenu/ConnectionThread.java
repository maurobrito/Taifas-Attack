package minionMenu;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectionThread extends Thread{
	private int port;
	private String addr;
	private static DataOutputStream os = null;
	private static DataInputStream is = null;
	  
	private Socket socket;
	private Boolean connected = false;
	private IClientCallback callback;
	
	public ConnectionThread(int port, String addr, IClientCallback callback) {
		this.port = port;
		this.addr = addr;
		this.callback = callback;
	}
	
	@Override
	public void run() {
		try {
			socket = new Socket(addr, port);
			os = new DataOutputStream(socket.getOutputStream());
		    is = new DataInputStream(socket.getInputStream());
			callback.connected();
			connected = true;
		} catch (UnknownHostException e) {
			callback.Error(e.getMessage());
		} catch (IOException e) {
			callback.Error(e.getMessage());
		}

		while(connected) {
			String msg = "";
			try {
				msg = is.readUTF();
			} catch (IOException e) {
				callback.Error(e.getMessage());
				break;
			}
			
			if(msg.startsWith("HS:")) {
				callback.highscoreReceived(msg.substring(3));
			} else {
				System.err.println("MSG desconhecida: "+ msg);
			}
		}
		
		try {
			if(os != null)
				os.close();
			if(is != null)
				is.close();
		} catch (IOException e) {
			callback.Error(e.getMessage());
		}
	}
	
	public synchronized void closeConnection() {
		if(socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void sendMessage(String msg) {
		if(!connected)
			return;
		
		try {
			os.writeUTF(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
