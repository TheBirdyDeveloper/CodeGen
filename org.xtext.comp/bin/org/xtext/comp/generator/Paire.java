package org.xtext.comp.generator;



public class Paire<L,R> {

	L left;
	R right;
	
	public Paire (L l, R r){
		left = l;
		this.right = r;
	}
	
	public void setLeft(L left){
		this.left=left;
	}
	
	public void setRight(R right){
		this.right=right;
	}
	
	public L getLeft(){
		return left;
	}
	
	public R getRight(){
		return right;
	}
	
	public L getleft(){
		return left;
	}
	
	
}
