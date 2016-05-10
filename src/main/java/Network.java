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
	private final static int row = 3;
	private final static int space = 45;
	private final static int defaultY = 50;
	private final static int circleRadius = 500, circleCenterX = 700, circleCenterY = 300;
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
			outside.add(characters.get(i).getNumber());	
		}
	}

	public void display(){
		for (int i = 0; i < this.outside.size(); i++) {			
			this.characters.get(this.outside.get(i)).setDimension(defaultX + (i%row)*space , defaultY + (i/row)*space);	
			this.characters.get(this.outside.get(i)).display();
		}
		for (int i = 0; i < this.inside.size(); i++) {	
			double degree =  360;
			degree = degree/ (double)this.inside.size()*(double) i;
			int x = circleCenterX + (int) (Math.cos( Math.toRadians( degree ) )*circleRadius);
			int y = circleCenterY + (int) (Math.sin( Math.toRadians( degree ) )*circleRadius);
			this.characters.get(this.inside.get(i)).setDimension(x , y);	
			this.characters.get(this.inside.get(i)).display();
		}		
	}
	public void moveToInside(int n){
		outside.remove(n);
	}
	public void moveToOutside(int n){
		
	}
}