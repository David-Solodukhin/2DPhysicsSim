package com.solduck.engine;
import org.lwjgl.opengl.Display;

public class ObjTester extends GameObject {
	private GOBallV ball;
	public static float sizeX = 60;
	public static float sizeY = 20;
	public boolean oneCol = false;
	public float mass = 0.4f;
	public float viX = -2f;
	public float viY = -4f;
	public float velY = 0;
	public float t = 0;
	public float regX = 0;
	public float regY = 0;
	public static float A = 9.8f;
	public ObjTester(float x, float y, float sx, float sy, float mass,
			GOBallV ball) {

		this.x = x;
		regX = this.x;
		this.y = y;
		regY = this.y;
		this.sx = sx;
		this.sy = sy;
		this.ball = ball;
		this.mass = mass;
	}

	public void update() {
		//System.out.println(viX+ " " + ball.viX);
		if (Physics.checkCollisions(this, ball) && ball.count != 1) {
			ball.momentum();
			if(Main.difficulty.equals("normal")){
			ball.count = 1;}
			float tempXO = ball.viX;
			float tempYO = ball.velY;
			float tempXO2 = viX;
			
			viX = (((2 * ball.mass) / (ball.mass + mass)) * ball.viX)
					- (((ball.mass - mass) / (ball.mass + mass)) * viX);
			
			viY = (((2 * ball.mass) / (ball.mass + mass)) * ball.velY)
					- (((ball.mass - mass) / (ball.mass + mass)) * velY);
			regX = this.x+1;
			regY = this.y;
			t = 0;
			ball.viX = (((ball.mass - mass) / (ball.mass + mass)) * tempXO)
					+ (((2 * mass) / (ball.mass + mass)) * tempXO2);
			ball.viY = (((ball.mass - mass) / (ball.mass + mass)) * tempYO)
					+ (((2 * mass) / (ball.mass + mass)) * velY);
		//	System.out.println(viX + " " + ball.viX);
		//	System.exit(1);
		} else {
			
			/*
			t = t + (float) (0.08f);
			this.x = viX * t + regX;
			this.y = (viY * t) + (0.5f * A * t * t) + regY;
			velY = viY + A * t;
			*/
			t = t + (float) (0.0166f);
			this.x = (viX * t + (regX)/GOBallV.scale)*GOBallV.scale;
			this.y = ((viY * t) + (0.5f * A * t * t) + (regY)/GOBallV.scale)*GOBallV.scale;
			velY = viY + (A * t);
		}
		if (Physics.checkCollisions(this, Main.game2.wall3)) {
			reverseY();
			
		}
		else if (Physics.checkCollisions(this, Main.game2.wall1) || Physics.checkCollisions(this, Main.game2.wall2)) {
			t = 0;

			regY = this.y;
			if (viX < 0) {
				regX = this.x + 2;
			}

			else {
				regX = this.x - 5;
			}
			
			viY=velY*.75f;
			viX *= -.75f;
			System.out.println("Collision-----------------------");
			
			
			
			
		}
		/*
		 * ONCOLLISION
		 * 
		 * 
		 * viX = (((2*ball.mass)/(ball.mass+mass))*ball.viX) -
		 * (((ball.mass-mass)/(ball.mass+mass))*viX); viY =
		 * (((2*ball.mass)/(ball.mass+mass))*ball.velY) -
		 * (((ball.mass-mass)/(ball.mass+mass))*velY);
		 */
		
		
		
		
		
		
		  for(int i = 0;i<Main.game2.other.size();i++)
		  {
		 		if(Physics.checkCollisions(this,Main.game2.other.get(i)) && !Main.game2.other.get(i).equals(this))
		 			{
		 			float tempXO = Main.game2.other.get(i).viX;
			float tempYO = Main.game2.other.get(i).velY;
			float tempXO2 = viX;
			
			viX = (((2 * Main.game2.other.get(i).mass) / (Main.game2.other.get(i).mass + mass)) * Main.game2.other.get(i).viX)
					- (((Main.game2.other.get(i).mass - mass) / (Main.game2.other.get(i).mass + mass)) * viX);
			
			viY = (((2 * Main.game2.other.get(i).mass) / (Main.game2.other.get(i).mass + mass)) * Main.game2.other.get(i).velY)
					- (((Main.game2.other.get(i).mass - mass) / (Main.game2.other.get(i).mass + mass)) * velY);
			regX = this.x+1;
			regY = this.y;
			t = 0;
			Main.game2.other.get(i).viX = (((Main.game2.other.get(i).mass - mass) / (Main.game2.other.get(i).mass + mass)) * tempXO)
					+ (((2 * mass) / (Main.game2.other.get(i).mass + mass)) * tempXO2);
			Main.game2.other.get(i).viY = (((Main.game2.other.get(i).mass - mass) / (Main.game2.other.get(i).mass + mass)) * tempYO)
					+ (((2 * mass) / (Main.game2.other.get(i).mass + mass)) * velY);
			Main.game2.other.get(i).t = 0;
			Main.game2.other.get(i).regX = Main.game2.other.get(i).x;
			Main.game2.other.get(i).regY = Main.game2.other.get(i).y;
		 
					}
		  }
		
			
		//	System.out.println(viX + " " + ball.viX);
		//	System.exit(1);
		
		  
		 
		  if(this.y>Display.getHeight()+sy)
		  {
			  regY = 100;
			  t = 0;
			  viY = 0;
			  viX = (float)(Math.random() * 40 + 1)-20;
			  regX = this.x;
			  mass = (float)Math.random() * 10 +1;
		  }
	}
	public void reverseY() {
		t = 0;
	
		regX = this.x;
		regY = this.y+1;
		viY = velY *= -.75f;
	}
	public float getCoords()
	{
		return x;
	}
	public float getCoords2()
	{
		return y;
	}
}
