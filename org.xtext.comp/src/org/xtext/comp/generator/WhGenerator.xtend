/*
 * generated by Xtext 2.10.0
 */
package org.xtext.comp.generator

import java.util.HashMap
import java.util.List
import java.util.Set
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext

/**
 * Generates code from your model files on save.
 *
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#code-generation
 */
class WhGenerator extends AbstractGenerator {
	private GenTable genTable;
	private final int globalIndent = 3;
	private String funName;
	override void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) {
		//N'est pas utilisée
	}

	def void doGenerate(GenTable genTable, IFileSystemAccess2 fsa, String outputName) {
		this.genTable = genTable;
		var corps = genTable.listCode3Adr.genCode3Adr
		if(needEqual) corps = entete + corps
    	fsa.generateFile(outputName, corps)
	}

	def String entete()
	'''
	require('luaLib/equals')

	'''

	def String genCode3Adr(HashMap<Code, List<Instr>> map){
		'''
		�FOR fun : map.keySet()�
		�funName = fun.name�
		function �funName�(�printList(genTable.environmentFonctions.get(fun.name).getInputs().keySet(),", ")�)
		�genCommands(map.get(fun),globalIndent)�

		return �printList(genTable.environmentFonctions.get(fun.name).getOutputs().keySet(),", ")�
		end
		�ENDFOR�
		'''
		}


	def String genCommands(List<Instr> instrs, int indent)
		'''
		�FOR instr : instrs�
		�genCommand(instr, indent)�
		�ENDFOR�
		'''

	def String genCommand(Instr instr, int pIndent)
	{
		if(instr instanceof InstrNop)	return "";
		if(instr instanceof InstrVar)	return genVar(instr, pIndent, funName);
		if(instr instanceof InstrIf)    return genIf(instr,pIndent, funName);
		if(instr instanceof InstrOr)	return genOr(instr, pIndent, funName);
		if(instr instanceof InstrAnd)	return genAnd(instr, pIndent, funName);
		if(instr instanceof InstrCons)	return genCons(instr, pIndent, funName);
		if(instr instanceof InstrAffect)	return genAffect(instr, pIndent, funName);
		if(instr instanceof InstrEq)	return genEq(instr, pIndent, funName);
		if(instr instanceof InstrNot)	return genNot(instr, pIndent, funName);
		if(instr instanceof InstrFor)	return genFor(instr, pIndent, funName);
		if(instr instanceof InstrWhile)	return genWhile(instr, pIndent, funName);
		/*if(instr instanceof InstrTl)	return genTl(instr, pIndent, funName);
		if(instr instanceof InstrHd)	return genHd(instr, pIndent, funName);*/
		return "TODO"
	}


	def String genIf(InstrIf instr, int pIndent){
		var parentIndent = makeIndent(pIndent)
	    var indent = pIndent + globalIndent
	    var cond = "";
		'''
		�parentIndent�if �cond� then
		�genCommands(instr.getSiVrai(), indent)�
		�IF !instr.getSiFaux().empty�
		�parentIndent�else
		�genCommands(instr.getSiFaux(),indent)�
		�ENDIF�
		�parentIndent�end
		'''
	}

	def String genOr(InstrOr instr, int pIndent, String funName){
		var expr1 = genTable.getInstr(funName, instr.getExpr1());
		var expr2 = genTable.getInstr(funName, instr.getExpr2());
		return "("+genCommand(expr1, pIndent,funName) + " or "+genCommand(expr2,pIndent,funName)+")";
	}

	def String genAnd(InstrAnd instr, int pIndent, String funName){
		var expr1 = genTable.getInstr(funName, instr.getExpr1());
		var expr2 = genTable.getInstr(funName, instr.getExpr2());
		return "("+genCommand(expr1, pIndent,funName) + " and "+genCommand(expr2,pIndent,funName)+")";
	}

	def String genCons(InstrCons instr, int pIndent, String funName){
		var expr1 = genTable.getInstr(funName, instr.varLecture1);
		var expr2 = genTable.getInstr(funName, instr.varLecture2);
		return "{ hd = "+genCommand(expr1,pIndent,funName)+", tl = "+genCommand(expr2,pIndent,funName)+" }";
	}

	def String genAffect(InstrAffect instr, int pIndent, String funName){
		var res = "";
		if(instr.isMultiple){
			var listGauche = new LinkedList<String>();
			var listDroite = new LinkedList<String>();
			for (affect : instr.getInstr)
			{
				listGauche.add(genCommand(genTable.getInstr(funName,affect.varEcriture),pIndent,funName));
				listDroite.add(genCommand(genTable.getInstr(funName,affect.varLecture1),pIndent,funName));
			}
			res = printList(listGauche,", ");
			res += "=";
			res += printList(listDroite,", ");

			listGauche = null;
			listDroite = null;
		} else {
			res += genCommand(genTable.getInstr(funName,instr.varEcriture),pIndent,funName);
			res += "=";
			res += genCommand(genTable.getInstr(funName,instr.varLecture1),pIndent,funName);
		}

		return res;
	}

	def String genEq(InstrEq instr, int pIndent, String funName){
		var expr1 = genCommand(genTable.getInstr(funName,instr.varLecture1),pIndent,funName);
		var expr2 = genCommand(genTable.getInstr(funName,instr.varLecture2),pIndent,funName);
		needEqual = true;
		return "equals("+expr1+","+expr2+",false)"


	}

	def String genNot(InstrNot instr, int pIndent, String funName){
		var expr = genCommand(genTable.getInstr(funName,instr.varLecture1),pIndent,funName);
		return "not "+expr;
	}

	/*def String genTl(InstrTl instr, int pIndent, String funName){

	}

	def String genTl(InstrHd instr, int pIndent, String funName){

	}

	def String genSym(InstrSym instr, int pIndent, String funName){

	}*/

	def String genWhile(InstrWhile instr, int pIndent, String funName){
		var parentIndent = makeIndent(pIndent)
	    var indent = pIndent + globalIndent
	    var cond = genCommand(genTable.getInstr(funName,instr.varEcriture),0,funName)
	    /*
		'''
		while �cond� do
		�makeIndent(indent)��genCommands(instr.getInstr(),indent,funName)�
		�parentIndent�end
		'''*/
		return "";
	}

	def String genFor(InstrFor instr, int pIndent, String funName){
		var parentIndent = makeIndent(pIndent)
	    var indent = pIndent + globalIndent
	    var cond = genCommand(genTable.getInstr(funName,instr.varEcriture),0,funName)
		'''
		while �cond� do
		�makeIndent(indent)��genCommands(instr.getInstr(),indent,funName)�
		�makeIndent(indent)��cond� = �cond�.tl --D�cr�mentation de la condition du For
		�parentIndent�end
		'''
	}

	def String printList(Iterable<String> list, String delim){

		var res = ""
    	if(list.size > 1){
    		for(i:0..list.size-2){
    			res+= list.get(i)+delim
    		}
    		res += list.get(list.size-1)
    	}
    	return res
	}

	 def String makeIndent(int indent){
    	var res = ""
    	for(i:0..indent){
    		if(i<indent) res+=" ";
    	}
    	return res
    }
}
