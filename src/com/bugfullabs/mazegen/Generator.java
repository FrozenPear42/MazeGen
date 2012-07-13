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
	
	public static final int UP = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int DOWN = 3;
	
	public static final int RE_LVL = 100;
	
	
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
	
	int tX;
	int tY;
	int tC;
	int tmp;
	
	int last = 10; //not 0, 1, 2
	
	
	tX = abs(r.nextInt()%(w-1)); //Start X
	tY = h-1;						 //Start Y
	tC = abs(r.nextInt()%2);     //Counter of deleted 'modules'
	
	System.out.printf("Start tX: " + Integer.toString(tX) + " tY: " + Integer.toString(tY)+ " tC: "+ Integer.toString(tC) + "\n");
	
	board[tX][tY].setDown(false);
	
	for(; tC >= 0; tC--){
		
		System.out.printf(Integer.toString(tC) + "\n");
			
		tmp = abs(r.nextInt()%3);
		
		switch(tmp){
		case 0:
			if(last == 0 || (tY-1) <= 0)
			{
				tC++;
				continue;
			}
			board[tX][tY].setUp(false);
			nextField(tX, tY-1, UP, RE_LVL);	
			last = 0;
			break;
		case 1:
			if(last == 1 || (tX-1) < 0)
			{
				tC++;
				continue;
			}
			board[tX][tY].setLeft(false);
			nextField(tX-1, tY, LEFT, RE_LVL);
			last = 1;
			break;
		case 2:
			if(last == 2 || (tX+1) >= w)
			{
				tC++;
				continue;
			}
			board[tX][tY].setRight(false);
			nextField(tX+1, tY, RIGHT, RE_LVL);
			last = 2;
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
		
		//System.out.printf("UP X:" + Integer.toString(i) + " Y: " + Integer.toString(j) + "\n");
			
		g2.drawLine(Main.MARGIN + i*Main.FIELD_WIDTH,
				    Main.MARGIN + j*Main.FIELD_HEIGHT,
				    Main.MARGIN + (i+1)*Main.FIELD_WIDTH,
				    Main.MARGIN + j*Main.FIELD_HEIGHT);	
		}
		if(board[i][j].getDown()){
		
		//System.out.printf("DOWN X:" + Integer.toString(i) + " Y: " + Integer.toString(j) + "\n");		
			
		g2.drawLine(Main.MARGIN + i*Main.FIELD_WIDTH,
				    Main.MARGIN + (j+1)*Main.FIELD_HEIGHT,
				    Main.MARGIN + (i+1)*Main.FIELD_WIDTH,
				    Main.MARGIN + (j+1)*Main.FIELD_HEIGHT);		
		}	
		if(board[i][j].getLeft()){
			
		//System.out.printf("LEFT X:" + Integer.toString(i) + " Y: " + Integer.toString(j) + "\n");	
			
		g2.drawLine(Main.MARGIN + i*Main.FIELD_WIDTH,
				    Main.MARGIN + j*Main.FIELD_HEIGHT,
				    Main.MARGIN + i*Main.FIELD_WIDTH,
				    Main.MARGIN + (j+1)*Main.FIELD_HEIGHT);			
		}
		if(board[i][j].getRight()){
			
		//System.out.printf("RIGHT X:" + Integer.toString(i) + " Y: " + Integer.toString(j) + "\n");	
			
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

private void nextField(int tX, int tY, int pos, int n){
	
	System.out.printf("NEXT: tX: " + Integer.toString(tX)+ "tY: " + Integer.toString(tY) + " n: " + Integer.toString(n) + "\n");
	
	
	if(n <= 0)
		return;
	
	
	if(board[tX][tY].isEdited()){
	return;
	}
	
	int tC;
	int tmp;
	
	boolean is0 = false;
	boolean is1 = false;
	boolean is2 = false;
	boolean is3 = false;
	

	tC = abs(r.nextInt()%2);     //Counter of deleted 'modules'

	switch(pos){
	case UP:
		is3 = true;
		board[tX][tY].setDown(false);
		break;
	case DOWN:
		is0 = true;
		board[tX][tY].setUp(false);
		break;
	case LEFT:
		is2 = true;
		board[tX][tY].setRight(false);
		break;
	case RIGHT:
		is1 = true;
		board[tX][tY].setLeft(false);
		break;
	}
	
	
	for(; tC >= 0; tC--){
		//System.out.printf("tC: " + Integer.toString(tC) + ".\n");
		tmp = abs(r.nextInt()%4);
		
		if(is0 && is1 && is2 && is3){
			System.out.printf("RETURN!!!\n");
			return;
		}
		
		switch(tmp){
		case 0:
			if(!is0){
			
			System.out.printf("is0 \n");
				
			is0 = true;
			
			if((tY-1) >= 0){
			
			
			if(board[tX][tY-1].isEdited()){
			tC++;
			continue;
			}
			
			System.out.printf("SUP");
			board[tX][tY].setUp(false);	
			nextField(tX, tY-1, UP, n-1);
			}
			}else{
			tC++;
			continue;
			}
			break;
			
		case 1:
			if(!is1){
			
			System.out.printf("is1 \n");
				
			is1 = true;	
			
			if((tX-1) >= 0){
			
			if(board[tX-1][tY].isEdited()){
			tC++;	
			continue;
			}
			
			System.out.printf("SLEFT");
			board[tX][tY].setLeft(false);
			nextField(tX-1, tY, LEFT, n-1);
			}
			}else{
			tC++;
			continue;
			}
			break;
			
		case 2:
			if(!is2){
			
			System.out.printf("is2 \n");	
				
			is2 = true;
			
			if( (tX+1) < w){
			
			if(board[tX+1][tY].isEdited()){
			tC++;
			continue;
			}
			System.out.printf("SRIGHT");
			board[tX][tY].setRight(false);
			nextField(tX+1, tY, RIGHT, n-1);
			}
			}else{
			tC++;
			continue;
			}
			break;
			
		case 3:
			if(!is3){
				
			System.out.printf("is3 \n");	
				
			is3 = true;
			
			if((tY+1) < h){
			
			if(board[tX][tY+1].isEdited()){
			tC++;
			continue;
			}
			System.out.printf("SDOWN");
			board[tX][tY].setDown(false);
			nextField(tX, tY+1, DOWN, n-1);
			}
			}else{
			tC++;
			continue;
			}
			break;	
		}
		
		
		
	}
	
	
}
	
		
private int abs(int a)	
{	
	if(a >= 0)
		return a;
	else
	return -a;
}	


//OLD GENERATOR - NOT WORKING
//while(true){
/*
for(int i = 0; i < 20; i++){	

	
if(board[tX][tY].getLeft() == false){

if(tX<= 0){

	board[tX][tY].setLeft(true);
	board[tX][tY].setRight(false);
continue;	
}
	
board[tX-1][tY] = Field.generateNext(board[tX][tY]);	
tX = tX - 1;	

}else if(board[tX][tY].getUp() == false){

	if(tY >= h){
		board[tX][tY].setUp(true);
		board[tX][tY].setDown(false);
		continue;
	}

	
board[tX][tY+1] = Field.generateNext(board[tX][tY]);	
tY = tY + 1;

}else if(board[tX][tY].getRight() == false){
if(tX >= w){
	board[tX][tY].setLeft(false);
	board[tX][tY].setRight(true);
	continue;
}
board[tX+1][tY] = Field.generateNext(board[tX][tY]);	
tX = tX + 1;	
		
}else if(board[tX][tY].getDown() == false){

	if(tY <= 0){
		board[tX][tY].setDown(true);
		board[tX][tY].setUp(false);
		continue;
	}	
	
board[tX][tY-1] = Field.generateNext(board[tX][tY]);	
tY = tY - 1;
		
}

if(tX == h || tX == 0 || tY == 0 || tY == w){
	break;
}


} 
*/



}