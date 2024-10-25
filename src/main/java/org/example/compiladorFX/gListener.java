package org.example.compiladorFX;// Generated from C:/Users/Bruno/IdeaProjects/gParserJavaFinal/src/main/java/g.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link gParser}.
 */
public interface gListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link gParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(gParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(gParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(gParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(gParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#if_statement}.
	 * @param ctx the parse tree
	 */
	void enterIf_statement(gParser.If_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#if_statement}.
	 * @param ctx the parse tree
	 */
	void exitIf_statement(gParser.If_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#while_loop}.
	 * @param ctx the parse tree
	 */
	void enterWhile_loop(gParser.While_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#while_loop}.
	 * @param ctx the parse tree
	 */
	void exitWhile_loop(gParser.While_loopContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#do_while}.
	 * @param ctx the parse tree
	 */
	void enterDo_while(gParser.Do_whileContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#do_while}.
	 * @param ctx the parse tree
	 */
	void exitDo_while(gParser.Do_whileContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#for_loop}.
	 * @param ctx the parse tree
	 */
	void enterFor_loop(gParser.For_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#for_loop}.
	 * @param ctx the parse tree
	 */
	void exitFor_loop(gParser.For_loopContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#variable_declaration}.
	 * @param ctx the parse tree
	 */
	void enterVariable_declaration(gParser.Variable_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#variable_declaration}.
	 * @param ctx the parse tree
	 */
	void exitVariable_declaration(gParser.Variable_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#variable_update}.
	 * @param ctx the parse tree
	 */
	void enterVariable_update(gParser.Variable_updateContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#variable_update}.
	 * @param ctx the parse tree
	 */
	void exitVariable_update(gParser.Variable_updateContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#variable_assign}.
	 * @param ctx the parse tree
	 */
	void enterVariable_assign(gParser.Variable_assignContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#variable_assign}.
	 * @param ctx the parse tree
	 */
	void exitVariable_assign(gParser.Variable_assignContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#math_expression}.
	 * @param ctx the parse tree
	 */
	void enterMath_expression(gParser.Math_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#math_expression}.
	 * @param ctx the parse tree
	 */
	void exitMath_expression(gParser.Math_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(gParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(gParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#power_expr}.
	 * @param ctx the parse tree
	 */
	void enterPower_expr(gParser.Power_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#power_expr}.
	 * @param ctx the parse tree
	 */
	void exitPower_expr(gParser.Power_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(gParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(gParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(gParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(gParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(gParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(gParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#logical_operation}.
	 * @param ctx the parse tree
	 */
	void enterLogical_operation(gParser.Logical_operationContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#logical_operation}.
	 * @param ctx the parse tree
	 */
	void exitLogical_operation(gParser.Logical_operationContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#logical_term}.
	 * @param ctx the parse tree
	 */
	void enterLogical_term(gParser.Logical_termContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#logical_term}.
	 * @param ctx the parse tree
	 */
	void exitLogical_term(gParser.Logical_termContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#logical_factor}.
	 * @param ctx the parse tree
	 */
	void enterLogical_factor(gParser.Logical_factorContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#logical_factor}.
	 * @param ctx the parse tree
	 */
	void exitLogical_factor(gParser.Logical_factorContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#boolean}.
	 * @param ctx the parse tree
	 */
	void enterBoolean(gParser.BooleanContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#boolean}.
	 * @param ctx the parse tree
	 */
	void exitBoolean(gParser.BooleanContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#comparison_operator}.
	 * @param ctx the parse tree
	 */
	void enterComparison_operator(gParser.Comparison_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#comparison_operator}.
	 * @param ctx the parse tree
	 */
	void exitComparison_operator(gParser.Comparison_operatorContext ctx);
}