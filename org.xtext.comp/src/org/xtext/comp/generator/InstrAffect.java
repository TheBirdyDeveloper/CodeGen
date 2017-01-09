package org.xtext.comp.generator;

import java.util.LinkedList;
import java.util.List;

import org.xtext.comp.wh.Command;
import org.xtext.comp.wh.Expr;

public class InstrAffect extends Instr {
	
	List<Instr> instr;
	boolean isMultiple = false;
	public InstrAffect(List<Instr> instrs, String var1, String var2, String var3, boolean isMultiple) {
		super(null, var1, var2, var3);
		this.isMultiple = isMultiple;
		if(instrs!=null)
			instr = instrs;
	}
	
	public List<Instr> getInstr(){
		return instr;
	}
	public String toString(){
		String result = "<AFFECT,"+this.varEcriture+","+this.varLecture1+","+this.varLecture2+">";
		if(this.isMultiple)
			result+=instr.toString();
		
		return result;
	}

}
