package stormTP.core;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

public class Runner{
	
	long id = -1;
	String nom = "";
	String animal = null;    // tortoise or rabbit
	long top = -1;
	int cellule = -1;
	int tour = -1;
	int total = -1;

	
	
	public Runner(){
		
	}
	
	public Runner(long id, String name, String animal, long top, int cellule, int tour, int total){
		this.id = id;
		this.nom = name;
		this.animal = animal;
		this.top = top;
		this.cellule = cellule;
		this.tour = tour;
		this.total = total;

	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getAnimal() {
		return animal;
	}


	public void setAnimal(String animal) {
		this.animal = animal;
	}

	public long getTop() {
		return top;
	}


	public void setTop(long top) {
		this.top = top;
	}


	public int getCellule() {
		return cellule;
	}


	public void setCellule(int cellule) {
		this.cellule = cellule;
	}




	public int getTour() {
		return tour;
	}


	public void setTour(int t) {
		this.tour = t;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	
	
	
	public String getJSON_V1(){
		JsonObjectBuilder r = null;
		r = Json.createObjectBuilder();
		/* construction de l'objet JSON résultat */
		r.add("id", this.id);
		r.add("nom", this.nom);
		r.add("animal", this.animal);
		r.add("top", this.top);
		r.add("cellule", this.cellule);
        r.add("tour", this.tour);
        r.add("total", this.total);
       
        return r.build().toString();
	}
	
	

	
}
