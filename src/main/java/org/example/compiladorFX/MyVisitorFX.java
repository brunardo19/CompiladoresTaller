package org.example.compiladorFX;

import java.util.*;

public class MyVisitorFX extends gBaseVisitor<Object> {

    public String getErrorOut() {
        return errorOut;
    }

    public String getTextOut() {
        return textOut;
    }

    private String errorOut;
    private String textOut;

    public MyVisitorFX(String errorOut, String textOut) {
        this.errorOut = errorOut;
        this.textOut = textOut;
    }

    // Tabla de símbolos.  Almacena las variables y funciones del programa.
    Map<String, Symbol> symbolTableGlobal = new HashMap<>(); // Tabla de símbolos global. Almacena variables y funciones globales.
    Stack<Map<String, Symbol>> symbolTableStack = new Stack<>(); // Pila de tablas de símbolos para manejar los ámbitos locales.


    /**
     * Clase interna para representar un símbolo (variable).
     */
    public static class Symbol {
        String name; // Nombre del símbolo.
        String type; // Tipo del símbolo.
        Object value; // Valor del símbolo.


        // Constructor para declarar un símbolo sin valor inicial.
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

    /**
     * Clase interna para representar un símbolo de función. Extiende la clase Symbol.
     */
    public static class FunctionSymbol extends Symbol {
        List<Symbol> parameters; // Lista de parámetros de la función.
        gParser.ProgramContext program; // Contexto del programa de la función.
        gParser.Return_expressionContext returnExpression; // Contexto de la expresión de retorno.

        // Constructor.
        public FunctionSymbol(String name, String type, List<Symbol> parameters, gParser.ProgramContext program, gParser.Return_expressionContext returnExpression) {
            super(name, type);
            this.parameters = parameters;
            this.program = program;
            this.returnExpression = returnExpression;
        }

        @Override
        public String toString() {
            return "{Funcion \"" + name + "\" que devuelve tipo " + type + " con parametros = " + parameters + "}";
        }

    }

    /**
     * Obtiene el valor de una variable de la tabla de símbolos.
     * Busca primero en los ámbitos locales y luego en el global.
     *
     * @param id El nombre de la variable.
     * @return El valor de la variable o null si no se encuentra.
     */
    public Object getTableValue(String id) {
        for (int i = symbolTableStack.size() - 1; i >= 0; i--) {  // Recorre los ámbitos locales desde el más interno.
            Map<String, Symbol> currentScope = symbolTableStack.get(i);
            if (currentScope.containsKey(id)) { // Si la variable existe en el ámbito actual.
                return currentScope.get(id).value; // Devuelve el valor de la variable.
            }
        }
        if (symbolTableGlobal.containsKey(id)) {  // Si no se encontró en ámbitos locales, busca en el global.
            return symbolTableGlobal.get(id).value; // Devuelve el valor de la variable global.
        }
        errorOut += "\n" + ("variable " + id + " no declarada"); // Error si la variable no se encuentra.
        return null;
    }

    /**
     * Obtiene el símbolo (objeto Symbol) de una variable de la tabla de símbolos.
     *
     * @param id El nombre de la variable.
     * @return El objeto Symbol o null si no se encuentra.
     */
    Symbol getTableSymbol(String id) {
        for (int i = symbolTableStack.size() - 1; i >= 0; i--) {  // Busca en ámbitos locales.
            Map<String, Symbol> currentScope = symbolTableStack.get(i);
            if (currentScope.containsKey(id)) {
                return currentScope.get(id);
            }
        }
        if (symbolTableGlobal.containsKey(id)) {  // Busca en el ámbito global.
            return symbolTableGlobal.get(id);
        }
        errorOut += "\n" + ("variable " + id + " no declarada"); // Error si no se encuentra.
        return null;
    }

    /**
     * Obtiene el tipo de una variable de la tabla de símbolos.
     *
     * @param id El nombre de la variable.
     * @return El tipo de la variable o null si no se encuentra.
     */
    String getTableType(String id) {
        for (int i = symbolTableStack.size() - 1; i >= 0; i--) {  // Busca en ámbitos locales.
            Map<String, Symbol> currentScope = symbolTableStack.get(i);
            if (currentScope.containsKey(id)) {
                return currentScope.get(id).type;
            }
        }
        if (symbolTableGlobal.containsKey(id)) {  // Busca en el ámbito global.
            return symbolTableGlobal.get(id).type;
        }
        errorOut += "\n" + ("variable " + id + " no declarada"); // Error si no se encuentra.
        return null;
    }

    /**
     * Declara una variable en la tabla de símbolos del ámbito actual.
     *
     * @param id    El nombre de la variable.
     * @param type  El tipo de la variable.
     * @param value El valor inicial de la variable.
     */
    void declareInTable(String id, String type, Object value) {
        Map<String, Symbol> currentScope = symbolTableStack.peek(); // Obtiene el ámbito actual.
        if (currentScope.containsKey(id)) { // Verifica si ya existe una variable con el mismo nombre en el ámbito actual.
            errorOut += "\n" + ("Variable " + id + " already declared in this scope or globally."); // Error si ya está declarada.
        } else {
            currentScope.put(id, new Symbol(id, type, value)); // Agrega la variable al ámbito actual.
        }
    }

