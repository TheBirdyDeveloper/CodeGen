package org.xtext.comp.generator;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.xtext.comp.wh.*;


public class SymTable {


	private String mainFunction;
	private TreeIterator<EObject> tree;
	private TreeIterator<EObject> tree2;
	private Set<Paire<String, List<Expr>>> appelTable;
	private HashMap<String,FunctionEnvironment> symTable;


	public SymTable(Resource resource){
		this.tree = resource.getAllContents().next().eAllContents();
		this.tree2 = resource.getAllContents().next().eAllContents();
		this.appelTable =new HashSet<Paire<String, List<Expr>>>();
		this.symTable = new HashMap<String,FunctionEnvironment>();
		this.createFunctionMap();
	}

	public String getMain(){
		return this.mainFunction;
	}
	public FunctionEnvironment get(String key){
		return symTable.get(key);
	}
	public Set<String> getNames(){
		return symTable.keySet();
	}
	public void createFunctionMap(){
		while(tree.hasNext()){//initialisation de toutes les fonctions
			EObject next = tree.next();
			if(next instanceof Program){
				EList<Function> listeFunctions = ((Program)next).getFunctions();
				mainFunction = listeFunctions.get(listeFunctions.size()-1).getName();
				for (int j=0; j<listeFunctions.size(); j++){
					String fName = ((Function) listeFunctions.get(j)).getName();
					if(!(symTable.containsKey(fName))){
						symTable.put(fName, new FunctionEnvironment((Function) listeFunctions.get(j), fName));
					}else{
						throw new Error("Cette fonction existe deja : "+fName);
					}
				}
			}
		}
		while(tree2.hasNext()){//initialisation des symboles
			EObject next = tree2.next();

			if(next instanceof ExprSimple){
				String symbole = ((ExprSimple) next).getSym();
				if (symbole != null){
					if(!(symTable.containsKey(symbole))){
						symTable.put(symbole, new FunctionEnvironment((ExprSimple) next));
					}else{
						symTable.put(symbole, symTable.get(symbole));
						symTable.get(symbole).setNbOccur(symTable.get(symbole).nbOccur+1);
					}
				}
			}

			else if(next instanceof Expr){
				String symbole = ((Expr) next).getNameFunction();
				List<Expr> appel = ((Expr) next).getVars();
				if (symbole != null){
					if(!(symTable.containsKey(symbole))){
						throw new Error("Cette fonction n'existe pas");
					}else{
						symTable.put(symbole, symTable.get(symbole));
						symTable.get(symbole).setNbOccur(symTable.get(symbole).nbOccur+1);
					}

					if (appel != null){

						appelTable.add(new Paire<String, List<Expr>>(((Expr) next).getNameFunction(), appel));
					}
				}
			}
		}
	}

	public String toStringSymboles(){
		return this.symTable.toString();
	}

	public void toStringError(){
		for (Paire<String, List<Expr>> current : appelTable){
			if(symTable.get(current.getLeft()) != null){
				if(current.getRight().size() != symTable.get(current.getLeft()).nbInput){//fonction pour compter le nombre de paramètres
					System.out.println("La fonction "+current.getLeft()+" n'est pas appelee avec le bon nombre de parametres ("+symTable.get(current.getLeft()).nbInput+" attendus)");
				}
			}
			else{
				throw new Error("La fonction "+current.getLeft()+" n'a pas ete declaree");
			}
		}

		return;
	}


	public String toStringAppels(){
		String result ="";
		result+="{";
		if(!appelTable.isEmpty()){
			for (Paire<String, List<Expr>> current : appelTable){
				result+=current.getLeft() + " : "+ ((List<Expr>) (current.getRight())).toString()+" ";//rendre lisible
			}
		}
		result+="}";
		return result;
	}
}
