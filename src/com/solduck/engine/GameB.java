package com.solduck.engine;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.ShapeRenderer;

public class GameB {
	private static float tempX = 0;
	private static float tempY = 0; // don't forget to subtract later
	public static boolean isStarted = false;
	public static int lineNum = 1;
	public static int firstTime = 0;
	public static boolean mouseCont;
	public static int collide1;
	public static int pScore;
	public static int lives = 10;
	public static Random rndm = new Random();
	public int randomNum = rndm.nextInt((700 - 10) + 1) + 10;
	public int randMass = rndm.nextInt((10 - 1) + 1) + 1;
	ArrayList<GameObject> objects;
	ArrayList<Asteroid> evShapes;
	private ArrayList<GameObject> blocks;
	public ArrayList<ObjTester> other =  new ArrayList<ObjTester>();
	private Block b;
	public GOPlayerV p1;
	public GOWALLV wall4;
	public float[] points2 = new float[8];
	public int  i = 0;
	Polygon extremetemp = new Polygon(points2);
	public Ship ship = new Ship(0,300);
	public GOBallV ball = new GOBallV(100 + (GOPlayerV.SIZEX / 2), 300);
	public Spring spring = new Spring(200, 300, ball);
	public float[] ast1f = {15,10,0,0,7,25,24,40,33,20,30,0};
	Polygon ast1p = new Polygon(ast1f);
	public float[] ast2f = {30,20,0,0,14,50,48,80,66,40,60,0};
	Polygon ast2p = new Polygon(ast2f);
	public Asteroid ast1 = new Asteroid(ball,ast1p, 100, 50);
	public Asteroid ast2 = new Asteroid(ball,ast2p, 200, 50);
	
	public ObjTester tester = new ObjTester(randomNum, 300, 30, 30, randMass,
			ball);// previous
	// parameters:
	// Display.getWidth()
	// / 2 -
	// GOBallV.SIZE
	// /2,
	// Display.getHeight()/2-GOBallV.SIZE/2
	GOWall wall1 = new GOWall(0, 0, GOWall.STDSIZE, Display.getHeight(), ball);
	GOWall wall2 = new GOWall(Display.getWidth() - GOWall.STDSIZE, 0,
			GOWall.STDSIZE, Display.getHeight(), ball);
	GOWALLV wall3 = new GOWALLV(0, 0, Display.getWidth(), 30, ball);

	public GameB() {
		p1 = new GOPlayerV(100, Display.getHeight() - GOPlayerV.SIZEY, ball); // previous
																				// parameters:
																				// Display.getWidth()
																				// /2
			Main.blorp.setLocation(100,100);																	// -GOPlayerV.SIZEX/2,Display.getHeight()-GOPlayerV.SIZEY
			//
		// GOWALLV tunnelB = new GOWALLV(400,500,300,GOWall.STDSIZE,ball);
		// GOWALLV tunnelT = new GOWALLV(400,400,300,GOWall.STDSIZE,ball);
		// GOWall tunnelE = new GOWall(1000,400,GOWall.STDSIZE,100,ball);

		b = new Block(600, 300, 32, 32, ball);
		wall4 = new GOWALLV(300, 0, 30, Display.getHeight(), ball);
		objects = new ArrayList<GameObject>();
		blocks = new ArrayList<GameObject>();
		evShapes = new ArrayList<Asteroid>();
		// objects.add(ball);
		objects.add(p1);
		objects.add(wall1);
		objects.add(wall2);
		objects.add(wall3);
		objects.add(wall4);
		objects.add(spring);
		// objects.add(tunnelB);
		// objects.add(tunnelE);

		// objects.add(tunnelT);
		// blocks.add(b);
	
	}

	public void getInput() {
		if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
			Main.game2.isStarted = false;
			objects.remove(ball);
		}
		

			
			        	
