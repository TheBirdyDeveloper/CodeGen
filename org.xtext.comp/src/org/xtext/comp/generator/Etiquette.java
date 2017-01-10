package org.xtext.comp.generator;

import java.util.LinkedList;
import java.util.List;

import org.xtext.comp.wh.Expr;
import org.xtext.comp.wh.ExprCons;
import org.xtext.comp.wh.ExprHd;
import org.xtext.comp.wh.ExprList;
import org.xtext.comp.wh.ExprNot;
import org.xtext.comp.wh.ExprSym;
import org.xtext.comp.wh.ExprTl;

public class Etiquette {
	
	static int compteur;
	String name;
	List<Instr> codeEtiquette;
	
	public Etiquette(List<Instr> instructions){
		this.name = "L"+compteur;
		compteur++;
		codeEtiquette = new LinkedList<Instr>(instructions);
	}
	
	public Etiquette(Expr expr){
		this.name = "L"+compteur;
		compteur++;
		codeEtiquette = new LinkedList<Instr>();
	}
	
	public Etiquette() {
		this.name = "L"+compteur;
		compteur++;
		codeEtiquette = new LinkedList<Instr>();
	}
	
	public String getName(){
		return name;
	}

	public List<Instr> get(){
		return codeEtiquette;
	}
	public void set(List<Instr> listInstructions){
		this.codeEtiquette = listInstructions;
	}
	public void put(Instr instruction){
		if(instruction!=null)
			this.codeEtiquette.add(instruction);
		/*else
			*this.codeEtiquette.add(new Instr("","","","")); Créer null instr
			**/
	}
	
	public String toString(){
		return this.name+" :\n"+""+codeEtiquette.toString()+"\n";
	}
	
	
}
