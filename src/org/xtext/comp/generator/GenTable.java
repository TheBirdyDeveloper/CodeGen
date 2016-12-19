package org.xtext.comp.generator;


import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.xtext.comp.wh.Affect;
import org.xtext.comp.wh.Command;
import org.xtext.comp.wh.Commands;
import org.xtext.comp.wh.For;
import org.xtext.comp.wh.If;
import org.xtext.comp.wh.Nop;
import org.xtext.comp.wh.While;

public class GenTable {

	SymTable table_m;
	List<String> listFonctions;
	List<Code> funDecl;
	HashMap<String,List<Instr>> listCode3Adr;
	
	GenTable (SymTable table){
		table_m = table;
		listFonctions=new LinkedList<String>();
		funDecl = new LinkedList<Code>();
		listCode3Adr = new LinkedHashMap<String,List<Instr>>();
		this.initialize();
		this.parseFunDecl();
	}
	
	private void initialize() {
		Iterator<String> ite = table_m.getNames().iterator();
		while(ite.hasNext()){
			String next = ite.next();
			listFonctions.add((String) next); 
			if(table_m.get(next).isFunction())
				funDecl.add(new Code(next, table_m.get(next).nbInput(), table_m.get(next).nbOutput(),table_m.get(next).getCommands()));
		}		
	}

	public String nomsToString(){
		String code3AdrString = "";
		for(Map.Entry<String, List<Instr>> entry : listCode3Adr.entrySet()){
			code3AdrString += entry.getKey().toString();
			code3AdrString += entry.getValue().toString()+"{";
			Iterator<Instr> ite = (entry.getValue()).iterator();
			while(ite.hasNext()){
				Iterator<Instr> ite2 = ite.next().getInstr().iterator();
				while(ite2.hasNext())
				 code3AdrString +=ite2.next().toString();
			}
			code3AdrString+="}";
			code3AdrString+="\n";
		};
		return "\n Liste de codes (nom, inputs, outputs, code) : "+funDecl.size()+"\n"+funDecl.toString()
				+"\n\nCode 3 adresses : "+"\n"+ code3AdrString;
				
	}
	
	/*
	 * Fonction qui parcourir la liste de Code et appelle la méthode chooseCommand pour chaque "Commands" (aka chaque code de fonction)
	 */
	public void parseFunDecl(){
		//On crée un itérateur sur les "Code" -> On récupère les "Commands" de chaque fonction
		Iterator<Code> ite = funDecl.iterator();
		while(ite.hasNext()) {
			Code nextCode = ite.next();
			//On récupère les "Command" du "Commands" originel (celui de la fonction)
			EList<Command> commands = ((Commands)nextCode.getCode()).getCommands();
			this.createInstr(nextCode.getName(),commands,null);
		}
	}
	
	public void createInstr(String name,EList<Command> listCommands, List<Instr> code3Adr) {
		if(code3Adr==null)	
			code3Adr = new LinkedList<Instr>();
		
		this.parseCommands(listCommands, code3Adr);
			
		listCode3Adr.put(name, code3Adr);
	}
	
	public void parseCommands(EList<Command> listCommands, List<Instr> code3Adr){
		
		EList<Command> tmpCommands = listCommands;
		//On crée un itérateur sur les "Commands" de chaque fonction -> On récupère les "Command" de chaque "Commands"
		Iterator<Command> iteC = tmpCommands.iterator();
		while(iteC.hasNext()){
			Command nextCommand = iteC.next();
			if((nextCommand.getCmd()) instanceof Nop){
					code3Adr.add(new InstrNop(null, null, null, null));
			}
			else if(nextCommand.getCmd() instanceof Affect){
				code3Adr.add(new InstrAffect(null, null, null, null));		
			}
			else if(nextCommand.getCmd() instanceof While){
				code3Adr.add(new InstrWhile(null, null, null, null));
			}
			else if(nextCommand.getCmd() instanceof If){
				InstrIf instr = (new InstrIf(null, null, null, null));
				code3Adr.add(instr);
				this.parseCommands( ((If) nextCommand.getCmd()).getCommands1().getCommands(),instr.getInstr() );
			    this.parseCommands( ((If) nextCommand.getCmd()).getCommands2().getCommands(),instr.getInstr() );
			}    
			else if(nextCommand.getCmd() instanceof For){
				
			}
		}
		
	}
		
	}

