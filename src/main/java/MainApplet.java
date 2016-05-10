package main.java;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/
@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	private String path = "main/resources/";
	private String file_part1 = "starwars-episode-";
	private String file_part2 = "-interactions.json";
	private final static int circleRadius = 400, circleCenterX = 700, circleCenterY = 300;
	JSONObject data;
	JSONArray nodes, links;
	private ArrayList<Character> characters;
	private Network network;
	private final static int width = 1200, height = 650;
	
	public void setup() {		
		characters = new ArrayList<Character>();		
		loadData(1);
		size(width, height);
		smooth();
		network = new Network(this, this.characters);
		network.display();
		
	}

	public void draw() {
		this.clear();
		background(200);
		ellipse(circleCenterX, circleCenterY, circleRadius, circleRadius);		
		network.display();
		
		
	}
	
	public void mousePressed() {
		for (int i = 0; i < this.characters.size(); i++) {	
			if(this.characters.get(i).mousePressInside(mouseX, mouseY)) {
				this.characters.get(i).setDragged(!this.characters.get(i).isdragged);
				break;
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
		}
		
	}
	

}
