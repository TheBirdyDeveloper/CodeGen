package org.xtext.comp.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.xtext.comp.wh.Expr;
import org.xtext.comp.wh.ExprSimple;
import org.xtext.comp.wh.impl.ExprSimpleImpl;

public class LocalEnvironment {

	HashMap<String,Expr> inputs;
	HashMap<String,Expr> outputs;
	HashMap<String,Instr> temp;
	HashMap<String,String> correspondances;
	int cpt;
	
	public LocalEnvironment(HashMap<String,Integer> inputs, HashMap<String,Integer> outputs, HashMap<String,Integer> variables){
		cpt = 1;
		this.correspondances = new HashMap<String,String>();
		this.inputs = new HashMap<String,Expr>();
		this.outputs = new HashMap<String,Expr>();
		this.temp = new HashMap<String,Instr>();
		
		this.temp.put("0",new InstrVar("nil",null,null,null));
		this.initializeTemp(this.temp,variables);
		this.initializeOutput(this.outputs,outputs);
		this.initializeOutput(this.inputs,inputs);
	}
	
	public Instr getInstr(String key){
		return this.temp.get(key);
	}
	
	private void initializeOutput(HashMap<String,Expr> map, HashMap<String, Integer> variables) {
		for(Entry<String,Integer> entry : variables.entrySet()){
			if(!this.correspondances.containsKey(entry.getKey())){
				this.correspondances.put(entry.getKey(), "X"+Integer.toString(cpt));
				cpt++;
			}
			map.put(this.correspondances.get(entry.getKey()), (Expr) new Nil());
		}
	}
	
	public String newVar(){
		String newVar = "X"+Integer.toString(cpt);
		String result = this.putInstr(new InstrVar(newVar,null,null,null));
		cpt++;
		return result;
	}
	private void initializeTemp(HashMap<String,Instr> map, HashMap<String, Integer> variables) {
		for(Entry<String,Integer> entry : variables.entrySet()){
			this.correspondances.put(entry.getKey(), "X"+Integer.toString(cpt));
			this.putInstr(new InstrVar("X"+Integer.toString(cpt),null,null,null));
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
		for(Entry<String, Instr> entry : temp.entrySet()){
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
	
	public String getAdrVar(String var){
		String val = this.getCorres(var);
		String res = null;
		for(Entry<String,Instr> entry : temp.entrySet()){
			if(entry.getValue() instanceof InstrVar && ((InstrVar) entry.getValue()).getVar().equals(val)){
				res = entry.getKey();
			}
		}
		return res;
	}

	
	public String getCorres(String key){
		return this.correspondances.get(key);
	}
	
	public String putInstr(Instr instr){
		int indice = this.temp.size();
		String string = Integer.toString(indice);
		
		this.temp.put(string, instr);
		return string;
	}
	public String putInstr(Expr expr){
		int indice = (this.temp.size());
		String string =Integer.toString(indice); 
		
		if(expr instanceof ExprSimple){
			boolean found = false;
			String val="";
			if(((ExprSimple) expr).getStr()!=null){
				string = "0"; 
				found = true;
			}
			else{
				if(((ExprSimple) expr).getSym()!=null){
					val =((ExprSimple) expr).getSym();
				}
				else if (((ExprSimple) expr).getVarSimple()!=null){
					val = this.getCorres(((ExprSimple) expr).getVarSimple());
				}
				if(!found){
						for(Entry<String, Instr> entry : temp.entrySet()){
							if( (entry.getValue() instanceof InstrVar) && ((InstrVar)entry.getValue()).getVar().equals(val)){
								string = entry.getKey();
								found=true;
							}
						}
					}
					if(!found){
						this.temp.put(string, new InstrVar(val,null,null,null));
					}
				}
		}
		return string;
	}

}
