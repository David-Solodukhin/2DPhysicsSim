package com.solduck.engine;


import java.awt.Rectangle;
import org.newdawn.slick.geom.*;;

public class Physics {

	public static boolean checkCollisions(GameObject go1, GameObject go2)
	{
		if(go1.equals(Main.game2.ball))
		{
			
			Rectangle r1 = new Rectangle((int)go1.getX(),(int)go1.getY(),(int)16,(int)16);
			Rectangle r2 = new Rectangle((int)go2.getX(),(int)go2.getY(),(int)go2.getSX(),(int)go2.getSY());
		
			return r1.intersects(r2);	
		}
		if(go2.equals(Main.game2.ball))
		{
			
			Rectangle r1 = new Rectangle((int)go1.getX(),(int)go1.getY(),(int)go1.getSX(),(int)go1.getSY());
			Rectangle r2 = new Rectangle((int)go2.getX(),(int)go2.getY(),(int)go2.getSX(),(int)go2.getSY());
			
			//anus = r1.intersects(r2);
		
			return r1.intersects(r2);	
		}
		
		
		else{
		Rectangle r1 = new Rectangle((int)go1.getX(),(int)go1.getY(),(int)go1.getSX(),(int)go1.getSY());
		Rectangle r2 = new Rectangle((int)go2.getX(),(int)go2.getY(),(int)go2.getSX(),(int)go2.getSY());
		
		//anus = r1.intersects(r2);
		
		return r1.intersects(r2);}
	}
	public static boolean doesCollide(Shape o1,Shape o2)
	{
		return o1.intersects(o2);
	}
	

}
