package com.solduck.engine;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.InterruptedIOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
public class Main_bars {

 DateFormat dateFormatt = new SimpleDateFormat("ss");
 //get current date time with Date()
 Date datet = new Date();
 int firstTime = Integer.parseInt((dateFormatt.format(datet)));
 float fTime = Sys.getTime();
	/** position of quad */
	float x = 0, y = 0;
	/** angle of quad rotation */
	float rotation = 0;
 
	/** time at last frame */
	long lastFrame;
 
	/** frames per second */
	int fps;
	/** last fps time */
	long lastFPS;
	int i = 0;//looper
	int jMover = 0;
	private Object bars;
	//static ArrayList<Bar> bars = new ArrayList<Bar>();
 public Main_bars()
 {
	 int toomp = (int)(((Sys.getTime() * 1000) / Sys.getTimerResolution())/1);
	   String tempor = String.valueOf(toomp);
	   tempor = tempor.charAt(4)+"";
	   firstTime = Integer.parseInt(tempor);
	 System.out.println("askfj");
	 Random rand = new Random();
	 for(int i = 0;i<25;i++)
	 {
		 int randomNum = rand.nextInt((70 - 10) + 1) + 10;
		 Bar temp = new Bar((float)i*30+16,0,randomNum*7);
	//	 bars.add(temp);
	 }
	 
	  
 }
	public void start() throws InterruptedException {
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
 
		initGL(); // init OpenGL
		getDelta(); // call once before loop to initialise lastFrame
		lastFPS = getTime(); // call before loop to initialise fps timer
 
		while (!Display.isCloseRequested()) {
			int delta = getDelta();
 
			update(delta);
			renderGL();
 
			Display.update();
			Display.sync(60); // cap fps to 60fps
		}
 
		Display.destroy();
	}
 
	public void update(int delta) throws InterruptedException{
		for(int q = 0;q<bars.size();q++)
		{
			bars.get(q).update();
		}
		
 
		 DateFormat dateFormat = new SimpleDateFormat("ss");
		 DateFormat dateF = new SimpleDateFormat("ss.S");
		   //get current date time with Date()
		   Date date = new Date();
		  
		   int temp = Integer.parseInt((dateFormat.format(date)));
		   // int tempor2 = Integer.parseInt(tempor);
		   
		   int toomp = (int)(((Sys.getTime() * 1000) / Sys.getTimerResolution())/1);
		   String tempor = String.valueOf(toomp);
		   tempor = tempor.charAt(5)+"";
		   int currentT = Integer.parseInt(tempor);
		   System.out.println(jMover);
		   if(Math.abs(currentT-firstTime)>1 && Math.abs(currentT-firstTime)<8)	
		   {
			   int toomp2 = (int)(((Sys.getTime() * 1000) / Sys.getTimerResolution())/1);
			   String tempor2 = String.valueOf(toomp2);
			   String tempor3 = tempor2.charAt(4)+"";
			   firstTime = Integer.parseInt(tempor3);
		
			  if(i<bars.size()){
				  jMover = i+1;
				  if(jMover<bars.size()){
			//  for(int j = i+1;j<bars.size();j++){
				  //jMover = j;
				  if(bars.get(jMover).height<bars.get(i).height){
			  Bar smallerNumber = bars.get(jMover);
			  bars.set(jMover,bars.get(i));
			
			  bars.set(i, smallerNumber);
			  }
				  jMover++;}
			  if(jMover==bars.size()-1){i++;}}
			   /*int toomp2 = (int)(((Sys.getTime() * 1000) / Sys.getTimerResolution())/1);
			   String tempor2 = String.valueOf(toomp2);
			   String tempor3 = tempor2.charAt(4)+"";
			   firstTime = Integer.parseInt(tempor3);
		
			  if(i<bars.size()){
				  //j=i+1;
		
			  for(int j = i+1;j<bars.size();j++){
				  jMover = j;
				  if(bars.get(j).height<bars.get(i).height){
			  Bar smallerNumber = bars.get(j);
			  bars.set(j,bars.get(i));
			  bars.set(i, smallerNumber);
			  }
				  }
			  if(jMover==bars.size()-1){i++;}}
			  */
		   }
		   
	}
 
	/** 
	 * Calculate how many milliseconds have passed 
	 * since last frame.
	 * 
	 * @return milliseconds passed since last frame 
	 */
	public int getDelta() {
	    long time = getTime();
	    int delta = (int) (time - lastFrame);
	    lastFrame = time;
 
	    return delta;
	}
 
	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
 
	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
 
	public void initGL() {
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_LIGHTING);

		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		// GL11.glClearDepth(1);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		// GL11.glViewport(0,0,800,600);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		glOrtho(0, Display.getWidth(),0 , Display.getHeight(), -1, 1);// 2d-
																		// square
																		// view
																		// default
																		// resolution
																		// independent

		GL11.glMatrixMode(GL11.GL_MODELVIEW);

	}
 
	public void renderGL() {
		glClear(GL_COLOR_BUFFER_BIT);
		glLoadIdentity(); // so it does it once
		
		for(int q = 0;q<bars.size();q++)
		{
			bars.get(q).render();
		}
	}
 
	public static void main(String[] argv) throws InterruptedException {
		Main_bars timerExample = new Main_bars();
		timerExample.start();
	}
	public static void rect(float x, float y, float width, float height) {
		glPushMatrix();// happens in a unique matrix- sets the matrix for the
						// current objects. to make less relative and more
						// absolute
		{
			
			glTranslatef(x, y, 0);
			GL11.glColor3f(0.5f, 0.5f, 1.0f);
			

			
			glBegin(GL_QUADS); // square
			{
				glVertex2f(0, 0);
				glVertex2f(0, height);
				glVertex2f(width, height);
				glVertex2f(width, 0);
			}
			glEnd();
		}
		glPopMatrix();
	}
}