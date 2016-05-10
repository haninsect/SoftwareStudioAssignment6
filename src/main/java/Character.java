package main.java;

import processing.core.PApplet;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	private final static int Radius = 20;
	private final static int defaultX = 50;
	private final static int defaultY = 50;
	private MainApplet parent;
	private int number;
	private String color;
	private String name;
	int[] connect;
	int size;
	int x;
	int y;
	boolean isdragged = false;
	
	
	public Character(MainApplet parent, int n, int s, String name, String c){
		this.number = n;
		this.color = c;
		this.name = name;
		this.connect = new int[s];
		for(int i = 0; i < s; i++) this.connect[i] = 0;
		this.size = s;
		this.parent = parent;
		this.setDimension(defaultX, defaultY);
	}

	public void display(){
		if(this.isDragged()){
			parent.ellipse(parent.mouseX, parent.mouseY, Radius, Radius);
			this.x = parent.mouseX;
			this.y = parent.mouseY;
		}
		else parent.ellipse(x, y, Radius, Radius);
	}
	
	
	public void setConnected(int n, int w){
		this.connect[n] = w;
	}
	public boolean isConnected(int n){
		if(this.connect[n]!=0) return true;
		else return false;
	}
	public void setDimension(int x, int y){
		this.x = x;
		this.y = y;		
	}
	public int getX(){
		return this.x;
		
	}
	public int getY(){
		return this.y;
	}
	public String getName(){
		return name;
	}
	public int getNumber(){
		return number;
	}
	public boolean isDragged(){
		return isdragged;
	}
	public void setDragged(boolean dragged){
		isdragged = dragged;
	}
	public boolean mousePressInside(int mouseX, int mouseY){
		if((mouseX-this.x)*(mouseX-this.x) + (mouseY-this.y)*(mouseY-this.y)<= Radius*Radius ) return true;
		else return false;
	}
}
