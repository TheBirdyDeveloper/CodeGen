package org.xtext.comp.generator;

public class InstrOr extends Instr{
	
	String codop;
	int etiquetteNb;
	String varEcriture;
	String varLecture1;
	String varLecture2;
	
	public InstrOr(String codop, String var1, String var2, String var3) {
		super(codop, var1, var2, var3);
		this.codop ="OR";
		this.varEcriture = var1;
		this.varLecture1 = var2;
		this.varLecture2 = var3;
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
