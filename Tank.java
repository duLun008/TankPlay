package model;

import java.awt.GridLayout;
import java.util.Vector;

public class Tank{
	private int x=0, y=0;//初始位置
	private int speed=3;
	protected int direct=0;//0、1、2、3、分别代表上右左下
	private int type=0;//坦克类型、0代表己方
	Bullet myBullet;
	public boolean isLive=true;
	protected boolean isStart=true;
	
	public Vector<Bullet> mybs = new Vector<Bullet>();

	public Tank(int x, int y, int direct, int type) {
		super();
		this.x=x; this.y=y; this.direct=direct; this.type=type;
	}
	
	public int getX(){
		return x;
	}
	public void setX(int x) {
		this.x=x;
	}
	public int getY(){
		return y;
	}
	public void setY(int y) {
		this.y=y;
	}
	public int getType(){
		return type;
	}
	public void setType(int type) {
		this.type=type;
	}
	public int getDirect() {
		return direct;
	}
	public void setDirect(int direct) {
		this.direct=direct;
	}
	
	public int getSpeed(){
		return x;
	}
	public void setSpeed(int speed) {
		this.speed=speed;
	}
	public Bullet getMyBullet() {
		return myBullet;
	}
	
	public void moveUp() {
		int ty=y-speed;
		if(ty<0) ty=0;
		if(!isOccupied(x, y-speed-1)) {
			clearMap(x, y);
			setMap(x, ty);
			y=ty;
		}
	}
	public void moveDown() {
		int ty=y+speed;
		if(ty>500) ty=500;
		if(!isOccupied(x, y+31)) {
			clearMap(x, y);
			setMap(x, ty);
			y=ty;
		}
	}
	public void moveLeft() {
		int tx=x-speed;
		if(tx<0) tx=0;
		if(!isOccupied(x-speed-1, y)) {
			clearMap(x, y);
			setMap(tx, y);
			x=tx;
		}
	}
	public void moveRight() {
		int tx=x+speed;
		if(tx>600) x=600;
		if(!isOccupied(x+31, y)) {
			clearMap(x, y);
			setMap(tx, y);
			x=tx;
		}
	
	}
	
	void setMap(int x, int y) {
		for(int i=0; i<=30; i++) {
			for(int j=0; j<=30; j++) {
				int tx=Math.min(x+i, 600);
				int ty=Math.min(y+j, 500);
				Map.location[tx][ty]=1;
			}
		}
	}
	void clearMap(int x, int y) {
		for(int i=0; i<=30; i++) {
			for(int j=0; j<=30; j++) {
				int tx=Math.min(x+i, 600);
				int ty=Math.min(y+j, 500);
				Map.location[tx][ty]=0;
			}
		}
	}
	boolean isOccupied(int x, int y) {
		if((direct&1)!=0) {
			for(int i=0; i<direct; i++) {
				for(int j=0; j<30; j++) {
					if(x+i>=0 && x+i<600 && y+i>=0 && y+i<500) {
						if(Map.location[x+i][y+i]==1) return  true;
					}
				}
			}
		}
		else {
			for(int i=0; i<30; i++) {
				for(int j=0; j<direct; j++){
					if(x+i>=0 && x+i<600 && y+i>=0 && y+i<500) {
						if(Map.location[x+i][y+i]==1) return true;
					}
				}
			}
		}
		return false;
	}
	
	public void shot() {
		int tx=x, ty=y;
		switch(direct) {
		case 0:
			tx+=15;
			break;
		case 1:
			tx+=30;
			ty+=15;
			break;
		case 2:
			tx+=15;
			ty+=30;
			break;
		case 3:
			ty+=15;
			break;
		}
		myBullet=new Bullet(tx, ty, 5, direct);
		mybs.add(myBullet);
	}	
	
	
}
