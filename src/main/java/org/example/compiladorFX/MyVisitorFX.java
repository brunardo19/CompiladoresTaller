package org.example.compiladorFX;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MyVisitorFX extends gBaseVisitor<Object> {

    public String getTextout() {
        return textout;
    }

    private String textout;

    public MyVisitorFX(String textout) {
        this.textout = textout;
    }

    // Tabla de símbolos
    Map<String, Symbol> symbolTable = new HashMap<>();
    Stack<Map<String, Symbol>> symbolTableStack = new Stack<>();

    void enterScope() {
        textout += "\n" + ("EnterScope");
        if (symbolTableStack.empty()){
            symbolTableStack.push(symbolTable);
        }else{
            symbolTableStack.push(new HashMap<>(symbolTable)); // Inherit parent scope
        }
    }

    void exitScope() {
        textout += "\n" + ("ExitScope");
        symbolTable = symbolTableStack.pop(); // Restore parent scope
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
            return "{Variable \"" + name + "\" de tipo " + type + " con valor = " + value +"}";
        }
    }

    boolean isDefined(String id) {
        return symbolTableStack.peek().containsKey(id);
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
                case "==": return leftVal == rightVal;
                case "!=": return leftVal != rightVal;
                case "<":  return leftVal < rightVal;
                case ">":  return leftVal > rightVal;
                case "<=": return leftVal <= rightVal;
                case ">=": return leftVal >= rightVal;
                default: return false; // Should not happen in correct grammar
            }
        } else if (left instanceof Boolean && right instanceof Boolean){
            boolean leftb = ((Boolean)left).booleanValue();
            boolean rightb = ((Boolean)right).booleanValue();
            switch (operator) {
                case "==": return leftb == rightb;
                case "!=": return leftb != rightb;
                default: return false; // Should not happen
            }
        } else {
            textout += "\n" + ("Error de tipos en comparacion : " + left + " " + operator + " " + right);
            return false; // Or throw an exception
        }
    }


    // Método para evaluar tipo en asignaciones
    Boolean checkAssignmentCompatibility(String id, Object value) {
        if (isDefined(id)) {
            String varType = symbolTableStack.peek().get(id).type;
            if (varType.equals("int") && !(value instanceof Integer)) {
                if (((Number)value).doubleValue() != Math.rint(((Number)value).doubleValue())){
                    textout += "\n" + ("Error de tipo: No se puede asignar un valor no entero a variable int '" + id + "'");
                    return false;
                }else{return true;}
            } else if (varType.equals("double") && !(value instanceof Double || value instanceof Integer)) { // Allow int to double conversion
                textout += "\n" + ("Error de tipo: No se puede asignar un valor no numerico a variable double '" + id + "'");
                return false;
            }
        }
        return true;
    }

    @Override
    public Object visitProgram(gParser.ProgramContext ctx) {
        enterScope(); // Enter the global scope
        for (gParser.StatementContext stmt : ctx.statement()) {
            visit(stmt);
        }
        exitScope(); // Exit the global scope
        return null;
    }

    @Override
    public Object visitVariable_declaration(gParser.Variable_declarationContext ctx) {
        String id = ctx.ID().getText();
        String type = ctx.type().getText();

        if (isDefined(id)) {
            textout += "\n" + ("Error: Variable '" + id + "' ya declarada.");
        } else {
            if (ctx.math_expression() != null) { // Declaration with initialization
                Object value = visit(ctx.math_expression());
                if (checkAssignmentCompatibility(id, value)) {
                    symbolTableStack.peek().put(id, new Symbol(id, type, value));
                    textout += "\n" + ("Declaracion de variable: " + symbolTableStack.peek().get(id));
                }
            } else { // Declaration without initialization
                symbolTableStack.peek().put(id, new Symbol(id, type));
                textout += "\n" + ("Declaracion de variable: " + symbolTableStack.peek().get(id));
            }
        }
        return null;
    }


    @Override
    public Object visitVariable_update(gParser.Variable_updateContext ctx) {
        String id = ctx.ID().getText();

        // Retrieve the symbol
        Symbol symbol = symbolTableStack.peek().get(id);

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
        Object value = visit(ctx.math_expression()); // Evaluate the right-hand side expression

        if (!isDefined(id)) {
            textout += "\n" + ("Error: Variable '" + id + "' not declared.");
        } else {
            if (checkAssignmentCompatibility(id, value)) {
                symbolTableStack.peek().get(id).value = value;
                textout += "\n" + ("Asignacion de variable: " + symbolTableStack.peek().get(id));
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
        while ((Boolean) visit(ctx.logical_operation())){
            visit(ctx.program()); // Execute the loop body
        };
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
        visit(ctx.variable_assign()); // Initialization

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
                Object val = symbolTableStack.peek().get(id).value;
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
        } else if (ctx.math_expression() != null) { // Comparison between math expressions
            Double left = (Double) visit(ctx.math_expression(0));
            Double right = (Double) visit(ctx.math_expression(1));
            String operator = ctx.comparison_operator().getText();
            System.out.println(left + " " + operator + " " + right);
            return evalCondition(left, operator, right);
        } else { // Must be FALSE
            return false;
        }
    }
}