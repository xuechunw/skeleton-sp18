import java.util.Scanner; 
import java.io.*; 


public class NBody {

	private Integer readNumber(String fileName) {
		In in = new In(fileName);
		int numOfPlanets = in.readInt();

		return numOfPlanets;
	}

	static public double readRadius(String fileName) {
		In in = new In(fileName);
		int first = in.readInt();
		double second = in.readDouble();

		return second;
	}

	static public Planet[] readPlanets(String fileName) {
		In in = new In(fileName);

		int numOfPlanets = in.readInt();
		double second = in.readDouble();

		int i,j;
		int n = 1;
		double third, fourth, fifth, sixth, seventh;
		String eighth;
		Planet newPlanet;
		Planet[] planets = new Planet[numOfPlanets];

		for (j=0; j<numOfPlanets; j++) {
	
			third = in.readDouble();
			fourth = in.readDouble();
			fifth = in.readDouble();
			sixth = in.readDouble();
			seventh = in.readDouble();
			eighth = in.readString();

			newPlanet = new Planet(third, fourth, fifth, sixth, seventh, eighth);
			planets[j]=newPlanet;

		}

		return planets;

	}






		public static void main(String[] args){


			NBody nbody = new NBody();

			double T = Double.parseDouble(args[0]);  // Read user input
			double dt = Double.parseDouble(args[1]); 

			String filename = args[2]; 

			int numOfPlanets = nbody.readNumber(filename);
			double radius = nbody.readRadius(filename);
			Planet[] planets = nbody.readPlanets(filename);

			StdDraw.setScale(-radius,radius);
			StdDraw.picture(0, 0, "images/starfield.jpg",2*radius,2*radius); //centered
			for(int i=0;i<planets.length;i++) {
					planets[i].draw();

				}

			StdDraw.enableDoubleBuffering();

			Double[] xForces = new Double[numOfPlanets];
			Double[] yForces = new Double[numOfPlanets];


			StdDraw.picture(0, 0, "images/starfield.jpg",2*radius,2*radius);

			double time;

			for(time=0; time<=T;time=time+dt){
		
				for(int i=0; i<numOfPlanets;i++){

					xForces[i] = planets[i].calcNetForceExertedByX(planets);
					yForces[i] = planets[i].calcNetForceExertedByY(planets);

				}

				for(int j=0; j<numOfPlanets;j++){
					planets[j].update(dt,xForces[j],yForces[j]);

				}

				StdDraw.picture(0, 0, "images/starfield.jpg",2*radius,2*radius); //centered
				
				for(int i=0;i<planets.length;i++) {
					planets[i].draw();

				}
				StdDraw.show();
				StdDraw.pause(20);

			 }

			 if(time==T){
			 	StdOut.printf("%d\n", planets.length);
				StdOut.printf("%.2e\n", radius);
				for (int i = 0; i < planets.length; i++) {
    			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                 planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                 planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
				}

			 }


	}


}