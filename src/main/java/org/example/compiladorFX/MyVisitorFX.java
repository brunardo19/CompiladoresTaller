package org.example.compiladorFX;

import java.util.*;

public class MyVisitorFX extends gBaseVisitor<Object> {

    public MyVisitorFX(String textout, Map<String, Symbol> symbolTableGlobal, Stack<Map<String, Symbol>> symbolTableStack) {
        this.textout = textout;
        this.symbolTableGlobal = symbolTableGlobal;
        this.symbolTableStack = symbolTableStack;
    }

    public String getTextout() {
        return textout;
    }

    private String textout;

    public MyVisitorFX(String textout) {
        this.textout = textout;
    }

    // Tabla de símbolos
    Map<String, Symbol> symbolTableGlobal = new HashMap<>();
    Map<String, Symbol> symbolTableLocal = new HashMap<>();
    Stack<Map<String, Symbol>> symbolTableStack = new Stack<>();

    public Object getTableValue(String id) {
        for (int i = symbolTableStack.size() - 1; i >= 0; i--) {  // Search local scopes first
            Map<String, Symbol> currentScope = symbolTableStack.get(i);
            if (currentScope.containsKey(id)) {
                return currentScope.get(id).value;
            }
        }
        if (symbolTableGlobal.containsKey(id)) {  // Then check global
            return symbolTableGlobal.get(id).value;
        }
        textout += "\n" + ("variable " + id + " no declarada");
        return null;
    }

    Symbol getTableSymbol(String id) {
        for (int i = symbolTableStack.size() - 1; i >= 0; i--) {  // Search local scopes first
            Map<String, Symbol> currentScope = symbolTableStack.get(i);
            if (currentScope.containsKey(id)) {
                return currentScope.get(id);
            }
        }
        if (symbolTableGlobal.containsKey(id)) {  // Then check global
            return symbolTableGlobal.get(id);
        }
        textout += "\n" + ("variable " + id + " no declarada");
        return null;
    }


    String getTableType(String id) {
        for (int i = symbolTableStack.size() - 1; i >= 0; i--) {  // Search local scopes first
            Map<String, Symbol> currentScope = symbolTableStack.get(i);
            if (currentScope.containsKey(id)) {
                return currentScope.get(id).type;
            }
        }
        if (symbolTableGlobal.containsKey(id)) {  // Then check global
            return symbolTableGlobal.get(id).type;
        }
        textout += "\n" + ("variable " + id + " no declarada");
        return null;
    }


    void declareInTable(String id, String type, Object value) {
        // Local variable
        Map<String, Symbol> currentScope = symbolTableStack.peek();
        if (currentScope.containsKey(id)) { // Check both local and global for redeclaration
            textout += "\n" + ("Variable " + id + " already declared in this scope or globally.");
        } else {
            currentScope.put(id, new Symbol(id, type, value));
        }

    }

    void updateTable(String id, Object value) {
        for (int i = symbolTableStack.size() - 1; i >= 0; i--) {
            Map<String, Symbol> currentScope = symbolTableStack.get(i);
            if (currentScope.containsKey(id)) {
                currentScope.get(id).value = value;
                return;
            }
        }
        if (symbolTableGlobal.containsKey(id)) {
            symbolTableGlobal.get(id).value = value;
            return;
        }
        textout += "\n" + ("variable " + id + " no declarada");

    }

    public void enterScope() {
        textout += "\n" + ("Entering Scope");
        symbolTableStack.push(new HashMap<>()); // New scope, doesn't inherit by default
    }


    public void exitScope() {
        if (symbolTableStack.size() > 1) { // prevent popping off the global scope accidentally
            textout += "\n" + ("Exiting Scope");
            Map<String, Symbol> leavingScope = symbolTableStack.pop();

            // Print or process leaving variables if you need to.
            System.out.println("Variables leaving scope: " + leavingScope);


        } else {
            symbolTableGlobal = symbolTableStack.pop();
            textout += "\nCannot exit global scope";
        }

    }

