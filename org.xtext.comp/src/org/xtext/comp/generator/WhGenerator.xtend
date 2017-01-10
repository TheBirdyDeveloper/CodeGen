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
    	fsa.generateFile(outputName, genTable.listCode3Adr.genCode3Adr)
	}
	
	def String genCode3Adr(HashMap<Code, List<Instr>> map){
		'''
		�FOR fun : map.keySet()�
		function �fun.getName�(�printList(genTable.environmentFonctions.get(fun.name).getInputs().keySet(),", ")�)
		�genCommands(map.get(fun),globalIndent,fun.getName)�
		
		return �printList(genTable.environmentFonctions.get(fun.name).getOutputs().keySet(),", ")�
		end
		�ENDFOR�
		'''
		}
	
	
	def String genCommands(List<Instr> instrs, int indent,String funName)
		'''
		�FOR instr : instrs�
		�genCommand(instr, indent, funName)�
		�ENDFOR�
		'''
	
	def String genCommand(Instr instr, int pIndent, String funName)
	{
		if(instr instanceof InstrNop)	return "";
		if(instr instanceof InstrVar)	return genVar(instr, pIndent, funName);
		if(instr instanceof InstrIf)    return genIf(instr,pIndent, funName);
		if(instr instanceof InstrOr)	return genOr(instr, pIndent, funName);
		if(instr instanceof InstrAnd)	return genAnd(instr, pIndent, funName);
		if(instr instanceof InstrCons)	return genCons(instr, pIndent, funName);
		return "TODO"
	}

	def String genVar(InstrVar instr, int pIndent,String funName){
		return instr.getVar;
	}
	
	def String genIf(InstrIf instr, int pIndent,String funName){
		var parentIndent = makeIndent(pIndent)
	    var indent = pIndent + globalIndent
	    var cond = genTable.getInstr(funName,instr.cond);
	    var condStr = genCommand(cond,0,funName);
		'''
		�parentIndent�if �condStr� then 
		�genCommands(instr.getSiVrai(), indent, funName)�
		�IF !instr.getSiFaux().empty�
		�parentIndent�else 
		�genCommands(instr.getSiFaux(),indent, funName)�
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
	
	
	def String printList(Set<String> list, String delim){
		
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
