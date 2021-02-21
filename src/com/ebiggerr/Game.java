package com.ebiggerr;

import java.awt.Canvas;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

// Runnable interface requires of the class to implement the run() method 
//without the interface, the thread will not have the promise to find the run()method 
public class Game extends Canvas implements Runnable {
    
   
	//private static final long serialVersionUID = 499776485135926340L;
	
	//setting the dimension of the JFrame 
	//set the aspect ratio of the frame to 4:3 
	public static final int WIDTH=640, HEIGHT=WIDTH/12*9;
	
	//access modifier 
	//private
	//data members and methods can only be accessible within the class 
	private Thread thread;
	private boolean running =false;
	
	
	private Random random;
	
	//handler are associated with message queue of a thread and used to send messages and runnable.
	//basically handler are used here to communicate between the UI and the background thread 
	private Handler handler;
	
	//head on display for the health bar
	private HUD hud;
	
	//BasicEnemy increases in increment of 1 as the level increases 
	private Level spawner;
	
	
    
    public Game(){
    	//KeyListener as the control of the movement is by keyinput 
    	handler=new Handler();
    	
    	//this. reference refers to the KeyInput object, whose method is being call upon.
    	
    	this.addKeyListener(new KeyInput(handler));
    	
    	//as the parameters is set as int width, int height, String title, Game game)
    	//this reference is used 
        new window(WIDTH,HEIGHT,"Game : WarriorX",this);
        
        hud=new HUD();
        spawner=new Level(handler,hud);
      //random method is used so that every time the program runs, the objects will be draw on random position 
        random=new Random();
        
        //add the Player object inside the game
        handler.addObject(new Player(random.nextInt(WIDTH),random.nextInt(HEIGHT),ID.Player,handler));
        
       //add the BasicEnemy object inside the game 
        handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH),random.nextInt(Game.HEIGHT),ID.BasicEnemy,handler));
		
        
       
       
    }
    
    //because of threads, synchronization is needed there is multiple components running on the same as the program runs 
    public synchronized void start(){
    	//when the thread is started, it will call the start() method
        thread=new Thread(this);
        thread.start();
        running=true;
        
    }
    public synchronized void stop() {
    	try {
    		thread.join();
    		running=false;
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }
    
    //separate the rendering and ticking into separate methods. and only set it to tick after a certain amount of time
    public void run(){
    	
    	//allow the components in the game can be focusable 
    	this.requestFocus();
    	long lastTime=System.nanoTime();
    	//and the amount is set as 60  
    	double amountofTicks=60.0;
    	double ns= 1000000000/amountofTicks;
    	double delta=0;
    	long timer=System.currentTimeMillis();
    	int frames=0;
    	while(running) {
    		long now=System.nanoTime();
    		delta+=(now-lastTime)/ns;
    		lastTime=now;
    			while(delta>=1) {
    				tick();
    				delta--;
    				
    			}
    			if(running)
    				render();
    			frames++;
    			
    			if(System.currentTimeMillis()-timer>1000) {
    				timer+=1000;
    				System.out.println("");
    				frames=0;
    			}
    		
    	}
    
        stop();
    }
    //tick update the game logic
    private void tick() {
    	handler.tick();
    	hud.tick();
    	spawner.tick();
    	
    	// if the HEALTH value drops from 100 to 0, the game exits 
    	if(HUD.HEALTH<=0) {
    		HUD.HEALTH=100;
    			System.exit(0);
    				
    				
    			}
    	}
    		
    	
    
    //the background of the JFrame set as gray in color 
    private void render() {
    	
    	//dealing with graphics drawing in the game 
    	BufferStrategy bs =this.getBufferStrategy();
    	if(bs==null) {
    		this.createBufferStrategy(3);
    		return;
    	}
    	Graphics graphics =bs.getDrawGraphics();
    	graphics.setColor(Color.LIGHT_GRAY);
    	graphics.fillRect(0, 0, WIDTH, HEIGHT);
    	
    	handler.render(graphics);
    	hud.render(graphics);
    	graphics.dispose();
    	bs.show();
    }
    
    //create a method that limit the movement of all objects only inside the game frame
    public static int limitarea (int var,int min,int max) {
    	if(var>=max) {
    		return var=max;
    	}
    	else if(var<=min) {
    		return var=min;
    		
    	}
    	else 
    		return var;
    	
    }	
    

    public static void main(String [] args){
    	new Game();
    }
}