    // Clase para representar símbolos
    static class Symbol {
        String name;
        String type;
        Object value;

        Symbol(String name, String type) {
            this.name = name;
            this.type = type;
        }

        Symbol(String name, String type, Object value) {
            this.name = name;
            this.type = type;
            this.value = value;
        }

        @Override
        public String toString() {
            if (value != null) {
                return "{Variable \"" + name + "\" de tipo " + type + " con valor = " + value + "}";
            } else {
                return "{Variable \"" + name + "\" de tipo " + type + "}";
            }
        }
    }

    // New FunctionSymbol class:
    static class FunctionSymbol extends Symbol {
        List<Symbol> parameters;
        //Object returnValue;
        gParser.ProgramContext program;
        gParser.Return_expressionContext returnExpression;


        public FunctionSymbol(String name, String type, List<Symbol> parameters, gParser.ProgramContext program, gParser.Return_expressionContext returnExpression) {
            super(name, type);
            this.parameters = parameters;
            this.program = program;
            this.returnExpression = returnExpression;
        }


        public String toString() {
            return "{Funcion \"" + name + "\" que devuelve tipo " + type + " con parametros = " + parameters + "}";
        }

    }

    boolean isDefined(String id) {
        // 1. Check local scopes (from innermost to outermost)
        for (int i = symbolTableStack.size() - 1; i >= 0; i--) {
            if (symbolTableStack.get(i).containsKey(id)) {
                return true;
            }
        }

        // 2. If not found locally, check the global scope
        return symbolTableGlobal.containsKey(id);
    }

    boolean isLocallyDefined(String id) {
        // 1. Check local scope
        Map<String, Symbol> currentScope = symbolTableStack.peek();
        return currentScope.containsKey(id);
    }

    // Método para verificar si una variable es del tipo esperado
    boolean checkType(String id, String expectedType) {
        return symbolTableStack.peek().get(id).type.equals(expectedType);
    }

    // Método para obtener el valor de una variable
    Object getValue(String id) {
        return symbolTableStack.peek().get(id).value;
    }

    // Método para evaluar una condición booleana
    Object evalCondition(Object left, String operator, Object right) {
        if (left instanceof Number && right instanceof Number) {
            double leftVal = ((Number) left).doubleValue();
            double rightVal = ((Number) right).doubleValue();
            switch (operator) {
                case "==":
                    return leftVal == rightVal;
                case "!=":
                    return leftVal != rightVal;
                case "<":
                    return leftVal < rightVal;
                case ">":
                    return leftVal > rightVal;
                case "<=":
                    return leftVal <= rightVal;
                case ">=":
                    return leftVal >= rightVal;
                default:
                    return false; // Should not happen in correct grammar
            }
        } else if (left instanceof Boolean && right instanceof Boolean) {
            boolean leftb = ((Boolean) left).booleanValue();
            boolean rightb = ((Boolean) right).booleanValue();
            switch (operator) {
                case "==":
                    return leftb == rightb;
                case "!=":
                    return leftb != rightb;
                default:
                    return false; // Should not happen
            }
        } else {
            textout += "\n" + ("Error de tipos en comparacion : " + left + " " + operator + " " + right);
            return false; // Or throw an exception
        }
    }


