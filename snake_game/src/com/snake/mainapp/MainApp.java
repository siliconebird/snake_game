package com.snake.mainapp;

import javax.swing.JFrame;

import com.snake.frame.SnakeFrame;


public class MainApp {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame.setDefaultLookAndFeelDecorated(true);
		SnakeFrame frame = new SnakeFrame();
		frame.setSize(350,350);
		frame.setLocation(330,220);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setTitle("snakeeeeee~~~");
	}

}
