package org.xtext.comp.generator;

import java.util.List;

public class InstrWhile extends Instr {
	
	
	List<Instr> instrWhile;
	public InstrWhile(List <Instr> commands, String var1, String var2, String var3) {
		super(null, var1, var2, var3);
		this.codop = "While";
		this.instrWhile=commands;
	}
	
	public String toString(){
		return "<"+this.codop+","+this.varEcriture+","+this.varLecture1+","+this.varLecture2+">";
	}
	
	public List<Instr> getInstr(){
		return instrWhile;
	}
}
