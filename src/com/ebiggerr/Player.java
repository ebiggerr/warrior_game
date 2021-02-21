package com.ebiggerr;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import java.awt.Color;

public class Player extends GameObject {

	Random r=new Random();
	Handler handler; 
	
	public Player(int x, int y, ID id,Handler handler) {
		super(x, y, id);
		this.handler=handler;
		
		
	}
	public Rectangle getBounds() {
		return new Rectangle(x,y,32,32);
		
	}
	
	public void tick() {
		
		x+=velX;
		y+=velY;
		
		//using the limitarea in Game class to limit the movement of Player  inside the frame 
		x=Game.limitarea(x, 0, Game.WIDTH-38);
		y=Game.limitarea(y,0,Game.HEIGHT-62);
		
		collision();
		
	}
	//collision detection by using bounds for Player and BasicEnemy 
	// as the bounds of the Player and BasicEnemy intersect, the HEALTH decrease by two in the rate of tick 
	public void collision() {
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject=handler.object.get(i);
			 if(tempObject.getId()==ID.BasicEnemy) {
				 if(getBounds().intersects(tempObject.getBounds())){
					 //collision
					 HUD.HEALTH-=2;
				 }
			 }
			
		}
		
	}

//the dimension and colour of the Player 	
	public void render(Graphics graphics ) {
		
		graphics.setColor(Color.white);
		graphics.fillRect(x, y, 32,32);
	}

}
