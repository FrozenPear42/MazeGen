package com.bugfullabs.mazegen;

import java.awt.BasicStroke;
import java.util.Random;


public class Generator{
	
	private int w;
	private int h;
	private int mS;
	@SuppressWarnings("unused")
	private int off;
	private Random r;
	private Field board[][];
	private boolean isExit = false;
	public boolean finished = false;
	private int RE_LVL;
	
	public static final int UP = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int DOWN = 3;
	
	public static final int TRY = 100;
	
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
		RE_LVL = 100 + mS;
		
		
	}
	
	
	
	public void start(){
	r.setSeed(System.currentTimeMillis());
	
	int cnt = 0;
	
	int tX;
	int tY;
	int tC;
	int tmp;
	
	int last = 10; //not 0, 1, 2
	
	isExit = false;
	
	do{
		Main.mProgress.setValue((cnt*10)/TRY);
		cnt++;
		
		if(cnt > TRY){
			//TODO: ERROR DIALOG
			return;
		}
		
		
		last = 10;
		
		board = new Field[w][h];
		
		for(int i = 0; i < w; i++){
			for(int j = 0; j < h; j++){
				board[i][j] = new Field();
			}
		}

		
	tX = abs(r.nextInt()%(w-1)); //Start X
	tY = h-1;						 //Start Y
	tC = 1;// abs(r.nextInt()%2);     //Counter of deleted 'modules'
	
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
			nextField(tX, tY-1, UP, RE_LVL, 0);	
			last = 0;
			break;
		case 1:
			if(last == 1 || (tX-1) < 0)
			{
				tC++;
				continue;
			}
			board[tX][tY].setLeft(false);
			nextField(tX-1, tY, LEFT, RE_LVL, 0);
			last = 1;
			break;
		case 2:
			if(last == 2 || (tX+1) >= w)
			{
				tC++;
				continue;
			}
			board[tX][tY].setRight(false);
			nextField(tX+1, tY, RIGHT, RE_LVL, 0);
			last = 2;
			break;	
		}
		
	}
	
	}while(!isExit);
	
	
	fixUneditedFields();
	
	
	finished = true;
	draw();
	}
	
	@SuppressWarnings("serial")
	private void draw(){
	
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
	Main.mDrawingFrame.setVisible(true);
	Main.mDrawingPanel.repaint();
	
}

private void nextField(int tX, int tY, int pos, int n, int len){
	
	System.out.printf("NEXT: tX: " + Integer.toString(tX)+ "tY: " + Integer.toString(tY) + " n: " + Integer.toString(n) + "\n");
	
	
	if(n == 0)
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
	

	tC = abs(r.nextInt()%3);     //Counter of deleted 'modules'
	
	
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
	
	if(!isExit ){
	if(tX == 0 || tX == w-1 || tY == 0 || tY == h-1){
		if(len >= mS){
			isExit = true;
			
			if(tX == 0)
				board[tX][tY].setLeft(false);
			
			if(tX == w-1)
				board[tX][tY].setRight(false);
			
			if(tY == 0)
				board[tX][tY].setUp(false);
			
			if(tY == h-1)
				board[tX][tY].setDown(false);
			
			
			return;
		}
	}
	}
	
	int lvl = 0;
	
	if(isExit)
		lvl = -1;
	else
	    lvl = n-1;

	
	
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
			nextField(tX, tY-1, UP, lvl, len+1);
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
			nextField(tX-1, tY, LEFT, lvl, len+1);
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
			nextField(tX+1, tY, RIGHT, lvl, len+1);
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
			nextField(tX, tY+1, DOWN, lvl, len+1);
			}
			}else{
			tC++;
			continue;
			}
			break;	
		}
		
		
		
	}
	
	
}
	


private void fixUneditedFields(){
	
	int s = 10;
	
	for(int i = 0; i < w; i++){
		for(int j = 0; j < h; j++){
			if(!board[i][j].isEdited()){
			
				if(i > 0 && i < w-1 && j > 0 && j < h-1){
				
						  if(board[i-1][j].isEdited()){
					    
						board[i-1][j].setRight(false);
						board[i][j].setLeft(false);
						
							  
					}else if(board[i+1][j].isEdited()){
						
						board[i+1][j].setLeft(false);
						board[i][j].setRight(false);
						
					}else if(board[i][j-1].isEdited()){
						
						board[i][j-1].setDown(false);
						board[i][j].setUp(false);
						
					}else if(board[i][j+1].isEdited()){
						
						board[i][j+1].setUp(false);
						board[i][j].setDown(false);
						
					}else{
					//THAT SHOULD NOT HAPPENED	
					//IF DO I'DONT KOW WHAT TO DO
					}
					
				}else{
				
				
				if(i-1 >= 0){
					if(board[i-1][j].isEdited())
						s = 0;
				}else
				
				if(i+1 < w){
					if(board[i+1][j].isEdited())
						s = 1;
				}else
				
				if(j-1 >= 0){
					if(board[i][j-1].isEdited())
						s = 2;
				}else 
				
				if(j+1 < h){
					if(board[i][j+1].isEdited())
						s = 3;
				}
				
				
				switch(s){
				
				case 0:
					board[i-1][j].setRight(false);
					board[i][j].setLeft(false);
					break;
				
				case 1:
					board[i+1][j].setLeft(false);
					board[i][j].setRight(false);	
					break;
				
				case 2:
					board[i][j-1].setDown(false);
					board[i][j].setUp(false);
					break;
					
				case 3:
					board[i][j+1].setUp(false);
					board[i][j].setDown(false);
					break;
					
				}
				
			}
			s = 10;	
			}
		}
	}
	
	
	
}





public boolean isFinished(){
	return finished;
}

public Field[][] getBoard(){
	return board;
}

private int abs(int a)	
{	
	if(a >= 0)
		return a;
	else
	return -a;
}



public int getWidth() {
	return w;
}



public int getHeight() {
	return h;
}	



}