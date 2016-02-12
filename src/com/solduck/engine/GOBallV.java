package com.solduck.engine;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class GOBallV extends GameObject {
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
	float Ax = 0;
	float Ay = 0;
	float velF = 0;
	public float regX = x;
	public float regY = y;
	
	public float mass = 1;
	public float count = 0;
	float tempX = 0;
	float tempY = 0;
	float g = 0.5f;
	float A = 9.8f;
	private float delta;
	public static float scale = 20f; //used to be 10;

	public GOBallV(float x, float y) {
		regX = x;
		regY = y;
		this.x = x;
		this.y = y;
		this.sx = SIZE;
		this.sy = SIZE;
		velX = 0;
		// velY = MAX_SPEEDX;;
	}

	public GOBallV(float x, float y, float difficulty) {
		startX = x;
		startY = y;
		this.x = x;
		this.y = y;
		this.sx = SIZE;
		this.sy = SIZE;

		velX = -difficulty;
		velY = 0;
	}

	@Override
	public void update() {

		// System.out.println(viY);
		// System.out.println(x + " " + y + "   " + viX + "  " + viY);
		if(Main.difficulty.equals("normal"))
		{
		if (Mouse.isButtonDown(0) && Mouse.getX() < Main.game2.wall4.x
				) {
			this.y = Display.getHeight() - Mouse.getY() - this.sy;
			this.x = Mouse.getX() - this.sx / 2;
			Draw.test = 0;
			count = 0;
			ftime = 1;
			regX = this.x;
			regY = this.y;
			t = 0;
			a = 9.8f;
			Ax = 0;
			Ay = 0;
			
			if (timeStep == 0) {
				// t1 =Mouse.getX();
				timeStep = 1;
				t3 = Mouse.getY();
			} else if (timeStep == 1) { // all of this code is deprecated i
										// think idk maybe i will use it again
										// sometime.
				t2 = Mouse.getX();		
				timeStep = 0;
				t4 = Mouse.getY();
				alphaTime = System.currentTimeMillis();
			}

			float speed = ((t1 - t2));
			float speedY = ((t3 - t4));
			if (t4 > t3) {
				speed *= -1;
			}
			// viX = speed;
			// viY = speedY;

			pressed = true;
		} else {
			
			if (pressed == true) {
				pressed = false;
				 delta = Math.abs((alphaTime%10000 - System.currentTimeMillis()%10000)/100.0f); //copyright me
				t3 = Mouse.getY();
				viY = ((t3 - t4)/(delta *scale));
				viY *= -1;
				t1 = Mouse.getX();
				viX = ((t1 - t2)/(delta*scale));
				ci = this.y;
				ciX = this.x;

			}
			float wind = 2;
			/*
			 * x = viX * t; y= viY*t + 0.5f*-9.8f*time*time;
			 */

			///////////////////////////////////////////////////////////////

			/*
			 * if(aX!=0){ t=t+(float)(0.08f); }//this ratio is about 1/60 since
			 * the formula is for seconds.
			 * 
			 * 
			 * this.y = .5f*(a)*t*t+viY*t+ci; //i don't know if this really
			 * works, but it looks like it and i don't care anymore cuz i spent
			 * like 4 hours on this
			 * 
			 * if(Main.difficulty.equals("Wind +x")){ if(viX>0 &&
			 * x>=ciX-1){this.x = .5f*(-2f)*t*t+viX*t+ciX;} //this is for wind
			 * in one set direction. Medium difficulty else if(viX<0 &&
			 * x<=ciX){this.x = .5f*(-2f)*t*t+viX*t+ciX;} }
			 * 
			 * if(Main.difficulty.equals("Random Extreme Wind")){ if(viX>0 &&
			 * x>=ciX){this.x = .5f*(-2f)*t*t+viX*t+ciX;} //this is for wind in
			 * both directions. Hard difficulty else if(viX<0 && x<=ciX){this.x
			 * = .5f*(2f)*t*t+viX*t+ciX;}} else
			 * if(Main.difficulty.equals("normal")){ velF=viX+aX*f*t; //this is
			 * no wind, but it is still pretty buggy if(velF>0 && viX>0){this.x
			 * = .5f*(-aX)*t*t+viX*t+ciX;} else if(velF<0 && viX<0){this.x =
			 * .5f*(aX)*t*t+viX*t+ciX;} // velF = viX+a*t x+=velF || x-=velF
			 * //this is kinda how it's supposed to look }
			 */

			// 0.2f was the previous acceleration

			// A = 1/Math.abs(x-Main.game2.p1.x+Main.game2.p1.sx/2);
			if (x < Main.game2.p1.x + Main.game2.p1.sx && x > Main.game2.p1.x) {			//magnet code ::fake acceleration gravity change by flooding viY float
				if (Math.abs(viY) < 30) {
				//	A +=0.01f;
					if (Keyboard.getEventKey() == Keyboard.KEY_H) {

						if (Keyboard.getEventKeyState()) {
					viY+=0.1f;}}
				}
			}
			else
			{
		
				if(viY>0.2f)
				{
					
					
			//		viY-=0.1f;
					
				}
			}
			//System.out.println(A + " " + viY);
			
			t = t + (float) (0.0166f);
			this.x = (viX * t + (regX)/scale)*scale;
			this.y = ((viY * t) + (0.5f * A * t * t) + (regY)/scale)*scale;
			velY = viY + (A * t);
			/*
			  velX = viX + (Ax * t);										this code is for acceleration in both directions-sort of like space with friction. Remember to change the the accelerations Ax and Ay to zero when motion ends.
			velY = viY + (Ay * t);
				if((int)velX>0){
				Ax = -2f;}
				else if((int)velX<0){Ax = 2f;}
				else if((velX/velX) ==(viX/viX) ){Ax = 0;viX = 0;regX = this.x;}
		    
		    if((int)velY>0){
				Ay = -2f;}
				else if((int)velY<0){Ay = 2f;}
				else if((velY/velY) ==(viY/viY) ){Ay = 0;viY = 0;regY = this.y;}
			
				
			this.x = ((viX * t) + (0.5f * Ax * t * t) + (regX)/scale)*scale;
			this.y = ((viY * t) + (0.5f * Ay * t * t) + (regY)/scale)*scale;
			  
			  
			 */
			System.out.println("");
			// THE CODE THAT WAS HERE BEFORE RELIED ON THE DAVID CONSTANT: 0.08 as a fixed time increment that acted as a relativistic friction factor. delta did not exist. the formula was inproportionate
		}

		/*if (Draw.test == 360f) {
			Draw.test = 0;
		}

		if (viX > 0 && aX != 0 && Draw.test != 90) {
			Draw.test++;
		} else if (viX < 0 && aX != 0 && Draw.test != 90) {

			Draw.test--; // i still have to fix the point of rotation bug.
		}*/
		
		
		
		// ////////////////////////////////////////////////////
		// System.out.println(Physics.checkCollisions(this, Main.game2.tester));

		// System.out.println(x + "y: " + y + " ciX : "+ ciX);

		// System.out.println(viX);

		// -------- vfY=viY+(9.8f)*t;
		// ------- if(aX==0 && Physics.checkCollisions(this,
		// Main.game2.p1)){a=9.8f;aX=-2;}
		if (Keyboard.getEventKey() == Keyboard.KEY_UP) {

			if (Keyboard.getEventKeyState()) {
		A+=.01f;ObjTester.A+=0.01f;Asteroid.A+=0.01f;}}
		else if (Keyboard.getEventKey() == Keyboard.KEY_DOWN) {

			if (Keyboard.getEventKeyState()) {
		A-=.01f;ObjTester.A-=0.01f;Asteroid.A-=0.01f;}}
		}
		else
		{
			if (Keyboard.getEventKey() == Keyboard.KEY_UP) {

				if (Keyboard.getEventKeyState()) {
			A+=.01f;ObjTester.A+=0.01f;Asteroid.A+=0.01f;g+=.01f;}}
			else if (Keyboard.getEventKey() == Keyboard.KEY_DOWN) {

				if (Keyboard.getEventKeyState()) {
			A-=.01f;ObjTester.A-=0.01f;Asteroid.A-=0.01f;g-=.01f;}}
			if (Mouse.isButtonDown(0) ) {
				
				
				this.y = Display.getHeight() - Mouse.getY() - this.sy/2;
				this.x = Mouse.getX() - this.sx / 2;
				Draw.test = 0;
				count = 0;
				ftime = 1;
				regX = this.x;
				regY = this.y;
				t = 0;
				a = 9.8f;
				Ax = 0;
				Ay = 0;
				if (timeStep == 0) {
					// t1 =Mouse.getX();
					timeStep = 1;
					t3 = Mouse.getY();
				} else if (timeStep == 1) { // all of this code is deprecated i
											// think idk maybe i will use it again
											// sometime.
					t2 = Mouse.getX();
					timeStep = 0;
					t4 = Mouse.getY();
				}

				float speed = ((t1 - t2));
				float speedY = ((t3 - t4));
				if (t4 > t3) {
					speed *= -1;
				}
				// viX = speed;
				// viY = speedY;

				pressed = true;
			} else {
				
				if (pressed == true) {
					pressed = false;
					
					t3 = Mouse.getY();
					viY = (t3 - t4);
					//viY = (Mouse.getDY());
					viY *= -1;
					t1 = Mouse.getX();
					viX = (t1 - t2);
					//viX = (Mouse.getDX());
					ci = this.y;
					ciX = this.x;

				}
				float temp = Main.blorp.getCenterX()-Main.game2.ball.x-Main.game2.ball.sx/2;
				float temp2 = Main.blorp.getCenterY()-Main.game2.ball.y;
				//float SpringForce = (float)Math.sqrt((temp*temp)+(temp2*temp2)) * -.001f;
				float k = .01f*.33f*.5f;
				float SpringX = temp *k;
				float SpringY = temp2 *k;
				float friction = 0.98f;
				//(.5*k*temp*temp)(.5*mass) = viX   Another method, although i don't know how energy conservation can play in here.
				//System.out.println(SpringForce);
				viX +=SpringX;
				viY +=SpringY;
				viY += g;
			
				viX*=friction;
				viY*=friction;
				t = t + (float) (0.0167f);
				//this.x = viX*t*+(regX);
				//this.y =viY*t*+regY;
				
				//					
				x+=viX;  // if i forget: the friction constant acts as a dampener which acts essentially as the time variable would. By eliminating time, we reduce the risk of physics to frame error.
				y+=viY;
			
				System.out.println(viX);
			}
			}
		
		
		
	}

	public void resetPosition() {
		if(Main.difficulty.equals("normal")){
		viY = 0;
		t = 0;
		viX = 0;
		x = 120;
		y = 300;
		regX = x;
		regY = y;
		count = 0;		
		System.out.println("HCR WORKS!");
		
		}

	}

	// float center

	public void reverseX2() {
		// if(numStorage==0){numStorage=1;T1 = System.currentTimeMillis();}
		// else if(numStorage==1){numStorage=0;T2 = System.currentTimeMillis();}
		t = 0;
		regY = this.y;
		if (viX < 0) {
			regX = this.x + 2;
		}

		else {
			regX = this.x - 2;
		}
		// if(viX <0 && viX+viX*-.25f<0){viX+=viX/-2;}
		// else if(viX >0 && viX-viX*.25f>0){viX-=viX/2;}
		viY=velY*.75f;
		viX *= -.75f;

		// if(viX>0 && viX-viX/2>0 ){viX-=viX/2;}
		// else if(viX<0 && viX+viX/2<0){viX+=viX/2;}

	}

	public void momentum() {
		/*
		 * tempX = viX; tempY = viY; float tempXO = Main.game2.tester.viX; float
		 * tempYO = Main.game2.tester.velY; viX =
		 * (((mass-Main.game2.tester.mass)
		 * /(mass+Main.game2.tester.mass))*viX)+((
		 * (2*Main.game2.tester.mass)/(mass
		 * +Main.game2.tester.mass))*Main.game2.tester.viX); viY =
		 * (((mass-Main.game2
		 * .tester.mass)/(mass+Main.game2.tester.mass))*velY)+(
		 * ((2*Main.game2.tester
		 * .mass)/(mass+Main.game2.tester.mass))*Main.game2.tester.velY);
		 */t = 0;
		regX = this.x;
		regY = this.y;
		/*
		 * Main.game2.tester.viX =
		 * (((2*mass)/(mass+Main.game2.tester.mass))*tempX) -
		 * (((mass-Main.game2.
		 * tester.mass)/(mass+Main.game2.tester.mass))*tempXO);
		 * Main.game2.tester.viY =
		 * (((2*mass)/(mass+Main.game2.tester.mass))*tempY) -
		 * (((mass-Main.game2.
		 * tester.mass)/(mass+Main.game2.tester.mass))*tempYO);
		 * Main.game2.tester.regX = Main.game2.tester.x; Main.game2.tester.regY
		 * = Main.game2.tester.y; Main.game2.tester.t = 0;
		 */

	}
	public void resetContact()
	{
		
	}

	public void reverseY(float center) {

		/*
		 * startY = Display.getHeight()-30;; dir = 1; // a = 0.1f; //a *=-1;
		 * 
		 * 
		 * 
		 * 
		 * velY *= -1; velX +=(getCenterX()-center)*DAMPING; if(velX >
		 * MAX_SPEEDY) { velX = MAX_SPEEDY; } if(velX< -MAX_SPEEDY) { velX =
		 * -MAX_SPEEDY; }
		 */
	}

	public void reverseY() {
		
		t = 0;
		count  = 0;
		regX = this.x;
		if(this.y>Main.game2.p1.y){
		t= 0;viY = 0;this.y = Main.game2.p1.y-sy;
		}
		else{
			regY = this.y;
		viY = velY * -.75f;}
		
		
		// experimenting with bounce- didn't learn it in physics yet so making
		// up my own rules vfy=vi+-9.8t

		// ciX = this.x;
		// ci = this.y;
		// if(this.y<Main.game2.wall4.y+Main.game2.wall4.sy){
		// //////////////////////////////////////////////////////////////////THIS
		// STUFF WORKED SORTA PREVIOUSLY
		/*
		 * if ((int) Math.abs(vfY) == (int) Math.abs(viY)) { t = 0; viY = 0; viX
		 * = 0; ciX = this.x; aX = 0;
		 * 
		 * a = 0; ci = this.y; } else { a = 9.8f; vfY = viY + (9.8f) * t; viY =
		 * -(vfY - t); System.out.println("hello2u"); }
		 */
		// ////////////////////////////////////////////////////////////////

		// else if(ftime==0 && viY>0){vfY=viY+(9.8f)*t;viY=-(vfY-t);}
		// else{viY=0;}
		// viY=vfY;

		// }
		// this.y=this.y--;

		// t= 0;
		// viX*=-1;//still have to figure out the deal with this. sort of got it

	}
	public boolean withinBoundaries()
	{
		
			if(Mouse.getX()>=this.x && Mouse.getX()<=this.x+sx && Display.getHeight()-Mouse.getY()>=this.y && Display.getHeight()-Mouse.getY()<=this.y+sy)
			
				return true;
			
		    
		
		
		return false;
	}
	public void whywouldthiswork() {
		velX = 16;
	}

	public float getX() {
		// velX =4;
		return this.x;
	}
	public float[] getbounds()
	{
	  float[] points = {x,y,x,y+sy,sx+x,y+sy,sx+x,y};
	  return points;
	}

	public float getY() {
		return this.y;
	}
	

}
