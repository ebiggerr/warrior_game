package com.ebiggerr;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
public class BasicEnemy extends GameObject {
	
	

	public BasicEnemy(int x,int y,ID id,Handler handler) {
		super(x,y,id);
		
		
		//set magnitude 
		velX=3;
		velY=3;
		 
	}
	//get the bounds for collision detection 
	public Rectangle getBounds() {
		return new Rectangle(x,y,16,16);
		
	}
	public void tick() {
		
		x += velX;
		y += velY;
		
		//bounce away from the side of the frame as it hits the side 
		if(y<=0||y>=Game.HEIGHT-38)
			velY*=-1;
		if(x<=0||x>=Game.WIDTH-16)
			velX*=-1;
		
		
		
	}
	public void render(Graphics graphics) {
		//set the dimension and colour of the of the BasicEnemy 
		graphics.setColor(Color.RED);
		graphics.fillRect(x,y,16,16);
		
		
	}

}
