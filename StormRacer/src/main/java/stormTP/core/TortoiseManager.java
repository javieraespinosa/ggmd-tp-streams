package stormTP.core;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

public class TortoiseManager {
	
	public static final String CONST = "C'est mieux!";
	public static final String PROG = "Statu quo";
	public static final String REGR = "ça sera sûrement mieux plus tard!";
	
	String nomsBinome ="";
	long dossard = -1;
	
	public TortoiseManager(long dossard, String nomsBinome){
		this.nomsBinome = nomsBinome;
		this.dossard = dossard;
	}
	
	
	/**
	 * Permet de filtrer les informations concernant votre tortue
	 * @param input
	 * @return
	 */
	public Runner filter(String input){
		
		Runner tortoise = null;
		
		//@TODO
		
		
		return tortoise;
				
				
	}
	
	
	//@TODO
	
		

	
	
	

}
