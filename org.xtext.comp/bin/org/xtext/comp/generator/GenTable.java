package org.xtext.comp.generator;


import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.xtext.comp.wh.Affect;
import org.xtext.comp.wh.Command;
import org.xtext.comp.wh.Commands;
import org.xtext.comp.wh.Expr;
import org.xtext.comp.wh.ExprAnd;
import org.xtext.comp.wh.ExprCons;
import org.xtext.comp.wh.ExprEq;
import org.xtext.comp.wh.ExprHd;
import org.xtext.comp.wh.ExprList;
import org.xtext.comp.wh.ExprNot;
import org.xtext.comp.wh.ExprOr;
import org.xtext.comp.wh.ExprSimple;
import org.xtext.comp.wh.ExprSym;
import org.xtext.comp.wh.ExprTl;
import org.xtext.comp.wh.For;
import org.xtext.comp.wh.If;
import org.xtext.comp.wh.Nop;
import org.xtext.comp.wh.While;

import com.ibm.icu.impl.Pair;

public class GenTable {

	SymTable table_m;
	List<String> listFonctionsSymboles;
	
	// à modifier : la liste des instructions est dans la liste des etiquettes
	HashMap<Code,List<Instr>> listCode3Adr; 
	
	HashMap<String,Code> funDecl;
	HashMap<String, HashMap<String, Etiquette>> listFonctions;
	HashMap<String, LocalEnvironment> environmentFonctions;
	
	GenTable (SymTable table){
		table_m = table;
		listFonctionsSymboles=new LinkedList<String>();
		listFonctions = new HashMap<String, HashMap<String, Etiquette>>();
		environmentFonctions = new HashMap<String, LocalEnvironment>();
		
		funDecl = new HashMap<String,Code>();
		listCode3Adr = new LinkedHashMap<Code,List<Instr>>();
		this.initialize();
		this.parseFunDecl();
	}
	
	private void initialize() {
		Iterator<String> ite = table_m.getNames().iterator();
		while(ite.hasNext()){
			String next = ite.next();
			listFonctionsSymboles.add((String) next); 
			if(table_m.get(next).isFunction())
				funDecl.put(next,new Code(next, table_m.get(next).nbInput(), table_m.get(next).nbOutput(),table_m.get(next).getCommands()));
		}		
	}

	public String nomsToString(){
		String code3AdrString = "";
		for(Entry<String, HashMap<String, Etiquette>> entry : listFonctions.entrySet()){
			String functionName = entry.getKey();
			HashMap<String,Etiquette> value = entry.getValue();
			code3AdrString+=functionName.toString()+"{"+funDecl.get(functionName).getInputs()+"}"+"{"+funDecl.get(functionName).getOutputs()+"}";
			code3AdrString+="Environnement :"+this.environmentFonctions.toString()+"\n";
			for(Entry<String, Etiquette> entry2 : value.entrySet()){
			String key = entry2.getKey();
			code3AdrString += entry2.getValue().toString();
			code3AdrString+="";
			};
		};
		return "\n Liste de codes (nom, inputs, outputs, code) : "+funDecl.size()+"\n"+funDecl.toString()
				+"\n\nCode 3 adresses : "+"\n"+ code3AdrString;
				
	}
	
	/*
	 * Fonction qui parcourir la liste de Code et appelle la méthode chooseCommand pour chaque "Commands" (aka chaque code de fonction)
	 */
	public void parseFunDecl(){
		//On crée un itérateur sur les "Code" -> On récupère les "Commands" de chaque fonction
		Iterator<Code> ite = funDecl.values().iterator();
		while(ite.hasNext()) {
			Code nextCode = ite.next();
			//On récupère les "Command" du "Commands" originel (celui de la fonction)
			EList<Command> commands = ((Commands)nextCode.getCode()).getCommands();
			this.createEnvironment(nextCode.getName());
			this.createInstr(nextCode,commands);
			
		}
	}

	private void createEnvironment(String functionName) {
		
		LocalEnvironment environment = new LocalEnvironment(this.table_m.get(functionName).getInputs(),this.table_m.get(functionName).getOutputs(),this.table_m.get(functionName).getVariables());
		this.environmentFonctions.put(functionName, environment);
		
		
	}

