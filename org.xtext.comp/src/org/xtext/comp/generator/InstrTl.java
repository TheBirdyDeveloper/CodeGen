package org.xtext.comp.generator;

public class InstrTl extends Instr {

	public InstrTl(String codop, String var1, String var2, String var3) {
		super(codop, var1, var2, var3);
		// TODO Auto-generated constructor stub
	}

	public String toString(){
		return"<Tl,"+this.varEcriture+","+this.varLecture1+","+this.varLecture2+">";
	}
}
