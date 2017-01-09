package org.xtext.comp.generator;

public class InstrVar extends Instr {
	
	
	
	public InstrVar(String codop, String var1, String var2, String var3) {
		super(codop,var1,var2,var3);

	}
	
	public String getVar(){
		return codop;
	}

}
