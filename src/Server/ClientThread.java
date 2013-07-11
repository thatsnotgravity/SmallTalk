package Server;

import Client.SmallTalkClient;

import java.io.DataInputStream;
import java.net.Socket;


public class ClientThread extends Thread {
	private Socket socket = null;
	private SmallTalkClient client = null;
	private DataInputStream streamIn = null;

	/**
	 * Constructor for ClientThread
	 *
	 * @param client The SmallTalkClient object to supply.
	 * @param socket The Socket Object to bind too.
	 */
	public ClientThread(SmallTalkClient client, Socket socket) {
		this.client = client;
		this.socket = socket;
		open();
		start();
	}

	/**
	 * Opens the stream in to receive information from the socket
	 */
	public void open() {
		try {
			streamIn = new DataInputStream(socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Closes the stream
	 */
	public void close() {
		try {
			if (streamIn != null)
				streamIn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Runs in the current thread.
	 * (non-Javadoc)
	 *
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		while (true) {
			try {
				System.out.println("running");
			} catch (Exception e) {
				e.printStackTrace();
				try {
					client.stop();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
