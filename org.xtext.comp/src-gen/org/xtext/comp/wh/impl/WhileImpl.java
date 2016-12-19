/**
 * generated by Xtext 2.10.0
 */
package org.xtext.comp.wh.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.xtext.comp.wh.Commands;
import org.xtext.comp.wh.Expr;
import org.xtext.comp.wh.WhPackage;
import org.xtext.comp.wh.While;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>While</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.xtext.comp.wh.impl.WhileImpl#getExpr <em>Expr</em>}</li>
 *   <li>{@link org.xtext.comp.wh.impl.WhileImpl#getCmds <em>Cmds</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WhileImpl extends MinimalEObjectImpl.Container implements While
{
  /**
   * The cached value of the '{@link #getExpr() <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpr()
   * @generated
   * @ordered
   */
  protected Expr expr;

  /**
   * The cached value of the '{@link #getCmds() <em>Cmds</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCmds()
   * @generated
   * @ordered
   */
  protected Commands cmds;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected WhileImpl()
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
    return WhPackage.Literals.WHILE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expr getExpr()
  {
    return expr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetExpr(Expr newExpr, NotificationChain msgs)
  {
    Expr oldExpr = expr;
    expr = newExpr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WhPackage.WHILE__EXPR, oldExpr, newExpr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExpr(Expr newExpr)
  {
    if (newExpr != expr)
    {
      NotificationChain msgs = null;
      if (expr != null)
        msgs = ((InternalEObject)expr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WhPackage.WHILE__EXPR, null, msgs);
      if (newExpr != null)
        msgs = ((InternalEObject)newExpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WhPackage.WHILE__EXPR, null, msgs);
      msgs = basicSetExpr(newExpr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, WhPackage.WHILE__EXPR, newExpr, newExpr));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Commands getCmds()
  {
    return cmds;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCmds(Commands newCmds, NotificationChain msgs)
  {
    Commands oldCmds = cmds;
    cmds = newCmds;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WhPackage.WHILE__CMDS, oldCmds, newCmds);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCmds(Commands newCmds)
  {
    if (newCmds != cmds)
    {
      NotificationChain msgs = null;
      if (cmds != null)
        msgs = ((InternalEObject)cmds).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WhPackage.WHILE__CMDS, null, msgs);
      if (newCmds != null)
        msgs = ((InternalEObject)newCmds).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WhPackage.WHILE__CMDS, null, msgs);
      msgs = basicSetCmds(newCmds, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, WhPackage.WHILE__CMDS, newCmds, newCmds));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case WhPackage.WHILE__EXPR:
        return basicSetExpr(null, msgs);
      case WhPackage.WHILE__CMDS:
        return basicSetCmds(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
      case WhPackage.WHILE__EXPR:
        return getExpr();
      case WhPackage.WHILE__CMDS:
        return getCmds();
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
      case WhPackage.WHILE__EXPR:
        setExpr((Expr)newValue);
        return;
      case WhPackage.WHILE__CMDS:
        setCmds((Commands)newValue);
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
      case WhPackage.WHILE__EXPR:
        setExpr((Expr)null);
        return;
      case WhPackage.WHILE__CMDS:
        setCmds((Commands)null);
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
      case WhPackage.WHILE__EXPR:
        return expr != null;
      case WhPackage.WHILE__CMDS:
        return cmds != null;
    }
    return super.eIsSet(featureID);
  }

} //WhileImpl
