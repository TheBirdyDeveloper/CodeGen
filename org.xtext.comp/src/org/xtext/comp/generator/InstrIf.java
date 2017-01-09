package org.xtext.comp.generator;

import java.util.List;
import java.util.ListIterator;

import org.xtext.comp.wh.Command;
import org.xtext.comp.wh.Commands;

public class InstrIf extends Instr{
	
	List<Instr> siVrai;
	List<Instr> siFaux;

	
	public InstrIf(List<List <Instr>> commands, String var1, String var2, String var3) {
		super(null, var1, var2, var3);
		
		ListIterator<List<Instr>> ite = commands.listIterator();
		if(ite.hasNext())
			siVrai = ite.next();
		if(ite.hasNext())
			siFaux = ite.next();
		else
			siFaux=null;
	}
	public List<Instr> getSiVrai(){
		return siVrai;
	}
	
	public List<Instr> getSiFaux(){
		return siFaux;
	}
	
	public String getCond(){
		return varEcriture;
	}
	
	
	
	public String toString(){
		return "<If,"+this.varEcriture+","+this.varLecture1+","+this.varLecture2+">" + siVrai.toString() + siFaux.toString();
	}
}