    /**
     * Actualiza el valor de una variable en la tabla de símbolos.
     *
     * @param id    El nombre de la variable.
     * @param value El nuevo valor de la variable.
     */
    void updateTable(String id, Object value) {
        for (int i = symbolTableStack.size() - 1; i >= 0; i--) { // Busca en ámbitos locales.
            Map<String, Symbol> currentScope = symbolTableStack.get(i);
            if (currentScope.containsKey(id)) {
                currentScope.get(id).value = value; // Actualiza el valor.
                return;
            }
        }
        if (symbolTableGlobal.containsKey(id)) { // Busca en el ámbito global.
            symbolTableGlobal.get(id).value = value; // Actualiza el valor global.
            return;
        }
        errorOut += "\n" + ("variable " + id + " no declarada"); // Error si no se encuentra.
    }

    /**
     * Entra en un nuevo ámbito. Crea una nueva tabla de símbolos para el ámbito local.
     */
    public void enterScope() {
        errorOut += "\n" + ("Entering Scope");
        symbolTableStack.push(new HashMap<>()); // Agrega una nueva tabla de símbolos a la pila.
    }

    /**
     * Sale del ámbito actual. Descarta la tabla de símbolos del ámbito local.
     */
    public void exitScope() {
        if (symbolTableStack.size() > 1) { // Verifica que no se esté saliendo del ámbito global.
            errorOut += "\n" + ("Exiting Scope");
            Map<String, Symbol> leavingScope = symbolTableStack.pop(); // Elimina la tabla de símbolos del ámbito actual.

            System.out.println("Variables leaving scope: " + leavingScope); // Imprime las variables que salen del ámbito.

        } else {
            symbolTableGlobal = symbolTableStack.pop(); // Vacia la pila y retorna a la tabla de símbolos global.
            errorOut += "\nCannot exit global scope";  // No se puede salir del ámbito global.
        }

    }

    /**
     * Verifica si una variable está definida en algún ámbito (local o global).
     *
     * @param id El nombre de la variable.
     * @return true si la variable está definida, false en caso contrario.
     */
    boolean isDefined(String id) {
        // Busca en ámbitos locales del más interno al más externo.
        for (int i = symbolTableStack.size() - 1; i >= 0; i--) {
            if (symbolTableStack.get(i).containsKey(id)) {
                return true;
            }
        }
        // Si no se encuentra localmente, busca en el ámbito global.
        return symbolTableGlobal.containsKey(id);
    }

    /**
     * Verifica si una variable está definida en el ámbito local actual.
     *
     * @param id El nombre de la variable.
     * @return true si la variable está definida localmente, false en caso contrario.
     */
    boolean isLocallyDefined(String id) {
        Map<String, Symbol> currentScope = symbolTableStack.peek(); // Obtiene el ámbito actual.
        return currentScope.containsKey(id);  // Verifica si la variable existe en el ámbito actual.
    }

    /**
     * Evalúa una condición booleana.
     *
     * @param left     El operando izquierdo.
     * @param operator El operador.
     * @param right    El operando derecho.
     * @return El resultado de la evaluación.
     */
    Object evalCondition(Object left, String operator, Object right) {
        if (left instanceof Number && right instanceof Number) { // Si ambos operandos son números.
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
                    return false; // No debería ocurrir.
            }
        } else if (left instanceof Boolean && right instanceof Boolean) { // Si ambos operandos son booleanos.
            boolean leftb = (Boolean) left;
            boolean rightb = (Boolean) right;
            switch (operator) {
                case "==":
                    return leftb == rightb;
                case "!=":
                    return leftb != rightb;
                default:
                    return false; // No debería ocurrir.
            }
        } else { // Error de tipos.
            errorOut += "\n" + ("Error de tipos en comparacion : " + left + " " + operator + " " + right);
            return false;
        }
    }

