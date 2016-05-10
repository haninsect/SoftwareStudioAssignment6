package main.java;

import java.util.ArrayList;

import processing.core.PApplet;

/**
* This class is used for the visualization of the network.
* Depending on your implementation, you might not need to use this class or create a class on your own.
* I used the class to draw the circle and re-arrange nodes and links.
* You will need to declare other variables.
*/
public class Network {
	private final static int defaultX = 50;
	private final static int defaultY = 50;
	private final static int circleRadius = 200, circleCenterX = 700, circleCenterY = 300;
	private PApplet parent;
	private ArrayList<Character> characters;
	private ArrayList<Integer> inside;
	private ArrayList<Integer> outside;

	public Network(PApplet parent, ArrayList<Character> c){
		this.characters = c;
		this.parent = parent;
		this.inside = new ArrayList<Integer>();
		this.outside = new ArrayList<Integer>();
		for (int i = 0; i < characters.size(); i++) {
			inside.add(characters.get(i).getNumber());	
		}
		System.out.println(outside.size());

	}

	public void display(){
		for (int i = 0; i < this.inside.size(); i++) {			
			this.characters.get(i).setDimension(defaultX + (i%3)*30 , defaultY + (i/3)*30);	
			this.characters.get(i).display();
		}
		for (int i = 0; i < this.outside.size(); i++) {	
			double degree =  360;
			degree = degree/this.outside.size()*i;
			int x = circleCenterX + (int) Math.cos( Math.toRadians( degree ) )*circleRadius;
			int y = circleCenterY + (int) Math.sin( Math.toRadians( degree ) )*circleRadius;
			this.characters.get(i).setDimension(x , y);	
			this.characters.get(i).display();
		}
		
	}
	
}