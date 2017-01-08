package org.xtext.comp.generator;

import java.util.List;
import java.util.ListIterator;

import org.xtext.comp.wh.Command;
import org.xtext.comp.wh.Commands;

public class InstrFor extends Instr{
	
	List<Instr> instrFor;

	String cond = "";
	int etiquetteNb;
	String varEcriture = "_";
	String varLecture1= "_";
	String varLecture2= "_";
	
	public InstrFor(List <Instr> commands, String var1, String var2, String var3) {
		super(null, var1, var2, var3);
		if(cond !=null){
			instrFor = commands;
		}
		if (var1 != null)
			this.varEcriture = var1;
		if (var2 != null)
			this.varLecture1 = var2;
		if (var3 != null)
			this.varLecture2 = var3;
	}
	
	public String getCond(){
		return cond;
	}
	
	public List<Instr> getInstr(){
		return instrFor;
	}
	
	public String toString(){
		return "<For,"+this.varEcriture+","+this.varLecture1+","+this.varLecture2+">" + instrFor.toString();
	}
}
