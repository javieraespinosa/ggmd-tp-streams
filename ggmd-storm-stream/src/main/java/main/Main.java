package main.java.main;

import main.java.core.StreamRunners;
import main.java.core.Racer;
import main.java.network.StreamServer;
//import network.StreamServer;

public class Main {
    /*
     * Fonction principale de lancement de flux
     * */
    public static void main(String[] args){
        try{

            // récupération des arguments
            String typeS = args[0];   // tortoise or rabbit
            String nbR = args[1];          // nombre de coureurs
            String nbC = args[2];          // nombre de cellules
            String port = args[3];         // port de communication TCP
            // affichage des arguments récupérés
            System.out.println("Racer properties : \n  - Animals: " + typeS
		 				+ " \n  - Nb of runners: " + nbR 
						+ " \n  - Nb of cells: " + nbC 
						+ " \n  - Port: " + port);

            // initialisation des paramètres
            Racer course = new Racer();
            course.setAnimals(typeS);
            course.setNbRunners(Integer.parseInt(nbR));
            course.setNbCellules(Integer.parseInt(nbC));

            // création du flux
            StreamRunners streamR = new StreamRunners( course );
            streamR.init();

            // publication du flux
            StreamServer sserver = new StreamServer( typeS , Integer.parseInt(port) );
            sserver.send( streamR );


        }catch( Exception e ){
            System.out.println( "ERROR : \n" +
                    "arg1 : kind of runner : tortoise or rabbit\n" +
                    "arg2 : number of runners  (more than one)\n" +
                    "arg3 : number of cells (more than two)" +
		    "arg3 : port to emit the stream.");
            System.out.println(e);
        }
    }

}
