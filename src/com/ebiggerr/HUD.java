package com.ebiggerr;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	
	public static int HEALTH=100;
	private int Greenbarvalue=255;
	
	private int score=0;
	private int level=1;
	
	public void tick(){
		
		
		HEALTH=Game.limitarea(HEALTH,0,100);
		Greenbarvalue=Game.limitarea(Greenbarvalue, 0, 255);
		
		Greenbarvalue=HEALTH*2;
		score++;
	}
	public void render(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(15, 15, 200, 32);
		graphics.setColor(new Color(80,Greenbarvalue,0));
		graphics.fillRect(15, 15,HEALTH*2, 32);
		graphics.setColor(Color.WHITE);
		graphics.drawRect(15, 15, 200, 32);
		
		graphics.drawString("Score : "+ score,10,64);
		graphics.drawString("Level : "+ level,10,80); 
	}
	
	public void score(int score) {
		this.score=score;
		
		
	}
	public int getScore() {
		return score;
	}
	
	public void setLevel(int level) {
		this.level=level;
		
	}
	public int getLevel() {
		return level;
	}
}
