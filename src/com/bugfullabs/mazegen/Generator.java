package com.bugfullabs.mazegen;

import java.awt.BasicStroke;
import java.util.Random;


public class Generator{
	
	private int w;
	private int h;
	@SuppressWarnings("unused")
	private int mS;
	@SuppressWarnings("unused")
	private int off;
	private Random r;
	private Field board[][];
	
	
	Generator(int width, int height, int minSteps, int offset){
		w = width;
		h = height;
		mS = minSteps;
		off = offset;
		board = new Field[w][h];
		
		for(int i = 0; i < w; i++){
			for(int j = 0; j < h; j++){
				board[i][j] = new Field();
			}
		}
		
		r = new Random();
	}
	
	@SuppressWarnings("serial")
	public void start(){
	r.setSeed(System.currentTimeMillis());
	
	int start = abs(r.nextInt()%(w-1));
	boolean tmp1 = r.nextBoolean();
	boolean tmp2 = r.nextBoolean();
	boolean tmp3 = r.nextBoolean();
	//boolean tmp4 = r.nextBoolean();
	
	int tX;
	int tY;
	
	
	if(tmp1 == true && tmp2 == true && tmp3 == true){
		tmp1 = false;
	}
	
	board[0][start].set(tmp1, tmp2, tmp3, false);
	tX = 0;
	tY = start;
	
	
	//while(true){
	for(int i = 0; i < 20; i++){	
	
		
	if(board[tX][tY].getLeft() == false){
	
	if(tX<= 0){
	continue;	
	}
		
	board[tX-1][tY] = Field.generateNext(board[tX][tY]);	
	tX = tX - 1;	
	
	}else if(board[tX][tY].getUp() == false){
	
		if(tY >= h){
			continue;
		}
	
		
	board[tX][tY+1] = Field.generateNext(board[tX][tY]);	
	tY = tY + 1;
	
	}else if(board[tX][tY].getRight() == false){
	if(tX >= w){
		continue;
	}
	board[tX+1][tY] = Field.generateNext(board[tX][tY]);	
	tX = tX + 1;	
			
	}else if(board[tX][tY].getDown() == false){

		if(tY <= 0){
			continue;
		}	
		
	board[tX][tY-1] = Field.generateNext(board[tX][tY]);	
	tY = tY - 1;
			
	}
	
	if(tX == h || tX == 0 || tY == 0 || tY == w){
		break;
	}
	
	
	}
	
	Main.mDrawingPanel = new DrawingPanel(){
		@Override
		public void onPaint(){
		g2.setStroke(new BasicStroke(2.0F));
		
		
		for(int i = 0; i < w; i++){
		for(int j = 0; j < h; j++){
		
		if(board[i][j].getUp()){
		g2.drawLine(Main.MARGIN + i*Main.FIELD_WIDTH,
				    Main.MARGIN + j*Main.FIELD_HEIGHT,
				    Main.MARGIN + (i+1)*Main.FIELD_WIDTH,
				    Main.MARGIN + j*Main.FIELD_HEIGHT);	
		}
		if(board[i][j].getDown()){
		g2.drawLine(Main.MARGIN + i*Main.FIELD_WIDTH,
				    Main.MARGIN + (j+1)*Main.FIELD_HEIGHT,
				    Main.MARGIN + (i+1)*Main.FIELD_WIDTH,
				    Main.MARGIN + (j+1)*Main.FIELD_HEIGHT);		
		}	
		if(board[i][j].getLeft()){
		g2.drawLine(Main.MARGIN + i*Main.FIELD_WIDTH,
				    Main.MARGIN + j*Main.FIELD_HEIGHT,
				    Main.MARGIN + i*Main.FIELD_WIDTH,
				    Main.MARGIN + (j+1)*Main.FIELD_HEIGHT);			
		}
		if(board[i][j].getRight()){
		g2.drawLine(Main.MARGIN + (i+1)*Main.FIELD_WIDTH,
				    Main.MARGIN + j*Main.FIELD_HEIGHT,
				    Main.MARGIN + (i+1)*Main.FIELD_WIDTH,
				    Main.MARGIN + (j+1)*Main.FIELD_HEIGHT);					
		}
		
		}
		}
			
		}
	};
	
	Main.mDrawingFrame.add(Main.mDrawingPanel);
	Main.mDrawingPanel.repaint();
	
}
	
private int abs(int a)	
{	
if(a >= 0)
	return a;
else
return -a;
}	



}