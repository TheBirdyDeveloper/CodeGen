package org.xtext.comp.generator;

import java.util.List;

public class InstrFun extends Instr {

	private List<String> paramètres;
	public InstrFun(String codop, String var1, String var2, String var3, List<String> inputs) {
		super(codop, var1, var2, var3);
		// TODO Auto-generated constructor stub
		
		paramètres = inputs;
	}

	public String toString(){
		return "<FUN,"+this.varEcriture+","+this.varLecture1+","+this.varLecture2+">";
	}
	
	public List<String> getParam(){
		return paramètres;
	}
}
