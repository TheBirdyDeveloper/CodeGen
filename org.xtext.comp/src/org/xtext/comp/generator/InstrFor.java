package org.xtext.comp.generator;

import java.util.List;
import java.util.ListIterator;

import org.xtext.comp.wh.Command;
import org.xtext.comp.wh.Commands;

public class InstrFor extends Instr{
	
	List<Instr> instrFor;

	
	public InstrFor(List <Instr> commands, String var1, String var2, String var3) {
		super(null, var1, var2, var3);
		instrFor = commands;
		
	}
	
	
	public List<Instr> getInstr(){
		return instrFor;
	}
	
	public String toString(){
		return "<For,"+this.varEcriture+","+this.varLecture1+","+this.varLecture2+">" + instrFor.toString();
	}
}
