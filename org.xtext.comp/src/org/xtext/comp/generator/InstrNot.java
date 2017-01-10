package org.xtext.comp.generator;

public class InstrNot extends Instr {

	public InstrNot(String codop, String var1, String var2, String var3) {
		super(codop, var1, var2, var3);
		// TODO Auto-generated constructor stub
	}
	
	public String toString(){
		return "<NOT,"+varEcriture+","+varLecture1+","+varLecture2+">";
	}

}
