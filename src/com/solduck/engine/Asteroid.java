package com.solduck.engine;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

public class Asteroid {
	private GOBallV ball;
	public static float sizeX = 60;
	public static float sizeY = 20;
	public boolean oneCol = false;
	public float mass = 0.4f;
	public float viX = 0f;
	public float viY = 0f;
	public float velY = 0;
	public float t = 0;
	public float regX = 0;
	public float regY = 0;
	public int i = 0;
	public int count = 0;
	public static float A = 0f;
	public Polygon body;
	public boolean collided = false;
	public Asteroid n;

	public Asteroid(GOBallV ball, Polygon tempest) {

		this.ball = ball;
		this.body = tempest;
		
		regX = body.getX();
		regY = body.getY();
		mass = (body.getMaxY() - body.getMinY()) * .09f;
	
	}
	public Asteroid(GOBallV ball, Polygon tempest, float x ,float y) {

		this.ball = ball;
		this.body = tempest;
		body.setLocation(x,y);
		body.setX(x);
		regX = x;
		regY = y;
		mass = (body.getMaxY() - body.getMinY()) * .09f;
		
	
	}

	public void update() {
		float[] experiment = {Mouse.getX(),Display.getHeight()-Mouse.getY(),Mouse.getX(),Display.getHeight()-Mouse.getY()-20,Mouse.getX()+20,Display.getHeight()-Mouse.getY()-20,Mouse.getX()+20,Display.getHeight()-Mouse.getY()};
		Polygon experimentP = new Polygon(experiment);
		
		
		
		if (Physics.doesCollide(this.body, Main.doop) && ball.count == 0) {
			ball.momentum();
			A = ball.A;
			
			if (Main.difficulty.equals("normal")) {
				ball.count = 1;
			}
			float tempXO = ball.viX;
			float tempYO = ball.velY;
			float tempXO2 = viX;

			viX = (((2 * ball.mass) / (ball.mass + mass)) * ball.viX)
					- (((ball.mass - mass) / (ball.mass + mass)) * viX);

			viY = (((2 * ball.mass) / (ball.mass + mass)) * ball.velY)
					- (((ball.mass - mass) / (ball.mass + mass)) * velY);
			regX = this.body.getX() + 1;
			regY = this.body.getY();
			t = 0;
			ball.viX = (((ball.mass - mass) / (ball.mass + mass)) * tempXO)
					+ (((2 * mass) / (ball.mass + mass)) * tempXO2);
			ball.viY = (((ball.mass - mass) / (ball.mass + mass)) * tempYO)
					+ (((2 * mass) / (ball.mass + mass)) * velY);
		}

		else {
			for (int i = 0; i < Main.game2.evShapes.size(); i++) {
				if (Mouse.isButtonDown(1) && (Main.game2.evShapes.get(i).body.contains(experimentP) || experimentP.intersects(Main.game2.evShapes.get(i).body)))
				{
					Polygon temp = Main.game2.evShapes.get(i).body;
					Main.game2.evShapes.get(i).body.setX(Mouse.getX()-(temp.getWidth()/2));
					Main.game2.evShapes.get(i).body.setY(Display.getHeight()-Mouse.getY()-temp.getHeight());
					Main.game2.evShapes.get(i).t = 0;
					Main.game2.evShapes.get(i).regX = (Mouse.getX()-(temp.getWidth()/2));
					Main.game2.evShapes.get(i).regY = (Display.getHeight()-Mouse.getY()-temp.getHeight()/2);
					Main.game2.evShapes.get(i).viY = 0;
					Main.game2.evShapes.get(i).viX = 0;
					//this.y = Display.getHeight() - Mouse.getY() - this.sy;
					//this.x = Mouse.getX() - this.sx / 2;
				}
				if (Physics.doesCollide(this.body, Main.game2.evShapes.get(i).body) && !Main.game2.evShapes.get(i).equals(this) && collided == false) {
				
					float tempXO = Main.game2.evShapes.get(i).viX;
					float tempYO = Main.game2.evShapes.get(i).velY;
					float tempXO2 = viX;

					viX = (((2 * Main.game2.evShapes.get(i).mass) / (Main.game2.evShapes
							.get(i).mass + mass)) * Main.game2.evShapes.get(i).viX)
							- (((Main.game2.evShapes.get(i).mass - mass) / (Main.game2.evShapes
									.get(i).mass + mass)) * viX);

					viY = (((2 * Main.game2.evShapes.get(i).mass) / (Main.game2.evShapes
							.get(i).mass + mass)) * Main.game2.evShapes.get(i).velY)
							- (((Main.game2.evShapes.get(i).mass - mass) / (Main.game2.evShapes
									.get(i).mass + mass)) * velY);
					regX = this.body.getX() + 1;
					regY = this.body.getY();
					t = 0;
					Main.game2.evShapes.get(i).viX = (((Main.game2.evShapes
							.get(i).mass - mass) / (Main.game2.evShapes.get(i).mass + mass)) * tempXO)
							+ (((2 * mass) / (Main.game2.evShapes.get(i).mass + mass)) * tempXO2);
					Main.game2.evShapes.get(i).viY = (((Main.game2.evShapes
							.get(i).mass - mass) / (Main.game2.evShapes.get(i).mass + mass)) * tempYO)
							+ (((2 * mass) / (Main.game2.evShapes.get(i).mass + mass)) * velY);
					Main.game2.evShapes.get(i).t = 0;
					Main.game2.evShapes.get(i).regX = Main.game2.evShapes
							.get(i).body.getX();
					Main.game2.evShapes.get(i).regY = Main.game2.evShapes
							.get(i).body.getY();
					if (collided == false) {
						collided = true;
						
						float[] points2 = body.getPoints();
						float[] extratemp = { (body.getCenterX()),
								(body.getMinY()), 
								(points2[0]), (points2[1]),
								(points2[2]), (points2[3]), 
								(points2[4]),
								(points2[5]), 
								(body.getCenterX()),(body.getMaxY()) 
								
						};
						float[] extratemp2 = { (body.getCenterX()),
								(body.getMinY()), (body.getCenterX()),
								(body.getMaxY()), (points2[6]), (points2[7]),
								(points2[8]), (points2[9]), (points2[10]),
								(points2[11]) };
						Polygon neff = new Polygon(extratemp);
						body = neff;
						Shape[] tempist = body.union(Main.game2.evShapes.get(i).body);
						Asteroid h = new Asteroid(ball, neff);
						// Main.game2.evShapes.add(h);
						Polygon temp = new Polygon(extratemp2);
						n = new Asteroid(ball, temp,body.getX(),body.getY());
						n.collided = true;
						n.viX = this.viX*-1;
						n.viY = this.viY*-1;
						
						Main.game2.evShapes.add(n);

					}
				}

			}

			/*
			 * t = t + (float) (0.08f); // this code uses the simpler but less
			 * accurate DAVID constant temp.setX(viX * t + regX); temp.setY((viY
			 * * t) + (0.5f * A * t * t) + regY); velY = viY + A * t;
			 */
			//body = new Polygon(points2);
			System.out.println(Main.game2.evShapes.size());
			t = t + (float) (0.0166f);
			body.setX((viX * t + (regX) / GOBallV.scale) * GOBallV.scale); // 10
																			// is
																			// the
																			// scale
																			// factor
																// :
																			// 10
																			// pixels
																			// per
																			// meter
			body.setY(((viY * t) + (0.5f * A * t * t) + (regY) / GOBallV.scale)
					* GOBallV.scale);
			velY = viY + (A * t);
		}
	}
}
