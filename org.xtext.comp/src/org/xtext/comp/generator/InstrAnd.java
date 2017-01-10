package org.xtext.comp.generator;

public class InstrAnd extends Instr{
	
	String codop;
	
	public InstrAnd(String codop, String var1, String var2, String var3) {
		super(codop, var1, var2, var3);
		this.codop ="AND";
	}
	
	public String getEcriture(){
		return varEcriture;
	}

	public String getExpr1(){
		return varLecture1;
	}
	public String getExpr2(){
		return varLecture2;
	}
	
	public String toString(){
		return "<"+this.codop+","+this.varEcriture+","+this.varLecture1+","+this.varLecture2+">";
	}
}