	public void createInstr(Code code,EList<Command> listCommands) {
		
		//listCode3Adr.put(code, code3Adr); va être remplacé
		HashMap<String,Etiquette> mapEtiquettes = new HashMap<String,Etiquette>();
		List<Etiquette> listEtiquettes = this.createListEtiquette(code.getName(),listCommands);
	    ListIterator ite = listEtiquettes.listIterator();
	    while(ite.hasNext()){
	    	Etiquette etiquette = (Etiquette) ite.next();
	    	mapEtiquettes.put(etiquette.getName(), etiquette);
	    }
		
		listFonctions.put(code.getName(), mapEtiquettes);
	}
	
	
	public List<Etiquette> createListEtiquette(String string, EList<Command> listCommands){
		
		List<Etiquette> listEtiquettes = new LinkedList<Etiquette>();
		this.parseCommands(string,listCommands, listEtiquettes);
		//à faire
		
		return listEtiquettes;
	}
	public void parseCommands(String functionName, EList<Command> listCommands, List<Etiquette> listEtiquettes){
		
		Etiquette etiquette = new Etiquette();
		List<Instr> instructions = new LinkedList<Instr>();
		EList<Command> tmpCommands = listCommands;
		//On crée un itérateur sur les "Commands" de chaque fonction -> On récupère les "Command" de chaque "Commands"
		Iterator<Command> iteC = tmpCommands.iterator();
		
		while(iteC.hasNext()){
			Command nextCommand = iteC.next();
			if((nextCommand.getCmd()) instanceof Nop){
					instructions.add(new InstrNop(null, null, null, null));
			}
			else if(nextCommand.getCmd() instanceof Affect){
				ListIterator<Expr> iteExpr = ((Affect)nextCommand.getCmd()).getExprs().listIterator();
				ListIterator<String> iteVar = ((Affect)nextCommand.getCmd()).getVars().listIterator();
				while(iteVar.hasNext()) {
					String var = iteVar.next();
					instructions.add(new InstrAffect(null, var, null, null));
				}
						
			}
			else if(nextCommand.getCmd() instanceof While){
				instructions.add(new InstrWhile(null, null, null, null));
			}
			else if(nextCommand.getCmd() instanceof If){
				Expr expression = ((If)nextCommand.getCmd()).getExpr();
				//Etiquette condEtiquette = new Etiquette();
				//listEtiquettes.add(condEtiquette);
				String place = this.evaluateExpr(functionName,expression, instructions);
				InstrIf instr = (new InstrIf(place, null, "L"+2, "L"+3));
				instructions.add(instr);
				
				this.parseCommands(functionName, ((If) nextCommand.getCmd()).getCommands1().getCommands(), listEtiquettes );
			    this.parseCommands(functionName, ((If) nextCommand.getCmd()).getCommands2().getCommands(), listEtiquettes );
				
			}    
			else if(nextCommand.getCmd() instanceof For){
				
			}
		}
		etiquette.set(instructions);
		listEtiquettes.add(etiquette);
		
	}

	public String evaluateExpr(String functionName, Expr expression, List<Instr> instructions){
			Expr expr = expression;
			String place = null;
			if(expr instanceof ExprSimple){
				if(((ExprSimple) expr).getVarSimple()!=null){
					place = ((ExprSimple) expr).getVarSimple();
				}
				else if(((ExprSimple) expr).getStr()!=null){
					place = ((ExprSimple) expr).getStr();
				}
				else if(((ExprSimple) expr).getSym()!=null){
					place = this.environmentFonctions.get(functionName).putExpr(expr);
				}
			}
			else if(expr instanceof ExprAnd){
				String arg1 = evaluateExpr(functionName,((ExprAnd) expr).getArg1(),instructions);
				String arg2 = evaluateExpr(functionName,((ExprAnd) expr).getArg2(),instructions);
				place = this.environmentFonctions.get(functionName).putExpr(expr);
				instructions.add(new InstrAnd(null,place,arg1,arg2));
			}
			else if(expr instanceof ExprOr){
				String arg1 = evaluateExpr(functionName,((ExprOr) expr).getArg1(),instructions);
				String arg2 = evaluateExpr(functionName,((ExprOr) expr).getArg2(),instructions);
				place = this.environmentFonctions.get(functionName).putExpr(expr);
				instructions.add(new InstrOr(null,place,arg1,arg2));
			}
			else if(expr instanceof ExprCons || expr instanceof Cons){
				//String arg1 = evaluateExpr(functionName,((ExprCons) expr).getExpr(),instructions);
				EList<Expr> listExpr=null;
				String arg1 = null;
				String arg2 = null;
				
				if(expr instanceof ExprCons)
					listExpr = ((ExprCons) expr).getArg();
				if(expr instanceof Cons)
					listExpr = ((Cons) expr).getArg();
				
				int size = listExpr.size();
				if(size>2){
					ListIterator<Expr> ite = listExpr.listIterator();
					if(ite.hasNext()){
						arg1 = this.evaluateExpr(functionName, (Expr) ite.next(), instructions);
						listExpr.remove(0);
						arg2 =this.evaluateExpr(functionName, new Cons(listExpr),instructions);
					}
				}
				else{
					ListIterator<Expr> ite = listExpr.listIterator();
					if(ite.hasNext()){
						arg1 = this.evaluateExpr(functionName, (Expr) ite.next(), instructions);
					}
					if(ite.hasNext()){
						arg2 = this.evaluateExpr(functionName, (Expr) ite.next(), instructions);
						}
				}
				
				
				place = this.environmentFonctions.get(functionName).putExpr(expr);
				instructions.add(new InstrCons(null,place,arg1,arg2));
			}
			else if(expr instanceof ExprList){
				
			}
			else if(expr instanceof ExprHd){
				
			}
			else if(expr instanceof ExprTl){
				
			}
			else if(expr instanceof ExprSym){
			}
			else if(expr instanceof ExprNot){
				
			}
		return place;
			
	}
	
	
		
	}