    /**
     * Evalúa la compatibilidad de tipos en una asignación.
     *
     * @param id    El nombre de la variable.
     * @param value El valor a asignar.
     * @return true si los tipos son compatibles, false en caso contrario.
     */
    boolean checkAssignmentCompatibility(String id, Object value) {
        if (!isDefined(id)) {
            return true; // Maneja el caso de id no definido primero. Retorna true para evitar NullPointerException.  Debería manejarse en otro lugar.
        }

        String varType = getTableType(id); // Obtiene el tipo de la variable.

        switch (varType) {
            case "int":
                if (!(value instanceof Integer)) {
                    // Permite asignar doubles/floats con valor entero a int.
                    if (value instanceof Number && ((Number) value).doubleValue() == Math.rint(((Number) value).doubleValue())) {
                        return true;
                    } else {
                        errorOut += "\n" + ("Error de tipo: No se puede asignar un valor no entero a variable int '" + id + "'");
                        return false;
                    }
                }
                return true; // El valor es un Integer, por lo que es compatible.

            case "double":
                // Permite asignar Integer o Double a double.
                if (!(value instanceof Double || value instanceof Integer)) {
                    errorOut += "\n" + ("Error de tipo: No se puede asignar un valor no numérico a variable double '" + id + "'");
                    return false;
                }
                return true;

            case "string":
                if (!(value instanceof String)) {
                    errorOut += "\n" + ("Error de tipo: No se puede asignar un valor no string a variable string '" + id + "'");
                    return false;
                }
                return true;

            case "boolean":
                if (!(value instanceof Boolean)) {
                    errorOut += "\n" + ("Error de tipo: No se puede asignar un valor no booleano a variable booleana '" + id + "'");
                    return false;
                }
                return true;

            default:  // Maneja otros tipos o el caso predeterminado según sea necesario.
                System.out.println("checkAssignmentCompatibility - tipo recibido invalido");
                return false;
        }
    }

    /**
     * Método para evaluar tipo en declaraciones.  Verifica si el tipo del valor inicial es compatible con el tipo declarado.
     *
     * @param id    El nombre de la variable.
     * @param type  El tipo declarado de la variable.
     * @param value El valor inicial.
     * @return true si los tipos son compatibles, false en caso contrario.
     */
    boolean checkDeclarationCompatibility(String id, String type, Object value) {
        if (isLocallyDefined(id)) {
            errorOut += "\n" + ("Error: la variable '" + id + "' ya está declarada.");
            return false; // La variable ya está declarada.
        }

        // Verifica la compatibilidad de tipos, manejando null para valores predeterminados.
        switch (type) {
            case "int":
                if (value != null && !(value instanceof Integer)) {
                    if (value instanceof Number && ((Number) value).doubleValue() == Math.rint(((Number) value).doubleValue())) {
                        return true; // Permite doubles/floats con valor entero.
                    } else {
                        errorOut += "\n" + ("Error de tipo: Valor inicial no entero para variable int '" + id + "'");
                        return false;
                    }
                }
                return true;

            case "double":
                if (value != null && !(value instanceof Double || value instanceof Integer)) {
                    errorOut += "\n" + ("Error de tipo: Valor inicial no numérico para variable double '" + id + "'");
                    return false;
                }
                return true;

            case "string":
                if (value != null && !(value instanceof String)) {
                    errorOut += "\n" + ("Error de tipo: Valor inicial no string para variable string '" + id + "'");
                    return false;
                }
                return true;

            case "boolean":
                if (value != null && !(value instanceof Boolean)) {
                    errorOut += "\n" + ("Error de tipo: Valor inicial no booleano para variable boolean '" + id + "'");
                    return false;
                }
                return true;

            default:
                System.out.println("checkDeclarationCompatibility - tipo recibido inválido");
                return false;
        }
    }

    // ---------------------- Visitors ----------------------

    //---------------------- Básicos ----------------------

    /**
     * Visita el nodo raíz del programa. Crea el ámbito global y visita las sentencias.
     *
     * @param ctx El contexto del programa.
     * @return null.
     */
    @Override
    public Object visitProgram(gParser.ProgramContext ctx) {
        enterScope();
        for (gParser.StatementContext stmt : ctx.statement()) {
            visit(stmt); // Visita cada sentencia del programa.
        }
        exitScope();
        return null;
    }

    /**
     * Visita una declaración de variable. Declara la variable en la tabla de símbolos con su tipo y valor inicial (si existe).
     *
     * @param ctx El contexto de la declaración de variable.
     * @return null.
     */
    @Override
    public Object visitVariable_declaration(gParser.Variable_declarationContext ctx) {
        String id = ctx.ID(0).getText(); // Obtiene el nombre de la variable.
        String type = ctx.type().getText(); // Obtiene el tipo de la variable.

        if (isLocallyDefined(id)) { // Verifica si la variable ya está declarada en el ámbito local.
            errorOut += "\n" + ("Error: Variable '" + id + "' ya declarada.");
        } else {
            if (ctx.ID(1) != null) { // Declaración con asignación de otra variable
                String id2 = ctx.ID(1).getText();
                if (!isDefined(id2)) { // Verifica si la variable a asignar existe.
                    errorOut += "\n" + ("Error: Variable '" + id2 + "' no declarada.");
                    return null;
                } else {
                    Object value2 = getTableValue(id2); // Obtiene el valor de la variable a asignar.
                    if (checkDeclarationCompatibility(id, type, value2)) { // Verifica la compatibilidad de tipos.
                        declareInTable(id, type, value2); // Declara la variable.
                        errorOut += "\n" + ("Declaración de variable: " + symbolTableStack.peek().get(id));
                    }
                    return null;
                }
            }
            if (ctx.expression() != null) { // Declaración con inicialización (expression no es nula).
                Object value = visit(ctx.expression()); // Evalúa la expresión de inicialización.
                if (checkDeclarationCompatibility(id, type, value)) { // Verifica la compatibilidad de tipos.
                    declareInTable(id, type, value); // Declara la variable.
                    errorOut += "\n" + ("Declaración de variable: " + symbolTableStack.peek().get(id));
                }
                return null;
            }
            // Declaración sin inicialización.
            declareInTable(id, type, null); // Declara la variable sin valor inicial.
            errorOut += "\n" + ("Declaración de variable: " + symbolTableStack.peek().get(id));
        }
        return null;
    }

