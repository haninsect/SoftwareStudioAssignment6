package main.java;

import processing.core.PApplet;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	private final static int Radius = 25;
	private final static int defaultX = 50;
	private final static int defaultY = 50;
	private MainApplet parent;
	private int number;
	private String color;
	private String name;
	private int[] connect;
	private int size;
	private int x;
	private int y;
	private boolean isdragged = false;
	private int hexColor ;
	private int listnumber;
	private boolean isinside;
	
	public Character(MainApplet parent, int n, int s, String name, String c){
		this.number = n;
		this.color = c;
		this.name = name;
		this.connect = new int[s];
		for(int i = 0; i < s; i++) this.connect[i] = 0;
		this.size = s;
		this.parent = parent;
		this.setDimension(defaultX, defaultY);
		hexColor = Long.decode("0x"+this.color.substring(1)).intValue();
		
	}

	public void display(){	
		if(this.isDragged()){
			this.parent.fill(hexColor);
			parent.ellipse(parent.mouseX, parent.mouseY, Radius, Radius);
			this.x = parent.mouseX;
			this.y = parent.mouseY;
		}
		else {
			this.parent.fill(hexColor);
			parent.ellipse(x, y, Radius, Radius);
		}
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
		if((mouseX-this.x)*(mouseX-this.x) + (mouseY-this.y)*(mouseY-this.y)<= (Radius/2)*(Radius/2) ) return true;
		else return false;
	}
	public void setListNumber(int n){
		this.listnumber = n;
	}
	public int getListNumber(){
		return this.listnumber;
	}
	public void setInside(boolean ins){
		this.isinside = ins;
	}
	public boolean isInside(){
		return isinside;
	}
}
