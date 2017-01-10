package org.xtext.comp.generator;

public class InstrHd extends Instr {

	public InstrHd(String codop, String var1, String var2, String var3) {
		super(codop, var1, var2, var3);
		// TODO Auto-generated constructor stub
	}
	
	public String toString(){
		return"<Hd,"+this.varEcriture+","+this.varLecture1+","+this.varLecture2+">";
	}

}