    /**
     * Visita una expresión de cadena. Concatena las cadenas y devuelve el resultado.
     *
     * @param ctx El contexto de la expresión de cadena.
     * @return El valor de la expresión de cadena.
     */
    @Override
    public Object visitString_expression(gParser.String_expressionContext ctx) {
        String text = ctx.STRING().getText();
        String value = text.substring(1, text.length() - 1); // Remueve las comillas.

        StringBuilder sb = new StringBuilder(value);
        if (ctx.string_expression() != null) { // Concatena con otras expresiones de cadena si existen.
            sb.append(visit(ctx.string_expression()));
        }
        return sb.toString();
    }

    /**
     * Visita los parámetros de impresión.  Concatena o suma los valores según su tipo y los devuelve.
     *
     * @param ctx El contexto de los parámetros de impresión.
     * @return El valor resultante de la evaluación de los parámetros.
     */
    @Override
    public Object visitPrint_params(gParser.Print_paramsContext ctx) {
        Object value = visit(ctx.getChild(0)); // Evalúa la primera expresión.

        if (ctx.getChildCount() > 1) { // Si hay más parámetros.
            Object nextValue = visit(ctx.print_params()); // Evalúa el resto de los parámetros recursivamente.

            if (value instanceof String) { // Concatenación de cadenas.
                value = (String) value + nextValue.toString();
            } else if (nextValue instanceof String) {  // Concatenación de cadenas.
                value = value.toString() + (String) nextValue;
            } else if (value instanceof Number && nextValue instanceof Number) { // Suma de números.
                if (value instanceof Double || nextValue instanceof Double) { // Si alguno es Double, el resultado es Double.
                    value = ((Number) value).doubleValue() + ((Number) nextValue).doubleValue();
                } else { // Si ambos son Integer, el resultado es Integer.
                    value = ((Number) value).intValue() + ((Number) nextValue).intValue();
                }
            } else { // Error de tipos incompatibles.
                throw new RuntimeException("Tipos de datos incompatibles para la operación.");
            }
        }

        return value;
    }


    /**
     * Visita la actualización de una variable (++, --, +=, -=).
     * Exclusivo para variables numéricas
     *
     * @param ctx El contexto de la actualización de la variable.
     * @return null.
     */
    @Override
    public Object visitVariable_update(gParser.Variable_updateContext ctx) {
        String id = ctx.ID().getText(); // Obtiene el nombre de la variable.
        Symbol symbol = getTableSymbol(id); // Obtiene el símbolo de la variable.

        if (symbol == null) { // Error si la variable no está declarada.
            errorOut += "\n" + ("Error: Variable '" + id + "' no declarada.");
            return null;
        }

        if (!(symbol.value instanceof Number)) {
            errorOut += "\n" + ("Error: La variable '" + id + "' no es numérica y no se puede usar con operadores de incremento/decremento.");
            return null;
        }

        Number currentValue = (Number) symbol.value; // Obtiene el valor actual de la variable.

        String operator = ctx.getChild(1).getText(); // Obtiene el operador.

        switch (operator) {
            case "++":
                symbol.value = currentValue.doubleValue() + 1;
                break;
            case "--":
                symbol.value = currentValue.doubleValue() - 1;
                break;
            case "+=": // Suma y asigna.
                Object exprValueAdd = visit(ctx.math_expression());
                if (exprValueAdd instanceof Number) {
                    symbol.value = currentValue.doubleValue() + ((Number) exprValueAdd).doubleValue();
                } else { // Error si la expresión no es numérica.
                    errorOut += "\n" + ("Error: La expresión en la asignación += debe ser numérica.");
                    return null;
                }
                break;
            case "-=": // Resta y asigna.
                Object exprValueSubtract = visit(ctx.math_expression());
                if (exprValueSubtract instanceof Number) {
                    symbol.value = currentValue.doubleValue() - ((Number) exprValueSubtract).doubleValue();
                } else {  // Error si la expresión no es numérica.
                    errorOut += "\n" + ("Error: La expresión en la asignación -= debe ser numérica.");
                    return null;
                }
                break;

            default: // Operador no soportado.
                errorOut += "\n" + ("Error: Operador '" + operator + "' no soportado.");
                return null;
        }

        errorOut += "\n" + ("Actualización de variable: " + symbol); // Imprime la actualización de la variable.
        return null;
    }

