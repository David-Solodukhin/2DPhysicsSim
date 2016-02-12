package com.solduck.engine;


public class GOWALLV extends GameObject{
	public static final int STDSIZE = 26;

	private GOBallV ballv;
	public GOWALLV(float x, float y, float sx, float sy, GOBallV ball)
	{
		this.x = x;
		this.y = y;
		this.sx = sx;
		this.sy = sy;
		this.ballv = ball;
	}
	public void update()
	{
	
		this.destroyed = false;
		if(Physics.checkCollisions(this, ballv)&& this.x==300)
				{
			System.out.println("hi");
				//	ballv.reverseY();
					this.destroyed = true;
					
				}
		else if(Physics.checkCollisions(this, ballv) && this.x!=300)// 300 is the boundary
		{
			ballv.reverseY();
			
		}
	}

}
