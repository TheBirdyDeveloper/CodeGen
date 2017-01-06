package org.xtext.comp.generator;

import org.xtext.comp.wh.Command;

public class InstrIf extends Instr{
	
	String cond;
	int etiquetteNb;
	String varEcriture;
	String varLecture1;
	String varLecture2;
	
	public InstrIf(String cond, String var1, String var2, String var3) {
		super(cond, var1, var2, var3);
		this.cond = cond;
		this.varEcriture = "_";
		this.varLecture1 = var2;
		this.varLecture2 = var3;
	}
	public String getSiVrai(){
		return varLecture1;
	}
	
	public String getSiFaux(){
		return varLecture2;
	}
	
	public String getCond(){
		return cond;
	}
	
	
	public String toString(){
		return "<If "+this.cond+","+this.varEcriture+","+this.varLecture1+","+this.varLecture2+">";
	}
}
