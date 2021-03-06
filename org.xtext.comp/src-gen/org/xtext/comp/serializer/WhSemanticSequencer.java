/*
 * generated by Xtext 2.10.0
 */
package org.xtext.comp.serializer;

import com.google.inject.Inject;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;
import org.xtext.comp.services.WhGrammarAccess;
import org.xtext.comp.wh.Affect;
import org.xtext.comp.wh.Command;
import org.xtext.comp.wh.Commands;
import org.xtext.comp.wh.Definition;
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
import org.xtext.comp.wh.Function;
import org.xtext.comp.wh.If;
import org.xtext.comp.wh.Input;
import org.xtext.comp.wh.Nop;
import org.xtext.comp.wh.Output;
import org.xtext.comp.wh.Program;
import org.xtext.comp.wh.Wh;
import org.xtext.comp.wh.WhPackage;
import org.xtext.comp.wh.While;

@SuppressWarnings("all")
public class WhSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private WhGrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == WhPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case WhPackage.AFFECT:
				sequence_Affect(context, (Affect) semanticObject); 
				return; 
			case WhPackage.COMMAND:
				sequence_Command(context, (Command) semanticObject); 
				return; 
			case WhPackage.COMMANDS:
				sequence_Commands(context, (Commands) semanticObject); 
				return; 
			case WhPackage.DEFINITION:
				sequence_Definition(context, (Definition) semanticObject); 
				return; 
			case WhPackage.EXPR:
				sequence_Expr(context, (Expr) semanticObject); 
				return; 
			case WhPackage.EXPR_AND:
				sequence_ExprAnd(context, (ExprAnd) semanticObject); 
				return; 
			case WhPackage.EXPR_CONS:
				sequence_ExprCons(context, (ExprCons) semanticObject); 
				return; 
			case WhPackage.EXPR_EQ:
				sequence_ExprEq(context, (ExprEq) semanticObject); 
				return; 
			case WhPackage.EXPR_HD:
				sequence_ExprHd(context, (ExprHd) semanticObject); 
				return; 
			case WhPackage.EXPR_LIST:
				sequence_ExprList(context, (ExprList) semanticObject); 
				return; 
			case WhPackage.EXPR_NOT:
				sequence_ExprNot(context, (ExprNot) semanticObject); 
				return; 
			case WhPackage.EXPR_OR:
				sequence_ExprOr(context, (ExprOr) semanticObject); 
				return; 
			case WhPackage.EXPR_SIMPLE:
				sequence_ExprSimple(context, (ExprSimple) semanticObject); 
				return; 
			case WhPackage.EXPR_SYM:
				sequence_ExprSym(context, (ExprSym) semanticObject); 
				return; 
			case WhPackage.EXPR_TL:
				sequence_ExprTl(context, (ExprTl) semanticObject); 
				return; 
			case WhPackage.FOR:
				sequence_For(context, (For) semanticObject); 
				return; 
			case WhPackage.FUNCTION:
				sequence_Function(context, (Function) semanticObject); 
				return; 
			case WhPackage.IF:
				sequence_If(context, (If) semanticObject); 
				return; 
			case WhPackage.INPUT:
				sequence_Input(context, (Input) semanticObject); 
				return; 
			case WhPackage.NOP:
				sequence_Nop(context, (Nop) semanticObject); 
				return; 
			case WhPackage.OUTPUT:
				sequence_Output(context, (Output) semanticObject); 
				return; 
			case WhPackage.PROGRAM:
				sequence_Program(context, (Program) semanticObject); 
				return; 
			case WhPackage.WH:
				sequence_Wh(context, (Wh) semanticObject); 
				return; 
			case WhPackage.WHILE:
				sequence_While(context, (While) semanticObject); 
				return; 
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Contexts:
	 *     Affect returns Affect
	 *
	 * Constraint:
	 *     (vars+=VARIABLE vars+=VARIABLE* exprs+=Expr exprs+=Expr*)
	 */
	protected void sequence_Affect(ISerializationContext context, Affect semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Command returns Command
	 *
	 * Constraint:
	 *     (cmd=Nop | cmd=Affect | cmd=If | cmd=For | cmd=While)
	 */
	protected void sequence_Command(ISerializationContext context, Command semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Commands returns Commands
	 *
	 * Constraint:
	 *     (commands+=Command commands+=Command*)
	 */
	protected void sequence_Commands(ISerializationContext context, Commands semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Definition returns Definition
	 *
	 * Constraint:
	 *     (input=Input commands=Commands output=Output)
	 */
	protected void sequence_Definition(ISerializationContext context, Definition semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, WhPackage.Literals.DEFINITION__INPUT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, WhPackage.Literals.DEFINITION__INPUT));
			if (transientValues.isValueTransient(semanticObject, WhPackage.Literals.DEFINITION__COMMANDS) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, WhPackage.Literals.DEFINITION__COMMANDS));
			if (transientValues.isValueTransient(semanticObject, WhPackage.Literals.DEFINITION__OUTPUT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, WhPackage.Literals.DEFINITION__OUTPUT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getDefinitionAccess().getInputInputParserRuleCall_1_0(), semanticObject.getInput());
		feeder.accept(grammarAccess.getDefinitionAccess().getCommandsCommandsParserRuleCall_3_0(), semanticObject.getCommands());
		feeder.accept(grammarAccess.getDefinitionAccess().getOutputOutputParserRuleCall_6_0(), semanticObject.getOutput());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Expr returns ExprAnd
	 *     ExprAnd returns ExprAnd
	 *
	 * Constraint:
	 *     (arg1=ExprSimple arg2=Expr)
	 */
	protected void sequence_ExprAnd(ISerializationContext context, ExprAnd semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, WhPackage.Literals.EXPR_AND__ARG1) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, WhPackage.Literals.EXPR_AND__ARG1));
			if (transientValues.isValueTransient(semanticObject, WhPackage.Literals.EXPR_AND__ARG2) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, WhPackage.Literals.EXPR_AND__ARG2));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getExprAndAccess().getArg1ExprSimpleParserRuleCall_0_0(), semanticObject.getArg1());
		feeder.accept(grammarAccess.getExprAndAccess().getArg2ExprParserRuleCall_2_0(), semanticObject.getArg2());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Expr returns ExprCons
	 *     ExprCons returns ExprCons
	 *
	 * Constraint:
	 *     (arg+=Expr arg+=Expr*)
	 */
	protected void sequence_ExprCons(ISerializationContext context, ExprCons semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Expr returns ExprEq
	 *     ExprEq returns ExprEq
	 *
	 * Constraint:
	 *     (arg1=ExprSimple arg2=ExprSimple)
	 */
	protected void sequence_ExprEq(ISerializationContext context, ExprEq semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, WhPackage.Literals.EXPR_EQ__ARG1) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, WhPackage.Literals.EXPR_EQ__ARG1));
			if (transientValues.isValueTransient(semanticObject, WhPackage.Literals.EXPR_EQ__ARG2) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, WhPackage.Literals.EXPR_EQ__ARG2));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getExprEqAccess().getArg1ExprSimpleParserRuleCall_0_0(), semanticObject.getArg1());
		feeder.accept(grammarAccess.getExprEqAccess().getArg2ExprSimpleParserRuleCall_2_0(), semanticObject.getArg2());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Expr returns ExprHd
	 *     ExprHd returns ExprHd
	 *
	 * Constraint:
	 *     arg=Expr
	 */
	protected void sequence_ExprHd(ISerializationContext context, ExprHd semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, WhPackage.Literals.EXPR_HD__ARG) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, WhPackage.Literals.EXPR_HD__ARG));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getExprHdAccess().getArgExprParserRuleCall_2_0(), semanticObject.getArg());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Expr returns ExprList
	 *     ExprList returns ExprList
	 *
	 * Constraint:
	 *     arg+=Expr
	 */
	protected void sequence_ExprList(ISerializationContext context, ExprList semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Expr returns ExprNot
	 *     ExprNot returns ExprNot
	 *
	 * Constraint:
	 *     arg1=ExprEq
	 */
	protected void sequence_ExprNot(ISerializationContext context, ExprNot semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, WhPackage.Literals.EXPR_NOT__ARG1) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, WhPackage.Literals.EXPR_NOT__ARG1));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getExprNotAccess().getArg1ExprEqParserRuleCall_1_0(), semanticObject.getArg1());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Expr returns ExprOr
	 *     ExprOr returns ExprOr
	 *
	 * Constraint:
	 *     (arg1=ExprSimple arg2=Expr)
	 */
	protected void sequence_ExprOr(ISerializationContext context, ExprOr semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, WhPackage.Literals.EXPR_OR__ARG1) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, WhPackage.Literals.EXPR_OR__ARG1));
			if (transientValues.isValueTransient(semanticObject, WhPackage.Literals.EXPR_OR__ARG2) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, WhPackage.Literals.EXPR_OR__ARG2));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getExprOrAccess().getArg1ExprSimpleParserRuleCall_0_0(), semanticObject.getArg1());
		feeder.accept(grammarAccess.getExprOrAccess().getArg2ExprParserRuleCall_2_0(), semanticObject.getArg2());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Expr returns ExprSimple
	 *     ExprSimple returns ExprSimple
	 *
	 * Constraint:
	 *     (str=NIL | varSimple=VARIABLE | sym=SYMBOLE)
	 */
	protected void sequence_ExprSimple(ISerializationContext context, ExprSimple semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Expr returns ExprSym
	 *     ExprSym returns ExprSym
	 *
	 * Constraint:
	 *     (arg1=SYMBOLE arg2+=Expr arg2+=Expr*)
	 */
	protected void sequence_ExprSym(ISerializationContext context, ExprSym semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Expr returns ExprTl
	 *     ExprTl returns ExprTl
	 *
	 * Constraint:
	 *     arg=Expr
	 */
	protected void sequence_ExprTl(ISerializationContext context, ExprTl semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, WhPackage.Literals.EXPR_TL__ARG) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, WhPackage.Literals.EXPR_TL__ARG));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getExprTlAccess().getArgExprParserRuleCall_2_0(), semanticObject.getArg());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Expr returns Expr
	 *
	 * Constraint:
	 *     expr=Expr
	 */
	protected void sequence_Expr(ISerializationContext context, Expr semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, WhPackage.Literals.EXPR__EXPR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, WhPackage.Literals.EXPR__EXPR));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getExprAccess().getExprExprParserRuleCall_10_1_0(), semanticObject.getExpr());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     For returns For
	 *
	 * Constraint:
	 *     (expr=Expr cmds=Commands)
	 */
	protected void sequence_For(ISerializationContext context, For semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, WhPackage.Literals.FOR__EXPR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, WhPackage.Literals.FOR__EXPR));
			if (transientValues.isValueTransient(semanticObject, WhPackage.Literals.FOR__CMDS) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, WhPackage.Literals.FOR__CMDS));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getForAccess().getExprExprParserRuleCall_1_0(), semanticObject.getExpr());
		feeder.accept(grammarAccess.getForAccess().getCmdsCommandsParserRuleCall_3_0(), semanticObject.getCmds());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Function returns Function
	 *
	 * Constraint:
	 *     (name=SYMBOLE definition=Definition)
	 */
	protected void sequence_Function(ISerializationContext context, Function semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, WhPackage.Literals.FUNCTION__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, WhPackage.Literals.FUNCTION__NAME));
			if (transientValues.isValueTransient(semanticObject, WhPackage.Literals.FUNCTION__DEFINITION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, WhPackage.Literals.FUNCTION__DEFINITION));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getFunctionAccess().getNameSYMBOLETerminalRuleCall_1_0(), semanticObject.getName());
		feeder.accept(grammarAccess.getFunctionAccess().getDefinitionDefinitionParserRuleCall_3_0(), semanticObject.getDefinition());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     If returns If
	 *
	 * Constraint:
	 *     (expr=Expr commands1=Commands commands2=Commands?)
	 */
	protected void sequence_If(ISerializationContext context, If semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Input returns Input
	 *
	 * Constraint:
	 *     (vars+=VARIABLE vars+=VARIABLE*)
	 */
	protected void sequence_Input(ISerializationContext context, Input semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Nop returns Nop
	 *
	 * Constraint:
	 *     nop='nop'
	 */
	protected void sequence_Nop(ISerializationContext context, Nop semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, WhPackage.Literals.NOP__NOP) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, WhPackage.Literals.NOP__NOP));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getNopAccess().getNopNopKeyword_0(), semanticObject.getNop());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Output returns Output
	 *
	 * Constraint:
	 *     (vars+=VARIABLE vars+=VARIABLE*)
	 */
	protected void sequence_Output(ISerializationContext context, Output semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Program returns Program
	 *
	 * Constraint:
	 *     functions+=Function+
	 */
	protected void sequence_Program(ISerializationContext context, Program semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Wh returns Wh
	 *
	 * Constraint:
	 *     elements+=Program
	 */
	protected void sequence_Wh(ISerializationContext context, Wh semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     While returns While
	 *
	 * Constraint:
	 *     (expr=Expr cmds=Commands)
	 */
	protected void sequence_While(ISerializationContext context, While semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, WhPackage.Literals.WHILE__EXPR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, WhPackage.Literals.WHILE__EXPR));
			if (transientValues.isValueTransient(semanticObject, WhPackage.Literals.WHILE__CMDS) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, WhPackage.Literals.WHILE__CMDS));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getWhileAccess().getExprExprParserRuleCall_1_0(), semanticObject.getExpr());
		feeder.accept(grammarAccess.getWhileAccess().getCmdsCommandsParserRuleCall_3_0(), semanticObject.getCmds());
		feeder.finish();
	}
	
	
}
