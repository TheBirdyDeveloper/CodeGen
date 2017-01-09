package org.xtext.comp.generator;

import org.xtext.comp.wh.Command;

public class InstrCons extends Instr {

	String codop;

	
	public InstrCons(String codop, String var1, String var2, String var3) {
		super(codop, var1, var2, var3);
		this.codop = "CONS";
	}
	
	public String toString(){
		return "<"+this.codop+","+this.varEcriture+","+this.varLecture1+","+this.varLecture2+">";
	}

}