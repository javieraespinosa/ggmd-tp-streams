package main.java.core;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.storm.shade.org.json.simple.JSONObject;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.Random;


public class StreamRunners {

	private int nbRunners = -1;
	private int nbCellules = -1;
	private JsonObject[] runners = null;
	private int[] runnersPos = null;
	private int[] runnersCumul = null;
	public static int top = 1;

	public StreamRunners(){

	}


	public StreamRunners( Racer  r){
		this();
		this.nbRunners = r.getNbRunners();
		this.nbCellules = r.getNbCellules();
		
		this.runners = new JsonObject[this.nbRunners];
		this.runnersPos = new int[this.nbRunners];
		this.runnersCumul = new int[this.nbRunners];
	}
	
	

	public void init(){
		
		// initialisation des position des coureurs
		for(int i = 0 ; i < this.nbRunners ; i++){
			this.runnersPos[i] = 0;
			this.runnersCumul[i] = 0;
		}
	}

	public String getMessage(){
		
		JsonObjectBuilder r = null;
		JsonObject row = null;
		JsonArrayBuilder ar = null;
		
		String res = null;
		
		ar = Json.createArrayBuilder();
		
		this.runners = this.getNewRow();
		
		for( int i = 0 ; i < this.runners.length ; i++ ){
			ar.add( this.runners[i] );
		}	
		
		r = Json.createObjectBuilder();
		r.add("timestamp", System.currentTimeMillis() );
		r.add("runners", ar);
		row = r.build();
		res = row.toString();
		System.out.println( res );
		/*
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(res);
		System.out.println( gson.toJson(je) );
		*/
		return res;
	}

	public JsonObject[] getNewRow(){

		JsonObjectBuilder json = Json.createObjectBuilder();
		JsonObject res = null;
		int tour = 0;
		Random alea = new Random(System.currentTimeMillis());
		int valea = 0;

		top++;

		// affectation des nouvelles positions (soit +1 +2 +3 soit rien)
		for(int i = 0 ; i < this.nbRunners ; i++){
			valea = alea.nextInt(3);
			runnersPos[i] = ( runnersPos[i] + valea) % this.nbCellules ;
			this.runnersCumul[i] += valea;

		tour = this.runnersCumul[i] /  this.nbCellules ;

		json.add("id", i);
		json.add("top", top);
		json.add("tour", tour);
		json.add("cellule", runnersPos[i] );
		json.add("total", this.nbRunners);
		json.add("maxcel", this.nbCellules);

			res = json.build();

		this.runners[i] = res;
		}

		return this.runners;
		
	}
	

}
