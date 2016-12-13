package org.xtext.comp.generator;

import org.xtext.comp.wh.Input;

public class Paire {

	String name;
	Input inputs;
	
	public Paire (String s, Input inputs){
		name = s;
		this.inputs = inputs;
	}
	
	public void setName(String s){
		name=s;
	}
	
	public void setInputs(Input inputs){
		this.inputs=inputs;
	}
	
	public Input getInputs(){
		return inputs;
	}
	
	public String getName(){
		return name;
	}
	
	public String toString(){
		return name+" : "+inputs.getVars().toString();
	}
}
