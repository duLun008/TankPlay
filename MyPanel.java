package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.*;

public class MyPanel extends JPanel implements Runnable{
	
	public MyTank myTank = null;
	EnemyTank ek=null;
	Image img;
	Vector<Tank> eks = new Vector<Tank>();
	Vector<Bomb> bs = new Vector<Bomb>();
	Map map = new Map();
	
	public MyPanel() {
		//创建己方坦克
		myTank = new MyTank(200,200,0,0);
		new Thread(myTank).start();
		//创建敌人坦克
		for(int i=0; i<6; i++) {
			ek=new EnemyTank(i*80, i*i*10,2,1);
			eks.add(ek);
			new Thread(ek).start();
		}
		
		img = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/bomb.png"));
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.fillRect(0, 0, 600, 600);
		//画坦克 &子弹
		if(myTank.isLive)
			drawTank(myTank.getX(), myTank.getY(), myTank.getType(), myTank.getDirect(), g);
		for(int i=0; i<myTank.mybs.size(); i++) {
			Bullet b = myTank.mybs.get(i);
			if(b.isLive) {
				g.setColor(Color.white);
				g.draw3DRect(b.getX(), b.getY(), 1, 1, false);
			}
			else {
				myTank.mybs.remove(b);
			}
		}
		
		for(int i=0; i<eks.size(); i++) {
			Tank tk=eks.get(i);
			if(tk.isLive) 
				drawTank(tk.getX(), tk.getY(), tk.getType(), tk.getDirect(), g);
			for(int j=0; j<tk.mybs.size(); j++) {
				Bullet b=tk.mybs.get(j);
				if(b.isLive) {
					g.setColor(Color.gray);
					g.draw3DRect(b.getX(), b.getY(), 2, 2, false);
				}
				else
					tk.mybs.remove(b);
			}
		}
		//画爆炸
		for (int i = 0; i < bs.size(); i++) {
            // System.out.println(bs.size());
            Bomb bb = bs.get(i);
            if (bb.isLive) {
                if (bb.getTime() > 6) {
                    /*try {
                        Thread.sleep(50);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }*/
                    g.drawImage(img, bb.getX(), bb.getY(), 30, 30, this);
                } else if (bb.getTime() > 3) {
                    g.drawImage(img, bb.getX(), bb.getY(), 15, 15, this);
                } else if (bb.getTime() > 0) {
                    g.drawImage(img, bb.getX(), bb.getY(), 1, 1, this);
                }
            }
            bb.lifeDown();
            if (bb.getTime() == 0)
                bs.remove(bb);
        }
	}
	//判断是否击中，击中则产生爆炸
	public boolean isHitEnemy(Bullet b, Tank tk) {
		 if(b.getX()>=tk.getX() && b.getX()<=tk.getX()+30 &&
				 b.getY()>=tk.getY() && b.getY()<=tk.getY()+30) {
			 b.isLive=false;
			 tk.isLive=false;
			 Bomb bb=new Bomb(tk.getX(), tk.getY());
			 bs.add(bb);
			 return true;
		 }
		 return false;
	}
	
	/*public void drawEnemyTank(int x, int y, int direct, Graphics g) {
		drawTank(x, y, 1, direct, g);
	}*/
	
	public void drawTank(int x, int y, int type, int direct, Graphics g) {
		if(type==0)
			g.setColor(Color.cyan);
		else
			g.setColor(Color.green);
		switch(direct) {
		case 0:
			g.fill3DRect(x, y, 5, 30, false);
			g.fill3DRect(x+5, y+5, 20, 20, false);
			g.fill3DRect(x+25, y, 5, 30, false);
			g.drawLine(x+15, y, x+15, y+5);
			break;
		case 1:
			g.fill3DRect(x, y, 30, 5, false);
			g.fill3DRect(x+5, y+5, 20, 20, false);
			g.fill3DRect(x, y+25, 30, 5, false);
			g.drawLine(x+25, y+15, x+30, y+15);
			break;
		case 2:
			g.fill3DRect(x, y, 5, 30, false);
			g.fill3DRect(x+5, y+5, 20, 20, false);
			g.fill3DRect(x+25, y, 5, 30, false);
			g.drawLine(x+15, y+25, x+15, y+30);
			break;
		case 3:
			g.fill3DRect(x, y, 30, 5, false);
			g.fill3DRect(x+5, y+5, 20, 20, false);
			g.fill3DRect(x, y+25, 30, 5, false);
			g.drawLine(x, y+15, x+5, y+15);
			break;
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				Thread.sleep(50);
			}catch(Exception e) {
				e.printStackTrace();
			}
			//判断我方坦克是否打到敌方
			for(int i=0; i<myTank.mybs.size(); i++) {
				Bullet b=myTank.mybs.get(i);
				if(b.isLive) {
					for(int j=0; j<eks.size(); j++) {
						Tank tk=eks.get(j);
						if(tk.isLive) {
							isHitEnemy(b, tk);
						}
					}
				}
			}
			//判断敌方坦克是否打到我
			for(int i=0; i<eks.size(); i++) {
				Tank tk=eks.get(i);
				if(tk.isLive) {
					for(int j=0; j<tk.mybs.size(); j++) {
						Bullet b=tk.mybs.get(j);
						if(b.isLive) {
							isHitEnemy(b, myTank);
						}
					}
				}
			}
			this.repaint();
			if(!myTank.isLive) {
				JOptionPane.showMessageDialog(this, "GG");
				myTank.isLive=true;
			}
		}
	}

}
