package com.bugfullabs.mazegen;

import java.util.Random;

public class Field {
	
	private static Random rand;
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	
	public Field(){
		left = true;
		right = true;
		up = true;
		down = true;
		rand = new Random();
	}
	
	public Field(boolean l, boolean r, boolean u, boolean d){
		left = l;
		right = r;
		up = u;
		down = d;
		rand = new Random();
	}
	
	
	public boolean getLeft(){
		return left;
	}
	
	public boolean getRight(){
		return right;
	}
	
	public boolean getUp(){
		return up;
	}
	
	public boolean getDown(){
		return down;
	}
	
	public void set(boolean l, boolean r, boolean u, boolean d){
		left = l;
		right = r;
		up = u;
		down = d;
	}
	
	public void setUp(boolean x){
		up = x;
	}
	
	public void setDown(boolean x){
		down = x;
	}
	
	public void setRight(boolean x){
		right = x;
	}
	
	public void setLeft(boolean x){
		left = x;
	}
	
	
	public static Field generateNext(Field f){
		
		Field newField = new Field();
		rand.setSeed(System.currentTimeMillis());
		
		boolean tmp1 = rand.nextBoolean();
		boolean tmp2 = rand.nextBoolean();
		boolean tmp3 = rand.nextBoolean();
		
		if(tmp1 == true && tmp2 == true && tmp3 == true){
			tmp1 = false;
		}
		
		if(f.getUp() == false){
		newField.setDown(false);	
		newField.setUp(tmp1);	
		newField.setLeft(tmp2);	
		newField.setRight(tmp3);	
		
		}else if(f.getDown() == false){
		newField.setUp(false);
		newField.setDown(tmp1);	
		newField.setLeft(tmp2);	
		newField.setRight(tmp3);
		}else if(f.getLeft() == false){
		newField.setRight(false);
		newField.setUp(tmp1);	
		newField.setLeft(tmp2);	
		newField.setDown(tmp3);
		}else if(f.getRight() == false){
		newField.setLeft(false);
		newField.setUp(tmp1);	
		newField.setDown(tmp2);	
		newField.setRight(tmp3);
		}
		
		
		return f;	
	}

	
	@SuppressWarnings("unused")
	private int abs(int a)	
	{	
	if(a >= 0)
		return a;
	else
	return -a;
	}	

	
}
