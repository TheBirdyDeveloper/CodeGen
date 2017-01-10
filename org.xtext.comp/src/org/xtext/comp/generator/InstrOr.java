package org.xtext.comp.generator;

public class InstrOr extends Instr{
	
	
	public InstrOr(String codop, String var1, String var2, String var3) {
		super(codop, var1, var2, var3);
		this.codop ="OR";
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
