package org.xtext.comp.generator;


import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.xtext.comp.wh.Affect;
import org.xtext.comp.wh.Command;
import org.xtext.comp.wh.Commands;
import org.xtext.comp.wh.Expr;
import org.xtext.comp.wh.ExprCond;
import org.xtext.comp.wh.ExprCons;
import org.xtext.comp.wh.ExprHd;
import org.xtext.comp.wh.ExprList;
import org.xtext.comp.wh.ExprNot;
import org.xtext.comp.wh.ExprSimple;
import org.xtext.comp.wh.ExprTl;
import org.xtext.comp.wh.For;
import org.xtext.comp.wh.If;
import org.xtext.comp.wh.Nop;
import org.xtext.comp.wh.While;




public class GenTable {

	SymTable table_m;
	List<String> listSymboles;
	
	// à modifier : la liste des instructions est dans la liste des etiquettes
	HashMap<Code,List<Instr>> listCode3Adr; 
	
	HashMap<String,Code> funDecl;
	//HashMap<String, HashMap<String, Etiquette>> listFonctions;
	HashMap<String, LocalEnvironment> environmentFonctions;
	
	GenTable (SymTable table){
		table_m = table;

		listSymboles=new LinkedList<String>();
		//listFonctions = new HashMap<String, HashMap<String, Etiquette>>();
		environmentFonctions = new HashMap<String, LocalEnvironment>();
		
		funDecl = new HashMap<String,Code>();
		listCode3Adr = new HashMap<Code,List<Instr>>();
		this.initialize();
		this.parseFunDecl();
	}
	public Instr getInstr(String name, String key){
		return this.environmentFonctions.get(name).getInstr(key);
	}
	private void initialize() {
		Iterator<String> ite = table_m.getNames().iterator();
		while(ite.hasNext()){
			String next = ite.next();

			if(!table_m.get(next).isFunction())
				listSymboles.add((String) next); 
			else if(table_m.get(next).isFunction())
				funDecl.put(next,new Code(next, table_m.get(next).nbInput(), table_m.get(next).nbOutput(),table_m.get(next).getCommands()));
		}		
	}

	public String nomsToString(){
		String code3AdrString = "";
		for(Entry<Code, List<Instr>> entry : listCode3Adr.entrySet()){
			Code functionCode = entry.getKey();
			List<Instr> value = entry.getValue();
			code3AdrString+=functionCode.toString();
			code3AdrString+="Environnement :\n"+this.environmentFonctions.get(functionCode.getName()).toString()+"\n";
			ListIterator ite = value.listIterator();
			while(ite.hasNext()){
				Object instr = ite.next();
				code3AdrString +=instr.toString();
				code3AdrString+="\n";
			};
			code3AdrString+="\n";
		};
		return code3AdrString;
				
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
		
		List<Instr> listInstr = this.createListInstr(code.getName(),listCommands);

		listCode3Adr.put(code, listInstr);
	}


	public List<Instr> createListInstr(String string, EList<Command> listCommands){
		
		
		//à faire
		List<Instr> listInstr = new LinkedList<Instr>();
		this.parseCommands(string,listCommands, listInstr);
		return listInstr;
	}
	public void parseCommands(String functionName, EList<Command> listCommands, List<Instr> listInstr){
		

		EList<Command> tmpCommands = listCommands;
		//On crée un itérateur sur les "Commands" de chaque fonction -> On récupère les "Command" de chaque "Commands"
		Iterator<Command> iteC = tmpCommands.iterator();
		
		while(iteC.hasNext()){
			Command nextCommand = iteC.next();
			
			if((nextCommand.getCmd()) instanceof Nop){
					listInstr.add(new InstrNop(null, null, null, null));
			}
			else if(nextCommand.getCmd() instanceof Affect){
				List<Expr> expr = ((Affect)nextCommand.getCmd()).getExprs();
				List<String> var = ((Affect)nextCommand.getCmd()).getVars();
				
				ListIterator<Expr> iteExpr = expr.listIterator();
				ListIterator<String> iteVar = var.listIterator();
				
				List<Instr> instrAffect = new LinkedList<Instr>();
				
				while(iteExpr.hasNext() || iteVar.hasNext()){
					String arg1=null;
					String arg2=null;
					if(iteExpr.hasNext()){
						arg1=this.evaluateExpr(functionName, iteExpr.next(), instrAffect);
					}
					else{
						arg1 ="0";
					}
					if(iteVar.hasNext()){
						arg2=this.environmentFonctions.get(functionName).getAdrVar(iteVar.next());
					}
			
					instrAffect.add(new InstrAffect(null, arg2 , arg1, null,false));
				}
				
				if(instrAffect.size()>1)
					listInstr.add(new InstrAffect(instrAffect,null,null,null,true));
				else
					listInstr.add(instrAffect.get(0));
				}
						
			else if(nextCommand.getCmd() instanceof While){
				listInstr.add(new InstrWhile(null, null, null, null));
			}
			else if(nextCommand.getCmd() instanceof If){
				Expr expression = ((If)nextCommand.getCmd()).getExpr();
				//Etiquette condEtiquette = new Etiquette();
				//listEtiquettes.add(condEtiquette);
				String place = this.evaluateExpr(functionName,expression, listInstr);

				
				List<Instr> siVrai = new LinkedList<Instr>();
				List<Instr> siFaux = new LinkedList<Instr>();
				
				this.parseCommands(functionName, ((If) nextCommand.getCmd()).getCommands1().getCommands(), siVrai );
				if((((If) nextCommand.getCmd()).getCommands2())!=null)
					this.parseCommands(functionName, ((If) nextCommand.getCmd()).getCommands2().getCommands(), siFaux );
				
				
				List<List <Instr>> ifInstr = new LinkedList();
				ifInstr.add(siVrai);
				ifInstr.add(siFaux);
				InstrIf instr = (new InstrIf(ifInstr, place, null, null));
				listInstr.add(instr);		
			}    
			else if(nextCommand.getCmd() instanceof For){
				For forCommand = ((For) (nextCommand.getCmd()));
				
				Expr expr = ((For)forCommand).getExpr();
				Commands commandsFor = (forCommand).getCmds();
				List<Instr> instrFor = new LinkedList<Instr>();
				
				this.parseCommands(functionName, commandsFor.getCommands(), instrFor);
				String place = this.evaluateExpr(functionName, expr, listInstr);
				String condTemp = this.environmentFonctions.get(functionName).newVar();
				
				listInstr.add(new InstrAffect(null,condTemp,place,null,false));
				listInstr.add(new InstrFor(instrFor,condTemp,null,null));
				
			}
		}
	}

	
	