    // Método para evaluar tipo en asignaciones
    boolean checkAssignmentCompatibility(String id, Object value) {
        if (!isDefined(id)) {
            return true; // Handle undefined id case first
        }

        //String varType = symbolTableStack.peek().get(id).type;
        String varType = getTableType(id);

        switch (varType) {
            case "int":
                if (!(value instanceof Integer)) {
                    if (value instanceof Number && ((Number) value).doubleValue() == Math.rint(((Number) value).doubleValue())) {
                        return true; // Allow integer-valued doubles/floats to int
                    } else {
                        textout += "\n" + ("Error de tipo: No se puede asignar un valor no entero a variable int '" + id + "'");
                        return false;
                    }
                }
                return true; // Value is an Integer, so it's compatible

            case "double":
                if (!(value instanceof Double || value instanceof Integer)) {
                    textout += "\n" + ("Error de tipo: No se puede asignar un valor no numerico a variable double '" + id + "'");
                    return false;
                }
                return true; // Value is a Double or Integer, so it's compatible

            case "string":
                if (!(value instanceof String)) {
                    textout += "\n" + ("Error de tipo: No se puede asignar un valor no string a variable string '" + id + "'");
                    return false;
                }
                return true;

            case "boolean":
                if (!(value instanceof Boolean)) {
                    textout += "\n" + ("Error de tipo: No se puede asignar un valor no booleano a variable booleana '" + id + "'");
                    return false;
                }
                return true;

            default:  // Handle other types or the default case as needed
                System.out.println("checkAssignmentCompatibility - tipo recibido invalido");
                return false;
        }
    }

    // Metodo para evaluar tipo en declaraciones
    boolean checkDeclarationCompatibility(String id, String type, Object value) {
        if (isLocallyDefined(id)) {
            textout += "\n" + ("Error: la variable '" + id + "' ya está declarada.");
            return false; // Variable already declared
        }

        // Now check type compatibility as before, but handle null for default values
        switch (type) {
            case "int":
                if (value != null && !(value instanceof Integer)) {
                    if (value instanceof Number && ((Number) value).doubleValue() == Math.rint(((Number) value).doubleValue())) {
                        return true; // Integer-valued doubles/floats allowed
                    } else {
                        textout += "\n" + ("Error de tipo: Valor inicial no entero para variable int '" + id + "'");
                        return false;
                    }
                }
                return true;

            case "double":
                if (value != null && !(value instanceof Double || value instanceof Integer)) {
                    textout += "\n" + ("Error de tipo: Valor inicial no numérico para variable double '" + id + "'");
                    return false;
                }
                return true;

            case "string":
                if (value != null && !(value instanceof String)) {
                    textout += "\n" + ("Error de tipo: Valor inicial no string para variable string '" + id + "'");
                    return false;
                }
                return true;

            case "boolean":
                if (value != null && !(value instanceof Boolean)) {
                    textout += "\n" + ("Error de tipo: Valor inicial no booleano para variable boolean '" + id + "'");
                    return false;
                }
                return true;

            default:
                System.out.println("checkDeclarationCompatibility - tipo recibido inválido"); // Or handle other types
                return false;
        }
    }

    boolean checkIdCompatibility(String a, String b) {
        String varTypeA = symbolTableStack.peek().get(a).type;
        String varTypeB = symbolTableStack.peek().get(b).type;

        return Objects.equals(varTypeA, varTypeB);
    }

    @Override
    public Object visitProgram(gParser.ProgramContext ctx) {
        enterScope(); // Enter the global scope
        //System.out.println(symbolTableStack.peek());
        for (gParser.StatementContext stmt : ctx.statement()) {
            visit(stmt);
        }
        exitScope(); // Exit the global scope
        return null;
    }

    @Override
    public Object visitVariable_declaration(gParser.Variable_declarationContext ctx) {
        String id = ctx.ID(0).getText();
        String type = ctx.type().getText();

        if (isLocallyDefined(id)) {
            textout += "\n" + ("Error: Variable '" + id + "' ya declarada.");
        } else {
            if (ctx.ID(1) != null) {
                String id2 = ctx.ID(1).getText();
                if (!isDefined(id2)) {
                    textout += "\n" + ("Error: Variable '" + id2 + "' no declarada.");
                    return null;
                } else {
                    String varType2 = getTableType(id2);
                    Object value2 = getTableValue(id2);
                    if (checkDeclarationCompatibility(id, type, value2)) {
                        declareInTable(id, type, value2);
                        //symbolTableStack.peek().put(id, new Symbol(id, type, value2));
                        textout += "\n" + ("Declaracion de variable: " + symbolTableStack.peek().get(id));
                    }
                    return null;
                }
            }
            if (ctx.expression() != null) {// Declaration with initialization
                Object value = visit(ctx.expression());
                if (checkDeclarationCompatibility(id, type, value)) {
                    declareInTable(id, type, value);
                    //symbolTableStack.peek().put(id, new Symbol(id, type, value));
                    textout += "\n" + ("Declaracion de variable: " + symbolTableStack.peek().get(id));
                }
                return null;
            }
            // Declaration without initialization
            //symbolTableStack.peek().put(id, new Symbol(id, type));
            declareInTable(id, type, null);
            textout += "\n" + ("Declaracion de variable: " + symbolTableStack.peek().get(id));

        }
        return null;
    }

