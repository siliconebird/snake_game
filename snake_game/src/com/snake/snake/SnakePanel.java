package com.snake.snake;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.snake.frame.SnakeFrame;
import com.snake.domain.*;
public class SnakePanel extends JPanel implements Runnable,KeyListener {

	JFrame parent = new JFrame();
	private int rows = 20;
	private int cols = 30;
	private SnakeModel snake;
	private LinkedList snakeBody;
	private JPanel[][] gridsPanel;
	private LinkedList otherBlocks;
	private location direction;
	private locationRO snakeHead;
	private locationRO snakeFood;
	private Color snakeHeadColor = Color.black;
	private Color snakeBodyColor = Color.orange;
	private Color snakeFoodColor = Color.red;
	private Color otherBlocksColor = Color.lightGray;
	private int score;
	private boolean addScore;
	private Thread t;
	private long speed;
	private boolean isEnd;//暂停
	private static boolean notExit;
	
	
	//constructor
	public SnakePanel(SnakeFrame snakeFrame) {
		// TODO Auto-generated constructor stub
		this.parent = snakeFrame;
		snakeBody = new LinkedList();
		snakeHead = new locationRO(0, 0);
		snakeFood = new locationRO(0, 0);
		direction = new location(0, 1);
		otherBlocks = new LinkedList();
		
		gridsPanel = new JPanel[rows][cols];
		setLayout(new GridLayout(rows, cols, 1, 1));
		//画网格
		for(int i =0;i<rows;i++){
			for(int j =0;j<cols;j++){
				gridsPanel[i][j]=new JPanel();
				gridsPanel[i][j].setBackground(otherBlocksColor);
				add(gridsPanel[i][j]);
			}
		}
	addKeyListener(this);	
		
	}
	//	开始游戏
	public void startGame(long speed) {
		// TODO Auto-generated method stub
		this.speed = speed;
		if(notExit){
			snake.init();
		}else{
			notExit = true;
			snake = new SnakeModel(rows, cols);
			t=new Thread(this);
			t.start();
		}
		requestFocus();
		direction.setX(0);
		direction.setY(1);
		score = 0;
		updateText(" "+score);
		isEnd = false;
	}

	private void updateText(String string) {
		// TODO Auto-generated method stub
		((SnakeFrame)parent).scoreField.setText(string);
	}
	private int  getScore(){
		return score;
	}

	public void stopGame() {
		// TODO Auto-generated method stub
		requestFocus();
		isEnd = true;
	}

	public void returnGame() {
		// TODO Auto-generated method stub
		requestFocus();
		isEnd = false;
	}

	private void updateColor(){
		//body color
		snakeBody = snake.getSnake();
		Iterator i = snakeBody.iterator();
		while(i.hasNext()){
			locationRO l = (locationRO)(i.next());
			gridsPanel[l.getX()][l.getY()].setBackground(snakeBodyColor);
		}
		
		//head color
		snakeHead = snake.getSnakeHead();
		gridsPanel[snakeHead.getX()][snakeHead.getY()].setBackground(snakeHeadColor);
		
		
		//other color
		otherBlocks = snake.getOthers();
		i = otherBlocks.iterator();
		while(i.hasNext()){
			locationRO t1 = (locationRO)(i.next());
			gridsPanel[t1.getX()][t1.getY()].setBackground(otherBlocksColor);
		}
		
		
		//food color
		snakeFood = snake.getSnakeFood();
		gridsPanel[snakeFood.getX()][snakeFood.getY()].setBackground(snakeFoodColor);
		
		
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCody = e.getKeyCode();
		if(notExit){
			if(keyCody == KeyEvent.VK_LEFT){
				direction.setX(0);
				direction.setY(-1);
			}
			if(keyCody == KeyEvent.VK_RIGHT){
				direction.setX(0);
				direction.setY(1);
			}
			if(keyCody == KeyEvent.VK_DOWN){
				direction.setX(1);
				direction.setY(0);
			}
			if(keyCody == KeyEvent.VK_UP){
				direction.setX(-1);
				direction.setY(0);
			}
			//shotcut pause
			if(keyCody == KeyEvent.VK_SPACE){
				isEnd=!isEnd;
			}
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {}
			if(!isEnd){
				isEnd = !snake.move(direction);
				updateColor();
				if(snake.getAddScore()){
					score+=10;
					updateText(score+"");
				}
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
