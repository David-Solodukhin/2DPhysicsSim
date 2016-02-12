package com.solduck.engine;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;



public class Block extends GameObject{
private GOBallV ball;
public static float sizeX = 60;
public static float sizeY = 20;
public boolean oneCol = false;
	public Block(float x, float y, float sx, float sy, GOBallV ball)
	{
	
		this.x = x;
		this.y = y;
		this.sx = sx;
		this.sy = sy;
		this.ball = ball;
		
	}
	public void update()
	{
	
		
		
		//if(Draw.increm2<fadeRange)
		//{
			//Draw.increm2++;
	//	}
		if(Physics.checkCollisions(this, ball) && oneCol == false && this.destroyed == false && this.x!=600)
		{	oneCol = true;
			this.destroyed = true;
		}
		else if(Physics.checkCollisions(this, ball) && oneCol == false && this.x==600)
		{	oneCol = true;
		this.destroyed = true;
		Main.scale =0.8f;
	}
			 //getCenterY()
			
			
		}
		
	
	}
