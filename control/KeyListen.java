package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import view.*;

public class KeyListen implements KeyListener{
	private MyPanel mp=null;
	static public int press=0;
	
	public KeyListen(MyPanel mp) {
		super();
		this.mp=mp;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			mp.myTank.setDirect(0);
			press |= 1<<0;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			mp.myTank.setDirect(1);
			press |= 1<<1;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			mp.myTank.setDirect(2);
			press |= 1<<2;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			mp.myTank.setDirect(3);
			press |= 1<<3;
		}
		
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			press |= 1<<4;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			press&=30;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			press&=29;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			press&=27;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			press&=23;
		}
		
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			press&=15;
		}
	}
	
}
