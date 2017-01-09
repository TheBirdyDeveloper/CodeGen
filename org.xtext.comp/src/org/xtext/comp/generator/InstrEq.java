package org.xtext.comp.generator;

public class InstrEq extends Instr {

	
	String varEcriture = "_";
	String varLecture1 = "_";
	String varLecture2 = "_";
	public InstrEq(String codop, String var1, String var2, String var3) {
		super(codop, var1, var2, var3);
		
		if(var1!=null)
			varEcriture=var1;
		if(var2!=null)
			varLecture1=var2;
		if(var3!=null)
			varLecture2=var3;
		
	}
	
	public String toString(){
		return "<EQ,"+varEcriture+","+varLecture1+","+varLecture2+">";
	}

}
