package org.xtext.comp.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.xtext.comp.wh.Expr;
import org.xtext.comp.wh.ExprSimple;
import org.xtext.comp.wh.ExprSym;
import org.xtext.comp.wh.impl.ExprSimpleImpl;

public class LocalEnvironment {

	HashMap<String,Expr> inputs;
	HashMap<String,Expr> outputs;
	HashMap<String,Expr> temp;
	
	public LocalEnvironment(HashMap<String,Integer> inputs, HashMap<String,Integer> outputs, HashMap<String,Integer> variables){
		this.inputs = new HashMap<String,Expr>();
		this.outputs = new HashMap<String,Expr>();
		this.temp = new HashMap<String,Expr>();
		this.initializeMap(this.outputs,outputs);
		this.initializeMap(temp,variables);
	}
	
	private void initializeMap(HashMap<String,Expr> map, HashMap<String, Integer> variables) {
		for(Entry<String,Integer> entry : variables.entrySet()){
			map.put(entry.getKey(), (Expr) new Nil());
		}
		
	}

	public HashMap<String,Expr> getInputs(){
		return inputs;
	}
	
	public HashMap<String,Expr> getOutputs(){
		return outputs;
	}
	
	public String toString(){
		String result = this.inputs.toString()+"\n";
				for(Entry<String,Expr> entry : temp.entrySet()){
					result+= entry.getKey().toString()+"=";
					if(entry.getValue() instanceof ExprSimple){
						result+=((ExprSimple)entry.getValue()).getSym();
						result+="\n";
					}
					else{
						result+=(entry.getValue().toString());
						result+="\n";
					}
						
				}
		result+=this.outputs.toString()+"\n";
		return result;
	}
	
	public void setExpr(String name, Expr expr){
		this.temp.put(name, expr);
	}
	
	public int getIndice(){
		return this.temp.size();
	}
	
	public String putExpr(Expr expr){
		int indice = (this.temp.size());
		String string =Integer.toString(indice); 
		
		if(expr instanceof ExprSimple){
			if(!temp.containsValue(expr)){
				this.temp.put(string, expr);
			}
		}
		else{
			this.temp.put(string, expr);
		}
		
		return string;
	}

}
