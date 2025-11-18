package stormTP.stream;


import java.io.Serializable;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Classe permettant d'émettre un output en UDP.
 */
public class StreamEmiter implements Serializable{
	

	private static final long serialVersionUID = 4262369370788016342L;;
	
	 private int port = -1;

	public StreamEmiter(int port){
			this.port = port;
	}


	public void send(String row){

		try {
			ServerSocket server = new ServerSocket(this.port);

			Socket serverClient = server.accept();  //server accept the client connection request

			BufferedWriter out = new BufferedWriter(
					new OutputStreamWriter(serverClient.getOutputStream()));

			while (true) {

				out.write(row);
				out.newLine();
				out.flush();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}


	@Override
		public String toString(){
			return "StreamEmiter[port="+ this.port +"]";
		}
		

}
