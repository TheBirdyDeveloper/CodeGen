package org.xtext.comp.generator;

public class InstrEq extends Instr {


	public InstrEq(String codop, String var1, String var2, String var3) {
		super(codop, var1, var2, var3);
		
	}
	
	public String toString(){
		return "<EQ,"+varEcriture+","+varLecture1+","+varLecture2+">";
	}

}
