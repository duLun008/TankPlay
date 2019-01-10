package model;

public class Bullet {
	private int x=0, y=0, speed=5, direct=0;
	public boolean isLive=true;
	static int[][] dir = {{0, -1},{1,0},{0,1},{-1,0}};
	public Bullet(int x, int y, int speed, int direct) {
		super();
		this.x=x; this.y=y; this.speed=speed; this.direct=direct;
		new Thread(new BulletThread()).start();
	}
	public Bullet() {
		super();
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getSpeed() {
		return speed;
	}
	public int getDirect() {
		return direct;
	}
	class BulletThread implements Runnable{
		@Override
		public void run() {
			while(isLive) {
				try {
					Thread.sleep(50);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				x+=dir[direct][0]*speed;
				y+=dir[direct][1]*speed;
				if(x<0 || x>500 || y<0 || y>500) isLive=false;
			}
		}
	}
	
}
