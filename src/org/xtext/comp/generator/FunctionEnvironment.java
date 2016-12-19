package org.xtext.comp.generator;

import java.util.HashMap;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.xtext.comp.wh.Affect;
import org.xtext.comp.wh.Commands;
import org.xtext.comp.wh.ExprSimple;
import org.xtext.comp.wh.Function;
import org.xtext.comp.wh.Input;
import org.xtext.comp.wh.Output;
import org.xtext.comp.wh.Program;

public class FunctionEnvironment {

	boolean isfunction;
	HashMap<String,Integer> inputList;
	HashMap<String,Integer> variableList;
	int nbInput;
	int nbOutput;
	String name;
	int nbOccur;
	Commands commands;

	public FunctionEnvironment(Function f, String name){
		this.inputList = new HashMap<String,Integer>();
		this.nbInput = 0;
		this.nbOutput = 0;
		this.createinputList(f);
		this.name= name;
		this.variableList = new HashMap<String,Integer>();
		this.createVariableList(f);
		this.commands = ((Function)f).getDefinition().getCommands();
		isfunction = true;


	}
	
	public boolean isFunction(){
		return isfunction;
	}
	public FunctionEnvironment(ExprSimple s){
		inputList=new HashMap<String,Integer>();
		this.nbOccur = 0;
		isfunction = false;
	}

	public void createinputList(Function f){
		EList<String> varListInput = null;
		if((f.getDefinition().getInput().getVars().size()>0)){
			varListInput = (f.getDefinition().getInput().getVars());
			for(int i = 0; i<(varListInput.size()); i++){
				String varName = varListInput.get(i);
				if(!(inputList.containsKey(varName))){
					inputList.put(varName, 1);
					nbInput++;
				}else{
					throw new Error("Cette variable existe déja");
				}
			}
		}
		EList<String> varListOutput = null;
		if((f.getDefinition().getOutput().getVars().size()>0)){
			varListOutput = (f.getDefinition().getOutput().getVars());
			for(int i = 0; i<(varListOutput.size()); i++){
				String varName = varListOutput.get(i);
				if(inputList.containsKey(varName)){
					nbOutput++;
				}else{
					inputList.put(varName, 0);
				}
			}
		}
	}

	public void createVariableList(Function f){
		TreeIterator<EObject> tree = f.eAllContents();

		while(tree.hasNext()){
			EObject next = tree.next();
			if(next instanceof ExprSimple){
				String var = ((ExprSimple) next).getVarSimple();
				if (var != null){
					if(!(variableList.containsKey(var))){
						variableList.put(var, 1);
					}else{
						variableList.put(var, variableList.get(var)+1);;
					}
				}
			}
			if(next instanceof Affect){
				EList<String> vars = ((Affect) next).getVars();
				for(String current : vars){
					if (current != null){
						if(!(variableList.containsKey(current))){
							variableList.put(current, 1);
						}else{
							variableList.put(current, variableList.get(current)+1);;
						}
					}
				}
			}
		}
	}

	public void setNbOccur(int occur){
		this.nbOccur=occur;
	}

	public String toString(){
		return inputList.toString();
	}
	public int nbInput(){
		return this.nbInput;
	}
	
	public int nbOutput(){
		return this.nbOutput;
	}

	public Commands getCommands(){
		return commands;
	}

}