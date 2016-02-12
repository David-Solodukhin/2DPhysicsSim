package com.solduck.engine;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;



public abstract class GameObject {
	protected float x;
	protected float y;
	protected float sx; //s==size
	protected float sy;
	protected boolean destroyed = false;
	public float increm2 = 90f;
	public float range = 300f;
	
	abstract void update();
	public void render()
	{
		
		Draw.rect(x,y,sx,sy);
	}
	
	public float getX()
	{
		return x;
	}
	public float getY()
	{
		return y;
	}
	public float getSX()
	{
		return sx;
	}
	public float getSY()
	{
		return sy;
	}
	public float getCenterY()
	{
		return y + sy/2;
	}
	public float getCenterX()
	{
		return x + sx/2;
	}
	public void render2()
	{
		
		Draw.rect2(x,y,sx,sy,128f);
	}
	public void renderBall()
	{
		
		Draw.drawBall(x,y,sx,sy);
	}
	public void renderPaddle()
	{
		
		Draw.drawPaddle(x,y,sx,sy);
	}
	public void render3() //for debug
	{
		
		glPushMatrix();//happens in a unique matrix- sets the matrix for the current objects. to make less relative and more absolute
		{
		//width /= 2; < this bull crap is buggy with orthographic
		//height /= 2;
		glTranslatef(x,y,0);
		
		//glColor3f(1f, 1f, color);<< SWITCH BACK TO THIS THING IF FADING IS A PAIN
		
		glColor4f(0.5f, 0.5f, 0.5f, (float) Math.sin(Math.toRadians(increm2))); 
	//	if(x==100f && y==100f && width ==100f){glRotatef(46,0,0,1);} //angle,axis of rotation-rotating on z rotates around point(corner) currently rotating around center.
		glBegin(GL_QUADS); //square
		{
			glVertex2f(0,0);
			glVertex2f(0,sy);
			glVertex2f(sx,sy);
			glVertex2f(sx,0);
		}
		glEnd();
	}
		glPopMatrix();
	}
	

}
