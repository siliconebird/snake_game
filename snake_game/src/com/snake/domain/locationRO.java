package com.snake.domain;

public class locationRO {
	private int x;
	private int y;
	public locationRO(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public boolean equalOrRev(locationRO e){
		return ((e.getX()==getX())&&(e.getY()==getY()))||((-1*e.getX()==getX())&&(e.getY()==getY()))||((e.getX()==getX())&&(-1*e.getY()==getY()));
				}
	public boolean equal(locationRO e){
		return (e.getX()==getX())&&(e.getY()==getY());
	}
	public boolean rev(locationRO e){
		return ((-1*e.getX()==getX())&&(e.getY()==getY()))||((e.getX()==getX())&&(-1*e.getY()==getY()));
	}
}
