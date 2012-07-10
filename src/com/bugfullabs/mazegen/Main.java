package com.bugfullabs.mazegen;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

/**
*
* @author Bugfullabs
* @email wojciech@bugfullabs.pl
* 
 */


public class Main{
	
	public static JFrame mFrame;
	public static JLabel jLabel1;
	public static JLabel jLabel2;
	public static JMenuBar mMenuBar;
	public static JLabel jLabel3;
	public static JLabel jLabel4;
	public static JMenuItem mExit;
	public static JButton mButtonStart;
	public static JMenu mMenuFile;
	public static JMenuItem mHelp;
	public static JMenuItem mAbout;
	public static JProgressBar mProgress;
	public static JTextField mTFDiff;
	public static JMenu mMenuHelp;
	public static JTextField mTFWidth;
	public static JTextField mTFHeight;

	
	
	public static void main(String[] args){
		mFrame = new JFrame();
	    initGUI();
	   
		}
	
	
	
	private static void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)mFrame.getContentPane());
			mFrame.getContentPane().setLayout(thisLayout);
			mFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			mFrame.setTitle("MazeGen");
			//mFrame.setIconImage(new ImageIcon(mFrame.getClass().getClassLoader().getResource("maze.png")).getImage());
			{
				mMenuBar = new JMenuBar();
				mFrame.setJMenuBar(mMenuBar);
				
				{
					mMenuFile = new JMenu();
					mMenuBar.add(mMenuFile);
					mMenuFile.setText("File");
					{
						mExit = new JMenuItem();
						mMenuFile.add(mExit);
						mExit.setText("Exit");
					}
				}
				
				
				{
					mMenuHelp = new JMenu();
					mMenuBar.add(mMenuHelp);
					mMenuHelp.setText("Help");
					{
						mAbout = new JMenuItem();
						mMenuHelp.add(mAbout);
						mAbout.setText("About");
					}
					{
						mHelp = new JMenuItem();
						mMenuHelp.add(mHelp);
						mHelp.setText("Help");
					}
				}
				
				
			}
			{
				jLabel1 = new JLabel();
				jLabel1.setText("Width: ");
				jLabel1.setFont(new java.awt.Font("Tahoma",0,14));
				jLabel1.setSize(80, 20);
			}
			{
				jLabel2 = new JLabel();
				GroupLayout jLabel2Layout = new GroupLayout((JComponent)jLabel2);
				jLabel2.setLayout(null);
				jLabel2.setText("Height: ");
				jLabel2.setFont(new java.awt.Font("Tahoma",0,14));
				jLabel2.setSize(80, 20);
				jLabel2Layout.setVerticalGroup(jLabel2Layout.createSequentialGroup());
				jLabel2Layout.setHorizontalGroup(jLabel2Layout.createSequentialGroup());
			}
			{
				mTFHeight = new JTextField();
			}
			{
				mButtonStart = new JButton();
				mButtonStart.setText("Start!");
			}
			{
				mProgress = new JProgressBar();
			}
			{
				jLabel4 = new JLabel();
				jLabel4.setText("Maze Generator");
				jLabel4.setFont(new java.awt.Font("Tahoma",1,14));
			}
			{
				mTFWidth = new JTextField();
			}
			{
				jLabel3 = new JLabel();
				GroupLayout jLabel3Layout = new GroupLayout((JComponent)jLabel3);
				jLabel3.setText("Min. Steps: ");
				jLabel3.setFont(new java.awt.Font("Tahoma",0,14));
				jLabel3.setLayout(null);
				jLabel3.setSize(80, 20);
				jLabel3Layout.setVerticalGroup(jLabel3Layout.createParallelGroup());
				jLabel3Layout.setHorizontalGroup(jLabel3Layout.createParallelGroup());
			}
			{
				mTFDiff = new JTextField();
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addGap(6)
				.addComponent(jLabel4, 0, 15, Short.MAX_VALUE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(jLabel2, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
				    .addComponent(mTFHeight, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(jLabel1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
				    .addComponent(mTFWidth, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(jLabel3, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
				    .addComponent(mTFDiff, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(mButtonStart, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(mProgress, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
				.addContainerGap());
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(thisLayout.createSequentialGroup()
				        .addGroup(thisLayout.createParallelGroup()
				            .addComponent(jLabel3, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
				            .addComponent(jLabel1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
				            .addGroup(thisLayout.createSequentialGroup()
				                .addGap(0, 0, Short.MAX_VALUE)
				                .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)))
				        .addGroup(thisLayout.createParallelGroup()
				            .addComponent(mTFDiff, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
				            .addComponent(mTFWidth, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
				            .addComponent(mTFHeight, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
				        .addGap(13))
				    .addComponent(mButtonStart, GroupLayout.Alignment.LEADING, 0, 148, Short.MAX_VALUE)
				    .addComponent(mProgress, GroupLayout.Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				    .addComponent(jLabel4, GroupLayout.Alignment.LEADING, 0, 148, Short.MAX_VALUE))
				.addContainerGap());
			mFrame.pack();
			mFrame.setSize(180, 250);
			mFrame.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}