    /**
     * Visita una asignación de variable. Evalúa la expresión del lado derecho y actualiza la variable en la tabla de símbolos.
     *
     * @param ctx El contexto de la asignación de variable.
     * @return null.
     */
    @Override
    public Object visitVariable_assign(gParser.Variable_assignContext ctx) {
        String id = ctx.ID().getText(); // Obtiene el nombre de la variable.
        Object value = visit(ctx.expression()); // Evalúa la expresión del lado derecho.

        if (!isDefined(id)) {  // Error si la variable no está declarada.
            errorOut += "\n" + ("Error: Variable '" + id + "' no está declarada.");
        } else {
            if (checkAssignmentCompatibility(id, value)) { // Verifica compatibilidad de tipos.
                updateTable(id, value); // Actualiza la variable en la tabla de símbolos.
                errorOut += "\n" + ("Asignación de variable: " + getTableSymbol(id).name + " = " + getTableValue(id));
            }
        }
        return null;
    }

    /**
     * Visita un tipo de dato. Devuelve el texto del tipo.
     *
     * @param ctx El contexto del tipo de dato.
     * @return El texto del tipo de dato.
     */
    @Override
    public Object visitType(gParser.TypeContext ctx) {
        return ctx.getText();
    }


    //---------------------- Estructuras de control ----------------------


    /**
     * Visita una sentencia if. Evalúa la condición y ejecuta el bloque correspondiente.
     *
     * @param ctx El contexto de la sentencia if.
     * @return null.
     */
    @Override
    public Object visitIf_statement(gParser.If_statementContext ctx) {
        Boolean condition = (Boolean) visit(ctx.logical_operation()); // Evalúa la condición.
        System.out.println(condition);
        if (condition) {
            visit(ctx.program(0)); // Ejecuta el bloque 'if'.
        } else if (ctx.program().size() > 1) { // Si hay un bloque 'else'.
            visit(ctx.program(1)); // Ejecuta el bloque 'else'.
        }
        return null; // Las sentencias if no tienen valor de retorno.
    }

    /**
     * Visita un bucle while. Evalúa la condición y ejecuta el cuerpo del bucle mientras la condición sea verdadera.
     *
     * @param ctx El contexto del bucle while.
     * @return null.
     */
    @Override
    public Object visitWhile_loop(gParser.While_loopContext ctx) {
        while ((Boolean) visit(ctx.logical_operation())) { // Evalúa la condición.
            visit(ctx.program()); // Ejecuta el cuerpo del bucle.
        }
        return null; // Los bucles while no tienen valor de retorno.
    }

    /**
     * Visita un bucle do-while. Ejecuta el cuerpo del bucle al menos una vez y luego repite mientras la condición sea verdadera.
     *
     * @param ctx El contexto del bucle do-while.
     * @return null.
     */
    @Override
    public Object visitDo_while(gParser.Do_whileContext ctx) {
        do {
            visit(ctx.program()); // Ejecuta el cuerpo del bucle.
        } while ((Boolean) visit(ctx.logical_operation())); // Evalúa la condición.
        return null; // Los bucles do-while no tienen valor de retorno.
    }

    /**
     * Visita un bucle for. Inicializa la variable de control, evalúa la condición y ejecuta el cuerpo del bucle,
     * actualizando la variable de control en cada iteración.
     *
     * @param ctx El contexto del bucle for.
     * @return null.
     */
    @Override
    public Object visitFor_loop(gParser.For_loopContext ctx) {
        enterScope(); // Entra en un nuevo ámbito para la variable de control.
        visit(ctx.variable_declaration()); // Inicialización de la variable de control.

        while ((Boolean) visit(ctx.logical_operation())) { // Evalúa la condición.
            visit(ctx.program()); // Ejecuta el cuerpo del bucle.
            visit(ctx.variable_update()); // Actualiza la variable de control.
        }
        exitScope(); // Sale del ámbito de la variable de control.

        return null; // Los bucles for no tienen valor de retorno.
    }

    //---------------------- Expresiones aritméticas ----------------------

    /**
     * Visita una expresión matemática. Evalúa las sumas y restas.
     *
     * @param ctx El contexto de la expresión matemática.
     * @return El valor de la expresión.
     */
    @Override
    public Object visitMath_expression(gParser.Math_expressionContext ctx) {
        Double value = (Double) visit(ctx.term(0)); // Evalúa el primer término.
        for (int i = 1; i < ctx.term().size(); i++) { // Evalúa el resto de los términos.
            Double termValue = (Double) visit(ctx.term(i));
            if (ctx.getChild(2 * i - 1).getText().equals("+")) { // Suma.
                value += termValue;
            } else { // Resta.
                value -= termValue;
            }
        }
        return value;
    }

    /**
     * Visita un término. Evalúa las multiplicaciones y divisiones.
     *
     * @param ctx El contexto del término.
     * @return El valor del término.
     */
    @Override
    public Object visitTerm(gParser.TermContext ctx) {
        Double value = (Double) visit(ctx.power_expr(0)); // Evalúa la primera expresión de potencia.
        for (int i = 1; i < ctx.power_expr().size(); i++) { // Evalúa el resto de las expresiones de potencia.
            Double factorValue = (Double) visit(ctx.power_expr(i));
            if (ctx.getChild(2 * i - 1).getText().equals("*")) { // Multiplicación.
                value *= factorValue;
            } else { // División.
                value /= factorValue;
            }
        }
        return value;
    }

