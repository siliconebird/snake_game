package com.snake.domain;

public class location {
	private int x;
	private int y;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public location(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public boolean equalOrRev(location e){
		return ((e.getX()==getX())&&(e.getY()==getY()))||((-1*e.getX()==getX())&&(e.getY()==getY()))||((e.getX()==getX())&&(-1*e.getY()==getY()));
				}
	public boolean equal(location e){
		return (e.getX()==getX())&&(e.getY()==getY());
	}
	public boolean rev(location e){
		return ((-1*e.getX()==getX())&&(e.getY()==getY()))||((e.getX()==getX())&&(-1*e.getY()==getY()));
	}
}
