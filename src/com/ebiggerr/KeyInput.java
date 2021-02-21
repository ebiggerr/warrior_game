package com.ebiggerr;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

 
public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	
	//up,down,left and right  
	private boolean[]keyDown=new boolean[4];
	public KeyInput (Handler handler) {
		this.handler=handler;
	
		//keyDown event is false when no key is pressed on the keyboard 
	keyDown[0]=false;
	keyDown[1]=false;
	keyDown[2]=false;
	keyDown[3]=false;
		
	}
	
	public void keyPressed(KeyEvent keypressed) {
		int key=keypressed.getKeyCode();
		
		//creating temporary object 
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject=handler.object.get(i);
				if(tempObject.getId()==ID.Player) {
					
					//keyDown event is fired when up,down,left,and right keys is pressed on the keyboard 
					//initiate the movement of the Player object 
					if(key==KeyEvent.VK_UP) { tempObject.setVelY(-3); keyDown[0]=true;}
					if(key==KeyEvent.VK_DOWN) { tempObject.setVelY(+3); keyDown[1]=true;}
					if(key==KeyEvent.VK_LEFT) {tempObject.setVelX(-3); keyDown[2]=true;}
					if(key==KeyEvent.VK_RIGHT) {tempObject.setVelX(+3);keyDown[3]=true;}
				}
				// to fix key glitch, if(key==KeyEvent.VK.UP) tempObject.setVelY(-3) is not used 
				
		}
		
		
	}
	public void keyReleased(KeyEvent keyrelease) {
		int key=keyrelease.getKeyCode();
		
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject=handler.object.get(i);
				if(tempObject.getId()==ID.Player) {
					
					//clearing the direction input as the key is released to eliminate sticky key issue 
					if(key==KeyEvent.VK_UP) keyDown[0]=false;
					if(key==KeyEvent.VK_DOWN) keyDown[1]=false;
					if(key==KeyEvent.VK_LEFT)keyDown[2]=false;
					if(key==KeyEvent.VK_RIGHT)keyDown[3]=false;
					
					if(!keyDown[0] && !keyDown[1])tempObject.setVelY(0);
					
					if(!keyDown[2] && !keyDown[3])tempObject.setVelX(0);
				}
				
		}
		
		
	}
		
	}

