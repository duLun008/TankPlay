 package model;

public class Bomb {
	private int x=0, y=0;
	private int time=9;
	public boolean isLive=true;
	
	public Bomb(int x, int y) {
		super();
		this.x=x; this.y=y;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getTime() {
		return time;
	}
	public void setX(int x) {
		this.x=x;
	}
	public void setY(int y) {
		this.y=y;
	}
	
	
	public void lifeDown() {
		if(time>0)
			time--;
		else
			isLive=false;
	}
	
}