    @Override
    public Object visitString_expression(gParser.String_expressionContext ctx) {
        String text = ctx.STRING().getText();
        String value = text.substring(1, text.length() - 1);

        StringBuilder sb = new StringBuilder(value);
        if (ctx.string_expression() != null) {
            sb.append(visit(ctx.string_expression()));
        }
        return sb.toString();
    }

    @Override
    public Object visitPrint_params(gParser.Print_paramsContext ctx) {
        // Evaluar la primera expresión
        Object value = visit(ctx.getChild(0)); // Asumiendo que el primer hijo es la expresión

        // Si hay más parámetros para procesar
        if (ctx.getChildCount() > 1) { // Verificar si hay más hijos
            // Evaluar el resto de los parámetros
            Object nextValue = visit(ctx.print_params());

            // Concatenar o sumar según el tipo
            if (value instanceof String) {
                // Si el primer valor es una cadena, concatenar el segundo valor como cadena
                value = (String) value + nextValue.toString();
            } else if (nextValue instanceof String) {
                // Si el segundo valor es una cadena, concatenar el primero como cadena
                value = value.toString() + (String) nextValue;
            } else if (value instanceof Number && nextValue instanceof Number) {
                // Si ambos son numéricos, sumarlos
                if (value instanceof Double || nextValue instanceof Double) {
                    value = ((Number) value).doubleValue() + ((Number) nextValue).doubleValue();
                } else {
                    value = ((Number) value).intValue() + ((Number) nextValue).intValue();
                }
            } else {
                throw new RuntimeException("Tipos de datos incompatibles para la operación.");
            }
        }

        return value;
    }

    @Override
    public Object visitVariable_update(gParser.Variable_updateContext ctx) {
        String id = ctx.ID().getText();

        // Retrieve the symbol
        Symbol symbol = getTableSymbol(id);

        if (symbol == null) {
            textout += "\n" + ("Error: Variable '" + id + "' no declarada.");
            return null;
        }

        Number currentValue = (Number) symbol.value;

        // Determine the operation
        String operator = ctx.getChild(1).getText();

        switch (operator) {
            case "++":
                symbol.value = currentValue.doubleValue() + 1;
                break;

            case "--":
                symbol.value = currentValue.doubleValue() - 1;
                break;

            case "+=":
                Object exprValueAdd = visit(ctx.math_expression());
                if (exprValueAdd instanceof Number) {
                    symbol.value = currentValue.doubleValue() + ((Number) exprValueAdd).doubleValue();
                } else {
                    textout += "\n" + ("Error: La expresión en la asignación += debe ser numérica.");
                    return null;
                }
                break;

            case "-=":
                Object exprValueSubtract = visit(ctx.math_expression());
                if (exprValueSubtract instanceof Number) {
                    symbol.value = currentValue.doubleValue() - ((Number) exprValueSubtract).doubleValue();
                } else {
                    textout += "\n" + ("Error: La expresión en la asignación -= debe ser numérica.");
                    return null;
                }
                break;

            default:
                textout += "\n" + ("Error: Operador '" + operator + "' no soportado.");
                return null;
        }

        // Output the updated variable
        textout += "\n" + ("Actualización de variable: " + symbol);
        return null;
    }

