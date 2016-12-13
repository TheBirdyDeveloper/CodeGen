package org.xtext.comp.generator;

import org.xtext.comp.wh.Command;

public class InstrNop extends Instr{

	private String codop,varEcriture, varLecture1, varLecture2;
	
	public InstrNop(Command codop, String var1, String var2, String var3) {
		super(codop, var1, var2, var3);
		this.codop = "NOP";
		this.varEcriture = "_";
		this.varLecture1 = "_";
		this.varLecture2 = "_";
	}

	public String toString(){
		return "<"+this.codop+","+this.varEcriture+","+this.varLecture1+","+this.varLecture2+">";
	}
}
