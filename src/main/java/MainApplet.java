package main.java;

import java.util.ArrayList;

import controlP5.ControlEvent;
import controlP5.ControlP5;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/
@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	private final static int width = 1200, height = 650;
	private final static int circleRadius = 500, circleCenterX = 700, circleCenterY = 300;
	private String path = "main/resources/";
	private String file_part1 = "starwars-episode-";
	private String file_part2 = "-interactions.json";
	JSONObject data;
	JSONArray nodes, links;
	private ArrayList<Character> characters;
	private Network network;
	private ControlP5 cp5;
	
	public void setup() {	
		size(width, height);
		//Add button.
		cp5 = new ControlP5(this);
		cp5.addButton("buttonA")
			.setLabel("ADD ALL")
			.setPosition(width-150, height/10)
			.setSize(100, 40);
		
		cp5.addButton("buttonB")
			.setLabel("CLEAR")
			.setPosition(width-150, 2*height/10)
			.setSize(100, 40);
		//Create cycle.
		characters = new ArrayList<Character>();	
		loadData(1);		
		smooth();
		network = new Network(this, this.characters);
		network.display();
		
	}

	public void draw() {
		this.clear();	
		this.background(255);		
		this.fill(255);
		this.stroke(0);
		ellipse(circleCenterX, circleCenterY, circleRadius, circleRadius);		
		network.display();
		
	}
	public void buttonA(){ //ADD ALL
		int size = this.network.getOutsideSize();
		for(int i = 0; i < size; i++){			
			this.network.moveToInside(0);			
		}		
	}
	public void buttonB(){ // CLEAR
		int size = this.network.getInsideSize();
		for(int i = 0; i < size; i++){			
			this.network.moveToOutside(0);			
		}
	}
	
	public void mousePressed() {
		for (int i = 0; i < this.characters.size(); i++) {	
			if(this.characters.get(i).mousePressInside(mouseX, mouseY)) {
				this.characters.get(i).setDragged(!this.characters.get(i).isDragged()); //Reverse cycle's move state.
				if(this.characters.get(i).isDragged() == false){ // Is realeased.
					//Pull out the cycle
					if((mouseX-circleCenterX)*(mouseX-circleCenterX) + (mouseY-circleCenterY)*(mouseY-circleCenterY) > (circleRadius/2)*(circleRadius/2) ){
						if(this.characters.get(i).isInside()) {
							this.network.moveToOutside(this.characters.get(i).getListNumber());
							System.out.println("Pull out");
						}
						
					}
					//Put in the cycle
					else if((mouseX-circleCenterX)*(mouseX-circleCenterX) + (mouseY-circleCenterY)*(mouseY-circleCenterY) <= (circleRadius/2)*(circleRadius/2) ){						
						if(!this.characters.get(i).isInside()) {
							System.out.println("Put in");
							this.network.moveToInside(this.characters.get(i).getListNumber());
						}
					
					}
				}
			}			
		}
	}
	public void keyPressed() {
		if(this.key == '1'){
			characters =new ArrayList<Character>();	
			this.loadData(1);
			network = new Network(this, this.characters);
		}
		else if(this.key == '2'){
			characters =new ArrayList<Character>();	
			this.loadData(2);
			network = new Network(this, this.characters);
		}
		else if(this.key == '3'){
			characters =new ArrayList<Character>();	
			this.loadData(3);
			network = new Network(this, this.characters);			
		}
		else if(this.key == '4'){
			characters =new ArrayList<Character>();	
			this.loadData(4);
			network = new Network(this, this.characters);
		}
		else if(this.key == '5'){
			characters =new ArrayList<Character>();	
			this.loadData(5);
			network = new Network(this, this.characters);
		}
		else if(this.key == '6'){
			characters =new ArrayList<Character>();	
			this.loadData(6);
			network = new Network(this, this.characters);
		}
		else if(this.key == '7'){
			characters =new ArrayList<Character>();	
			this.loadData(7);
			network = new Network(this, this.characters);
		}
	}
	
	private void loadData(int episode){
		
		data = loadJSONObject(path + file_part1 + episode + file_part2);
		nodes = data.getJSONArray("nodes");
		links = data.getJSONArray("links");
		for (int i = 0; i < nodes.size(); i++) {
			JSONObject node = nodes.getJSONObject(i);
			String name = node.getString("name");
			String colour = node.getString("colour");
			characters.add(new Character(this, i, nodes.size(), name, colour));
		}
		for (int i = 0; i < links.size(); i++) {
			JSONObject link = links.getJSONObject(i);
			int source = link.getInt("source");		
			int target = link.getInt("target");
			int weight = link.getInt("value");
			characters.get(source).setConnected(target, weight);
			characters.get(target).setConnected(source, weight);
		}		
	}
}
