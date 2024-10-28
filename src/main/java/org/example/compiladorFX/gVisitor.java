package org.example.compiladorFX;// Generated from C:/Users/Bruno/IdeaProjects/Compiladorsito/src/main/g.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link gParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface gVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link gParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(gParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(gParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#if_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_statement(gParser.If_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#while_loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_loop(gParser.While_loopContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#do_while}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDo_while(gParser.Do_whileContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#for_loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_loop(gParser.For_loopContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#variable_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_declaration(gParser.Variable_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#variable_assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_assign(gParser.Variable_assignContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#variable_update}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_update(gParser.Variable_updateContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#function_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_declaration(gParser.Function_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#return_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_expression(gParser.Return_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParams(gParser.ParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#function_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_call(gParser.Function_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#params_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParams_call(gParser.Params_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#print_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint_call(gParser.Print_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#print_params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint_params(gParser.Print_paramsContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(gParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#string_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString_expression(gParser.String_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#math_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMath_expression(gParser.Math_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(gParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#power_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPower_expr(gParser.Power_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(gParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(gParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(gParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#logical_operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogical_operation(gParser.Logical_operationContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#logical_term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogical_term(gParser.Logical_termContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#logical_factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogical_factor(gParser.Logical_factorContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#boolean}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean(gParser.BooleanContext ctx);
	/**
	 * Visit a parse tree produced by {@link gParser#comparison_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison_operator(gParser.Comparison_operatorContext ctx);
}