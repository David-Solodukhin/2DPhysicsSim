package com.solduck.engine;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glOrtho;

import java.awt.Font;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.ShapeRenderer;

public class Main {
	public static String text = "";
	public static GameB game2; // change back later if buggy
	public static TrueTypeFont font;
	public static TrueTypeFont font2;
	public static Color color = new Color(Color.yellow);
	public static Color color2 = new Color(Color.blue);
	public static Color color3 = new Color(Color.blue);
	public static Image logo;
	public static Image base;
	public static Image wallpaper;
	public static int gameID = 1; // for now it's not dynamic
	public static int menuItem = 0;
	private static Long startGameTime = System.currentTimeMillis();
	public static String difficulty = "normal";
	public static boolean timeUp = false;
	public static String timeLeft = "60";
	public static float fade = 0;
	public static float scale = 0.2f;
	public static Color star = new Color(color.red);
	static float[] points2 = new float[8];
	static int  i = 0;
	public static Polygon doop;
	public static Circle blorp = new Circle(0, 0, 4);
	public static void main(String[] args) throws SlickException {
		// initialize program
	//	System.setProperty("org.lwjgl.librarypath", new File("lib/natives").getAbsolutePath());
		initDisplay();
		initGl();
		initGame();
		gameLoop();
		cleanUp();
		

	}

	private static void initGame() throws SlickException {
		// game = new Game();
		game2 = new GameB();
		Font awtFont = new Font("Star jedi", Font.BOLD, 24);
		Font awtFont2 = new Font("Calibri", Font.BOLD, 12);
		logo = new Image("logo.png");
		base = new Image("Meteor.gif");

		wallpaper = new Image("wallp.jpeg");
		font = new TrueTypeFont(awtFont, true);
		font2 = new TrueTypeFont(awtFont2, true);

	}