	public String evaluateExpr(String functionName, Expr expression, List<Instr> instructions){
			Expr expr = expression;
			String place = null;
			if(expr instanceof ExprSimple){
				if(((ExprSimple) expr).getVarSimple()!=null){

					place = this.environmentFonctions.get(functionName).putInstr(expr);
				}
				else if(((ExprSimple) expr).getStr()!=null){
					place = this.environmentFonctions.get(functionName).putInstr(expr);
				}
				else if(((ExprSimple) expr).getSym()!=null){
					place = this.environmentFonctions.get(functionName).putInstr(expr);
				}
			}
			else if(expr instanceof ExprCond){
				if(((ExprCond)expr).getCond().equals("and")){
					String arg1 = evaluateExpr(functionName,((ExprCond) expr).getArg1(),instructions);
					String arg2 = evaluateExpr(functionName,((ExprCond) expr).getArg2(),instructions);
					place = this.environmentFonctions.get(functionName).putInstr(new InstrAnd(null,place,arg1,arg2));
				}
				
				else if(((ExprCond)expr).getCond().equals("or")){
				String arg1 = evaluateExpr(functionName,((ExprCond) expr).getArg1(),instructions);
				String arg2 = evaluateExpr(functionName,((ExprCond) expr).getArg2(),instructions);
				place = this.environmentFonctions.get(functionName).putInstr(new InstrOr(null,place,arg1,arg2));
				
				}
				else if(((ExprCond)expr).getCond().equals("=?")){

					String arg1 = this.evaluateExpr(functionName,(((ExprCond)expr).getArg1()), instructions);
					String arg2 = this.evaluateExpr(functionName,(((ExprCond)expr).getArg2()), instructions);
					
					place = this.environmentFonctions.get(functionName).putInstr(new InstrEq(null,null,arg1,arg2));
				}
			}

			else if(expr instanceof ExprCons || expr instanceof Cons){
				//String arg1 = evaluateExpr(functionName,((ExprCons) expr).getExpr(),instructions);
						
				EList<Expr> listExpr;
				
				if(expr instanceof ExprCons)
					listExpr = ((ExprCons) expr).getArg();
				else
					listExpr = ((Cons) expr).getArg();
				
				String arg1 = null;
				String arg2 = null;	
				
				
				ListIterator<Expr> iteExpr = listExpr.listIterator();
				
				
				if(listExpr.size()>2){
				arg1 = this.evaluateExpr(functionName, (Expr) iteExpr.next(), instructions);
				listExpr.remove(0);
				arg2 =this.evaluateExpr(functionName, (new Cons(listExpr)),instructions);
				}
				else{
					arg1 = this.evaluateExpr(functionName, (Expr) iteExpr.next(), instructions);
					if(iteExpr.hasNext())
						arg2=this.evaluateExpr(functionName, iteExpr.next(), instructions);
					else
						arg2="0";
				}

				
				
				place = this.environmentFonctions.get(functionName).putInstr(new InstrCons(null,null,arg1,arg2));
			}
			else if(expr instanceof ExprList){
				EList<Expr> listExpr = ((ExprList)expr).getArg();
				String arg1;
				String arg2;
				
				if(listExpr.size()==1){
					arg1 = this.evaluateExpr(functionName, listExpr.get(0), instructions);
					place = this.environmentFonctions.get(functionName).putInstr(new InstrCons(null,null,arg1,"0"));
				}
				else{
					arg1 = this.evaluateExpr(functionName, listExpr.get(0), instructions);
					listExpr.remove(0);
					arg2 = this.evaluateExpr(functionName, new Cons(listExpr), instructions);
					place = this.environmentFonctions.get(functionName).putInstr(new InstrCons(null,null,arg1,arg2));
				}

			}
			else if(expr instanceof ExprHd){
				String arg = this.evaluateExpr(functionName, ((ExprHd)expr).getArg(), instructions);
				place = this.environmentFonctions.get(functionName).putInstr(new InstrHd(null,null,arg,null));
			}
			else if(expr instanceof ExprTl){
				String arg = this.evaluateExpr(functionName, ((ExprTl)expr).getArg(), instructions);
				place = this.environmentFonctions.get(functionName).putInstr(new InstrTl(null,null,arg,null));
			}

			else if(expr instanceof ExprNot){
				String arg = this.evaluateExpr(functionName, ((ExprNot) expr).getArg1(), instructions);
				
				place = this.environmentFonctions.get(functionName).putInstr(new InstrNot(null,null,arg,null));
				
			}
		return place;
			
	}
	
	
		
	}

