package com.bugfullabs.mazegen;


public class Field {
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	private boolean edited = false;
	
	private int opens = 0;
	
	public Field(){
		left = true;
		right = true;
		up = true;
		down = true;
		edited = false;
	}
	
	public Field(boolean l, boolean r, boolean u, boolean d){
		left = l;
		right = r;
		up = u;
		down = d;
		edited = false;
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
	
	public boolean isEdited(){
		return edited;
	}
	
	public void setEdited(boolean b){
		edited = b;
	}
	
	public void set(boolean l, boolean r, boolean u, boolean d){
		edited = true;
		left = l;
		right = r;
		up = u;
		down = d;
	}
	
	public void setUp(boolean x){
		edited = true;
		
		if(x == false){
		opens++;	
		}else{
		opens--;	
		}
		
		up = x;
	}
	
	public void setDown(boolean x){
		edited = true;
		if(x == false){
		opens++;	
		}else{
		opens--;	
		}
		down = x;
	}
	
	public void setRight(boolean x){
		edited = true;
		if(x == false){
		opens++;	
		}else{
		opens--;	
		}
		right = x;
	}
	
	public void setLeft(boolean x){
		edited = true;
		if(x == false){
		opens++;	
		}else{
		opens--;	
		}
		left = x;
	}
	
	
	public int getOpens(){
		return opens;
	}

	
//OLD GENERATOR - NOT WORKING
/*	
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
*/
	
}