    @Override
    public Object visitVariable_assign(gParser.Variable_assignContext ctx) {
        String id = ctx.ID().getText();
        Object value = visit(ctx.expression()); // Evaluate the right-hand side expression

        if (!isDefined(id)) {
            textout += "\n" + ("Error: Variable '" + id + "' not declared.");
        } else {
            if (checkAssignmentCompatibility(id, value)) {
                updateTable(id, value);
                //symbolTableStack.peek().get(id).value = value;
                textout += "\n" + ("Asignacion de variable: " + getTableSymbol(id).name + " = " + getTableValue(id));
            }
        }
        return null;
    }

    @Override
    public Object visitIf_statement(gParser.If_statementContext ctx) {
        Boolean condition = (Boolean) visit(ctx.logical_operation());
        System.out.println(condition);
        if (condition) {
            visit(ctx.program(0)); // Execute the 'if' block
        } else if (ctx.program().size() > 1) { // Check if there's an 'else' block
            visit(ctx.program(1)); // Execute the 'else' block
        }
        return null; // If-statements don't have a return value
    }

    @Override
    public Object visitWhile_loop(gParser.While_loopContext ctx) {
        while ((Boolean) visit(ctx.logical_operation())) {
            visit(ctx.program()); // Execute the loop body
        }
        ;
        return null; // Do-while loops don't have a return value
    }

    @Override
    public Object visitDo_while(gParser.Do_whileContext ctx) {
        do {
            visit(ctx.program()); // Execute the loop body
        } while ((Boolean) visit(ctx.logical_operation()));
        return null; // Do-while loops don't have a return value
    }

    @Override
    public Object visitFor_loop(gParser.For_loopContext ctx) {
        enterScope();
        Object declaration = visit(ctx.variable_declaration()); // Initialization


        while ((Boolean) visit(ctx.logical_operation())) {
            visit(ctx.program()); // Execute the loop body
            visit(ctx.variable_update()); // Update expression
        }
        exitScope();

        return null; // For loops don't have a return value
    }

    @Override
    public Object visitMath_expression(gParser.Math_expressionContext ctx) {
        Double value = (Double) visit(ctx.term(0));
        for (int i = 1; i < ctx.term().size(); i++) {
            Double termValue = (Double) visit(ctx.term(i));
            if (ctx.getChild(2 * i - 1).getText().equals("+")) {
                value += termValue;
            } else { // Must be '-'
                value -= termValue;
            }
        }
        return value;
    }

    @Override
    public Object visitTerm(gParser.TermContext ctx) {
        Double value = (Double) visit(ctx.power_expr(0));
        for (int i = 1; i < ctx.power_expr().size(); i++) {
            Double factorValue = (Double) visit(ctx.power_expr(i));
            if (ctx.getChild(2 * i - 1).getText().equals("*")) {
                value *= factorValue;
            } else { // Must be '/'
                value /= factorValue;
            }
        }
        return value;
    }

    @Override
    public Object visitPower_expr(gParser.Power_exprContext ctx) {
        double value = ((Number) visit(ctx.factor(0))).doubleValue();
        if (ctx.factor().size() > 1) { // If there's a power operation
            double exponent = ((Number) visit(ctx.factor(1))).doubleValue();
            value = Math.pow(value, exponent);
        }
        return value;
    }

    @Override
    public Object visitFactor(gParser.FactorContext ctx) {
        if (ctx.ID() != null) { // Factor is an ID (variable)
            String id = ctx.ID().getText();
            if (!isDefined(id)) {
                textout += "\n" + ("Error: Variable '" + id + "' no declarada.");
                return 0.0;
            } else {
                //Object val = symbolTableStack.peek().get(id).value;
                Object val = getTableValue(id);
                if (val == null) {
                    textout += "\n" + ("Error: Variable '" + id + "' no inicializada.");
                    return 0.0;
                } else if (val instanceof Double) {
                    return (Double) val;
                } else if (val instanceof Integer) {
                    return ((Integer) val).doubleValue();
                } else {
                    textout += "\n" + ("Error: Variable '" + id + "' no es una variable numerica");
                    return 0.0;
                }
            }
        } else if (ctx.number() != null) { // Factor is a number
            return visit(ctx.number());
        } else { // Factor is a parenthesized expression
            return visit(ctx.math_expression());
        }
    }

