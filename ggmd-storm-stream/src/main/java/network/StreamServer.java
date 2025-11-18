package main.java.network;

import main.java.core.StreamRunners;


import java.net.*;
import java.text.SimpleDateFormat;

import java.io.*;

import java.io.IOException;

public class StreamServer {

	private int port = -1;
	private int delay = 0;


	public StreamServer(String animals, int port) {
		this.port = port;
		this.delay = this.getDelay(animals);

	}

	/*
	affectation des latences en fonction du type d'animal
	* */
	private int getDelay(String a) {

		switch (a) {
			case "tortoise":
				return 5000;
			case "rabbit":
				return 100;
			default:
				return 1000;
		}

	}


	public void send(StreamRunners sr) throws Exception {

		try {
			ServerSocket server = new ServerSocket(this.port);

			int counter = 0;
			System.out.println("Server Started ....");
			Socket serverClient = server.accept();  //server accept the client connection request

			BufferedWriter out = new BufferedWriter(
					new OutputStreamWriter(serverClient.getOutputStream()));

			while (true) {

				out.write(sr.getMessage());
				out.newLine();
				out.flush();
				try {
					Thread.sleep(this.delay);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
			/*	udpSocket = new DatagramSocket();
				mcIPAddress = InetAddress.getByName(this.ipUDP);
				group = new InetSocketAddress( mcIPAddress, this.port );
				netIf = NetworkInterface.getByName("enp1s0");
				skt = new MulticastSocket( this.port );
				packet = null;

				skt.joinGroup( new InetSocketAddress(mcIPAddress, 0), netIf );
				byte[] msg = null;
				json = row.getMessage();
				msg = json.getBytes();
				try{
					packet = new DatagramPacket(msg, msg.length, group);
					//packet.setAddress(mcIPAddress);
					//packet.setPort(this.port);
					skt.send(packet);

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
//				System.out.println( sdf.format(System.currentTimeMillis()) + ":" + json.substring(0,150)   );
					System.out.println( sdf.format(System.currentTimeMillis()) + ":" + json  );

				}catch(IOException e){ e.printStackTrace();}



				try
				{
					fr = new FileReader (f);
					br = new BufferedReader (fr);

					try
					{
						line = br.readLine();

						go = ( line.equals("GO") );

						br.close();
						fr.close();
					}
					catch (IOException e){ e.printStackTrace();}
				}
				catch (FileNotFoundException ex){ex.printStackTrace();}




				//}
				try {
					Thread.sleep(this.delay );
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				cpt++;


			}

		}catch(IOException e){
			e.printStackTrace();
		}finally{
			udpSocket.close();
		}

	}

}*/

/*package main.java.network;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import main.java.core.Row;
import main.java.core.*;

public class StreamServer {

	public static final String STATEFILE = "./streams/streamstate";
	private int id = -1;
	private int port = -1;
	private String ipUDP = "";
	
	private String infoEmit = "";
	private boolean go = true;
	private double cpt = 0;
	
	
	
	
	public StreamServer(int numRoom){
		this.port = 9000 + numRoom;
		this.ipUDP = "224.0.0." + numRoom;
		this.go = true;
			
	}
	
	
	 public  void send(JsonStream stream) throws IOException {
	 
	 int j;
	 DatagramSocket udpSocket = null;
	 InetAddress mcIPAddress = null;
	 DatagramPacket packet = null;
	 int cpt = 0;
	 
	 File f = new File (STATEFILE);
	 FileReader fr = null;
	 BufferedReader br = null;
	 String line = null;
	 
	 try{
		 
	    udpSocket = new DatagramSocket();
	    
	    packet = null;
	    byte[] msg = null;
	    String message = null;
	    String[] rows = null;
	   	    
		System.out.println("Sending...");
		
		cpt = 1;
		
		while( go ){
		 
		// rows = stream.getNewRow(cpt);
			
	//	for( j = 1 ; j <= rows.length ; j++ ){	
			message = stream.getMessage();
			msg = message.getBytes(); 
			//rows[j-1].getBytes();
		
			//mcIPAddress = InetAddress.getByName(this.ipUDP + j);
			mcIPAddress = InetAddress.getByName(this.ipUDP);
			 		
			packet = new DatagramPacket(msg, msg.length);
			packet.setAddress(mcIPAddress);
			//packet.setPort(this.port+j);
			packet.setPort(this.port);
			udpSocket.send(packet);
 		
 		
			//System.out.println("row:"+ rows[j-1] + "; timestamp: " + System.currentTimeMillis() + "; " + (this.ipUDP + j) + "; " + (this.port+j));
			System.out.println("rows at timestamp: " + System.currentTimeMillis() + " via IP: " + this.ipUDP + " and port: " + this.port);
			System.out.println(message);
			try
			{
			    fr = new FileReader (f);
			    br = new BufferedReader (fr);
			 
			    try
			    {
			        line = br.readLine();
			        
			        go = ( line.equals("GO") );
			      
			        br.close();
			        fr.close();
			    }
			    catch (IOException e){ e.printStackTrace();}
			}
			catch (FileNotFoundException ex){ex.printStackTrace();}
			
			
		
 
 		//}
		try {
		Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		cpt++;
		
		
	 }
		
	 }catch(IOException e){
		 e.printStackTrace();
	 }finally{		
	    udpSocket.close();
	 }
	    
}
	

}
*/