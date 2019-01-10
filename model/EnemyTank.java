package model;

public class EnemyTank extends Tank implements Runnable{
	
	public EnemyTank(int x, int y, int direct, int type) {
		super(x, y, direct, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		while(isLive) {
			
			int step;
			step=(int)( Math.random()*50 )+20;
				direct=(int)(Math.random()*4);
				try {
					for(int i=0; i<step; i++) {
						switch(direct) {
						case 0:
							moveUp();
							break;
						case 1:
							moveRight();
							break;
						case 2:
							moveDown();
							break;
						case 3:
							moveLeft();
							break;
						}
						Thread.sleep(80);
						int s=(int)(Math.random()*10);
						if(s>=9) shot();
					}
				}catch(Exception e){
					e.printStackTrace();
				}		
			
		}
	}
}
