/**
 * generated by Xtext 2.10.0
 */
package org.xtext.comp.wh;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expr Eq</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.xtext.comp.wh.ExprEq#getArg1 <em>Arg1</em>}</li>
 *   <li>{@link org.xtext.comp.wh.ExprEq#getArg2 <em>Arg2</em>}</li>
 * </ul>
 *
 * @see org.xtext.comp.wh.WhPackage#getExprEq()
 * @model
 * @generated
 */
public interface ExprEq extends Expr
{
  /**
   * Returns the value of the '<em><b>Arg1</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Arg1</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Arg1</em>' containment reference.
   * @see #setArg1(ExprSimple)
   * @see org.xtext.comp.wh.WhPackage#getExprEq_Arg1()
   * @model containment="true"
   * @generated
   */
  ExprSimple getArg1();

  /**
   * Sets the value of the '{@link org.xtext.comp.wh.ExprEq#getArg1 <em>Arg1</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Arg1</em>' containment reference.
   * @see #getArg1()
   * @generated
   */
  void setArg1(ExprSimple value);

  /**
   * Returns the value of the '<em><b>Arg2</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Arg2</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Arg2</em>' containment reference.
   * @see #setArg2(ExprSimple)
   * @see org.xtext.comp.wh.WhPackage#getExprEq_Arg2()
   * @model containment="true"
   * @generated
   */
  ExprSimple getArg2();

  /**
   * Sets the value of the '{@link org.xtext.comp.wh.ExprEq#getArg2 <em>Arg2</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Arg2</em>' containment reference.
   * @see #getArg2()
   * @generated
   */
  void setArg2(ExprSimple value);

} // ExprEq
