package org.xtext.comp.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.generator.GeneratorDelegate;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess;
import org.eclipse.xtext.validation.IResourceValidator;
import org.xtext.comp.wh.*;
import org.xtext.comp.wh.impl.ProgramImpl;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class SymTable {


	private TreeIterator<EObject> tree;
	private TreeIterator<EObject> treeF;
	//HashMap<String,Input> appelTable;//Table des appels
	private Set<Paire> appelTable;
	private HashMap<String,FunctionEnvironment> symTable;


	public SymTable(Resource resource){
		this.tree = resource.getAllContents().next().eAllContents();
		//this.appelTable = new HashMap<String,Input>();
		this.appelTable =new HashSet<Paire>();
		this.symTable = new HashMap<String,FunctionEnvironment>();
		this.createFunctionMap();
	}

	public FunctionEnvironment get(String key){
		return symTable.get(key);
	}
	public Set<String> getNames(){
		return symTable.keySet();
	}
	public void createFunctionMap(){
		while(tree.hasNext()){
			EObject next = tree.next();
			if(next instanceof Program){
				EList<Function> listeFunctions = ((Program)next).getFunctions();
				for (int j=0; j<listeFunctions.size(); j++){
					String fName = ((Function) listeFunctions.get(j)).getName();
					if(!(symTable.containsKey(fName))){
						symTable.put(fName, new FunctionEnvironment((Function) listeFunctions.get(j), fName));
					}else{
						throw new Error("Cette fonction existe déja");
					}
				}
			}
			else if(next instanceof ExprSimple){
				String symbole = ((ExprSimple) next).getSym();
				Input appel = ((ExprSimple) next).getVars();
				if (symbole != null){
					if(!(symTable.containsKey(symbole))){
						symTable.put(symbole, new FunctionEnvironment((ExprSimple) next));
					}else{
						symTable.put(symbole, symTable.get(symbole));
						symTable.get(symbole).setNbOccur(symTable.get(symbole).nbOccur+1);
					}
				}
				else if (appel != null){
					appelTable.add(new Paire(((ExprSimple) next).getNameFunction(), appel));
				}
			}
		}
	}

	public String toStringSymboles(){
		return this.symTable.toString();
	}

	public void toStringError(){
		for (Paire current : appelTable){
			if(symTable.get(current.getName()) != null){
				if((current).getInputs().getVars().size() != symTable.get(current.getName()).nbInput){
					System.out.println("La fonction "+current.getName()+" n'est pas appelée avec le bon nombre de paramètres ("+symTable.get(current.getName()).nbInput+" attendus)");
				}
			}
			else{
				throw new Error("La fonction "+current.getName()+" n'a pas été déclarée");
			}
		}

		return;
	}

	public String toStringAppels(){
		String result ="";
		result+="{";
		if(!appelTable.isEmpty()){
			for (Paire current : appelTable){
				result+=current.getName() + " : "+ current.getInputs().getVars().toString()+" ";
			}
		}
		result+="}";
		return result;
	}

}