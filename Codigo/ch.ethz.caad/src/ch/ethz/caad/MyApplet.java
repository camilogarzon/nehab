package ch.ethz.caad;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;




import processing.core.*;
import SimpleOpenNI.*;

import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * A simple Processing Demo Applet
 * 
 * Used to demonstrate the combination of JFrame, JButton, JFileChooser
 * and PApplet
 * 
 * Moves and displays a list of balls on the applet's screen
 * A background image can be loaded
 * 
 * This applet can be used as ActionListener for Java Applications.
 * @author georg munkel
 *
 */
 
public class MyApplet extends PApplet implements ActionListener, MouseMotionListener{
	
	 SimpleOpenNI context;
	 PVector jointPos = new PVector();
	 float lastX=0;
	 float lastY=0;
	 boolean Contained=false;
	 public float J;
	 public float K;
	
	//list of all balls
	ArrayList <Ball> ballList;
	//the background image
	PImage bgImg = null;
	
	@Override
	public void setup() {
		size(640, 480);
		ballList = new ArrayList<Ball>();
		//creates a first ball
		createNewBall();
		//se crea objeto SimpleOpenNI
		context = new SimpleOpenNI(this);
		// Habilito sensor de profundidad
		context.enableDepth();
		// habilito para funcion de skeletondata
		context.enableUser();
	}
	
	@Override
	public void draw() {
		context.startTrackingSkeleton(1);   //trackea para 1er usuario
		//PImage depth = context.depthImage();
		//image(depth, 0, 0);
		IntVector blabla = new IntVector();  // se crea vector para obtener usuarios
		context.update();
		context.getUsers(blabla); // <----------------!!!!!!!!!!!!!


		//check if the background image is already loaded
		//if not, the background is painted white
		if (bgImg == null) {
			background(255);
		} else {
			image(bgImg,0,0, width, height);
		}
		
		//move and display all balls
		for (int i=0; i<ballList.size(); i++) {
			Ball ball = ballList.get(i);
			ball.move();
			ball.display();
			
		}
		if (blabla.size() > 0) {    // si hay almenos 1 usuario siendo "trackeado"
			// get the first user
			int userId2 = blabla.get(0);             // usuario 1
			
			context.getJointPositionSkeleton(userId2,SimpleOpenNI.SKEL_RIGHT_HAND, jointPos);  //obtiene la posicion de la mano derecha
			PVector convertedRightHand = new PVector();   // crea vector para coordenadas convertidas de mano derecha
			

			context.convertRealWorldToProjective(jointPos, convertedRightHand); //convierte a coordenadas en 2D la mano derecha
			
			 J=convertedRightHand.x;   
			 K=convertedRightHand.y;
			
			if(J!=lastX){

				

				float interpolatedX = lerp(lastX, J, 0.3f);       //funcion para evitar cambios bruscos de la bolita
				float interpolatedY = lerp(lastY, K, 0.3f);
				lastX = J;                          
				lastY = K;
				fill(255, 0, 0);
				ellipse(interpolatedX, interpolatedY, 15, 15);         //crea elipse que se situa en las coordenadas de mano derecha
				//a.Mouse((int)X, (int)Y);
				
			}
		}
	}
	
	/**
	 * implementation from interface ActionListener
	 * method is called from the Application
     * the String being compared is the ActionCommand from the button
	 */
	public void actionPerformed(ActionEvent evt) {
		if (evt.getActionCommand().equals("create ball")) {
			createNewBall();
			//Contained=false;
		} else {
			println("actionPerformed(): can't handle " +evt.getActionCommand());
		}
	}
	
	/**
	 * this method is called by the ActionListener asigned to
	 * the JButton buttonLoad in Application
	 */
	public void loadBgImage(File selectedFile) {
		bgImg = loadImage(selectedFile.getAbsolutePath());
	}
	
	/*
	 * creates a new Ball instance and adds it to ballList
	 */
	public void createNewBall() {
		Ball nBall = new Ball();
		ballList.add(nBall);
	}
	
	/*
	 * simple inner class Ball
	 * balls have a position, speed, size and color
	 * the basic constructor assign random values to all properties
	 * 
	 * balls can move
	 * balls can display themselves
	*/
	public  class Ball {
		
		float x;
		float y;
		float size;
		float speedX;
		float speedY;
		Color color;
		
		public Ball() {
			this.size = random(60, 100);
			this.x = random(this.size, width-this.size);
			this.y = random(this.size, height-this.size);
			this.speedX = random(-2, 2) *3;
			this.speedY = random(-2, 2) *3;
			this.color = new Color(random(1), random(1), random(1));
		}
		
		public void move() {
			if (x+size/2f > width || x-size/2f < 0) speedX = -speedX;
			if (y+size/2f > height || y-size/2f < 0) speedY = -speedY;
			x += speedX;
			y += speedY;
			
		}
		
		public void display() {
			
			Ellipse2D el = new Ellipse2D.Float(x, y, size, size);
			Contained=el.contains(J,K);
			if (Contained==false){
			stroke(color.getRGB());
			fill(color.getRGB(), 120);
			ellipse(x, y, size, size);
			//System.out.println(Contained);
			//System.out.println(J);
			
			}

		}
		/* public void mouseDragged(MouseEvent e) {
	        System.out.println("Mouse dragged (" + e.getX() + ',' + e.getY() + ')');
	    }

	  
	    public void mouseMoved(MouseEvent e) {
	    	Ellipse2D el = new Ellipse2D.Float(x, y, size, size);
	    	Contained=el.contains(e.getX(),e.getY());
	    	System.out.println(Contained);


	    } */
		
	}

	
}
