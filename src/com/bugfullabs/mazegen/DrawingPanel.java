package com.bugfullabs.mazegen;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class DrawingPanel extends JPanel{

	
	public Graphics2D g2;
	
	
	@Override
	public void paintComponent(Graphics g){		
		g2 = (Graphics2D)g;
		onPaint();
	}
	
	public void onPaint(){
		
	}
	
}