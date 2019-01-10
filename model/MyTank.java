package model;

import control.KeyListen;

public class MyTank extends Tank implements Runnable{
	
	public MyTank(int x, int y, int direct, int type) {
		super(x, y, direct, type);
	}
	
	@Override 
	public void run(){
		while(isLive) {
			try{
				Thread.sleep(100);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			if((KeyListen.press&1)!=0){
				moveUp();
			}
			if((KeyListen.press&(1<<1))!=0) {
				moveRight();
			}
			if((KeyListen.press&(1<<2))!=0) {
				moveDown();
			}
			if((KeyListen.press&(1<<3))!=0) {
				moveLeft();
			}
			if((KeyListen.press&(1<<4))!=0 && mybs.size()<5) {
				shot();
			}
		}
	}
}
