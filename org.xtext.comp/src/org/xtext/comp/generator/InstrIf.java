package org.xtext.comp.generator;

import java.util.List;
import java.util.ListIterator;

import org.xtext.comp.wh.Command;
import org.xtext.comp.wh.Commands;

public class InstrIf extends Instr{
	
	List<Instr> sivrai;
	List<Instr> sifaux;
	String cond = "";
	int etiquetteNb;
	String varEcriture = "_";
	String varLecture1= "_";
	String varLecture2= "_";
	
	public InstrIf(List<List <Instr>> commands, String var1, String var2, String var3) {
		super(null, var1, var2, var3);
		if(cond !=null){
			ListIterator<List<Instr>> ite = commands.listIterator();
			if(ite.hasNext())
				sivrai = ite.next();
			if(ite.hasNext())
				sifaux = ite.next();
			else
				sifaux=null;
					
			
		}
		if (var1 != null)
			this.varEcriture = var1;
		if (var2 != null)
			this.varLecture1 = var2;
		if (var3 != null)
			this.varLecture2 = var3;
	}
	public String getSiVrai(){
		return varLecture1;
	}
	
	public String getSiFaux(){
		return varLecture2;
	}
	
	public String getCond(){
		return cond;
	}
	
	
	public String toString(){
		return "<If "+this.cond+","+this.varEcriture+","+this.varLecture1+","+this.varLecture2+">" + sivrai.toString() + sifaux.toString();
	}
}