    /**
     * Visita una expresión de potencia. Evalúa la potencia.
     *
     * @param ctx El contexto de la expresión de potencia.
     * @return El valor de la expresión de potencia.
     */
    @Override
    public Object visitPower_expr(gParser.Power_exprContext ctx) {
        double value = ((Number) visit(ctx.factor(0))).doubleValue(); // Evalúa la base.
        if (ctx.factor().size() > 1) { // Sí hay una operación de potencia.
            double exponent = ((Number) visit(ctx.factor(1))).doubleValue(); // Evalúa el exponente.
            value = Math.pow(value, exponent); // Calcula la potencia.
        }
        return value;
    }

    /**
     * Visita un factor. Puede ser un identificador, un número o una expresión entre paréntesis.
     *
     * @param ctx El contexto del factor.
     * @return El valor del factor.
     */
    @Override
    public Object visitFactor(gParser.FactorContext ctx) {
        if (ctx.ID() != null) { // Si el factor es un identificador (variable).
            String id = ctx.ID().getText();
            if (!isDefined(id)) { // Error si la variable no está declarada.
                errorOut += "\n" + ("Error: Variable '" + id + "' no declarada.");
                return 0.0;
            } else {
                Object val = getTableValue(id); // Obtiene el valor de la variable.
                if (val == null) { // Error si la variable no está inicializada.
                    errorOut += "\n" + ("Error: Variable '" + id + "' no inicializada.");
                    return 0.0;
                } else if (val instanceof Double) { // Si es Double, retorna el valor.
                    return (Double) val;
                } else if (val instanceof Integer) {  // Si es Integer, lo convierte a Double.
                    return ((Integer) val).doubleValue();
                } else {  // Error si la variable no es numérica.
                    errorOut += "\n" + ("Error: Variable '" + id + "' no es una variable numerica");
                    return 0.0;
                }
            }
        } else if (ctx.number() != null) { // Si el factor es un número.
            return visit(ctx.number()); // Visita el número.
        } else if (ctx.function_call() != null) { // Si el factor es una llamada de funcion
            Object val = visit(ctx.function_call());
            if (val instanceof Number) {
                return visit(ctx.function_call());
            } else {
                errorOut += "\n" + ("Error: La funcion '" + ctx.function_call().ID().getText() + "' no devuelve una variable numerica");
                throw new RuntimeException("Error: La funcion '" + ctx.function_call().ID().getText() + "' no devuelve una variable numerica");
            }
        } else {// el factor es una expresión entre paréntesis.
            return visit(ctx.math_expression()); // Visita la expresión.
        }
    }

    /**
     * Visita un número. Puede ser un entero o un double.
     *
     * @param ctx El contexto del número.
     * @return El valor del número.
     */
    @Override
    public Object visitNumber(gParser.NumberContext ctx) {
        if (ctx.INT() != null) { // Si es un entero.
            if (ctx.getChild(0).getText().equals("-")) { // Maneja números negativos.
                return -Integer.parseInt(ctx.INT().getText());
            } else {
                return Integer.parseInt(ctx.INT().getText());
            }
        } else { // Si es un double.
            if (ctx.getChild(0).getText().equals("-")) {  // Maneja números negativos.
                return -Double.parseDouble(ctx.DOUBLE().getText());
            } else {
                return Double.parseDouble(ctx.DOUBLE().getText());
            }
        }
    }


    //---------------------- Expresiones lógicas --------------------------


    /**
     * Visita una operación lógica. Evalúa las operaciones OR (||).
     *
     * @param ctx El contexto de la operación lógica.
     * @return El resultado de la operación lógica.
     */
    @Override
    public Object visitLogical_operation(gParser.Logical_operationContext ctx) {
        Boolean value = (Boolean) visit(ctx.logical_term(0)); // Evalúa el primer término lógico.
        for (int i = 1; i < ctx.logical_term().size(); i++) { // Evalúa el resto de los términos lógicos.
            Boolean termValue = (Boolean) visit(ctx.logical_term(i));
            value = value || termValue; // Aplica el operador OR (||).
        }
        return value;
    }

    /**
     * Visita un término lógico. Evalúa las operaciones AND (&&).
     *
     * @param ctx El contexto del término lógico.
     * @return El resultado del término lógico.
     */
    @Override
    public Object visitLogical_term(gParser.Logical_termContext ctx) {
        Boolean value = (Boolean) visit(ctx.logical_factor(0)); // Evalúa el primer factor lógico.
        for (int i = 1; i < ctx.logical_factor().size(); i++) { // Evalúa el resto de los factores lógicos.
            Boolean factorValue = (Boolean) visit(ctx.logical_factor(i));
            value = value && factorValue; // Aplica el operador AND (&&).
        }
        return value;
    }

