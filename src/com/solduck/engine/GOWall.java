package com.solduck.engine;


public class GOWall extends GameObject{
	public static final int STDSIZE = 26;

	private GOBallV ballv;
	public GOWall(float x, float y, float sx, float sy, GOBallV ball)
	{
		this.x = x;
		this.y = y;
		this.sx = sx;
		this.sy = sy;
		this.ballv = ball;
	}
	public void update()
	{
		
		if(Physics.checkCollisions(this, ballv))
				{
					ballv.reverseX2();
					
				}
	}

}
