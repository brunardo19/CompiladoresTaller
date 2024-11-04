package org.example.compiladorFX;// Generated from C:/Users/Bruno/IdeaProjects/Compiladorsito/src/main/g.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class gParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, IF=26, ELSE=27, WHILE=28, DO=29, FOR=30, TRUE=31, FALSE=32, 
		INT_TYPE=33, DOUBLE_TYPE=34, BOOLEAN_TYPE=35, STRING_TYPE=36, PRINT_ID=37, 
		ID=38, INT=39, DOUBLE=40, STRING=41, MATH_OP=42, LOG_OP=43, COMP_OP=44, 
		ASSIGN=45, PUNCT=46, WS=47;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_if_statement = 2, RULE_while_loop = 3, 
		RULE_do_while = 4, RULE_for_loop = 5, RULE_variable_declaration = 6, RULE_variable_assign = 7, 
		RULE_variable_update = 8, RULE_function_declaration = 9, RULE_return_expression = 10, 
		RULE_params = 11, RULE_function_call = 12, RULE_params_call = 13, RULE_print_call = 14, 
		RULE_print_params = 15, RULE_expression = 16, RULE_string_expression = 17, 
		RULE_math_expression = 18, RULE_term = 19, RULE_power_expr = 20, RULE_factor = 21, 
		RULE_number = 22, RULE_type = 23, RULE_logical_operation = 24, RULE_logical_term = 25, 
		RULE_logical_factor = 26, RULE_boolean = 27, RULE_comparison_operator = 28;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "if_statement", "while_loop", "do_while", "for_loop", 
			"variable_declaration", "variable_assign", "variable_update", "function_declaration", 
			"return_expression", "params", "function_call", "params_call", "print_call", 
			"print_params", "expression", "string_expression", "math_expression", 
			"term", "power_expr", "factor", "number", "type", "logical_operation", 
			"logical_term", "logical_factor", "boolean", "comparison_operator"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'('", "')'", "'{'", "'}'", "'++'", "'--'", "'+='", "'-='", 
			"'return'", "','", "'+'", "'-'", "'*'", "'/'", "'^'", "'||'", "'&&'", 
			"'!'", "'=='", "'!='", "'<'", "'>'", "'<='", "'>='", "'if'", "'else'", 
			"'while'", "'do'", "'for'", "'true'", "'false'", "'int'", "'double'", 
			"'boolean'", "'string'", "'print'", null, null, null, null, null, null, 
			null, "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "IF", "ELSE", "WHILE", "DO", "FOR", "TRUE", "FALSE", "INT_TYPE", 
			"DOUBLE_TYPE", "BOOLEAN_TYPE", "STRING_TYPE", "PRINT_ID", "ID", "INT", 
			"DOUBLE", "STRING", "MATH_OP", "LOG_OP", "COMP_OP", "ASSIGN", "PUNCT", 
			"WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "g.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public gParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gVisitor) return ((gVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 543112036352L) != 0)) {
				{
				{
				setState(58);
				statement();
				}
				}
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public Variable_declarationContext variable_declaration() {
			return getRuleContext(Variable_declarationContext.class,0);
		}
		public Variable_assignContext variable_assign() {
			return getRuleContext(Variable_assignContext.class,0);
		}
		public Variable_updateContext variable_update() {
			return getRuleContext(Variable_updateContext.class,0);
		}
		public If_statementContext if_statement() {
			return getRuleContext(If_statementContext.class,0);
		}
		public While_loopContext while_loop() {
			return getRuleContext(While_loopContext.class,0);
		}
		public Do_whileContext do_while() {
			return getRuleContext(Do_whileContext.class,0);
		}
		public For_loopContext for_loop() {
			return getRuleContext(For_loopContext.class,0);
		}
		public Function_declarationContext function_declaration() {
			return getRuleContext(Function_declarationContext.class,0);
		}
		public Function_callContext function_call() {
			return getRuleContext(Function_callContext.class,0);
		}
		public Print_callContext print_call() {
			return getRuleContext(Print_callContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gVisitor) return ((gVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(84);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(64);
				variable_declaration();
				setState(65);
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				variable_assign();
				setState(68);
				match(T__0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(70);
				variable_update();
				setState(71);
				match(T__0);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(73);
				if_statement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(74);
				while_loop();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(75);
				do_while();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(76);
				for_loop();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(77);
				function_declaration();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(78);
				function_call();
				setState(79);
				match(T__0);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(81);
				print_call();
				setState(82);
				match(T__0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class If_statementContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(gParser.IF, 0); }
		public Logical_operationContext logical_operation() {
			return getRuleContext(Logical_operationContext.class,0);
		}
		public List<ProgramContext> program() {
			return getRuleContexts(ProgramContext.class);
		}
		public ProgramContext program(int i) {
			return getRuleContext(ProgramContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(gParser.ELSE, 0); }
		public If_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).enterIf_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).exitIf_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gVisitor) return ((gVisitor<? extends T>)visitor).visitIf_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_statementContext if_statement() throws RecognitionException {
		If_statementContext _localctx = new If_statementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_if_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			match(IF);
			setState(87);
			match(T__1);
			setState(88);
			logical_operation();
			setState(89);
			match(T__2);
			setState(90);
			match(T__3);
			setState(91);
			program();
			setState(92);
			match(T__4);
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(93);
				match(ELSE);
				setState(94);
				match(T__3);
				setState(95);
				program();
				setState(96);
				match(T__4);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class While_loopContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(gParser.WHILE, 0); }
		public Logical_operationContext logical_operation() {
			return getRuleContext(Logical_operationContext.class,0);
		}
		public ProgramContext program() {
			return getRuleContext(ProgramContext.class,0);
		}
		public While_loopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while_loop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).enterWhile_loop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).exitWhile_loop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gVisitor) return ((gVisitor<? extends T>)visitor).visitWhile_loop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final While_loopContext while_loop() throws RecognitionException {
		While_loopContext _localctx = new While_loopContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_while_loop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(WHILE);
			setState(101);
			match(T__1);
			setState(102);
			logical_operation();
			setState(103);
			match(T__2);
			setState(104);
			match(T__3);
			setState(105);
			program();
			setState(106);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Do_whileContext extends ParserRuleContext {
		public TerminalNode DO() { return getToken(gParser.DO, 0); }
		public ProgramContext program() {
			return getRuleContext(ProgramContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(gParser.WHILE, 0); }
		public Logical_operationContext logical_operation() {
			return getRuleContext(Logical_operationContext.class,0);
		}
		public Do_whileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_do_while; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).enterDo_while(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).exitDo_while(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gVisitor) return ((gVisitor<? extends T>)visitor).visitDo_while(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Do_whileContext do_while() throws RecognitionException {
		Do_whileContext _localctx = new Do_whileContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_do_while);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(DO);
			setState(109);
			match(T__3);
			setState(110);
			program();
			setState(111);
			match(T__4);
			setState(112);
			match(WHILE);
			setState(113);
			match(T__1);
			setState(114);
			logical_operation();
			setState(115);
			match(T__2);
			setState(116);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class For_loopContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(gParser.FOR, 0); }
		public Variable_declarationContext variable_declaration() {
			return getRuleContext(Variable_declarationContext.class,0);
		}
		public Logical_operationContext logical_operation() {
			return getRuleContext(Logical_operationContext.class,0);
		}
		public Variable_updateContext variable_update() {
			return getRuleContext(Variable_updateContext.class,0);
		}
		public ProgramContext program() {
			return getRuleContext(ProgramContext.class,0);
		}
		public For_loopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_loop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).enterFor_loop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).exitFor_loop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gVisitor) return ((gVisitor<? extends T>)visitor).visitFor_loop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final For_loopContext for_loop() throws RecognitionException {
		For_loopContext _localctx = new For_loopContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_for_loop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			match(FOR);
			setState(119);
			match(T__1);
			setState(120);
			variable_declaration();
			setState(121);
			match(T__0);
			setState(122);
			logical_operation();
			setState(123);
			match(T__0);
			setState(124);
			variable_update();
			setState(125);
			match(T__2);
			setState(126);
			match(T__3);
			setState(127);
			program();
			setState(128);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Variable_declarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(gParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(gParser.ID, i);
		}
		public TerminalNode ASSIGN() { return getToken(gParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Variable_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).enterVariable_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).exitVariable_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gVisitor) return ((gVisitor<? extends T>)visitor).visitVariable_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Variable_declarationContext variable_declaration() throws RecognitionException {
		Variable_declarationContext _localctx = new Variable_declarationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_variable_declaration);
		try {
			setState(143);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(130);
				type();
				setState(131);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(133);
				type();
				setState(134);
				match(ID);
				setState(135);
				match(ASSIGN);
				setState(136);
				match(ID);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(138);
				type();
				setState(139);
				match(ID);
				setState(140);
				match(ASSIGN);
				setState(141);
				expression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Variable_assignContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(gParser.ID, 0); }
		public TerminalNode ASSIGN() { return getToken(gParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Variable_assignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_assign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).enterVariable_assign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).exitVariable_assign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gVisitor) return ((gVisitor<? extends T>)visitor).visitVariable_assign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Variable_assignContext variable_assign() throws RecognitionException {
		Variable_assignContext _localctx = new Variable_assignContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_variable_assign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(ID);
			setState(146);
			match(ASSIGN);
			setState(147);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Variable_updateContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(gParser.ID, 0); }
		public Math_expressionContext math_expression() {
			return getRuleContext(Math_expressionContext.class,0);
		}
		public Variable_updateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_update; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).enterVariable_update(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).exitVariable_update(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gVisitor) return ((gVisitor<? extends T>)visitor).visitVariable_update(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Variable_updateContext variable_update() throws RecognitionException {
		Variable_updateContext _localctx = new Variable_updateContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_variable_update);
		int _la;
		try {
			setState(154);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(149);
				match(ID);
				setState(150);
				_la = _input.LA(1);
				if ( !(_la==T__5 || _la==T__6) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(151);
				match(ID);
				setState(152);
				_la = _input.LA(1);
				if ( !(_la==T__7 || _la==T__8) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(153);
				math_expression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Function_declarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(gParser.ID, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public ProgramContext program() {
			return getRuleContext(ProgramContext.class,0);
		}
		public Return_expressionContext return_expression() {
			return getRuleContext(Return_expressionContext.class,0);
		}
		public Function_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).enterFunction_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).exitFunction_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gVisitor) return ((gVisitor<? extends T>)visitor).visitFunction_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_declarationContext function_declaration() throws RecognitionException {
		Function_declarationContext _localctx = new Function_declarationContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_function_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			type();
			setState(157);
			match(ID);
			setState(158);
			match(T__1);
			setState(159);
			params();
			setState(160);
			match(T__2);
			setState(161);
			match(T__3);
			setState(162);
			program();
			setState(163);
			return_expression();
			setState(164);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Return_expressionContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Return_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).enterReturn_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).exitReturn_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gVisitor) return ((gVisitor<? extends T>)visitor).visitReturn_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Return_expressionContext return_expression() throws RecognitionException {
		Return_expressionContext _localctx = new Return_expressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_return_expression);
		try {
			setState(171);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
				enterOuterAlt(_localctx, 1);
				{
				setState(166);
				match(T__9);
				setState(167);
				expression();
				setState(168);
				match(T__0);
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParamsContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(gParser.ID, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).exitParams(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gVisitor) return ((gVisitor<? extends T>)visitor).visitParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_params);
		try {
			setState(181);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(173);
				type();
				setState(174);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(176);
				type();
				setState(177);
				match(ID);
				setState(178);
				match(T__10);
				setState(179);
				params();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Function_callContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(gParser.ID, 0); }
		public Params_callContext params_call() {
			return getRuleContext(Params_callContext.class,0);
		}
		public Function_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).enterFunction_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).exitFunction_call(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gVisitor) return ((gVisitor<? extends T>)visitor).visitFunction_call(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_callContext function_call() throws RecognitionException {
		Function_callContext _localctx = new Function_callContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_function_call);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(ID);
			setState(184);
			match(T__1);
			setState(185);
			params_call();
			setState(186);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Params_callContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Params_callContext params_call() {
			return getRuleContext(Params_callContext.class,0);
		}
		public Params_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).enterParams_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).exitParams_call(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gVisitor) return ((gVisitor<? extends T>)visitor).visitParams_call(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Params_callContext params_call() throws RecognitionException {
		Params_callContext _localctx = new Params_callContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_params_call);
		try {
			setState(193);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(188);
				expression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(189);
				expression();
				setState(190);
				match(T__10);
				setState(191);
				params_call();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Print_callContext extends ParserRuleContext {
		public TerminalNode PRINT_ID() { return getToken(gParser.PRINT_ID, 0); }
		public Print_paramsContext print_params() {
			return getRuleContext(Print_paramsContext.class,0);
		}
		public Print_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_print_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).enterPrint_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).exitPrint_call(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gVisitor) return ((gVisitor<? extends T>)visitor).visitPrint_call(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Print_callContext print_call() throws RecognitionException {
		Print_callContext _localctx = new Print_callContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_print_call);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195);
			match(PRINT_ID);
			setState(196);
			match(T__1);
			setState(197);
			print_params();
			setState(198);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Print_paramsContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Print_paramsContext print_params() {
			return getRuleContext(Print_paramsContext.class,0);
		}
		public Print_paramsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_print_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).enterPrint_params(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).exitPrint_params(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gVisitor) return ((gVisitor<? extends T>)visitor).visitPrint_params(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Print_paramsContext print_params() throws RecognitionException {
		Print_paramsContext _localctx = new Print_paramsContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_print_params);
		try {
			setState(205);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(200);
				expression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(201);
				expression();
				setState(202);
				match(T__11);
				setState(203);
				print_params();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public Math_expressionContext math_expression() {
			return getRuleContext(Math_expressionContext.class,0);
		}
		public Logical_operationContext logical_operation() {
			return getRuleContext(Logical_operationContext.class,0);
		}
		public String_expressionContext string_expression() {
			return getRuleContext(String_expressionContext.class,0);
		}
		public Function_callContext function_call() {
			return getRuleContext(Function_callContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gVisitor) return ((gVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_expression);
		try {
			setState(211);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(207);
				math_expression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(208);
				logical_operation();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(209);
				string_expression();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(210);
				function_call();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class String_expressionContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(gParser.STRING, 0); }
		public String_expressionContext string_expression() {
			return getRuleContext(String_expressionContext.class,0);
		}
		public String_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).enterString_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).exitString_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gVisitor) return ((gVisitor<? extends T>)visitor).visitString_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final String_expressionContext string_expression() throws RecognitionException {
		String_expressionContext _localctx = new String_expressionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_string_expression);
		try {
			setState(217);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(213);
				match(STRING);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(214);
				match(STRING);
				setState(215);
				match(T__11);
				setState(216);
				string_expression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Math_expressionContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public Math_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_math_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).enterMath_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).exitMath_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gVisitor) return ((gVisitor<? extends T>)visitor).visitMath_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Math_expressionContext math_expression() throws RecognitionException {
		Math_expressionContext _localctx = new Math_expressionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_math_expression);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			term();
			setState(224);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(220);
					_la = _input.LA(1);
					if ( !(_la==T__11 || _la==T__12) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(221);
					term();
					}
					} 
				}
				setState(226);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermContext extends ParserRuleContext {
		public List<Power_exprContext> power_expr() {
			return getRuleContexts(Power_exprContext.class);
		}
		public Power_exprContext power_expr(int i) {
			return getRuleContext(Power_exprContext.class,i);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gVisitor) return ((gVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227);
			power_expr();
			setState(232);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__13 || _la==T__14) {
				{
				{
				setState(228);
				_la = _input.LA(1);
				if ( !(_la==T__13 || _la==T__14) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(229);
				power_expr();
				}
				}
				setState(234);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Power_exprContext extends ParserRuleContext {
		public List<FactorContext> factor() {
			return getRuleContexts(FactorContext.class);
		}
		public FactorContext factor(int i) {
			return getRuleContext(FactorContext.class,i);
		}
		public Power_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_power_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).enterPower_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).exitPower_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gVisitor) return ((gVisitor<? extends T>)visitor).visitPower_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Power_exprContext power_expr() throws RecognitionException {
		Power_exprContext _localctx = new Power_exprContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_power_expr);
		try {
			setState(240);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(235);
				factor();
				{
				setState(236);
				match(T__15);
				setState(237);
				factor();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(239);
				factor();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FactorContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(gParser.ID, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public Math_expressionContext math_expression() {
			return getRuleContext(Math_expressionContext.class,0);
		}
		public Function_callContext function_call() {
			return getRuleContext(Function_callContext.class,0);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).exitFactor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gVisitor) return ((gVisitor<? extends T>)visitor).visitFactor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_factor);
		try {
			setState(249);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(242);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(243);
				number();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(244);
				match(T__1);
				setState(245);
				math_expression();
				setState(246);
				match(T__2);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(248);
				function_call();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumberContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(gParser.INT, 0); }
		public TerminalNode DOUBLE() { return getToken(gParser.DOUBLE, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gVisitor) return ((gVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_number);
		int _la;
		try {
			setState(259);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(252);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__11 || _la==T__12) {
					{
					setState(251);
					_la = _input.LA(1);
					if ( !(_la==T__11 || _la==T__12) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(254);
				match(INT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(256);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__11 || _la==T__12) {
					{
					setState(255);
					_la = _input.LA(1);
					if ( !(_la==T__11 || _la==T__12) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(258);
				match(DOUBLE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TerminalNode INT_TYPE() { return getToken(gParser.INT_TYPE, 0); }
		public TerminalNode DOUBLE_TYPE() { return getToken(gParser.DOUBLE_TYPE, 0); }
		public TerminalNode BOOLEAN_TYPE() { return getToken(gParser.BOOLEAN_TYPE, 0); }
		public TerminalNode STRING_TYPE() { return getToken(gParser.STRING_TYPE, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gVisitor) return ((gVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 128849018880L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Logical_operationContext extends ParserRuleContext {
		public List<Logical_termContext> logical_term() {
			return getRuleContexts(Logical_termContext.class);
		}
		public Logical_termContext logical_term(int i) {
			return getRuleContext(Logical_termContext.class,i);
		}
		public Logical_operationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logical_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).enterLogical_operation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).exitLogical_operation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gVisitor) return ((gVisitor<? extends T>)visitor).visitLogical_operation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Logical_operationContext logical_operation() throws RecognitionException {
		Logical_operationContext _localctx = new Logical_operationContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_logical_operation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			logical_term();
			setState(268);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__16) {
				{
				{
				setState(264);
				match(T__16);
				setState(265);
				logical_term();
				}
				}
				setState(270);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Logical_termContext extends ParserRuleContext {
		public List<Logical_factorContext> logical_factor() {
			return getRuleContexts(Logical_factorContext.class);
		}
		public Logical_factorContext logical_factor(int i) {
			return getRuleContext(Logical_factorContext.class,i);
		}
		public Logical_termContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logical_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).enterLogical_term(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).exitLogical_term(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gVisitor) return ((gVisitor<? extends T>)visitor).visitLogical_term(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Logical_termContext logical_term() throws RecognitionException {
		Logical_termContext _localctx = new Logical_termContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_logical_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(271);
			logical_factor();
			setState(276);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__17) {
				{
				{
				setState(272);
				match(T__17);
				setState(273);
				logical_factor();
				}
				}
				setState(278);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Logical_factorContext extends ParserRuleContext {
		public BooleanContext boolean_() {
			return getRuleContext(BooleanContext.class,0);
		}
		public Function_callContext function_call() {
			return getRuleContext(Function_callContext.class,0);
		}
		public Logical_factorContext logical_factor() {
			return getRuleContext(Logical_factorContext.class,0);
		}
		public Logical_operationContext logical_operation() {
			return getRuleContext(Logical_operationContext.class,0);
		}
		public Logical_factorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logical_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).enterLogical_factor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).exitLogical_factor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gVisitor) return ((gVisitor<? extends T>)visitor).visitLogical_factor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Logical_factorContext logical_factor() throws RecognitionException {
		Logical_factorContext _localctx = new Logical_factorContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_logical_factor);
		try {
			setState(287);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(279);
				boolean_();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(280);
				function_call();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(281);
				match(T__18);
				setState(282);
				logical_factor();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(283);
				match(T__1);
				setState(284);
				logical_operation();
				setState(285);
				match(T__2);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BooleanContext extends ParserRuleContext {
		public List<Math_expressionContext> math_expression() {
			return getRuleContexts(Math_expressionContext.class);
		}
		public Math_expressionContext math_expression(int i) {
			return getRuleContext(Math_expressionContext.class,i);
		}
		public Comparison_operatorContext comparison_operator() {
			return getRuleContext(Comparison_operatorContext.class,0);
		}
		public TerminalNode TRUE() { return getToken(gParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(gParser.FALSE, 0); }
		public TerminalNode ID() { return getToken(gParser.ID, 0); }
		public BooleanContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolean; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).enterBoolean(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).exitBoolean(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gVisitor) return ((gVisitor<? extends T>)visitor).visitBoolean(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BooleanContext boolean_() throws RecognitionException {
		BooleanContext _localctx = new BooleanContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_boolean);
		try {
			setState(296);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(289);
				math_expression();
				setState(290);
				comparison_operator();
				setState(291);
				math_expression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(293);
				match(TRUE);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(294);
				match(FALSE);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(295);
				match(ID);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Comparison_operatorContext extends ParserRuleContext {
		public Comparison_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).enterComparison_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gListener ) ((gListener)listener).exitComparison_operator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gVisitor) return ((gVisitor<? extends T>)visitor).visitComparison_operator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Comparison_operatorContext comparison_operator() throws RecognitionException {
		Comparison_operatorContext _localctx = new Comparison_operatorContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_comparison_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 66060288L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001/\u012d\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0001\u0000\u0005\u0000<\b\u0000\n\u0000\f\u0000"+
		"?\t\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001U\b\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002"+
		"c\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0003\u0006\u0090\b\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003"+
		"\b\u009b\b\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u00ac"+
		"\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u00b6\b\u000b\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003"+
		"\r\u00c2\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f"+
		"\u00ce\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010"+
		"\u00d4\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011"+
		"\u00da\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0005\u0012\u00df\b"+
		"\u0012\n\u0012\f\u0012\u00e2\t\u0012\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0005\u0013\u00e7\b\u0013\n\u0013\f\u0013\u00ea\t\u0013\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u00f1\b\u0014\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0003\u0015\u00fa\b\u0015\u0001\u0016\u0003\u0016\u00fd\b\u0016"+
		"\u0001\u0016\u0001\u0016\u0003\u0016\u0101\b\u0016\u0001\u0016\u0003\u0016"+
		"\u0104\b\u0016\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0005\u0018\u010b\b\u0018\n\u0018\f\u0018\u010e\t\u0018\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0005\u0019\u0113\b\u0019\n\u0019\f\u0019\u0116\t\u0019"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0003\u001a\u0120\b\u001a\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0003\u001b"+
		"\u0129\b\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0000\u0000\u001d\u0000"+
		"\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c"+
		"\u001e \"$&(*,.02468\u0000\u0006\u0001\u0000\u0006\u0007\u0001\u0000\b"+
		"\t\u0001\u0000\f\r\u0001\u0000\u000e\u000f\u0001\u0000!$\u0001\u0000\u0014"+
		"\u0019\u0136\u0000=\u0001\u0000\u0000\u0000\u0002T\u0001\u0000\u0000\u0000"+
		"\u0004V\u0001\u0000\u0000\u0000\u0006d\u0001\u0000\u0000\u0000\bl\u0001"+
		"\u0000\u0000\u0000\nv\u0001\u0000\u0000\u0000\f\u008f\u0001\u0000\u0000"+
		"\u0000\u000e\u0091\u0001\u0000\u0000\u0000\u0010\u009a\u0001\u0000\u0000"+
		"\u0000\u0012\u009c\u0001\u0000\u0000\u0000\u0014\u00ab\u0001\u0000\u0000"+
		"\u0000\u0016\u00b5\u0001\u0000\u0000\u0000\u0018\u00b7\u0001\u0000\u0000"+
		"\u0000\u001a\u00c1\u0001\u0000\u0000\u0000\u001c\u00c3\u0001\u0000\u0000"+
		"\u0000\u001e\u00cd\u0001\u0000\u0000\u0000 \u00d3\u0001\u0000\u0000\u0000"+
		"\"\u00d9\u0001\u0000\u0000\u0000$\u00db\u0001\u0000\u0000\u0000&\u00e3"+
		"\u0001\u0000\u0000\u0000(\u00f0\u0001\u0000\u0000\u0000*\u00f9\u0001\u0000"+
		"\u0000\u0000,\u0103\u0001\u0000\u0000\u0000.\u0105\u0001\u0000\u0000\u0000"+
		"0\u0107\u0001\u0000\u0000\u00002\u010f\u0001\u0000\u0000\u00004\u011f"+
		"\u0001\u0000\u0000\u00006\u0128\u0001\u0000\u0000\u00008\u012a\u0001\u0000"+
		"\u0000\u0000:<\u0003\u0002\u0001\u0000;:\u0001\u0000\u0000\u0000<?\u0001"+
		"\u0000\u0000\u0000=;\u0001\u0000\u0000\u0000=>\u0001\u0000\u0000\u0000"+
		">\u0001\u0001\u0000\u0000\u0000?=\u0001\u0000\u0000\u0000@A\u0003\f\u0006"+
		"\u0000AB\u0005\u0001\u0000\u0000BU\u0001\u0000\u0000\u0000CD\u0003\u000e"+
		"\u0007\u0000DE\u0005\u0001\u0000\u0000EU\u0001\u0000\u0000\u0000FG\u0003"+
		"\u0010\b\u0000GH\u0005\u0001\u0000\u0000HU\u0001\u0000\u0000\u0000IU\u0003"+
		"\u0004\u0002\u0000JU\u0003\u0006\u0003\u0000KU\u0003\b\u0004\u0000LU\u0003"+
		"\n\u0005\u0000MU\u0003\u0012\t\u0000NO\u0003\u0018\f\u0000OP\u0005\u0001"+
		"\u0000\u0000PU\u0001\u0000\u0000\u0000QR\u0003\u001c\u000e\u0000RS\u0005"+
		"\u0001\u0000\u0000SU\u0001\u0000\u0000\u0000T@\u0001\u0000\u0000\u0000"+
		"TC\u0001\u0000\u0000\u0000TF\u0001\u0000\u0000\u0000TI\u0001\u0000\u0000"+
		"\u0000TJ\u0001\u0000\u0000\u0000TK\u0001\u0000\u0000\u0000TL\u0001\u0000"+
		"\u0000\u0000TM\u0001\u0000\u0000\u0000TN\u0001\u0000\u0000\u0000TQ\u0001"+
		"\u0000\u0000\u0000U\u0003\u0001\u0000\u0000\u0000VW\u0005\u001a\u0000"+
		"\u0000WX\u0005\u0002\u0000\u0000XY\u00030\u0018\u0000YZ\u0005\u0003\u0000"+
		"\u0000Z[\u0005\u0004\u0000\u0000[\\\u0003\u0000\u0000\u0000\\b\u0005\u0005"+
		"\u0000\u0000]^\u0005\u001b\u0000\u0000^_\u0005\u0004\u0000\u0000_`\u0003"+
		"\u0000\u0000\u0000`a\u0005\u0005\u0000\u0000ac\u0001\u0000\u0000\u0000"+
		"b]\u0001\u0000\u0000\u0000bc\u0001\u0000\u0000\u0000c\u0005\u0001\u0000"+
		"\u0000\u0000de\u0005\u001c\u0000\u0000ef\u0005\u0002\u0000\u0000fg\u0003"+
		"0\u0018\u0000gh\u0005\u0003\u0000\u0000hi\u0005\u0004\u0000\u0000ij\u0003"+
		"\u0000\u0000\u0000jk\u0005\u0005\u0000\u0000k\u0007\u0001\u0000\u0000"+
		"\u0000lm\u0005\u001d\u0000\u0000mn\u0005\u0004\u0000\u0000no\u0003\u0000"+
		"\u0000\u0000op\u0005\u0005\u0000\u0000pq\u0005\u001c\u0000\u0000qr\u0005"+
		"\u0002\u0000\u0000rs\u00030\u0018\u0000st\u0005\u0003\u0000\u0000tu\u0005"+
		"\u0001\u0000\u0000u\t\u0001\u0000\u0000\u0000vw\u0005\u001e\u0000\u0000"+
		"wx\u0005\u0002\u0000\u0000xy\u0003\f\u0006\u0000yz\u0005\u0001\u0000\u0000"+
		"z{\u00030\u0018\u0000{|\u0005\u0001\u0000\u0000|}\u0003\u0010\b\u0000"+
		"}~\u0005\u0003\u0000\u0000~\u007f\u0005\u0004\u0000\u0000\u007f\u0080"+
		"\u0003\u0000\u0000\u0000\u0080\u0081\u0005\u0005\u0000\u0000\u0081\u000b"+
		"\u0001\u0000\u0000\u0000\u0082\u0083\u0003.\u0017\u0000\u0083\u0084\u0005"+
		"&\u0000\u0000\u0084\u0090\u0001\u0000\u0000\u0000\u0085\u0086\u0003.\u0017"+
		"\u0000\u0086\u0087\u0005&\u0000\u0000\u0087\u0088\u0005-\u0000\u0000\u0088"+
		"\u0089\u0005&\u0000\u0000\u0089\u0090\u0001\u0000\u0000\u0000\u008a\u008b"+
		"\u0003.\u0017\u0000\u008b\u008c\u0005&\u0000\u0000\u008c\u008d\u0005-"+
		"\u0000\u0000\u008d\u008e\u0003 \u0010\u0000\u008e\u0090\u0001\u0000\u0000"+
		"\u0000\u008f\u0082\u0001\u0000\u0000\u0000\u008f\u0085\u0001\u0000\u0000"+
		"\u0000\u008f\u008a\u0001\u0000\u0000\u0000\u0090\r\u0001\u0000\u0000\u0000"+
		"\u0091\u0092\u0005&\u0000\u0000\u0092\u0093\u0005-\u0000\u0000\u0093\u0094"+
		"\u0003 \u0010\u0000\u0094\u000f\u0001\u0000\u0000\u0000\u0095\u0096\u0005"+
		"&\u0000\u0000\u0096\u009b\u0007\u0000\u0000\u0000\u0097\u0098\u0005&\u0000"+
		"\u0000\u0098\u0099\u0007\u0001\u0000\u0000\u0099\u009b\u0003$\u0012\u0000"+
		"\u009a\u0095\u0001\u0000\u0000\u0000\u009a\u0097\u0001\u0000\u0000\u0000"+
		"\u009b\u0011\u0001\u0000\u0000\u0000\u009c\u009d\u0003.\u0017\u0000\u009d"+
		"\u009e\u0005&\u0000\u0000\u009e\u009f\u0005\u0002\u0000\u0000\u009f\u00a0"+
		"\u0003\u0016\u000b\u0000\u00a0\u00a1\u0005\u0003\u0000\u0000\u00a1\u00a2"+
		"\u0005\u0004\u0000\u0000\u00a2\u00a3\u0003\u0000\u0000\u0000\u00a3\u00a4"+
		"\u0003\u0014\n\u0000\u00a4\u00a5\u0005\u0005\u0000\u0000\u00a5\u0013\u0001"+
		"\u0000\u0000\u0000\u00a6\u00a7\u0005\n\u0000\u0000\u00a7\u00a8\u0003 "+
		"\u0010\u0000\u00a8\u00a9\u0005\u0001\u0000\u0000\u00a9\u00ac\u0001\u0000"+
		"\u0000\u0000\u00aa\u00ac\u0001\u0000\u0000\u0000\u00ab\u00a6\u0001\u0000"+
		"\u0000\u0000\u00ab\u00aa\u0001\u0000\u0000\u0000\u00ac\u0015\u0001\u0000"+
		"\u0000\u0000\u00ad\u00ae\u0003.\u0017\u0000\u00ae\u00af\u0005&\u0000\u0000"+
		"\u00af\u00b6\u0001\u0000\u0000\u0000\u00b0\u00b1\u0003.\u0017\u0000\u00b1"+
		"\u00b2\u0005&\u0000\u0000\u00b2\u00b3\u0005\u000b\u0000\u0000\u00b3\u00b4"+
		"\u0003\u0016\u000b\u0000\u00b4\u00b6\u0001\u0000\u0000\u0000\u00b5\u00ad"+
		"\u0001\u0000\u0000\u0000\u00b5\u00b0\u0001\u0000\u0000\u0000\u00b6\u0017"+
		"\u0001\u0000\u0000\u0000\u00b7\u00b8\u0005&\u0000\u0000\u00b8\u00b9\u0005"+
		"\u0002\u0000\u0000\u00b9\u00ba\u0003\u001a\r\u0000\u00ba\u00bb\u0005\u0003"+
		"\u0000\u0000\u00bb\u0019\u0001\u0000\u0000\u0000\u00bc\u00c2\u0003 \u0010"+
		"\u0000\u00bd\u00be\u0003 \u0010\u0000\u00be\u00bf\u0005\u000b\u0000\u0000"+
		"\u00bf\u00c0\u0003\u001a\r\u0000\u00c0\u00c2\u0001\u0000\u0000\u0000\u00c1"+
		"\u00bc\u0001\u0000\u0000\u0000\u00c1\u00bd\u0001\u0000\u0000\u0000\u00c2"+
		"\u001b\u0001\u0000\u0000\u0000\u00c3\u00c4\u0005%\u0000\u0000\u00c4\u00c5"+
		"\u0005\u0002\u0000\u0000\u00c5\u00c6\u0003\u001e\u000f\u0000\u00c6\u00c7"+
		"\u0005\u0003\u0000\u0000\u00c7\u001d\u0001\u0000\u0000\u0000\u00c8\u00ce"+
		"\u0003 \u0010\u0000\u00c9\u00ca\u0003 \u0010\u0000\u00ca\u00cb\u0005\f"+
		"\u0000\u0000\u00cb\u00cc\u0003\u001e\u000f\u0000\u00cc\u00ce\u0001\u0000"+
		"\u0000\u0000\u00cd\u00c8\u0001\u0000\u0000\u0000\u00cd\u00c9\u0001\u0000"+
		"\u0000\u0000\u00ce\u001f\u0001\u0000\u0000\u0000\u00cf\u00d4\u0003$\u0012"+
		"\u0000\u00d0\u00d4\u00030\u0018\u0000\u00d1\u00d4\u0003\"\u0011\u0000"+
		"\u00d2\u00d4\u0003\u0018\f\u0000\u00d3\u00cf\u0001\u0000\u0000\u0000\u00d3"+
		"\u00d0\u0001\u0000\u0000\u0000\u00d3\u00d1\u0001\u0000\u0000\u0000\u00d3"+
		"\u00d2\u0001\u0000\u0000\u0000\u00d4!\u0001\u0000\u0000\u0000\u00d5\u00da"+
		"\u0005)\u0000\u0000\u00d6\u00d7\u0005)\u0000\u0000\u00d7\u00d8\u0005\f"+
		"\u0000\u0000\u00d8\u00da\u0003\"\u0011\u0000\u00d9\u00d5\u0001\u0000\u0000"+
		"\u0000\u00d9\u00d6\u0001\u0000\u0000\u0000\u00da#\u0001\u0000\u0000\u0000"+
		"\u00db\u00e0\u0003&\u0013\u0000\u00dc\u00dd\u0007\u0002\u0000\u0000\u00dd"+
		"\u00df\u0003&\u0013\u0000\u00de\u00dc\u0001\u0000\u0000\u0000\u00df\u00e2"+
		"\u0001\u0000\u0000\u0000\u00e0\u00de\u0001\u0000\u0000\u0000\u00e0\u00e1"+
		"\u0001\u0000\u0000\u0000\u00e1%\u0001\u0000\u0000\u0000\u00e2\u00e0\u0001"+
		"\u0000\u0000\u0000\u00e3\u00e8\u0003(\u0014\u0000\u00e4\u00e5\u0007\u0003"+
		"\u0000\u0000\u00e5\u00e7\u0003(\u0014\u0000\u00e6\u00e4\u0001\u0000\u0000"+
		"\u0000\u00e7\u00ea\u0001\u0000\u0000\u0000\u00e8\u00e6\u0001\u0000\u0000"+
		"\u0000\u00e8\u00e9\u0001\u0000\u0000\u0000\u00e9\'\u0001\u0000\u0000\u0000"+
		"\u00ea\u00e8\u0001\u0000\u0000\u0000\u00eb\u00ec\u0003*\u0015\u0000\u00ec"+
		"\u00ed\u0005\u0010\u0000\u0000\u00ed\u00ee\u0003*\u0015\u0000\u00ee\u00f1"+
		"\u0001\u0000\u0000\u0000\u00ef\u00f1\u0003*\u0015\u0000\u00f0\u00eb\u0001"+
		"\u0000\u0000\u0000\u00f0\u00ef\u0001\u0000\u0000\u0000\u00f1)\u0001\u0000"+
		"\u0000\u0000\u00f2\u00fa\u0005&\u0000\u0000\u00f3\u00fa\u0003,\u0016\u0000"+
		"\u00f4\u00f5\u0005\u0002\u0000\u0000\u00f5\u00f6\u0003$\u0012\u0000\u00f6"+
		"\u00f7\u0005\u0003\u0000\u0000\u00f7\u00fa\u0001\u0000\u0000\u0000\u00f8"+
		"\u00fa\u0003\u0018\f\u0000\u00f9\u00f2\u0001\u0000\u0000\u0000\u00f9\u00f3"+
		"\u0001\u0000\u0000\u0000\u00f9\u00f4\u0001\u0000\u0000\u0000\u00f9\u00f8"+
		"\u0001\u0000\u0000\u0000\u00fa+\u0001\u0000\u0000\u0000\u00fb\u00fd\u0007"+
		"\u0002\u0000\u0000\u00fc\u00fb\u0001\u0000\u0000\u0000\u00fc\u00fd\u0001"+
		"\u0000\u0000\u0000\u00fd\u00fe\u0001\u0000\u0000\u0000\u00fe\u0104\u0005"+
		"\'\u0000\u0000\u00ff\u0101\u0007\u0002\u0000\u0000\u0100\u00ff\u0001\u0000"+
		"\u0000\u0000\u0100\u0101\u0001\u0000\u0000\u0000\u0101\u0102\u0001\u0000"+
		"\u0000\u0000\u0102\u0104\u0005(\u0000\u0000\u0103\u00fc\u0001\u0000\u0000"+
		"\u0000\u0103\u0100\u0001\u0000\u0000\u0000\u0104-\u0001\u0000\u0000\u0000"+
		"\u0105\u0106\u0007\u0004\u0000\u0000\u0106/\u0001\u0000\u0000\u0000\u0107"+
		"\u010c\u00032\u0019\u0000\u0108\u0109\u0005\u0011\u0000\u0000\u0109\u010b"+
		"\u00032\u0019\u0000\u010a\u0108\u0001\u0000\u0000\u0000\u010b\u010e\u0001"+
		"\u0000\u0000\u0000\u010c\u010a\u0001\u0000\u0000\u0000\u010c\u010d\u0001"+
		"\u0000\u0000\u0000\u010d1\u0001\u0000\u0000\u0000\u010e\u010c\u0001\u0000"+
		"\u0000\u0000\u010f\u0114\u00034\u001a\u0000\u0110\u0111\u0005\u0012\u0000"+
		"\u0000\u0111\u0113\u00034\u001a\u0000\u0112\u0110\u0001\u0000\u0000\u0000"+
		"\u0113\u0116\u0001\u0000\u0000\u0000\u0114\u0112\u0001\u0000\u0000\u0000"+
		"\u0114\u0115\u0001\u0000\u0000\u0000\u01153\u0001\u0000\u0000\u0000\u0116"+
		"\u0114\u0001\u0000\u0000\u0000\u0117\u0120\u00036\u001b\u0000\u0118\u0120"+
		"\u0003\u0018\f\u0000\u0119\u011a\u0005\u0013\u0000\u0000\u011a\u0120\u0003"+
		"4\u001a\u0000\u011b\u011c\u0005\u0002\u0000\u0000\u011c\u011d\u00030\u0018"+
		"\u0000\u011d\u011e\u0005\u0003\u0000\u0000\u011e\u0120\u0001\u0000\u0000"+
		"\u0000\u011f\u0117\u0001\u0000\u0000\u0000\u011f\u0118\u0001\u0000\u0000"+
		"\u0000\u011f\u0119\u0001\u0000\u0000\u0000\u011f\u011b\u0001\u0000\u0000"+
		"\u0000\u01205\u0001\u0000\u0000\u0000\u0121\u0122\u0003$\u0012\u0000\u0122"+
		"\u0123\u00038\u001c\u0000\u0123\u0124\u0003$\u0012\u0000\u0124\u0129\u0001"+
		"\u0000\u0000\u0000\u0125\u0129\u0005\u001f\u0000\u0000\u0126\u0129\u0005"+
		" \u0000\u0000\u0127\u0129\u0005&\u0000\u0000\u0128\u0121\u0001\u0000\u0000"+
		"\u0000\u0128\u0125\u0001\u0000\u0000\u0000\u0128\u0126\u0001\u0000\u0000"+
		"\u0000\u0128\u0127\u0001\u0000\u0000\u0000\u01297\u0001\u0000\u0000\u0000"+
		"\u012a\u012b\u0007\u0005\u0000\u0000\u012b9\u0001\u0000\u0000\u0000\u0016"+
		"=Tb\u008f\u009a\u00ab\u00b5\u00c1\u00cd\u00d3\u00d9\u00e0\u00e8\u00f0"+
		"\u00f9\u00fc\u0100\u0103\u010c\u0114\u011f\u0128";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}