	private static void getInput() {
		// game.getInput();
		game2.getInput();
		if (Mouse.getY() > 510 && Mouse.getY() < 540 && Mouse.isButtonDown(0)) {
			color = new Color(Color.yellow);
			GameB.isStarted = true;
		}
		// System.out.println("X: "+ Mouse.getX()+"Y: "+ Mouse.getY()+"");//for
		// debugging mouse

		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_UP) {
				if (Keyboard.getEventKeyState()) {

					if (menuItem == 1) {
						menuItem = 0;
						color2 = new Color(Color.blue);
						color = new Color(Color.yellow);
						color3 = new Color(Color.blue);
					} else if (menuItem == 2) {
						menuItem = 1;
						color3 = new Color(Color.blue);
						color2 = new Color(Color.yellow);
						color = new Color(Color.blue);
					} else if (menuItem == 0) {
						menuItem = 2;
						color = new Color(Color.blue);
						color3 = new Color(Color.yellow);
						color2 = new Color(Color.blue);
					}
				} else {

				}
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_M) {
				if (Keyboard.getEventKeyState()) {
					if (GameB.mouseCont == false) {
						GameB.mouseCont = true;
					} else {
						GameB.mouseCont = false;
					}
				}
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_DOWN && game2.isStarted == false) {
				if (Keyboard.getEventKeyState()) {

					if (menuItem == 0) {
						menuItem = 1;
						color2 = new Color(Color.yellow);
						color = new Color(Color.blue);
						color3 = new Color(Color.blue);
					} else if (menuItem == 1) {
						menuItem = 2;
						color3 = new Color(Color.yellow);
						color2 = new Color(Color.blue);
						color = new Color(Color.blue);
					} else if (menuItem == 2) {
						menuItem = 0;
						color = new Color(Color.yellow);
						color3 = new Color(Color.blue);
						color2 = new Color(Color.blue);
					}
				}
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_RETURN && game2.isStarted==false) {
				if (Keyboard.getEventKeyState()) {
					if (menuItem == 0) {
						GameB.isStarted = true;
					} else if (menuItem == 2) {
						System.exit(1);
					} else if (menuItem == 1) {
						if (difficulty.equals("normal")) {
							difficulty = "Debug Mode";
						} else if (difficulty.equals("Debug Mode")) { //Wind +x
							difficulty = "Random Extreme Wind";
						} else if (difficulty.equals("Random Extreme Wind")) {
							difficulty = "normal";
						}
					}

				}
			}
		}
	}

	private static void update() {

		if (fade < 181) {
			fade++;
		}
		if (fade == 180) {
			fade = 0;
		}
		// game.update();
		if (GameB.isStarted == false) {
			startGameTime = System.currentTimeMillis();
		}
		if (GameB.isStarted) {
			timeLeft = (120000 - (System.currentTimeMillis() - startGameTime))
					+ "";

			if (Integer.parseInt(timeLeft) <= 10) {
				//timeUp = true;
			}
		}
		game2.update();
	}

	private static void render() {

		glClear(GL_COLOR_BUFFER_BIT);
		glLoadIdentity(); // so it does it once

		// Color.white.bind();
		// game.render();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		wallpaper.draw(0, 0);

		GL11.glDisable(GL11.GL_TEXTURE_2D);
		game2.render();
		// wallpaper.draw(0,0);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		// wallpaper.draw(0,0);

		if (game2.lives == 0 || timeUp == true) {
			Draw.increm++;
			game2.ball.velY = 0;
			game2.ball.velX = 0;
			timeLeft = "Time Up!";
			{
				color = new Color(Color.red);
				font.drawString(Display.getWidth() / 4f, 100,
						"Developed by David Solodukhin", color2);
			}
			font.drawString(Display.getWidth() / 2.5f, Display.getHeight() / 3,
					"Score: " + GameB.pScore, Color.yellow);
			font.drawString(Display.getWidth() / 2.5f, Display.getHeight() / 2,
					"GAME OUT", color);
		}
		if (GameB.isStarted == false) {
			
			font.drawString(Display.getWidth() / 2.5f, 400, "Exit", color3);
			font.drawString(Display.getWidth() / 2.5f, 380, "Difficulty: "
					+ difficulty, color2);
			font.drawString(Display.getWidth() / 2.5f, 360, "Start", color);
			logo.setAlpha((float) Math.sin(Math.toRadians(fade)));
			logo.draw(Display.getWidth() / 2 - logo.getWidth() / 4, 10, 0.5f);

			// the /4 is because the width is of the original image and i'm
			// setting it at half size after calculation.

		}

		if (game2.isStarted == true) {
			font2.drawString(game2.tester.x, game2.tester.y, "MASS:"
					+ game2.tester.mass, color);
			font2.drawString(game2.tester.x, game2.tester.y - 10, "VEL Y:"
					+ game2.tester.velY, color);
			font2.drawString(100, 0, "Gravity:"
					+ game2.ball.A*-1);
			font2.drawString(200, 0, "Ball viX" + game2.ball.viX);
			
			font2.drawString(300, 0, "SCALE FACTOR: 1 meter = "+ GOBallV.scale +" pixels");
			//blorp.setLocation(Mouse.getX(),Display.getHeight()-Mouse.getY());
			
			Line test = new Line(Main.game2.spring.x+Main.game2.spring.sx/2,Main.game2.spring.y,blorp.getX()+blorp.getRadius()/2,blorp.getY());
			ShapeRenderer.draw(test);
			
			/*if(difficulty!="normal"){
		    blorp.setLocation(Mouse.getX(),Display.getHeight()-Mouse.getY());
			Line test2 = new Line(Main.game2.ball.x+Main.game2.ball.sx/2,Main.game2.ball.y,blorp.getX()+blorp.getRadius()/2,blorp.getY());
			
			
		
			}*/
			Line temp = new Line(500,5,500,5+GOBallV.scale);
			ShapeRenderer.draw(temp);
			ShapeRenderer.draw(blorp);
			ShapeRenderer.fill(blorp);
			float[] points = Main.game2.ball.getbounds();
		  doop = new Polygon(points);
			ShapeRenderer.draw(doop);
			

			
				
					
					
				
				
			
			//System.out.println(Physics.doesCollide(shithole, doop));
		//	ShapeRenderer.fill(doop);
		}

		GL11.glDisable(GL11.GL_TEXTURE_2D);

		// base.draw(game2.tester.x-game2.tester.sx,game2.tester.y-game2.tester.sy,1.23f);
		// GL11.glEnable(GL11.GL_TEXTURE_2D);
		// GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);

		Display.update();
		Display.sync(60);

	}

	private static void gameLoop() {
		// Game loop
		while (!Display.isCloseRequested()) {
			getInput();
			update();
			render();

		}
	}

	private static void initGl() {

		// GL11.glShadeModel(GL11.GL_SMOOTH);
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
		glOrtho(0, Display.getWidth(), Display.getHeight(), 0, -1, 1);// 2d-
																		// square
																		// view
																		// default
																		// resolution
																		// independent

		GL11.glMatrixMode(GL11.GL_MODELVIEW);

	}

	private static void cleanUp() {
		Display.destroy();
		Keyboard.destroy();
	}

	private static void initDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(1200, 675));
			Display.create();
			Display.setVSyncEnabled(true);
			Keyboard.create();
			Mouse.create();
		} catch (LWJGLException ex) {
			ex.printStackTrace();
		}
	}

}