    /**
     * Visita un factor lógico. Puede ser un booleano, una negación (!) o una operación lógica entre paréntesis.
     *
     * @param ctx El contexto del factor lógico.
     * @return El resultado del factor lógico.
     */
    @Override
    public Object visitLogical_factor(gParser.Logical_factorContext ctx) {
        if (ctx.boolean_() != null) { // Si es un booleano (true, false, variable booleana).
            return visit(ctx.boolean_());
        } else if (ctx.logical_factor() != null) { // Si es una negación (!).
            Boolean value = (Boolean) visit(ctx.logical_factor());
            return !value; // Retorna la negación del valor.
        } else if (ctx.function_call() != null) {
            Object value = visit(ctx.function_call());
            if (value instanceof Boolean){
                return value;
            }else {
                errorOut += "\n" + ("Error: La funcion '" + ctx.function_call().ID().getText() + "' no devuelve una variable booleana");
                throw new RuntimeException("Error: La funcion '" + ctx.function_call().ID().getText() + "' no devuelve una variable booleana");
            }
        } else { // Si es una operación lógica entre paréntesis.
            return visit(ctx.logical_operation());
        }
    }

    /**
     * Visita un booleano. Puede ser true, false o una variable booleana.
     *
     * @param ctx El contexto del booleano.
     * @return El valor del booleano.
     */
    @Override
    public Object visitBoolean(gParser.BooleanContext ctx) {
        if (ctx.TRUE() != null) { // Si es true.
            return true;
        } else if (ctx.FALSE() != null) {  // Si es false.
            return false;
        } else if (ctx.ID() != null) { // Si es una variable.
            String id = ctx.ID().getText();
            if (!isDefined(id)) {  // Error si la variable no está declarada.
                errorOut += "\n" + ("Error: Variable '" + id + "' no declarada.");
                return null;
            } else {
                Object val = symbolTableStack.peek().get(id).value; // Obtiene el valor de la variable.
                if (val == null) { // Error si la variable no está inicializada.
                    errorOut += "\n" + ("Error: Variable '" + id + "' no inicializada.");
                    return null;
                } else if (val instanceof Boolean) { // Si es un booleano, retorna el valor.
                    return (Boolean) val;
                } else { // Error si la variable no es booleana.
                    errorOut += "\n" + ("Error: Variable '" + id + "' no es una variable booleana");
                    return null;
                }
            }
        } else if (ctx.math_expression() != null) { // Si es una comparación entre expresiones matemáticas.
            Double left = (Double) visit(ctx.math_expression(0)); // Evalúa la expresión izquierda.
            Double right = (Double) visit(ctx.math_expression(1)); // Evalúa la expresión derecha.
            String operator = ctx.comparison_operator().getText();  // Obtiene el operador de comparación.
            System.out.println(left + " " + operator + " " + right);
            return evalCondition(left, operator, right); // Evalúa la condición.
        }
        return false;
    }


    //---------------------- Funciones --------------------------


    /**
     * Visita la declaración de una función.  Almacena la información de la función en la tabla de símbolos global.
     *
     * @param ctx El contexto de la declaración de la función.
     * @return null.
     */
    @Override
    public Object visitFunction_declaration(gParser.Function_declarationContext ctx) {
        String functionName = ctx.ID().getText(); // Obtiene el nombre de la función.
        String returnType = ctx.type().getText(); // Obtiene el tipo de retorno.

        List<Symbol> parameters = new ArrayList<>(); // Crea una lista para los parámetros.
        if (ctx.params() != null) { // Si la función tiene parámetros.
            parameters = visitParams(ctx.params()); // Visita los parámetros.
        }

        // Crea un símbolo de función y lo almacena en la tabla de símbolos global.
        FunctionSymbol functionSymbol = new FunctionSymbol(functionName, returnType, parameters, ctx.program(), ctx.return_expression());
        symbolTableGlobal.put(functionName, functionSymbol);

        return null; // La declaración de función no retorna un valor.
    }

    /**
     * Visita los parámetros de una declaración de función. Crea una lista de símbolos para los parámetros.
     *
     * @param ctx El contexto de los parámetros.
     * @return Una lista de símbolos que representan los parámetros.
     */
    public List<Symbol> visitParams(gParser.ParamsContext ctx) {
        List<Symbol> params = new ArrayList<>();
        String paramType = ctx.type().getText(); // Obtiene el tipo del parámetro.
        String paramName = ctx.ID().getText();  // Obtiene el nombre del parámetro.
        params.add(new Symbol(paramName, paramType)); // Crea un nuevo símbolo para el parámetro.
        if (ctx.params() != null) {  // Si hay más parámetros.
            params.addAll((List<Symbol>) visit(ctx.params())); // Los agrega recursivamente.
        }
        return params;
    }

