package com.ebiggerr;

import java.awt.Graphics;
 
import java.util.LinkedList;
public class Handler {
	LinkedList<GameObject> object =new LinkedList<GameObject>();
	public void tick() {
		for(int i=0;i<object.size();i++) {
			GameObject tempObject=object.get(i);
			
			tempObject.tick();
			
		}
	}
	public void render(Graphics graphics) {
		for(int i=0;i<object.size();i++) {
			GameObject tempObject=object.get(i);
			tempObject.render(graphics );
		}

	}
	
	//create a method to add object into the game 
	public void addObject(GameObject object) {
		this.object.add(object);
		
		
	}
	public void removeObject(GameObject object) {
		this.object.remove(object);
		
	}
}
