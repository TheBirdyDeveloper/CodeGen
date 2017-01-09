/**
 * generated by Xtext 2.10.0
 */
package org.xtext.comp.wh.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.xtext.comp.wh.ExprSimple;
import org.xtext.comp.wh.WhPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expr Simple</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.xtext.comp.wh.impl.ExprSimpleImpl#getStr <em>Str</em>}</li>
 *   <li>{@link org.xtext.comp.wh.impl.ExprSimpleImpl#getVarSimple <em>Var Simple</em>}</li>
 *   <li>{@link org.xtext.comp.wh.impl.ExprSimpleImpl#getSym <em>Sym</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExprSimpleImpl extends ExprImpl implements ExprSimple
{
  /**
   * The default value of the '{@link #getStr() <em>Str</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStr()
   * @generated
   * @ordered
   */
  protected static final String STR_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getStr() <em>Str</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStr()
   * @generated
   * @ordered
   */
  protected String str = STR_EDEFAULT;

  /**
   * The default value of the '{@link #getVarSimple() <em>Var Simple</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVarSimple()
   * @generated
   * @ordered
   */
  protected static final String VAR_SIMPLE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getVarSimple() <em>Var Simple</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVarSimple()
   * @generated
   * @ordered
   */
  protected String varSimple = VAR_SIMPLE_EDEFAULT;

  /**
   * The default value of the '{@link #getSym() <em>Sym</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSym()
   * @generated
   * @ordered
   */
  protected static final String SYM_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSym() <em>Sym</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSym()
   * @generated
   * @ordered
   */
  protected String sym = SYM_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ExprSimpleImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return WhPackage.Literals.EXPR_SIMPLE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getStr()
  {
    return str;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStr(String newStr)
  {
    String oldStr = str;
    str = newStr;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, WhPackage.EXPR_SIMPLE__STR, oldStr, str));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getVarSimple()
  {
    return varSimple;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVarSimple(String newVarSimple)
  {
    String oldVarSimple = varSimple;
    varSimple = newVarSimple;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, WhPackage.EXPR_SIMPLE__VAR_SIMPLE, oldVarSimple, varSimple));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getSym()
  {
    return sym;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSym(String newSym)
  {
    String oldSym = sym;
    sym = newSym;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, WhPackage.EXPR_SIMPLE__SYM, oldSym, sym));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case WhPackage.EXPR_SIMPLE__STR:
        return getStr();
      case WhPackage.EXPR_SIMPLE__VAR_SIMPLE:
        return getVarSimple();
      case WhPackage.EXPR_SIMPLE__SYM:
        return getSym();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case WhPackage.EXPR_SIMPLE__STR:
        setStr((String)newValue);
        return;
      case WhPackage.EXPR_SIMPLE__VAR_SIMPLE:
        setVarSimple((String)newValue);
        return;
      case WhPackage.EXPR_SIMPLE__SYM:
        setSym((String)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case WhPackage.EXPR_SIMPLE__STR:
        setStr(STR_EDEFAULT);
        return;
      case WhPackage.EXPR_SIMPLE__VAR_SIMPLE:
        setVarSimple(VAR_SIMPLE_EDEFAULT);
        return;
      case WhPackage.EXPR_SIMPLE__SYM:
        setSym(SYM_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case WhPackage.EXPR_SIMPLE__STR:
        return STR_EDEFAULT == null ? str != null : !STR_EDEFAULT.equals(str);
      case WhPackage.EXPR_SIMPLE__VAR_SIMPLE:
        return VAR_SIMPLE_EDEFAULT == null ? varSimple != null : !VAR_SIMPLE_EDEFAULT.equals(varSimple);
      case WhPackage.EXPR_SIMPLE__SYM:
        return SYM_EDEFAULT == null ? sym != null : !SYM_EDEFAULT.equals(sym);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (str: ");
    result.append(str);
    result.append(", varSimple: ");
    result.append(varSimple);
    result.append(", sym: ");
    result.append(sym);
    result.append(')');
    return result.toString();
  }

} //ExprSimpleImpl
