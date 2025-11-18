package main.java.core;

public class Racer {
    String animals = null;
    int nbRunners = 0;
    int nbCells = 0;
    int port = 0;

public Racer(){

}

    public String getAnimals() {
        return animals;
    }

    public int getNbRunners() {
        return nbRunners;
    }

    public int getNbCellules() {
        return nbCells;
    }

    public int getPort() {
        return port;
    }

    public void setAnimals(String animals) {
        this.animals = animals;
    }

    public void setNbRunners(int nbRunners) {
        this.nbRunners = nbRunners;
    }

    public void setNbCellules(int nbCells) {
        this.nbCells = nbCells;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
