package com.solduck.engine;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.lwjgl.opengl.GL11;


public class Bar {
public float x = 0;
public float y = 0;
public float width = 16;
public float height = 0;
public float color = 0.5f;
	public Bar(float x, float y, float height)
	{
		this.x = x;
		this.y = y;
		this.height = height;
	}
	public void render()
	{
		
		rect(x,y,width,height);
		
	}
	public void update()
	{
		this.color = 0.5f;
	//this.x = (float)Main.bars.indexOf(this)*30f+16f;
	}
	public void rect(float x, float y, float width, float height) {
		glPushMatrix();// happens in a unique matrix- sets the matrix for the
						// current objects. to make less relative and more
						// absolute
		{
			
			glTranslatef(x, y, 0);
			GL11.glColor3f(0.5f, color, 1.0f);
			

			
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
