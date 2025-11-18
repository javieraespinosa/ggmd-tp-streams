package stormTP.stream;

import java.io.Serializable;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Classe permettant aux spout d'écouter un flux UDP
 */

public class StreamBuffer implements Serializable{

	private static final long serialVersionUID = 1L;
	private Queue<String> fifo = null;
	private int port = -1;
	String host = "";

	
	public StreamBuffer(String host,int port){
		    this.host = host;
			this.port = port;
			this.fifo = new ConcurrentLinkedQueue<String>();
	}
	
	
	public void listenStream()
    {

		try (Socket socket = new Socket(host, port)) {
			System.out.println("Connected to server.");

			BufferedReader in = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));

			// Thread qui écoute le flux du serveur
			new Thread(() -> {
				try {
					String data = null;
					while ((data = in.readLine()) != null) {
						System.out.println( "->" +  data + "\n");
						this.fifo.add( data );
					}
				} catch (IOException ignored) {}
			}).start();

			socket.close();


			// Le programme reste ouvert tant que la connexion est active
			Thread.sleep(Long.MAX_VALUE);



		} catch (Exception e) {
			e.printStackTrace();
		}finally{

			System.out.println("ListenSocket closed");
		}

	}
	
	
	 public String readTuple() throws Exception {
	      return this.fifo.poll();
		   
	  }

}
