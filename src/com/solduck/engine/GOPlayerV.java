package com.solduck.engine;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class GOPlayerV extends GameObject {
	public static final int SIZEX = 16 * 7;
	public static int SIZEY = 16; // change back to final if this completely
									// screws up
	public float SPEED = 0.5f; // change back to final if screws up
	public URL tester;
	private GOBallV ball;
	public float ci = 300;
	public float ciX = 300;
	public float ciX2 = 300;
	public float viX = 0;
	public float viY = 0;
	public float t = 0;
	public float dir = 0;
	public float vfY = 2;
	public float timeStep = 1;
	float t1 = 0;
	float t2 = 0;
	float f = 0;
	float t3 = 0;
	float t4 = 0;
	float a = 9f;
	float aO = -9f;
	private int mag = 2;
	public float ftime = 0;
	public float ftime2 = 0;
	public float ftime3 = 0;
	public float ftime4 = 0;
	private boolean pressed;

	public GOPlayerV(float x, float y, GOBallV ball) {
		this.x = x;
		this.y = y;
		this.sx = SIZEX;
		this.sy = SIZEY;
		this.ball = ball;
		// velX = -MAX_SPEEDX;
		// velY = 0;
	}

	@Override
	public void update() {
	
		if(x<0){x=1;}
		// System.out.println(viX);

		if (GameB.mouseCont) {
			this.x = (Mouse.getX()) - this.sx / 2;
		}

		if (Keyboard.getEventKey() == Keyboard.KEY_D) {

			if (Keyboard.getEventKeyState()) {
				
				//	if(x<0 ){x=0;x-=SPEED*mag;}
				//	else if(x>Display.getWidth()-this.sx){x=Display.getWidth()-sx;x-=SPEED*mag;}
					//else
				if(SPEED<5){
				SPEED+= 0.2f;}
				if(x+sx<Display.getWidth()&&x>0){
				x+=SPEED;
				viX = (float)Math.pow(SPEED,2);	}
						

				// System.out.println("A Key Pressed");
			}

			else {
				if(SPEED>0){
				SPEED-= 0.2f;
				if(x+sx<Display.getWidth()&&x>=0){
				x+=SPEED;
				viX = (float)Math.pow(SPEED,2);}
				else{x=Display.getWidth()-1-sx;viX = (float)Math.pow(SPEED,2);}
				}
				// System.out.println("A Key Released");

			}
		}
		if (Keyboard.getEventKey() == Keyboard.KEY_A) {

			if (Keyboard.getEventKeyState()) {
				
				// System.out.println("A Key Pressed");
				if(SPEED<5){
				SPEED+= 0.2f;viX = (float)Math.pow(SPEED,2)*-1;}
				if(x<Display.getWidth()&&x>=3){
				x+=SPEED*-1;
				viX = (float)Math.pow(SPEED,2)*-1;}
			}

			else {
				// System.out.println("A Key Released");
				if(SPEED>0){
					SPEED-= 0.2f;
					if(x<Display.getWidth()&&x>0){
					x-=SPEED;
					viX = (float)Math.pow(SPEED,2)*-1;}
					else{x=2;viX = 0;}
					//viX=(int)SPEED;
					}
				
				} 
				}

				
			
		
		// System.out.println(a + " " + t);

		/*
		 * 
		 * 
		 * if(ftime2 == 0){ftime2 = 1;ciX=this.x;t=0;viX = velF; a*=-1;}
		 * t=t+(float)(0.08f);
		 * 
		 * if(this.x<Display.getWidth()-this.sx){this.x =
		 * .5f*(a)*t*t+viX*t+ciX;}
		 * if(velF-1<0){ciX=this.x;ftime=0;a*=-1;System.out
		 * .println("changed2");System.out.println(aO);} } else
		 * if(Keyboard.isKeyDown(Keyboard.KEY_A)==false &&
		 * Keyboard.isKeyDown(Keyboard.KEY_D)==false && (ftime3==1) &&velF2<0 ){
		 * 
		 * if(ftime4 == 0){;ftime4 = 1;ciX=this.x;t2=0;viX = velF2; aO*=-1;}
		 * t2=t2+(float)(0.08f);
		 * 
		 * if(this.x>0){this.x = .5f*(aO)*t2*t2+viX*t2+ciX;}
		 * if(velF2+1>0){ftime3
		 * =0;ciX=this.x;aO*=-1;viX=0;System.out.println("changed"
		 * );System.out.println(aO);}
		 * 
		 * } if(this.x<0){this.x=1;}
		 */

		// if(ball.getY()>Display.getHeight()){ball.resetPosition();Game.pScore++;
		// System.out.println(Game.pScore);}
		// if(ball.getY()<0){ball.resetPosition();Game.eScore++;
		// System.out.println(Game.eScore);}

		if (Physics.checkCollisions(this, ball) && this.y-sy<=ball.y) {
		
		// a=4/Math.abs(ball.x-x);						float diff = Math.abs(ball.x-x);
			
			// ball.reverseY(getCenterX()); //getCenterY() FORCES TESTING Trig
			// calculations are not the best solution. I resorted to simplified
			// compoenent method.
			// ball.whywouldthiswork2();
			// ball.viY = 0;
			// ball.viX = 0;
			// ball.a = 0;
			// ball.t = 0;
			// ball.ci = this.y-this.getSY();
			/*
			 * float resFx = ball.velF *
			 * (float)Math.acos(ball.velF/ball.x)+this.viX;
			 * 
			 * float resFy = ball.vfY*(float)Math.asin(ball.vfY/ball.x); float
			 * test = (float)Math.sqrt(Math.pow(resFx, 2)+Math.pow(resFy, 2));
			 * float resF = test*(float)Math.atan(resFy/resFx); ball.t = 0;
			 * ball.ci = this.y-this.getSY(); ball.ciX= this.x; ball.y--;
			 * ball.viX = resFx; ball.viY = resFy;
			 */
			// ball.reverseY();

			
			ball.reverseY();
			
			
			
				float resFx = ball.viX + this.viX;
				// float resFy = ball.vfY;
				if((int)ball.viX==0){}
				ball.viX = (ball.viX+(this.viX)*2)*.45f;
				// ball.viY= -resFy;
		

		}

		// this.sy = SIZEY;

	}

	public void move(float mag) // because of the text, i may have to set this
								// back... to y+=SPEED*mag
	{

		// if(x<=0 ){x=0;x-=SPEED*mag;}
		// else
		// if(x>=Display.getWidth()-this.sx){x=Display.getWidth()-sx;x-=SPEED*mag;}

	}

	public void getSizePower() {
		this.sy = this.sy + 1;
	}

	public void resetSize() {
		this.sy = SIZEY;
		SPEED = 4f;

	}

	private void playSound() {

		try {

			if (System.getProperty("os.name").contains("Mac")) {
				tester = new URL(
						"file:///Users/DavidMac/Desktop/School/Pong1.1/pong.wav");
			} else {
				tester = new URL(
						"file:///C:/Users/David/workspace/Pong/pong.wav");
			}
			AudioClip clip = Applet.newAudioClip(tester);

			clip.play();
		} catch (MalformedURLException murle) {
			System.out.println(murle);
		}
	}

}