    @Override
    public Object visitNumber(gParser.NumberContext ctx) {
        if (ctx.INT() != null) {
            if (ctx.getChild(0).getText().equals("-")) {
                return -Integer.parseInt(ctx.INT().getText());
            } else {
                return Integer.parseInt(ctx.INT().getText());
            }
        } else { //Es double
            if (ctx.getChild(0).getText().equals("-")) {
                return -Double.parseDouble(ctx.DOUBLE().getText());
            } else {
                return Double.parseDouble(ctx.DOUBLE().getText());
            }
        }
    }

    @Override
    public Object visitType(gParser.TypeContext ctx) {
        return ctx.getText(); // Return the type as a String ("int" or "double")
    }

    @Override
    public Object visitLogical_operation(gParser.Logical_operationContext ctx) {
        Boolean value = (Boolean) visit(ctx.logical_term(0));
        for (int i = 1; i < ctx.logical_term().size(); i++) {
            Boolean termValue = (Boolean) visit(ctx.logical_term(i));
            value = value || termValue; // Apply the || operator
        }
        return value;
    }

    @Override
    public Object visitLogical_term(gParser.Logical_termContext ctx) {
        Boolean value = (Boolean) visit(ctx.logical_factor(0));
        for (int i = 1; i < ctx.logical_factor().size(); i++) {
            Boolean factorValue = (Boolean) visit(ctx.logical_factor(i));
            value = value && factorValue; // Apply the && operator
        }
        return value;
    }

    @Override
    public Object visitLogical_factor(gParser.Logical_factorContext ctx) {
        if (ctx.boolean_() != null) {
            return visit(ctx.boolean_());
        } else if (ctx.logical_factor() != null) { // Negation (!)
            Boolean value = (Boolean) visit(ctx.logical_factor());
            return !value;
        } else { // Parenthesized logical operation
            return visit(ctx.logical_operation());
        }
    }

    @Override
    public Object visitBoolean(gParser.BooleanContext ctx) {
        if (ctx.TRUE() != null) {
            return true;
        } else if (ctx.FALSE() != null) {
            return false;
        } else if (ctx.ID() != null) {
            String id = ctx.ID().getText();
            if (!isDefined(id)) {
                textout += "\n" + ("Error: Variable '" + id + "' no declarada.");
                return null;
            } else {
                Object val = symbolTableStack.peek().get(id).value;
                if (val == null) {
                    textout += "\n" + ("Error: Variable '" + id + "' no inicializada.");
                    return null;
                } else if (val instanceof Boolean) {
                    return (Boolean) val;
                } else {
                    textout += "\n" + ("Error: Variable '" + id + "' no es una variable booleana");
                    return null;
                }
            }
        } else if (ctx.math_expression() != null) { // Comparison between math expressions
            Double left = (Double) visit(ctx.math_expression(0));
            Double right = (Double) visit(ctx.math_expression(1));
            String operator = ctx.comparison_operator().getText();
            System.out.println(left + " " + operator + " " + right);
            return evalCondition(left, operator, right);
        }
        return false;
    }

    @Override
    public Object visitPrint_call(gParser.Print_callContext ctx) {
        // Evaluar los parámetros de impresión
        Object result = visit(ctx.print_params());

        // Imprimir el resultado
        System.out.println(result);

        // Devuelve null ya que la función print no tiene un valor de retorno
        return null;
    }


    @Override
    public Object visitFunction_declaration(gParser.Function_declarationContext ctx) {
        String functionName = ctx.ID().getText();
        String returnType = ctx.type().getText();

        // Handle parameters
        List<Symbol> parameters = new ArrayList<>();
        if (ctx.params() != null) {
            parameters = visitParams(ctx.params()); // Create a helper method for params
        }

        // Store function information in the symbol table
        FunctionSymbol functionSymbol;

        functionSymbol = new FunctionSymbol(functionName, returnType, parameters, ctx.program(), ctx.return_expression());

        symbolTableGlobal.put(functionName, functionSymbol); // Global scope for functions

        return null;  // Function declaration doesn't return a value itself
    }

