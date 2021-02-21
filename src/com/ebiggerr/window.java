package com.ebiggerr;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/*we want the windows class has all the methods provided by Canvas 

*/
public class window extends Canvas {

    
  
	private static final long serialVersionUID = 6298104165574277094L;
//creating a JFrame 
//passing the method to public Game in Game class 
	
	public window(int width, int height, String title,Game game){
        JFrame frame=new JFrame(title);
        
        frame.setPreferredSize(new Dimension(width,height));
        frame.setMaximumSize(new Dimension(width,height));
        frame.setMinimumSize(new Dimension(width,height));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();
        
       
        
        
    }
}


