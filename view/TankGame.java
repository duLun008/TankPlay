package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import control.KeyListen;

public class TankGame {
	private JFrame frame;
	
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TankGame window = new TankGame();
					window.frame.setVisible(true);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}
	
	public TankGame() {
		initialize();
	}
	
	private void initialize() {
		frame=new JFrame("坦克大战");
		frame.setBounds(450, 70, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menubar=new JMenuBar();
		frame.setJMenuBar(menubar);
		
		JMenu mnNewMenu = new JMenu("选项");
		mnNewMenu.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		menubar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem= new JMenuItem("开始游戏");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MyPanel mp = new MyPanel();
				Thread t=new Thread(mp);
				t.start();
				frame.getContentPane().add(mp, BorderLayout.CENTER);
				frame.addKeyListener(new KeyListen(mp));				
				frame.setVisible(true);
			}
		});
		
		mntmNewMenuItem.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("游戏说明");
		mnNewMenu.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		menubar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1= new JMenuItem("关于");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(frame, "上：w， 下：s， 左：a， 右：d\n made by du_lun");
			}
		});
		mntmNewMenuItem_1.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		mnNewMenu_1.add(mntmNewMenuItem_1);
	
	}
	
}
