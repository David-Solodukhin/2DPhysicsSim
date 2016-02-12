package com.solduck.engine;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;

public class Draw {
	public static float color = 1;
	public static float color2 = 0.01f;
	public static float increm;
	public static float increm2;
	static float test;

	public static void rect(float x, float y, float width, float height) {
		glPushMatrix();// happens in a unique matrix- sets the matrix for the
						// current objects. to make less relative and more
						// absolute
		{
			// width /= 2; < this bull crap is buggy with orthographic
			// height /= 2;
			glTranslatef(x, y, 0);

			// glColor3f(1f, 1f, color);<< SWITCH BACK TO THIS THING IF FADING
			// IS A PAIN
			// if (increm < 90) {
			glColor4f(0.29f, 0.11f, color,
					(float) Math.sin(Math.toRadians(increm)));// } //fading code

			if (width == 16f) {
				{
					glRotatef(test, 0, 0, 1);
				}
			} // angle,axis of rotation-rotating on z rotates around
				// point(corner) currently rotating around center.
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

	public static void drawBall(float x, float y, float width, float height) {
		glPushMatrix();// happens in a unique matrix- sets the matrix for the
						// current objects. to make less relative and more
						// absolute
		{
			// width /= 2; < this bull crap is buggy with orthographic
			// height /= 2;
			
			
			
			//torque = 
			//
			//
			//
			//
			// glTranslatef(x-(width/2),y-(width/2),0);
			GL11.glTranslatef(x + width / 2, y + height / 2, 0); // M1 - 2nd
																	// translation
			GL11.glRotatef(test, 0.0f, 0.0f, 1.0f); // M2
			GL11.glTranslatef(-width / 2, -height / 2, 0); // M3 - 1st
															// translation

			glColor4f(0.1f, 0.24f, color,
					(float) Math.sin(Math.toRadians(increm)));// } //fading code

			// glRotatef(test,0,0,1);//angle,axis of rotation-rotating on z
			// rotates around point(corner) currently rotating around center.
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

	public static void rect2(float x, float y, float width, float height,
			float test) {
		glPushMatrix();// happens in a unique matrix- sets the matrix for the
						// current objects. to make less relative and more
						// absolute
		{
			// width /= 2; < this bull crap is buggy with orthographic
			// height /= 2;
			glTranslatef(x, y, 0);

			// glColor3f(1f, 1f, color);<< SWITCH BACK TO THIS THING IF FADING
			// IS A PAIN

			glColor4f(0.99f, color2, 0.1f,
					(float) Math.sin(Math.toRadians(increm2))); // fading code
			// if(x==100f && y==100f && width ==100f){glRotatef(46,0,0,1);}
			// //angle,axis of rotation-rotating on z rotates around
			// point(corner) currently rotating around center.
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

	public static void rect3(float x, float y, float width, float height) {
		glPushMatrix();// happens in a unique matrix- sets the matrix for the
						// current objects. to make less relative and more
						// absolute
		{
			// width /= 2; < this bull crap is buggy with orthographic
			// height /= 2;
			glTranslatef(x, y, 0);

			// glColor3f(1f, 1f, color);<< SWITCH BACK TO THIS THING IF FADING
			// IS A PAIN

			glColor3f(0.5f, 0.5f, 0.1f);
			// if(x==100f && y==100f && width ==100f){glRotatef(46,0,0,1);}
			// //angle,axis of rotation-rotating on z rotates around
			// point(corner) currently rotating around center.
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

	public static void drawPaddle(float x, float y, float width, float height) {
		glPushMatrix();// happens in a unique matrix- sets the matrix for the
						// current objects. to make less relative and more
						// absolute
		{
			// width /= 2; < this bull crap is buggy with orthographic
			// height /= 2;
			glTranslatef(x - (width / 2), y - (width / 2), 0);

			// glColor3f(1f, 1f, color);<< SWITCH BACK TO THIS THING IF FADING
			// IS A PAIN
			// if (increm < 90) {
			glColor4f(0.5f, 0.5f, color,
					(float) Math.sin(Math.toRadians(increm)));// } //fading code

			glRotatef(test, 0, 0, 1);// angle,axis of rotation-rotating on z
										// rotates around point(corner)
										// currently rotating around center.
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
