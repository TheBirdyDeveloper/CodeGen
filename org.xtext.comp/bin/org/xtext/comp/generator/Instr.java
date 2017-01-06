package org.xtext.comp.generator;

import java.util.LinkedList;
import java.util.List;

import org.xtext.comp.wh.Command;
import org.xtext.comp.wh.If;

public abstract class Instr {

	
	List<Instr> instructions;
	public Instr(String codop, String var1, String var2, String var3){
		instructions = new LinkedList<Instr>();
	}
	
	public void add(Instr instr){
		this.instructions.add(instr);
	}
	
	public List<Instr> getInstr(){
		return this.instructions;
	}
	
	public String toString(){
		return instructions.toString();
	}

}
