package com.solduck.engine;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Blorp extends JPanel{

	private static final long serialVersionUID = 1L;
	
	static int[] arr;
	Random r = new Random();
	static boolean running = false;
	
	static JFrame f = new JFrame();
	static boolean makeNext = true;
	
	public Blorp(){
		arr = new int[5];
		for(int i = 0; i < arr.length; i++){
			arr[i] = r.nextInt(15)+1;
		}
	}

	public static void main(String[] a) throws InterruptedException { 
		  running = true;
	      f.setSize(150, 200);
	      
	      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      f.setResizable(false);
	      f.setLocationRelativeTo(null);
	      f.add(new Blorp());
	      f.setVisible(true);
	      while(running){
	    	  boolean sorted = false;
	      	while(!sorted){
	          	sorted = true;
	          	for(int i = 0; i < arr.length-1; i++){
	              	if(arr[i] > arr[i+1]){
	                  	sorted = false;
	              	}
	          	}
	          	Random r = new Random();
	          	int q = r.nextInt(arr.length);
	          	int b = r.nextInt(arr.length);
	          	if(arr[q] <= arr[b]){
	              	int temp = arr[q];
	              	arr[q] = arr[b];
	              	arr[b] = temp;
	          	}
	          	if(!sorted){
	          		if(makeNext){
	          			Thread.sleep(50);
	          			f.repaint();
	          		}
	          	}if(sorted){
	          		f.setTitle("DONE");
	          		sorted = true;
	          		running = false;
	          		System.out.println("DONE");
	          		makeNext = false;
	          		break;
	          	}
	      	}
	    	  
	      } 
	}
	
	public void paint(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 150, 200);
		g.setColor(Color.CYAN);
		int xOffset = 0;
		for(int i = 0; i < arr.length; i++){
			g.fillRect(xOffset, 178-(arr[i]*10), 10, arr[i]*10);
			xOffset += 11;
		}
	}
	
	public static void blorpSort(){
		String s = "";
		for(int i = 0; i < arr.length; i++){
			s += arr[i] + " ";
		}
		System.out.println(s);
		
    	
	}
	
}