						while (Mouse.next()){
						    if (Mouse.getEventButtonState()) {
						        if (Mouse.getEventButton() == 0) {
						            System.out.println("Left button pressed");
						        }
						    }else {
						        if (Mouse.getEventButton() == 0) {
						            System.out.println("Left button released");
						            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
						            ObjTester tramp = new ObjTester(Mouse.getX(),Display.getHeight()-Mouse.getY(),20,20,1,ball);
									objects.add(tramp);
									other.add(tramp);
						        }
						            if (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
						            	
						            	evShapes.add(ast2);
							        
							          if(i<7){
							        	  if(i==6){
							        		  points2[i] = Mouse.getX();
								        	  points2[i+1]= Display.getHeight()-Mouse.getY();
								        	i=0; 
								        	
									          Polygon temp = new Polygon(points2);
									          Asteroid temp2 = new Asteroid(ball,temp);
									          evShapes.add(temp2);
									          for(int i = 0;i<points2.length;i++)
									          {
									        	  points2[i] = 0;
									          }
								        	
							        	  }
							        	  else if(i==0){
							        		  points2[i] = Mouse.getX();
								        	  points2[i+1]= Display.getHeight()-Mouse.getY();
								        	i+=2;
								        	for(int x = 2;x<points2.length-1;x+=2)
								        	{
								        		points2[x] = points2[0]+3;
								        		points2[x+1] = points2[1]+3;
								        	}
							        	  }
							        	  else{
							        	  points2[i] = Mouse.getX();
							        	  points2[i+1]= Display.getHeight()-Mouse.getY();
							        	i+=2;
							        	
							        	

							        	  }
							        	  extremetemp = new Polygon(points2);
							          }
							          else
							          {
							         // i=0;
							          }
						            
						        }
						    }
						
						    }
						}
						
				
				
				
			}
		
		
		
	

	public void update() {
		ship.update();
		Display.setTitle("Difficulty: " + Main.difficulty + "    Score: "
				+ pScore + "  Time Left: "
				+ (!Main.timeUp ? Main.timeLeft : "") + " lives: " + lives);
		if (Draw.increm < 90 && isStarted) {
			Draw.increm++;
		}
		for (GameObject go : objects)
			go.update();
		for (GameObject go : blocks)
			go.update();
		for(int i = 0;i<evShapes.size();i++)
			evShapes.get(i).update();
		if (ball.getY() >= Display.getHeight()) {
			ball.resetPosition();
			p1.ciX = 100;
			p1.t = 0;
			p1.viX = 0;
			pScore -= 250;
			lives--;
		}
		for (int i = 0; i < blocks.size() - 1; i++) {

			if (i < blocks.size()
					&& Physics.checkCollisions(blocks.get(i), ball)
					&& Physics.checkCollisions(blocks.get(i + 1), ball)) {
			}

		}
	}

	public void render() // i sort of tied this in with update
	{
		ShapeRenderer.draw(ship.body);
		ShapeRenderer.draw(extremetemp);
		// Main.base.draw(ball.x,ball.y,0.5f);
		for(int i = 0;i<evShapes.size();i++)
		{
			ShapeRenderer.draw(evShapes.get(i).body);
		}
		//Shape shithole = dorp.transform(Transform.createRotateTransform((float)Math.toRadians(45),50f,50f));
	
		if (pScore < 0) {
			if (Draw.color > 0.1f)
				Draw.color = Draw.color - 0.01f;
			else {
				Draw.color = 1f;
			}
		}
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i).destroyed == true) {

				objects.get(i).render3();
				if (objects.get(i).increm2 == 180) {
					objects.get(i).increm2 = 0;
				} else if (objects.get(i).increm2 < 181) {
					objects.get(i).increm2++;
				}

				// blocks.remove(i);
			} else if (objects.get(i).equals(ball)) {
				objects.get(i).renderBall();
			} else if (objects.get(i).equals(p1)) {
				objects.get(i).render();
			} else {
				objects.get(i).render();
			}

		}
		for (int i = 0; i < blocks.size(); i++) {
			if (blocks.get(i).destroyed == true) {

				blocks.get(i).render3();
				if (blocks.get(i).increm2 == 180) {
					blocks.remove(i);
					pScore += 700;
					i--;
				} else if (blocks.get(i).increm2 < 181) {
					blocks.get(i).increm2++;
				}

				// blocks.remove(i);
			} else {
				blocks.get(i).render();
			}
		}
		if (!objects.contains(ball) && isStarted) {
			// tester = new ObjTester(500, 300, 16, 16, 1, ball);
			objects.add(ball);
			objects.add(tester);
			other.add(tester);
			evShapes.add(ast1);
			ast2.body.setX(300);
			//evShapes.add(ast2);
		}

		if (firstTime == 0) {
			tempX = 800;
			tempY = 300;
			for (int i = 0; i < 30; i++) {
				b = new Block(tempX, tempY, 60, 20, ball);
				ObjTester torp = new ObjTester(tempX, tempY, 30, 30, 1, ball);
				blocks.add(b);
				// objects.add(torp);
				if (tempX >= 850) {
					tempY += 21;
					if (lineNum % 2 != 0) {
						tempX = 830;
					} else {
						tempX = 800;
					}
					lineNum++;
				} else {
					tempX += 62;
				}
			}
			firstTime = 1;
		}
		// for(GameObject go: objects ){
		// go.render();
	}

	public void removeFieldObj(GameObject go) {
		for (int i = 0; i < blocks.size(); i++) {
			if (blocks.get(i).equals(go)) {
				blocks.remove(i);
			}
		}
	}

}
