package org.xtext.comp.generator;

import org.xtext.comp.wh.Command;

public class InstrCons extends Instr {
	String codop;
	String varEcriture = "_";
	String varLecture1= "_";
	String varLecture2= "_";
	
	public InstrCons(String codop, String var1, String var2, String var3) {
		super(codop, var1, var2, var3);
		this.codop = "CONS";
		if(var1!=null)
			this.varEcriture = var1;
		if(var2!=null)
			this.varLecture1 = var2;
		if(var3!=null)
			this.varLecture2 = var3;
	}
	
	public String toString(){
		return "<"+this.codop+","+this.varEcriture+","+this.varLecture1+","+this.varLecture2+">";
	}

}