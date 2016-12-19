package org.xtext.comp.generator;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.xtext.comp.wh.Command;
import org.xtext.comp.wh.Commands;
import org.xtext.comp.wh.Expr;
import org.xtext.comp.wh.For;
import org.xtext.comp.wh.If;
import org.xtext.comp.wh.While;

public class CommandIf implements CommandInstr,If{

	Commands commands1;
	Commands commands2;
	Expr expression;
	
	public CommandIf(Command c){
		this.commands1 = (Commands) ((If) c).getCommands1();
		this.commands2 = (Commands) ((If) c).getCommands2();

		this.expression = ((If)c).getExpr();
	}

	
	public String toString(){
		return " if (expression du If)\n" + "then " + commands1.toString() +"\n else then  "+ commands2.toString();
	}

	@Override
	public EObject getCmd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCmd(EObject value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EClass eClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resource eResource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EObject eContainer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EStructuralFeature eContainingFeature() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EReference eContainmentFeature() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EList<EObject> eContents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TreeIterator<EObject> eAllContents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eIsProxy() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EList<EObject> eCrossReferences() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object eGet(EStructuralFeature feature) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object eGet(EStructuralFeature feature, boolean resolve) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eSet(EStructuralFeature feature, Object newValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean eIsSet(EStructuralFeature feature) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void eUnset(EStructuralFeature feature) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object eInvoke(EOperation operation, EList<?> arguments) throws InvocationTargetException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EList<Adapter> eAdapters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eDeliver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void eSetDeliver(boolean deliver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eNotify(Notification notification) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Expr getExpr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setExpr(Expr value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Commands getCommands1() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCommands1(Commands value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Commands getCommands2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCommands2(Commands value) {
		// TODO Auto-generated method stub
		
	}
}
