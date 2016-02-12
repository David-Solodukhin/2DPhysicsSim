package com.solduck.engine;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class Spring extends GameObject {
	public static int SIZE = 16; // put final back if it's buggy
	public static float MAX_SPEEDX = 4f; // change back to final if buggy
	public static final float MAX_SPEEDY = 16f;
	public static float DAMPING = 0.05f; // change back to final if glitchy
	public float velX;
	public float velY;

	public float startX;
	public float startY;
	// public float a = 0;
	public float ci = 300;
	public float ciX = 300;
	public float viX = 0;
	public float viY = 0;
	public float t = 0;
	public long alphaTime = 0;
	public float betaTime = 0;
	public float vfY = 2;
	public float timeStep = 1;
	float t1 = 0;
	float t2 = 0;
	float f = 0;
	float t3 = 0;
	float t4 = 0;
	float a = 9.8f;
	float numStorage = 0;
	boolean pressed = false;
	public int ftime = 1;
	float aX = -2f;
	float velF = 0;
	public float regX = x;
	public float regY = y;
	
	public float mass = 1;
	public float count = 0;
	float tempX = 0;
	float tempY = 0;
	float g = 9.8f;
	float A = 9.8f;
	private float delta;
	public static float scale = 20f;
	public GOBallV ball;
	public Spring(float x, float y, GOBallV ball) {
		regX = x;
		regY = y;
		this.x = x;
		this.y = y;
		this.sx = SIZE;
		this.sy = SIZE;
		velX = 0;
		this.ball = ball;
		// velY = MAX_SPEEDX;
	}
	public void update() {
		
		float temp = (Main.blorp.getCenterX()-this.x-sx/2);
		float temp2 = (Main.blorp.getCenterY()-this.y);
		//float SpringForce = (float)Math.sqrt((temp*temp)+(temp2*temp2)) * -.001f;
		//float SpringForce = (float)Math.sqrt((temp*temp)+(temp2*temp2)) * -.001f;
		float k = .00165f;
		float SpringX = temp *k;
		float SpringY = temp2 *k;
		float friction = 0.98f;
		//(.5*k*temp*temp)(.5*mass) = viX   Another method, although i don't know how energy conservation can play in here.
		//System.out.println(SpringForce);
		viX +=SpringX;
		viY +=SpringY;
		viY += 0.2f;
	
		viX*=friction;
		viY*=friction;
		t = t + (float) (0.0167f);
		//this.x = viX*t*+(regX);
		//this.y =viY*t*+regY;
		
		//					
		x+=viX;  // if i forget: the friction constant acts as a dampener which acts essentially as the time variable would. By eliminating time, we reduce the risk of physics to frame error.
		y+=viY;
		
		
		
		
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
		
		
		
		
	}
	private void reverseY() {
		t = 0;
		
		regX = this.x;
		regY = this.y+1;
		viY = velY *= -.75f;
		
	}
}