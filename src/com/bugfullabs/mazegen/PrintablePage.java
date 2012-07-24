package com.bugfullabs.mazegen;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;




public class PrintablePage implements Printable{

	private Field b[][];
	private int w;
	private int h;
	
	public PrintablePage(Field board[][], int width, int height){
		
		w = width;
		h = height;
		b = board;
		
	}
	
	@Override
	public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
	
		
		if(page == 0){
			Graphics2D g2 = (Graphics2D) g;
		
			g2.setColor (Color.black);
			
			g2.setStroke(new BasicStroke(2.0F));
			

			int f_size;
			int tmp;
			int x_margin = 0;
			int y_margin = 0;
			
			
			
			f_size = (int) (pf.getImageableWidth()/w);	
			tmp = (int) (pf.getImageableHeight()/h);
			
			if(tmp < f_size)
				f_size = tmp;
			
			if (f_size <= 0)
				f_size = 1;
			
			x_margin = (int) ((pf.getImageableWidth()-(w*f_size))/2);
			y_margin = (int) ((pf.getImageableHeight()-(h*f_size))/2);
			
			
			g2.translate (pf.getImageableX (), pf.getImageableY ());
			
			
			for(int i = 0; i < w; i++){
			for(int j = 0; j < h; j++){
			
			if(b[i][j].getUp()){
				
			g2.drawLine(x_margin + i*f_size,
					    y_margin + j*f_size,
					    x_margin + (i+1)*f_size,
					    y_margin + j*f_size);	
			}
			if(b[i][j].getDown()){
				
			g2.drawLine(x_margin + i*f_size,
						y_margin + (j+1)*f_size,
					    x_margin + (i+1)*f_size,
					    y_margin + (j+1)*f_size);		
			}	
			if(b[i][j].getLeft()){
				
				
			g2.drawLine(x_margin + i*f_size,
				     	y_margin + j*f_size,
					    x_margin + i*f_size,
					    y_margin + (j+1)*f_size);			
			}
			if(b[i][j].getRight()){
					
			g2.drawLine(x_margin + (i+1)*f_size,
					    y_margin + j*f_size,
					    x_margin + (i+1)*f_size,
					    y_margin + (j+1)*f_size);					
			}
			
			}
			}
		return PAGE_EXISTS;	
		}
	
		return NO_SUCH_PAGE;
	}
	
	
	
	
}