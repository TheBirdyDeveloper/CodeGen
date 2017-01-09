package org.xtext.comp.generator;

import org.xtext.comp.wh.Commands;
import org.xtext.comp.wh.Input;
import org.xtext.comp.wh.Output;

public class Code {

	String name;
	int inputs;
	int outputs;
	Commands code;
	
	public Code (String s, int inputs, int outputs, Commands code){
		name = s;
		this.inputs = inputs;
		this.outputs = outputs;
		if(code!=null)
			this.code = code;
	}
	
	public void setName(String s){
		name=s;
	}
	
	public void setInputs(int inputs){
		this.inputs=inputs;
	}
	
	public void setOutputs(int outputs){
		this.outputs=outputs;
	}
	
	public void setCode(Commands code){
		this.code=code;
	}
	
	
	public int getInputs(){
		return inputs;
	}
	
	public int getOutputs(){
		return outputs;
	}
	
	public Commands getCode(){
		return code;
	}
	
	public String getName(){
		return name;
	}
	
	public String toString(){
		return "name : "+name+" | Nb inputs/outputs : "+inputs+"/"+outputs+"\n";
	}
}