    public List<Symbol> visitParams(gParser.ParamsContext ctx) {
        List<Symbol> params = new ArrayList<>();
        String paramType = ctx.type().getText();
        String paramName = ctx.ID().getText();
        params.add(new Symbol(paramName, paramType));
        if (ctx.params() != null) {
            params.addAll((List<Symbol>) visit(ctx.params()));
        }
        return params;
    }

    public List<Object> visitParams_call(gParser.Params_callContext ctx) {
        List<Object> params = new ArrayList<>();
        Object paramValue = visit(ctx.expression());
        params.add(paramValue);
        if (ctx.params_call() != null) {
            params.addAll((List<Object>) visit(ctx.params_call()));
        }
        return params;
    }

    public boolean checkParametersCompatibility(List<Symbol> param, List<Object> args) {
        for (int i = 0; i < param.size(); i++) {
            String type = param.get(i).type;
            Object value = args.get(i);
            switch (type) {
                case "int":
                    if (!(value instanceof Integer)) {
                        return value instanceof Number && ((Number) value).doubleValue() == Math.rint(((Number) value).doubleValue()); // Allow integer-valued doubles/floats to int
                    }
                    return true; // Value is an Integer, so it's compatible

                case "double":
                    return value instanceof Double || value instanceof Integer;// Value is a Double or Integer, so it's compatible

                case "string":
                    return value instanceof String;

                case "boolean":
                    return value instanceof Boolean;
            }
        }
        return true;
    }


    public boolean checkReturnCompatibility(String type, Object value) {

        switch (type) {
            case "int":
                if (!(value instanceof Integer)) {
                    return value instanceof Number && ((Number) value).doubleValue() == Math.rint(((Number) value).doubleValue()); // Allow integer-valued doubles/floats to int
                }
                return true; // Value is an Integer, so it's compatible

            case "double":
                return value instanceof Double || value instanceof Integer;// Value is a Double or Integer, so it's compatible

            case "string":
                return value instanceof String;

            case "boolean":
                return value instanceof Boolean;
        }

        return true;
    }

    @Override
    public Object visitFunction_call(gParser.Function_callContext ctx) {
        Object returnValue = null;
        String functionName = ctx.ID().getText();

        // 1. Check if the function is defined
        if (!isDefined(functionName)) {
            throw new RuntimeException("Function '" + functionName + "' not defined.");
        }

        FunctionSymbol functionSymbol = (FunctionSymbol) symbolTableGlobal.get(functionName);
        List<Symbol> param = functionSymbol.parameters;
        List<Object> args = (List<Object>) visit(ctx.params_call());

        if (checkParametersCompatibility(param, args)) {
            enterScope(); // Entrar en el ámbito de la función
            for (int i = 0; i < param.size(); i++) {
                String type = param.get(i).type;
                String id = param.get(i).name;
                Object value = args.get(i);
                symbolTableStack.peek().put(id, new Symbol(id, type, value));
            }

            MyVisitorFX visitor = new MyVisitorFX("");

            // Aquí se ejecutan las declaraciones de la función
            for (gParser.StatementContext stmt : functionSymbol.program.statement()) {
                visit(stmt);
            }

            // Evaluar el valor de retorno
            if (functionSymbol.returnExpression != null) {
                returnValue = visit(functionSymbol.returnExpression.expression());
                System.out.println("STACK:" + returnValue);
                String type = functionSymbol.type;
                if (!checkReturnCompatibility(type, returnValue)) {
                    throw new RuntimeException("Error: In function " + functionSymbol.name + ", return value is not of type " + functionSymbol.type);
                }
            }
            exitScope(); // Salir del ámbito de la función
        }
        return returnValue;
    }

}