package org.xtext.comp.generator;

import java.util.LinkedList;
import java.util.List;

import org.xtext.comp.wh.Command;
import org.xtext.comp.wh.If;

public abstract class Instr {

	String codop;
	String varEcriture = "_";
	String varLecture1= "_";
	String varLecture2= "_";

	public Instr(String codop, String var1, String var2, String var3){
		this.codop=null;
		if(var1!=null)
			this.varEcriture = var1;
		if(var2!=null)
			this.varLecture1 = var2;
		if(var3!=null)
			this.varLecture2 = var3;
	}
	

}
