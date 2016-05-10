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
			characters.get(i).setListNumber(i); //< 0 means it is outside the cycle
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
			int x = circleCenterX + (int) (Math.cos( Math.toRadians( degree ) )*circleRadius/2);
			int y = circleCenterY + (int) (Math.sin( Math.toRadians( degree ) )*circleRadius/2);
			this.characters.get(this.inside.get(i)).setDimension(x , y);	
			this.characters.get(this.inside.get(i)).display();
		}	
		for (int i = 0; i < this.inside.size(); i++) {	
			for (int j = i+1; j < this.inside.size(); j++) {
				int x1 = this.characters.get(this.inside.get(i)).getX(),
					y1 = this.characters.get(this.inside.get(i)).getY(),
					x2 = this.characters.get(this.inside.get(j)).getX(),
					y2 =  this.characters.get(this.inside.get(j)).getY();
				if(this.characters.get(this.inside.get(i)).isConnected(this.inside.get(j))){
					

					this.parent.noFill();
					this.parent.curve(x1*2 - circleCenterX , y1*2 - circleCenterY, 
							 x1, x2, y1, y2,							
							x2*2 -circleCenterX, y2*2 - circleCenterY);
				}
				
			}
		}
	}
	public void moveToInside(int n){
		characters.get(outside.get(n)).setInside(!this.characters.get(outside.get(n)).isInside());
		characters.get(outside.get(n)).setListNumber(inside.size());
		inside.add(outside.get(n));
		for(int i = n+1; i < outside.size(); i++){
			characters.get(outside.get(i)).setListNumber(characters.get(outside.get(i)).getListNumber()-1);
		}
		outside.remove(n);
		
	}
	public void moveToOutside(int n){
		characters.get(inside.get(n)).setInside(!this.characters.get(inside.get(n)).isInside());
		characters.get(inside.get(n)).setListNumber(outside.size());
		outside.add(inside.get(n));
		for(int i = n+1; i < inside.size(); i++){
			characters.get(inside.get(i)).setListNumber(characters.get(inside.get(i)).getListNumber()-1);
		}
		inside.remove(n);
	}
	public int getOutsideSize(){
		return this.outside.size();
	}
	public int getInsideSize(){
		return this.inside.size();
	}
}
