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
	HashMap<String,String> correspondances;
	int cpt;
	
	public LocalEnvironment(HashMap<String,Integer> inputs, HashMap<String,Integer> outputs, HashMap<String,Integer> variables){
		cpt = 1;
		this.correspondances = new HashMap<String,String>();
		this.inputs = new HashMap<String,Expr>();
		this.outputs = new HashMap<String,Expr>();
		this.temp = new HashMap<String,Expr>();
		
		this.temp.put("0", new Nil());
		this.initializeTemp(this.temp,variables);
		this.initializeOutput(this.outputs,outputs);
	}
	
	public Expr getExpr(String key){
		String val = key;
		if(this.correspondances.containsKey(key))
			val = this.correspondances.get(key);
		
		if(this.inputs.containsKey(val))
			return this.inputs.get(val);
		else if(this.temp.containsKey(val))
			return this.temp.get(val);
		else
			return this.outputs.get(val);
	}
	
	private void initializeOutput(HashMap<String,Expr> map, HashMap<String, Integer> variables) {
		for(Entry<String,Integer> entry : variables.entrySet()){
			map.put(this.correspondances.get(entry.getKey()), (Expr) new Nil());
		}
	}
	private void initializeTemp(HashMap<String,Expr> map, HashMap<String, Integer> variables) {
		for(Entry<String,Integer> entry : variables.entrySet()){
			this.correspondances.put(entry.getKey(), Integer.toString(cpt));
			map.put(Integer.toString(cpt), (Expr) new Nil());
			cpt++;
		}
		
	}

	public HashMap<String,Expr> getInputs(){
		return inputs;
	}
	
	public HashMap<String,Expr> getOutputs(){
		return outputs;
	}
	
	public String toString(){
		String result = "Table des correspondances : "+this.correspondances.toString()+"\n";
		result+=this.inputs.toString()+", ";
		for(Entry<String,Expr> entry : temp.entrySet()){
			result+= entry.getKey().toString()+"=";
			if(entry.getValue() instanceof ExprSimple){
				if(((ExprSimple)entry.getValue()).getSym()!=null)
					result+=((ExprSimple)entry.getValue()).getSym();
				else if(((ExprSimple)entry.getValue()).getStr()!=null)
					result+=((ExprSimple)entry.getValue()).getStr();
				else if(((ExprSimple)entry.getValue()).getVarSimple()!=null)
					result+=((ExprSimple)entry.getValue()).getVarSimple();
				result+=", ";
			}
			else{
				result+=(entry.getValue().toString());
				result+=", ";
			}
				
		}
		result+=this.outputs.toString()+"\n";
		return result;
	}
	
	public void setExpr(String name, Expr expr){
		this.temp.put(name, expr);
	}
	
	public String getCorres(String key){
		return this.correspondances.get(key);
	}
	
	public String putExpr(Expr expr){
		int indice = (this.temp.size());
		String string =Integer.toString(indice); 
		
		if(expr instanceof ExprSimple){
			boolean found = false;
			String val="";
			if(((ExprSimple) expr).getStr()!=null){
				string = "0"; 
				found = true;
			}
			else if(((ExprSimple) expr).getVarSimple()!=null){
				string = this.correspondances.get(((ExprSimple) expr).getVarSimple());
				found = true;
			}
			else if(((ExprSimple) expr).getSym()!=null){
				val =((ExprSimple) expr).getSym(); 
			
				if(!found){
					for(Entry<String,Expr> entry : temp.entrySet()){
						if( (entry.getValue() instanceof ExprSimple) && val.equals(((ExprSimple) entry.getValue()).getSym())){
							string = entry.getKey();
							found=true;
						}
					}
				}
				if(!found)
					this.temp.put(string, expr);
			}
		}
		else{
			this.temp.put(string, expr);
		}
		
		return string;
	}

}
