package org.xtext.comp.generator;


import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EList;
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
	
	
	
	HashMap<Code,List<Instr>> listCode3Adr; 
	HashMap<String,Code> funDecl;
	HashMap<String, LocalEnvironment> environmentFonctions;
	HashMap<String,String> functionCorrespondances;
	String mainFunction;
	GenTable (SymTable table){
		table_m = table;
		mainFunction=table_m.getMain();
		listSymboles=new LinkedList<String>();
		environmentFonctions = new HashMap<String, LocalEnvironment>();
		funDecl = new HashMap<String,Code>();
		listCode3Adr = new HashMap<Code,List<Instr>>();
		functionCorrespondances=new HashMap<String,String>();
		this.initialize();
		this.parseFunDecl();
	}
	public Instr getInstr(String name, String key){
		return this.environmentFonctions.get(name).getInstr(key);
	}
	private void initialize() {
		Iterator<String> ite = table_m.getNames().iterator();
		int cpt = 2;
		while(ite.hasNext()){
			String next = ite.next();
			if(!table_m.get(next).isFunction())
				listSymboles.add((String) next); 
			else if(table_m.get(next).isFunction()){
				if(next.equals(mainFunction)){
					functionCorrespondances.put(next, "F1");
					funDecl.put("F1",new Code("F1", table_m.get(next).nbInput(), table_m.get(next).nbOutput(),table_m.get(next).getCommands()));
				}
				else{
					functionCorrespondances.put(next, "F"+cpt);
					funDecl.put("F"+cpt,new Code("F"+cpt, table_m.get(next).nbInput(), table_m.get(next).nbOutput(),table_m.get(next).getCommands()));
					cpt++;
				}
			}
		}		
	}

	public String nomsToString(){
		String code3AdrString="Table des fonctions :"+this.functionCorrespondances.toString()+"\n\n";
		for(Entry<Code, List<Instr>> entry : listCode3Adr.entrySet()){
			Code functionCode = entry.getKey();
			List<Instr> value = entry.getValue();
			code3AdrString+=functionCode.toString();
			code3AdrString+="Environnement :\n"+this.environmentFonctions.get(functionCode.getName()).toString()+"\n";
			ListIterator<Instr> ite = value.listIterator();
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
	 * Fonction qui parcourir la liste de Code et appelle la m�thode chooseCommand pour chaque "Commands" (aka chaque code de fonction)
	 */
	public void parseFunDecl(){
		//On cr�e un it�rateur sur les "Code" -> On r�cup�re les "Commands" de chaque fonction
		Iterator<Code> ite = funDecl.values().iterator();
		while(ite.hasNext()) {
			Code nextCode = ite.next();
			//On r�cup�re les "Command" du "Commands" originel (celui de la fonction)
			EList<Command> commands = ((Commands)nextCode.getCode()).getCommands();
			this.createEnvironment(nextCode.getName());
			this.createInstr(nextCode,commands);
			
		}
	}

	private String getRealName(String corres){
		String real = "";
		for(Entry<String,String> entry : functionCorrespondances.entrySet()){
			if(entry.getValue().equals(corres))
				real = entry.getKey();
		}
		return real;
	}
	private void createEnvironment(String function) {

		String functionName = this.getRealName(function);
		LocalEnvironment environment = new LocalEnvironment(this.table_m.get(functionName).getInputs(),this.table_m.get(functionName).getOutputs(),this.table_m.get(functionName).getVariables());
		
		this.environmentFonctions.put(this.functionCorrespondances.get(functionName), environment);
		
		
	}

	public void createInstr(Code code,EList<Command> listCommands) {
		
		//listCode3Adr.put(code, code3Adr); va �tre remplac�
		
		List<Instr> listInstr = this.createListInstr(code.getName(),listCommands);

		listCode3Adr.put(code, listInstr);
	}


	public List<Instr> createListInstr(String string, EList<Command> listCommands){
		
		
		//� faire
		List<Instr> listInstr = new LinkedList<Instr>();
		this.parseCommands(string,listCommands, listInstr);
		return listInstr;
	}
	public void parseCommands(String functionName, EList<Command> listCommands, List<Instr> listInstr){
		
		LocalEnvironment environnement = this.environmentFonctions.get(functionName);
		EList<Command> tmpCommands = listCommands;
		//On cr�e un it�rateur sur les "Commands" de chaque fonction -> On r�cup�re les "Command" de chaque "Commands"
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
						arg2=environnement.getAdrVar(iteVar.next());
					}
			
					instrAffect.add(new InstrAffect(null, arg2 , arg1, null,false));
				}
				
				if(instrAffect.size()>1)
					listInstr.add(new InstrAffect(instrAffect,null,null,null,true));
				else
					listInstr.add(instrAffect.get(0));
				}
						
			else if(nextCommand.getCmd() instanceof While){
				While whileCommand = ((While) (nextCommand.getCmd()));
				
				Expr expr = ((While)whileCommand).getExpr();
				Commands commandsFor = (whileCommand).getCmds();
				List<Instr> instrWhile = new LinkedList<Instr>();
				
				this.parseCommands(functionName, commandsFor.getCommands(), instrWhile);
				String place = this.evaluateExpr(functionName, expr, listInstr);
				listInstr.add(new InstrWhile(instrWhile,place,null,null));
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
				
				
				List<List <Instr>> ifInstr = new LinkedList<List<Instr>>();
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
				String condTemp = environnement.newVar();
				
				listInstr.add(new InstrAffect(null,condTemp,place,null,false));
				listInstr.add(new InstrFor(instrFor,condTemp,null,null));
				
			}
		}
	}

	
	
	public String evaluateExpr(String functionName, Expr expression, List<Instr> instructions){
		
		LocalEnvironment environnement = this.environmentFonctions.get(functionName);
			Expr expr = expression;
			String place = null;
			if(expr instanceof ExprSimple){
				if(((ExprSimple) expr).getVarSimple()!=null){

					place = environnement.putInstr(expr);
				}
				else if(((ExprSimple) expr).getStr()!=null){
					place = environnement.putInstr(expr);
				}
				else if(((ExprSimple) expr).getSym()!=null){
					place = environnement.putInstr(expr);
				}
			}
			else if(expr instanceof ExprCond){
				if(((ExprCond)expr).getCond().equals("and")){
					String arg1 = evaluateExpr(functionName,((ExprCond) expr).getArg1(),instructions);
					String arg2 = evaluateExpr(functionName,((ExprCond) expr).getArg2(),instructions);
					place = environnement.putInstr(new InstrAnd(null,place,arg1,arg2));
				}
				
				else if(((ExprCond)expr).getCond().equals("or")){
				String arg1 = evaluateExpr(functionName,((ExprCond) expr).getArg1(),instructions);
				String arg2 = evaluateExpr(functionName,((ExprCond) expr).getArg2(),instructions);
				place = environnement.putInstr(new InstrOr(null,place,arg1,arg2));
				
				}
				else if(((ExprCond)expr).getCond().equals("=?")){

					String arg1 = this.evaluateExpr(functionName,(((ExprCond)expr).getArg1()), instructions);
					String arg2 = this.evaluateExpr(functionName,(((ExprCond)expr).getArg2()), instructions);
					
					place = environnement.putInstr(new InstrEq(null,null,arg1,arg2));
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

				
				
				place = environnement.putInstr(new InstrCons(null,null,arg1,arg2));
			}
			else if(expr instanceof ExprList){
				EList<Expr> listExpr = ((ExprList)expr).getArg();
				String arg1;
				String arg2;
				
				if(listExpr.size()==1){
					arg1 = this.evaluateExpr(functionName, listExpr.get(0), instructions);
					place = environnement.putInstr(new InstrCons(null,null,arg1,"0"));
				}
				else{
					arg1 = this.evaluateExpr(functionName, listExpr.get(0), instructions);
					listExpr.remove(0);
					arg2 = this.evaluateExpr(functionName, new Cons(listExpr), instructions);
					place = environnement.putInstr(new InstrCons(null,null,arg1,arg2));
				}

			}
			else if(expr instanceof ExprHd){
				String arg = this.evaluateExpr(functionName, ((ExprHd)expr).getArg(), instructions);
				place = environnement.putInstr(new InstrHd(null,null,arg,null));
			}
			else if(expr instanceof ExprTl){
				String arg = this.evaluateExpr(functionName, ((ExprTl)expr).getArg(), instructions);
				place = environnement.putInstr(new InstrTl(null,null,arg,null));
			}

			else if(expr instanceof ExprNot){
				String arg = this.evaluateExpr(functionName, ((ExprNot) expr).getArg1(), instructions);
				
				place = environnement.putInstr(new InstrNot(null,null,arg,null));
				
			}
			else if(expr.getNameFunction()!=null){
				String arg = environnement.putInstr(new InstrVar(this.functionCorrespondances.get(expr.getNameFunction()),null,null,null));
				
				LinkedList<String> inputs = new LinkedList<String>();
				EList<Expr> vars = expr.getVars();
				
				ListIterator<Expr> ite = vars.listIterator();
				while(ite.hasNext()){
					inputs.add(this.evaluateExpr(functionName, (Expr) ite.next(), instructions));
				}
				place = environnement.putInstr(new InstrFun(null,null,arg,null, inputs));
			}
			
		return place;
			
	}
	
	
		
	}

