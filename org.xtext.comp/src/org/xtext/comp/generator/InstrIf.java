package org.xtext.comp.generator;

import org.xtext.comp.wh.Command;

public class InstrIf extends Instr{
	
	
	String cond = "";
	int etiquetteNb;
	String varEcriture = "_";
	String varLecture1= "_";
	String varLecture2= "_";
	
	public InstrIf(String cond, String var1, String var2, String var3) {
		super(cond, var1, var2, var3);
		if(cond !=null)
			this.cond = cond;
		if (var1 != null)
			this.varEcriture = var1;
		if (var2 != null)
			this.varLecture1 = var2;
		if (var3 != null)
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
