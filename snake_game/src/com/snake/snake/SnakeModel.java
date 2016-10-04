package com.snake.snake;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.snake.domain.location;
import com.snake.domain.locationRO;

public class SnakeModel {
	private int rows,cols;
	private LinkedList playBlocks,snake;
	private location snakeHead,runningDirict;
	private locationRO snakeFood;
	private locationRO[][] locRO;
	private int gameScore = 0;
	private boolean addScore;
	
	//getter
	public locationRO getSnakeHead(){
		return (locationRO)snake.getLast();
	}
	public locationRO getSnakeTail(){
		return (locationRO)snake.getFirst();
	}
	public location getRuntimeDiriction(){
		return runningDirict;
	}
	public LinkedList getSnake(){
		return snake;
	}
	public LinkedList getOthers(){
		return playBlocks;
	}
	public locationRO getSnakeFood(){
		return snakeFood;
	}
	public int getScore(){
		return gameScore;
	}
	public boolean getAddScore(){
		return addScore;
	}
	
	private void setSnakeFood(){
		snakeFood = (locationRO)playBlocks.get((int)(Math.random()*playBlocks.size()));
	}
	private void setSnakeHead(location snakeHead){
		this.snakeHead = snakeHead;
	}
	
	public void moveTo(Object a,LinkedList fromList,LinkedList toList){
		fromList.remove(a);
		toList.add(a);
	}
	
	
	//蛇体的初始化
	public void init(){
		playBlocks.clear();
		snake.clear();
		gameScore = 0;
		for(int i =0;i<rows;i++){
			for(int j =0;j<cols;j++){
				playBlocks.add(locRO[i][j]);
			}
		}
		//蛇的初始化
		for(int i = 4;i<7;i++){
			moveTo(locRO[4][i], playBlocks, snake);
		}
		//蛇头的位置
		snakeHead = new location(4,6);
		//设置一个食物
		snakeFood = new locationRO(0, 0);
		setSnakeFood();
		//初始化运动方向
		runningDirict = new location(0, 1);	
	}
	
	public SnakeModel(int rows1,int cols1){
		rows = rows1;
		cols = cols1;
		snake = new LinkedList();
		playBlocks = new LinkedList();
		locRO = new locationRO[rows][cols];
		
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				locRO[i][j] = new locationRO(i, j);
			}
		}
		
		init();
	}
	
	//difine the snake move method,if move successfully,return true
	//else return false
	public boolean move(location direction){
		//判断是否有方向相反的情况
		if(direction.rev(runningDirict)){
			snakeHead.setX(snakeHead.getX()+runningDirict.getX());
			snakeHead.setY(snakeHead.getY()+runningDirict.getY());
		}else{
			snakeHead.setX(snakeHead.getX()+direction.getX());
			snakeHead.setY(snakeHead.getY()+direction.getY());
		}
		//判断是否吃到了食物
		if((snakeHead.getX()==snakeFood.getX())&&(snakeHead.getY()==snakeFood.getY())){
			moveTo(locRO[snakeHead.getX()][snakeHead.getY()], playBlocks, snake);
			//吃了一个食物重置另一个
			setSnakeFood();
			gameScore+=10;
			addScore = true;
		}else{
			//没有吃到食物，要继续移动
			//是不是撞墙了
			addScore = false;
			if((snakeHead.getX()>=0)&&(snakeHead.getY()>=0)&&(snakeHead.getX()<rows)&&(snakeHead.getY()<cols)){
				//是不是跟自身相交了
				if(snake.contains(locRO[snakeHead.getX()][snakeHead.getY()])){
					JOptionPane.showConfirmDialog(null, "Game Over", "游戏结束", JOptionPane.PLAIN_MESSAGE);
					return false;
				}else{
					//正常移动,加入头部，移除尾部
					moveTo(locRO[snakeHead.getX()][snakeHead.getY()], playBlocks, snake);
					moveTo(snake.getFirst(), snake, playBlocks);
				}
			}else{
				JOptionPane.showConfirmDialog(null, "Game Over", "游戏结束", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
			if(!(direction.rev(runningDirict)||direction.equal(runningDirict))){
				runningDirict.setX(direction.getX());
				runningDirict.setY(direction.getY());
			}
		}
		return true;
		
	}
	
}
