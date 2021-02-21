package com.ebiggerr;

import java.util.Random;
public class Level {
	private Handler handler;
	private Random random=new Random();
	private HUD hud;
	private int scoreKeeper=0;
	
	public Level(Handler handler,HUD hud) {
		this.handler=handler;
		this.hud=hud;
		
	}
	public void tick() {
		//the scoreKeep increase as the tick, 60 
		scoreKeeper++;
		
		//set condition, as the scoreKeep reaches every interval of 500 , the getLevel increased in 1 increment
		if(scoreKeeper>= 500) {
			scoreKeeper=0;
			hud.setLevel(hud.getLevel()+1);
			
			//new BasicEnemy object is added as every times the getLevel increases by 1 
				handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH),random.nextInt(Game.HEIGHT),ID.BasicEnemy,handler));
				
		
				
			}
			
		}
		
	}