    /**
     * Visita los parámetros de una llamada a función. Crea una lista de valores de los argumentos.
     *
     * @param ctx El contexto de los parámetros de la llamada.
     * @return Una lista de objetos que representan los valores de los argumentos.
     */
    public List<Object> visitParams_call(gParser.Params_callContext ctx) {
        List<Object> params = new ArrayList<>();
        Object paramValue = visit(ctx.expression()); // Evalúa la expresión del argumento
        params.add(paramValue);  // Agrega el valor del argumento a la lista.

        if (ctx.params_call() != null) {  // Si hay más argumentos.
            params.addAll((List<Object>) visit(ctx.params_call())); // Los agrega recursivamente.
        }
        return params;
    }

    /**
     * Visita la llamada a una función. Busca la función en la tabla de símbolos,
     * evalúa los argumentos, ejecuta el cuerpo de la función y retorna el valor de retorno.
     *
     * @param ctx El contexto de la llamada a la función.
     * @return El valor de retorno de la función.
     */
    @Override
    public Object visitFunction_call(gParser.Function_callContext ctx) {
        Object returnValue = null;
        String functionName = ctx.ID().getText(); // Obtiene el nombre de la función.

        if (!isDefined(functionName)) { // Verifica si la función está definida.
            throw new RuntimeException("Function '" + functionName + "' not defined.");
        }

        FunctionSymbol functionSymbol = (FunctionSymbol) symbolTableGlobal.get(functionName); // Obtiene el símbolo de la función.
        List<Symbol> param = functionSymbol.parameters; // Obtiene los parámetros de la función.

        List<Object> args = (List<Object>) visit(ctx.params_call()); // Obtiene los argumentos de la llamada.


        if (checkParametersCompatibility(param, args)) { // Verifica compatibilidad de tipos entre parámetros y argumentos
            enterScope(); // Entra en el ámbito de la función.

            // Agrega los parámetros al ámbito local de la función.
            for (int i = 0; i < param.size(); i++) {
                String type = param.get(i).type;
                String id = param.get(i).name;
                Object value = args.get(i);
                symbolTableStack.peek().put(id, new Symbol(id, type, value));
            }

            // Ejecuta las sentencias dentro de la función.
            for (gParser.StatementContext stmt : functionSymbol.program.statement()) {
                visit(stmt);
            }

            // Evalúa la expresión de retorno, si existe.
            if (functionSymbol.returnExpression != null) {
                returnValue = visit(functionSymbol.returnExpression.expression());
                String type = functionSymbol.type;  // Get the expected return type
                if (!checkReturnCompatibility(type, returnValue)) {  // Verify return type compatibility
                    throw new RuntimeException("Error: In function " + functionSymbol.name + ", return value is not of type " + functionSymbol.type);
                }
            }

            exitScope(); // Sale del ámbito de la función.
        }


        return returnValue;
    }

    /**
     * Visita una llamada a la función print. Evalúa los parámetros e imprime el resultado en la consola.
     *
     * @param ctx El contexto de la llamada a print.
     * @return null.
     */
    @Override
    public Object visitPrint_call(gParser.Print_callContext ctx) {
        Object result = visit(ctx.print_params()); // Evalúa los parámetros de impresión.
        textOut += "\n" + result.toString(); // Imprime el resultado.
        return null; // La función print no tiene valor de retorno.
    }

    /**
     * Verifica la compatibilidad de tipos entre los parámetros de una función y los argumentos de la llamada.
     *
     * @param param La lista de parámetros de la función.
     * @param args  La lista de argumentos de la llamada.
     * @return true si los tipos son compatibles, false en caso contrario.
     */
    public boolean checkParametersCompatibility(List<Symbol> param, List<Object> args) {
        if (param.size() != args.size()) return false; //Error: no coinciden numero de parametros y argumentos

        for (int i = 0; i < param.size(); i++) {
            String type = param.get(i).type;
            Object value = args.get(i);
            switch (type) {
                case "int":
                    if (!(value instanceof Integer)) {
                        return value instanceof Number && ((Number) value).doubleValue() == Math.rint(((Number) value).doubleValue()); // Permite doubles/floats con valor entero a int
                    }
                    return true;

                case "double":
                    return value instanceof Double || value instanceof Integer;

                case "string":
                    return value instanceof String;

                case "boolean":
                    return value instanceof Boolean;
            }
        }
        return true;
    }

    /**
     * Verifica si el valor de retorno de una función es compatible con el tipo declarado.
     *
     * @param type  El tipo de retorno declarado.
     * @param value El valor de retorno.
     * @return true si los tipos son compatibles, false en caso contrario.
     */
    public boolean checkReturnCompatibility(String type, Object value) {

        switch (type) {
            case "int":
                if (!(value instanceof Integer)) {
                    return value instanceof Number && ((Number) value).doubleValue() == Math.rint(((Number) value).doubleValue()); // Permite doubles/floats con valor entero.
                }
                return true;

            case "double":
                return value instanceof Double || value instanceof Integer;


            case "string":
                return value instanceof String;


            case "boolean":
                return value instanceof Boolean;

        }

        return true;
    }


}