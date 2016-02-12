package com.solduck.engine;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.geom.Transform;

public class Ship {
	private GOBallV ball;
	public static float sizeX = 60;
	public static float sizeY = 20;
	public boolean oneCol = false;
	public float mass = 0.4f;
	public float viX = 1f;
	public float viY = 0f;
	public float velY = 0f;
	public float t = 0f;
	public float regX = 0f;
	public float regY = 0f;
	public float[] points2 = {16,6,0,0,16,20,32,0};
	public int i = 0;
	public int count = 0;
	public static float A = 9.8f;
	public Polygon body = new Polygon(points2);
	public float damping = 0.1f;
	public float thrust = 0.1f;
	public float angle = 90;
	public float centerX = body.getCenterX();
	public float centerY = body.getCenterY();
	private float velX = 0f;
	float Ax = 0f;
	private float Ay = 0f;
	float delta = 0;
	public float lastFrame = 0;
	public static float scale = 20f;
	public boolean thrusting = false;
	public Ship(float x, float y) {
		body.setLocation(x, y);
		regX = x;
		regY = y;
		body = (Polygon)body.transform(Transform.createRotateTransform((float)Math.toRadians(-90),body.getCenterX(),body.getCenterY()));
		
		
	}

	public void update() {
		
		
		if(Main.game2.isStarted){
		body.setX(viX+body.getX());
		body.setY(viY+body.getY());}
		

		
		
		
	}
	public void draw()
	{
		ShapeRenderer.draw(body);
	}
}
