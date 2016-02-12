package com.solduck.engine;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;



public class BallTest extends GameObject{
	public static  int SIZE = 16; //put final back if it's buggy
	public static float MAX_SPEEDX = 4f; //change back to final if buggy
	public static final float MAX_SPEEDY = 16f;
	public static float DAMPING = 0.05f; //change back to final if glitchy
	public float velX;
	public float velY;
	
	public float startX;
	public float startY;
	//public float a = 0;
	public float ci = 300;
	public float ciX = 300;
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
	float a = 9.8f;
	float numStorage = 0;
	boolean pressed = false;
	public int ftime = 1;
	float aX = -2f;
	float velF = 0;
	public float regX = x;
	public float regY = y;
	public static float grabs = 20;
	public BallTest(float x, float y)
	{
		startX = x;
		startY = y;
		this.x = x;
		this.y = y;
		this.sx = SIZE;
		this.sy = SIZE;
		
		velX = 0;
		velY = MAX_SPEEDX;;
	}
	public BallTest(float x, float y, float difficulty)
	{
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
	public void update()
	{
		
	
	System.out.println(startX);
	//System.out.println(x + " " + y + "   " + viX + "  " + viY);
		if(Mouse.isButtonDown(0) && Mouse.getX()<Main.game2.wall4.x && grabs!=0){this.y = Display.getHeight()-Mouse.getY()-this.sy;this.x = Mouse.getX()-this.sx/2;	Draw.test=0;
ftime=1;
		t=0;
		 a = 9.8f;
		 aX = -2f;
		if(timeStep == 0){
			// t1 =Mouse.getX();
			 timeStep =1;
			 t3 = Mouse.getY();
			 }
			else if(timeStep == 1){				//all of this code is deprecated i think idk maybe i will use it again sometime.
			 t2 = Mouse.getX()
					 ;timeStep = 0;
			t4 = Mouse.getY();}
			
			float speed =  ((t1-t2));
			float speedY = ((t3-t4));
			if(t4>t3){speed*=-1;}
		//	viX = speed;
		 //   viY = speedY;
		    		
		
		pressed = true;
		}
		else{
		if(pressed == true){pressed = false;t3 = Mouse.getY();viY=t3-t4;viY*=-1;t1 = Mouse.getX();viX = t1-t2;
		ci = this.y;
		ciX = this.x;
		
		
		}
		float wind = 2;
		/*
		 * x = viX * t;
		 * y= viY*t + 0.5f*-9.8f*time*time;
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		
		
	//////////////////////////////////////////////////////////////
		
	 /*  if(aX!=0){
		t=t+(float)(0.08f); }//this ratio is about 1/60 since the formula is for seconds.
		
	
		this.y = .5f*(a)*t*t+viY*t+ci;
					//i don't know if this really works, but it looks like it and i don't care anymore cuz i spent like 4 hours on this
		
		if(Main.difficulty.equals("Wind +x")){
			if(viX>0 && x>=ciX-1){this.x = .5f*(-2f)*t*t+viX*t+ciX;} //this is for wind in one set direction. Medium difficulty
			else if(viX<0 && x<=ciX){this.x = .5f*(-2f)*t*t+viX*t+ciX;}
		}
		
		if(Main.difficulty.equals("Random Extreme Wind")){
				if(viX>0 && x>=ciX){this.x = .5f*(-2f)*t*t+viX*t+ciX;}  //this is for wind in both directions. Hard difficulty
				else if(viX<0 && x<=ciX){this.x = .5f*(2f)*t*t+viX*t+ciX;}}
		else if(Main.difficulty.equals("normal")){
				velF=viX+aX*f*t; //this is no wind, but it is still pretty buggy
				if(velF>0 && viX>0){this.x = .5f*(-aX)*t*t+viX*t+ciX;} 
				else if(velF<0 && viX<0){this.x = .5f*(aX)*t*t+viX*t+ciX;} //   velF = viX+a*t x+=velF || x-=velF //this is kinda how it's supposed to look 
		}*/
		
		 x = viX * t;
		  y= viY*t + 0.5f*-9.8f*t*t;
		}

		 if(Draw.test == 360f){Draw.test = 0;}
		
		if(viX>0 && aX!=0 && Draw.test!=90)
		{
			Draw.test++;
		}
		else if(viX<0 && aX!=0 && Draw.test!=90)
		{

			Draw.test--; //i still have to fix the point of rotation bug.
		}
//////////////////////////////////////////////////////
		 

		

	//	System.out.println(x + "y: " + y + " ciX : "+ ciX);

	
		
	//	System.out.println(viX);
			
		//--------  vfY=viY+(9.8f)*t;	
	//-------	  if(aX==0 && Physics.checkCollisions(this, Main.game2.p1)){a=9.8f;aX=-2;}
	}
	public void resetPosition()
	{
		
		x = 120;
	ciX=startX;
	ci=startY;
		
		t=0;
		viX=0;
		viY=0;
		vfY=0;
	Draw.test = 0;	
		
		//velX *= -1; // for player scoring
	}
//float center
	
	public void reverseX2(){
		//if(numStorage==0){numStorage=1;T1 = System.currentTimeMillis();}
		//else if(numStorage==1){numStorage=0;T2 = System.currentTimeMillis();}
			t=0;
			viY=0;
			ci=this.y;
			if(viX<0){
				ciX = this.x+2;}

			else
			{ciX=this.x-5;}
			if(viX <0 && viX+viX*-.25f<0){viX+=viX/-2;}	
			else if(viX >0 && viX-viX*.25f>0){viX-=viX/2;}

	//	this.x++; //so that the ball doesn't get stuck in the wall.
		
				viX*=-1;
	

			
 // if(viX>0 && viX-viX/2>0 ){viX-=viX/2;}
  //else if(viX<0 && viX+viX/2<0){viX+=viX/2;}

	}
	public void reverseY
	(float center) {
		
			/*startY = Display.getHeight()-30;;
			dir = 1;
		//	a = 0.1f;
			//a *=-1;
			
			
			
			
			velY *= -1;
			velX +=(getCenterX()-center)*DAMPING;
			if(velX > MAX_SPEEDY)
			{
				velX = MAX_SPEEDY;
			}
			if(velX< -MAX_SPEEDY)
			{
				velX = -MAX_SPEEDY;
			}
		*/
	}
	public void reverseY() {
		
		 //experimenting with bounce- didn't learn it in physics yet so making up my own rules   vfy=vi+-9.8t
		
	//	ciX = this.x;
	//	ci = this.y;
		//if(this.y<Main.game2.wall4.y+Main.game2.wall4.sy){
			
			if((int)Math.abs(vfY)==(int)Math.abs(viY)){t=0;viY=0;viX=0;ciX=this.x;aX=0;
		
			
			a=0;ci=this.y;
	}else
	{a=9.8f;vfY=viY+(9.8f)*t;viY=-(vfY-t);System.out.println("hello2u");}
			//else if(ftime==0 && viY>0){vfY=viY+(9.8f)*t;viY=-(vfY-t);}
			//else{viY=0;}
		//	viY=vfY;

			
	//	}
		//this.y=this.y--;
		
	//	t= 0;
		//viX*=-1;//still have to figure out the deal with this. sort of got it
	
		
		
	}
	public void whywouldthiswork(){
		velX =16;
	}
	public float getX(){
		//velX =4;
		return this.x;
	}
	public float getY(){
		return this.y;
	}

}
